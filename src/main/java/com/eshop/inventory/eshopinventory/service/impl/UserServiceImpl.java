package com.eshop.inventory.eshopinventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eshop.inventory.eshopinventory.entity.User;
import com.eshop.inventory.eshopinventory.mapper.TestMapper;
import com.eshop.inventory.eshopinventory.service.RedisService;
import com.eshop.inventory.eshopinventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public List<User> getUserInfo() {
        return testMapper.getAll();
    }

    @Override
    public User getCachedUserInfo() {
        redisService.set("cached_user", "{\"name\": \"zhangsan\", \"age\": 25}") ;
        String json = redisService.get("cached_user").toString();
        JSONObject jsonObject = JSONObject.parseObject(json);

        User user = new User();
        user.setName(jsonObject.getString("name"));
        user.setAge(jsonObject.getInteger("age"));

        return user;
    }
}
