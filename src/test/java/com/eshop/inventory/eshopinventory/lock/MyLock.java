package com.eshop.inventory.eshopinventory.lock;

import java.util.concurrent.CountDownLatch;

public class MyLock {

    // 测试转账的main方法
    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account target = new Account(10000);
        Allocator.getInstance().apply(src,target);


        CountDownLatch countDownLatch = new CountDownLatch(9999);
        for (int i = 0; i < 9999; i++) {

            new Thread(()->{
                src.transactionToTarget(1,target);
                countDownLatch.countDown();
            }).start();
            Thread.sleep(3000);
            Allocator.getInstance().release(src,target);
        }
        countDownLatch.await();
        System.out.println("src="+src.getBanalce() );
        System.out.println("target="+target.getBanalce() );
    }


}
