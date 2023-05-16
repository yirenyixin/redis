package com.xm.Redisson;



import com.xm.mapper.IndentMapper;
import com.xm.mapper.ProductDetailMapper;
import com.xm.mapper.ProductMapper;
import com.xm.mapper.WalletMapper;
import jmu.model.Indent;
import jmu.model.Product;
import jmu.model.Wallet;
import jmu.utils.SQLUtils;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Service
//@EnableAsync
public class RedisOutFromQueue {

//    @Autowired
//    private ProductMapper productMapper;
//    @Autowired
//    private ProductDetailMapper productDetailMapper;
//    @Autowired
//    private IndentMapper indentMapper;
//    @Autowired
//    private WalletMapper walletMapper;

    public static void main(String args[]) throws SQLException, InterruptedException {
        SQLUtils sqlUtils=new SQLUtils();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBlockingQueue<Employer> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");
        RDelayedQueue<Employer> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);

        Employer employer=new Employer();
        while (true) {
            Employer callCdr = null;
            try {
                callCdr = blockingFairQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            employer.setPacked(callCdr.getPacked());
            employer.setUserid(callCdr.getUserid());
            employer.setProduct_details_productID(callCdr.getProduct_details_productID());
            employer.setPutTime(callCdr.getPutTime());
//            Thread.sleep(1000);
            System.out.println("接收消息时间：" + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "==订单生成时间" + employer.getPutTime()+"接受到的消息"+ employer.toString());
            if(sqlUtils.redisson(employer)){
                System.out.println("延迟队列取消成功，商品id："+employer.getProduct_details_productID());
            }else{
                System.out.println("改商品已经确认订单："+employer.getProduct_details_productID());
            }
//            Indent indent=indentMapper.selectOne(Integer.parseInt(employer.getProduct_details_productID()));
//            if(indent!=null){
//                Product product=new Product();
//                Wallet shop=new Wallet();
//                Wallet buyer=new Wallet();
//                product=productMapper.getOne(employer.getPacked());
//                shop.setUserid(product.getShopid());
//                shop.setBalance(product.getPrice());
//                buyer.setUserid(employer.getUserid());
//                buyer.setBalance(product.getPrice());
//                productMapper.updateStock_add(employer.getPacked());
//                productDetailMapper.updateOne(Integer.parseInt(employer.getProduct_details_productID()));
//                walletMapper.add(buyer);
//                walletMapper.reduce(shop);
//            }
        }
    }
}
