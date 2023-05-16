package com.xm.service;


import com.xm.Redisson.Employer;
import com.xm.controller.IProductPacketController;
import com.xm.rabbitmq.hanOut_Fanout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import pojo.ProductDto;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;


@Service
public class ProductPacketService implements IProductPacketService {

//    private static final Logger log= LoggerFactory.getLogger(ProductPacketService.class);


    private static final String keyPrefix="redis:product:packet:";



    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IProductService productService;
    @Autowired
    private ILogisticsService logisticsService;
    @Autowired
    private IOrder1Service order1Service;
    @Autowired
    IOrderDetailsService orderDetailsService;
    @Autowired
    WalletService walletService;
    @Autowired
    private IndentService indentService;
    @Autowired
    private IProductPacketController productPacketController;

    public static final String EXCHANGE_NAME = "RedPack";



//    private final IProductService productService=new ProductService();
    @Override
    public String handOut_Product(ProductDto dto) throws Exception {
        if (Integer.valueOf(dto.getPrice())>0 && Integer.valueOf(dto.getStock())>0){
//            生成数组
//            List<Integer> list= ProductUtil.divideProductPackage(Integer.valueOf(dto.getStock()));//获取id数组


            String timestamp=String.valueOf(System.nanoTime());
            String pack = new StringBuffer(keyPrefix).append(dto.getShopId()).append(":").append(timestamp).toString();
//            redisTemplate.opsForList().leftPushAll(pack,list);//存入redis，存数组
//
//            String ProductTotalKey = pack+":total";
//            redisTemplate.opsForValue().set(ProductTotalKey,dto.getStock());//存入redis，存商品个数

            //异步记录红包发出的记录-包括个数与随机金额
            productService.recordProduct(dto,pack);//存入数据库


            productPacketController.afterPropertiesSet(pack);

            hanOut_Fanout hanOut_fanout= new hanOut_Fanout();
            hanOut_fanout.getproduct(pack);//发消息


            return pack;
        }else{
            throw new Exception("系统异常-分发红包-参数不合法!");
        }
    }



    /**
     * 加分布式锁的情况
     * @throws Exception
     */
    @Override
    public synchronized BigDecimal rob_product(Integer userId, String packed) throws Exception {
        ValueOperations valueOperations=redisTemplate.opsForValue();
        //用户是否抢过
//        Object obj=valueOperations.get(packed+userId+":rob");
//        if (obj!=null){
//            return new BigDecimal(obj.toString());
//        }
        //"点红包"
//        Boolean res=click_product(packed);
//        if (res){
        //上锁
        final String lockKey=packed+userId+"-lock";
        Boolean lock=valueOperations.setIfAbsent(lockKey,packed);
        redisTemplate.expire(lockKey,24L, TimeUnit.HOURS);
        try {
            if (lock) {
                //"抢红包"-且红包有钱
                Object value = redisTemplate.opsForList().leftPop(packed);//取值，取商品id
                if (value != null) {
                        BigDecimal result = new BigDecimal(value.toString());
                        if (walletService.update(userId, packed, value.toString(),1)) {

                            String redTotalKey = packed + ":total";

                            Integer currTotal = valueOperations.get(redTotalKey) != null ? (Integer) valueOperations.get(redTotalKey) : 0;//空等于0
                            valueOperations.set(redTotalKey, currTotal - 1);

                            indentService.insert(userId,value.toString(),packed);
//                            int orderid = logisticsService.insert();//添加订单
//                            order1Service.insert(userId, packed, orderid);
//                            orderDetailsService.insert(userId, packed, orderid);

                            productService.recordRobProductPacket(userId, packed, new BigDecimal(value.toString()));//减库存
                            valueOperations.set(packed + userId + ":rob", result, 24L, TimeUnit.HOURS);

                            Employer employer=new Employer();
                            employer.setUserid(String.valueOf(userId));
                            employer.setPacked(packed);
                            employer.setProduct_details_productID(value.toString());
                            productPacketController.get(employer);


                            hanOut_Fanout hanOut_fanout = new hanOut_Fanout();//发送成功消息
                            hanOut_fanout.getrob(userId, packed, result);

//                            Thread.sleep(200);//
                            return result;
                        } else {
                            hanOut_Fanout hanOut_fanout = new hanOut_Fanout();//当余额不足，发送错误消息
                            hanOut_fanout.getNoBalance(userId, packed);
                            return null;
                        }
                    }

            }
        }catch (Exception e){
            throw new Exception("系统异常-抢红包-加分布式锁失败!");
        }
        redisTemplate.delete(lockKey);//为了能重复抢单删除key，不需要重复抢单可以删除，解锁
//        }

//        hanOut_Fanout hanOut_fanout = new hanOut_Fanout();
//        hanOut_fanout.getnorob(userId);
        return BigDecimal.valueOf(-1);

    }



    private  Boolean click_product(String ProductId) throws Exception{
            ValueOperations valueOperations = redisTemplate.opsForValue();

            String ProductTotalKey = ProductId + ":total";
            Object total = valueOperations.get(ProductTotalKey);
            if (total != null && Integer.valueOf(total.toString()) > 0) {
                return true;
            }
            return false;
    }
}