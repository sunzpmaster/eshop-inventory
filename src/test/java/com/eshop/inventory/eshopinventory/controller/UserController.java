package com.eshop.inventory.eshopinventory.controller;


import com.eshop.inventory.eshopinventory.entity.User;
import com.eshop.inventory.eshopinventory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Api(tags="测试Controller")
@RequestMapping("/test")
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation(value="获取信息1", notes="获取信息2")
    @GetMapping("/getUserInfo")
    @ResponseBody
    public List<User> getUserInfo() {
        List<User> userInfo = userService.getUserInfo();
        return userInfo;
    }


    @GetMapping("/getCachedUserInfo")
    @ResponseBody
    public User getCachedUserInfo()  {
        User user = userService.getCachedUserInfo();
        return user;
    }

}
