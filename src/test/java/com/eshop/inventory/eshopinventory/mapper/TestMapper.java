package com.eshop.inventory.eshopinventory.mapper;

import com.eshop.inventory.eshopinventory.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {

    @Select("SELECT * FROM user")
    List<User> getAll();
}