package com.xm.controller;

import api.BaseResponse;
import api.StatusCode;


import com.xm.Redisson.Employer;
import com.xm.mapper.IndentMapper;
import com.xm.mapper.ProductDetailMapper;
import com.xm.mapper.ProductMapper;
import com.xm.mapper.WalletMapper;
import com.xm.rabbitmq.hanOut_Fanout;
import com.xm.service.AddressService;
import com.xm.service.IProductPacketService;
import com.xm.service.ProductService;
import com.xm.service.WalletService;
import jmu.model.Address;
import jmu.model.Indent;
import jmu.model.Product;
import jmu.model.Wallet;
import jmu.utils.ProductUtil;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pojo.ProductDto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
public class ProductPacketController implements IProductPacketController{



    private static final String prefix="red/packet";

    @Autowired
    private IProductPacketService productPacketService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WalletService walletService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private  IndentMapper indentMapper;
    @Autowired
    private  WalletMapper walletMapper;
    @Autowired
    private  ProductMapper productMapper;
    @Autowired
    private  ProductDetailMapper productDetailMapper;

    static RedissonClient redissonClient = null;

    /**
     * redis版内置的布隆过滤器
     */
    static RBloomFilter rBloomFilter = null;
    //redisson
    static RBlockingQueue<Employer> blockingFairQueue=null;
    static RDelayedQueue<Employer> delayedQueue=null;

    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);

        // 集群版
        //config.useClusterServers().addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6379");

        //构造redisson
        redissonClient = Redisson.create(config);
        //通过redisson构造rBloomFilter
        rBloomFilter = redissonClient.getBloomFilter("seckillGoodsBloomFilter",new StringCodec());
        rBloomFilter.tryInit(10000,0.03);

        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
        blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");
        delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);


    }


    @Override
    public void get(Employer employer){
        Employer callCdr = new Employer();
        callCdr.setPutTime();
        callCdr.setPacked(employer.getPacked());
        callCdr.setUserid(employer.getUserid());
        callCdr.setProduct_details_productID(employer.getProduct_details_productID());
        delayedQueue.offer(callCdr, 30, TimeUnit.SECONDS);//设置时间
//        delayedQueue.remove();
//        hanOut_Fanout hanOut_fanout= new hanOut_Fanout();
//        try {
//            hanOut_fanout.getRedisson(callCdr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("callCdr =================================> " + callCdr);
        //在该对象不再需要的情况下，应该主动销毁。
        // 仅在相关的Redisson对象也需要关闭的时候可以不用主动销毁。
        delayedQueue.destroy();
    }


    @Override
    public void afterPropertiesSet(String pack) throws Exception {
        rBloomFilter.add(pack);
    }


    /**
     * 发
     */
    @RequestMapping(value = prefix+"/hand/out_product",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse handOutproduct(@RequestBody ProductDto dto, BindingResult result) throws Exception {
//        if (result.hasErrors()){
//            return new BaseResponse(StatusCode.InvalidParams);
//        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String productid=productPacketService.handOut_Product(dto);//发货
            response.setData(productid);
        }catch (Exception e){
//            log.error("发布商品发生异常：dto={} ",dto,e.fillInStackTrace());
            hanOut_Fanout hanOut_fanout= new hanOut_Fanout();
            hanOut_fanout.getError("发布商品发生异常：dto={} ",dto,e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

//抢单
    @RequestMapping(value = prefix+"/rob_product",method = RequestMethod.GET)
    public BaseResponse rob_product(@RequestParam Integer userId, @RequestParam String redId) throws Exception {
        if(rBloomFilter.contains(redId)) {
//            System.out.println("存在");
            hanOut_Fanout hanOut_fanout = new hanOut_Fanout();
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Address address = new Address();
            address = addressService.getAddress(userId);
            redisTemplate.delete(userId);
            redisTemplate.opsForValue().set(userId, address);

            BaseResponse response = new BaseResponse(StatusCode.Success);
            List<Integer> list = new ArrayList<>();
            ProductUtil productUtil = new ProductUtil();
            list = ProductUtil.getList(redId);//获取还没被抢的商品
            Product product = new Product();
            product = productService.getOne(redId);
            redisTemplate.opsForValue().set("cost", product.getPrice());
            redisTemplate.opsForValue().set("shopid", product.getShopid());

            redisTemplate.delete(redId);//清除redis缓存
            redisTemplate.opsForList().rightPushAll(redId, list);//存入没被抢的商品id数组
            redisTemplate.opsForList().trim(redId, 0, Long.parseLong(product.getStock()) - 1);

//        long listLength=redisTemplate.opsForList().size(redId);
//        System.out.println(listLength);


            String ProductTotalKey = redId + ":total";
            redisTemplate.delete(ProductTotalKey);
            ;//清除redis缓存
            int a = Integer.parseInt(product.getStock());
            redisTemplate.opsForValue().set(ProductTotalKey, Integer.parseInt(product.getStock()));//存入当前库存

            while (true) {//重复抢单,超过一定数量会爆
//            System.out.println(total);

                Object total = valueOperations.get(ProductTotalKey);
//            System.out.println(Integer.valueOf(total.toString()) );
                if (list != null && total != null && Integer.valueOf(total.toString()) > 0) {
                    try {
                        BigDecimal result = productPacketService.rob_product(userId, redId);//开始抢单
                        if (result != null) {
                            response.setData(result);
                        } else {
                            hanOut_fanout.leave(userId, 0);
                            break;
                        }
                    } catch (Exception e) {

                        if (!walletService.update(userId, redId, "", 0)) {
                            hanOut_fanout.refund("退款失败");
                        }
                        hanOut_fanout.getErrorRob("抢单发生异常：userId={} redId={}", userId, redId, e.fillInStackTrace());
                        response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
                    }
                } else {
                    hanOut_fanout.leave(userId, 1);
                    return response;
                }
//            Thread.sleep(500);//设置休眠时间防止短时间访问数据库过多出现错误
            }
            hanOut_fanout.leave(userId, 1);
            return response;
        }else {
            hanOut_Fanout hanOut_fanout = new hanOut_Fanout();
            hanOut_fanout.getBloomFilter(redId);
//            System.out.println("不存在");
            return null;
        }
    }

}