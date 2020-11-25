package com.eshop.inventory.eshopinventory.service;

import com.eshop.inventory.eshopinventory.entity.User;


import java.util.List;

public interface UserService {

    public List<User> getUserInfo();
    public User getCachedUserInfo();
}
