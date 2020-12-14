package com.eshop.inventory.eshopinventory.redismq;

import com.alibaba.fastjson.JSONObject;
import com.eshop.inventory.eshopinventory.conf.RedisTaskQueue;
import com.eshop.inventory.eshopinventory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class DealMessageController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTaskQueue redisTaskQueue;
    // @Value("${mq.list.first}") private String MQ_LIST_FIRST;

    @Scheduled(cron="*/5 * * * * *")
    public void sendMsg() {
        // 消费 这里是demo，所以只取出来List:1中的待消费队列，因为已知只在List:1存了消息
        List<Object> msgs = redisService.consume(redisTaskQueue.getRoutes().get(0).getList());
        int len;
        if (!CollectionUtils.isEmpty(msgs)) {
            // 将每一条消息转为JSONObject
            JSONObject jObj;
            for (Object msg : msgs) {
                jObj = JSONObject.parseObject((String) msg);
                // 取出消息
                System.out.println(jObj.toJSONString());
            }
        }
    }
}
