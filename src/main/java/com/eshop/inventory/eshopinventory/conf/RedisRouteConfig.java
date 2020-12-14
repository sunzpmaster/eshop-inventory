package com.eshop.inventory.eshopinventory.conf;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisRouteConfig {

    @Value("${mq.monitor.count}")
    private int monitorCount;
    @Value("${mq.queue.first}")
    private String queueFirst;
    @Value("${mq.queue.second}")
    private String queueSecond;
    @Value("${mq.consumer.first}")
    private String listFirst;
    @Value("${mq.consumer.second}")
    private String listSecond;

    @Bean(name = "redisMQ")
    @Primary
    public RedisTaskQueue getRedisMq() {
        RedisTaskQueue redisTaskQueue = new RedisTaskQueue();
        // 配置监听队列元素数量
        redisTaskQueue.setMonitorCount(monitorCount);
        // 配置路由表
        redisTaskQueue.setRoutes(getrouteList());
        return redisTaskQueue;
    }

    /**
     * 返回路由表
     * @return
     */
    public List<RedisRoute> getrouteList() {
        List<RedisRoute> routeList = new ArrayList<>();
        RedisRoute routeFirst = new RedisRoute(queueFirst, listFirst);
        RedisRoute routeSecond = new RedisRoute(queueSecond, listSecond);
        routeList.add(routeFirst);
        routeList.add(routeSecond);
        return routeList;
    }
}
