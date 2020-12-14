package com.eshop.inventory.eshopinventory.redismq;


import com.eshop.inventory.eshopinventory.entity.BatpayTask;
import com.eshop.inventory.eshopinventory.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Slf4j
@Api("延迟消息发送")
@Controller
public class PostMessageController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${mq.queue.first}")
    private String MQ_QUEUE_FIRST;

    @ApiOperation("消息入延迟队列")
    @PutMapping("/putDelayMessge")
    @ResponseBody
    public void putDelayMessge(){
        BatpayTask batpayTask = new BatpayTask(1L, 1L, 20L,20*60);

        //将消息存入单独String中，可做数据备查
        redisService.set(UUID.randomUUID().toString(),batpayTask.getOrderId(),20*60);

        //将数据放入zset进行排序
        redisTemplate.opsForZSet().add(MQ_QUEUE_FIRST,batpayTask.getOrderId(),System.currentTimeMillis()+10*60*1000*1000*1000);
    }


}
