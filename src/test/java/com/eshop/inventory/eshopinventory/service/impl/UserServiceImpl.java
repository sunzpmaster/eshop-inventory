package com.eshop.inventory.eshopinventory.service.impl;

import com.eshop.inventory.eshopinventory.dao.RedisDAO;
import com.eshop.inventory.eshopinventory.entity.User;
import com.eshop.inventory.eshopinventory.mapper.TestMapper;
import com.eshop.inventory.eshopinventory.service.UserService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RedisDAO redisDAO;

    @Override
    public List<User> getUserInfo() {
        return testMapper.getAll();
    }

    @Override
    public User getCachedUserInfo() {
        redisDAO.set("cached_user", "{\"name\": \"zhangsan\", \"age\": 25}") ;
        String json = redisDAO.get("cached_user");
        JSONObject jsonObject = JSONObject.parseObject(json);

        User user = new User();
        user.setName(jsonObject.getString("name"));
        user.setAge(jsonObject.getInteger("age"));

        return user;
    }
}
