package com.eshop.inventory.eshopinventory;


import com.eshop.inventory.eshopinventory.entity.User;
import com.eshop.inventory.eshopinventory.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestDataSource {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void getAll(){
        List<User> users = testMapper.getAll();
        System.out.print("-------------------------------------------"+users.toString());
    }
}
