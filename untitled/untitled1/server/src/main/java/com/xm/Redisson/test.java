package com.xm.Redisson;

import api.BaseResponse;
import api.StatusCode;
import com.xm.rabbitmq.hanOut_Fanout;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

public class test {
    public static void main(String[] args){
            Config config = new Config();
            config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
            // 集群版
            //config.useClusterServers().addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6379");

            //构造redisson
            RedissonClient redissonClient = Redisson.create(config);
            //通过redisson构造rBloomFilter
            RBloomFilter rBloomFilter = redissonClient.getBloomFilter("seckillGoodsBloomFilter", new StringCodec());
            rBloomFilter.tryInit(10000,0.03);
            rBloomFilter.add("");
    }
}
