package com.xm.rabbitmq;



import com.rabbitmq.client.*;
import jmu.utils.RabbitMqUtils;


public class rob_Fanout {

    //交换机的名称
    public static final String EXCHANGE_NAME = "RedPack";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //声明一个临时队列
        /*
         *
         * 生成一个临时的队列、队列的名称是随机的
         * 当消费者断开与队列的连接的时候, 队列就自动删除了
         * */
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机
        /*
         * 参数一: 队列名称
         * 参数二: 交换机名称
         * 参数三: 路由key
         * */
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        //接收消息

        DeliverCallback deliverCallback = (consumerTag,message)-> {
            System.out.println("成功接收到消息: " + new String(message.getBody()));
            /*
             * 消息手动应答
             * */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("接收失败的消息的队列标记值: " + consumerTag);
        };

        //消费者取消消息时触发
        /*
         *
         * 参数一: 队列名称
         * 参数二: 是否自动应答
         * 参数三: 成功确认消息回调函数
         * 参数四: 失败消息回调函数
         * */
        boolean autoAck = false;
        channel.basicConsume(queueName,autoAck,deliverCallback,cancelCallback);

    }


}

