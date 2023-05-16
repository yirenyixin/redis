package com.xm.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xm.Redisson.Employer;
import pojo.ProductDto;
import jmu.utils.RabbitMqUtils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class hanOut_Fanout {
    public static final String EXCHANGE_NAME = "RedPack";
    public void getred(String redId, List<Integer> list) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

//        System.out.println("红包Id: "+redId+"\n"+"红包金额: "+list.toString());
        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("上发票Id: "+redId+"\n"+"商品金额: "+list.toString()).getBytes(StandardCharsets.UTF_8));
    }

    public void getproduct(String redId) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

//        System.out.println("商品集合Id: "+redId+"\n"+"商品id: "+list.toString());
        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("商品类Id: "+redId+"\n").getBytes(StandardCharsets.UTF_8));
    }


    public void getrob(Integer userId, String redId, BigDecimal amount) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("一个用户抢到商品.用户Id: "+userId+" 商品packed: "+redId+" 商品Id："+amount).getBytes(StandardCharsets.UTF_8));
    }

    public void getnorob(Integer userId) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("一个用户没有抢到商品.用户Id: "+userId).getBytes(StandardCharsets.UTF_8));
    }

    public void getError(String s, ProductDto dto, Throwable fillInStackTrace) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, (s+dto+fillInStackTrace).getBytes(StandardCharsets.UTF_8));
    }

    public void getErrorRob(String s, Integer userId, String redId, Throwable fillInStackTrace) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("抢单发生异常：userId={"+userId+"} packed={"+redId+"} "+fillInStackTrace).getBytes(StandardCharsets.UTF_8));
    }

    public void getNoBalance(Integer userId, String packed) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("一个用户余额不足没有抢到商品.用户Id: "+userId).getBytes(StandardCharsets.UTF_8));
    }

    public void refund(String toString) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, (toString).getBytes(StandardCharsets.UTF_8));
    }
    public void leave(Integer userId, int i) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        if(i==0) {
            channel.basicPublish(EXCHANGE_NAME, "", null, ("一个用户余额不足已退出抢单.用户Id: " + userId).getBytes(StandardCharsets.UTF_8));
        }else{
            channel.basicPublish(EXCHANGE_NAME, "", null, ("一个用户已退出抢单.用户Id: " + userId).getBytes(StandardCharsets.UTF_8));
        }
    }

    public void getRedisson(Employer callCdr) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("延迟队列发送：callCdr =================================> " + callCdr).getBytes(StandardCharsets.UTF_8));
    }

    public void getBloomFilter(String redId) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //发送消息
        /*
         * 参数一: 交换机名称
         * 参数二: 交换机路由key值
         * 参数三: 额外参数
         * 参数四: 消息
         * */
        channel.basicPublish(EXCHANGE_NAME,"",null, ("布隆过滤器检查商品不存在，pack：" +redId).getBytes(StandardCharsets.UTF_8));
    }
}
