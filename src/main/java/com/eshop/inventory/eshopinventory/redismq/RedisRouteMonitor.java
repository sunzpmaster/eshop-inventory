package com.eshop.inventory.eshopinventory.redismq;

import com.eshop.inventory.eshopinventory.conf.RedisRouteConfig;
import com.eshop.inventory.eshopinventory.conf.RedisTaskQueue;
import com.eshop.inventory.eshopinventory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class RedisRouteMonitor {

    @Autowired
    private RedisTaskQueue redisTaskQueue;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 消息队列监听器<br>
     * 监听所有路由器，将消息队列中的消息路由到待消费列表
     */
    @Scheduled(cron="*/5 * * * * *")
    public void monitor() {
        // 获取消息路由
        int route_size;
        if (null == redisTaskQueue.getRoutes() || 1 > (route_size = redisTaskQueue.getRoutes().size())) return;
        String queue, list;
        Set<String> set;
        for (int i = 0; i < route_size; i++) {
            queue = redisTaskQueue.getRoutes().get(i).getQueue();
            list = redisTaskQueue.getRoutes().get(i).getList();
            //获取队列中的消息，根据score降序，score大的优先
            set = redisTemplate.opsForZSet().range(queue, 0, redisTaskQueue.getMonitorCount());
            if (null != set) {
                long current = System.currentTimeMillis();
                long score;
                for (String id : set) {
                    //取出队列score
                    score = redisTemplate.opsForZSet().score(queue, id).longValue();
                    if (current >= score) {
                        // 添加到list 一个先进先出队列
                        if (redisService.insertList(list, id)) {
                            // 删除queue中的元素
                            redisTemplate.opsForZSet().remove(queue, id);
                        }
                    }
                }
            }
        }
    }
}
