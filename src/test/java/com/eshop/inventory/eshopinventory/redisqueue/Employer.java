package com.eshop.inventory.eshopinventory.redisqueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer implements Serializable {
    private String name;
    private int age;
    private String wife;
    private Double salary;
    private String putTime;

    public void setPutTime() {
        this.putTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}
