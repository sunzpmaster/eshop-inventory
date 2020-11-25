package com.eshop.inventory.eshopinventory.dao.impl;

import com.eshop.inventory.eshopinventory.dao.RedisDAO;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

    @Resource
    private JedisCluster jedisClusterNodes;

    @Override
    public void set(String key, String value) {
        jedisClusterNodes.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisClusterNodes.get(key);
    }

}
