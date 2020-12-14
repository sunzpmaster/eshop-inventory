package com.eshop.inventory.eshopinventory;

import com.eshop.inventory.eshopinventory.futuretask.T1Task;
import com.eshop.inventory.eshopinventory.futuretask.T2Task;
import com.eshop.inventory.eshopinventory.lock.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    private static volatile AtomicInteger count = new AtomicInteger(0);


    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count.getAndAdd(1) ;
        }
    }


    @Test
    public  void calc() throws InterruptedException {
        final ThreadTest test = new ThreadTest();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{ test.add10K(); });
        Thread th2 = new Thread(()->{ test.add10K(); });
        // 启动两个线程
         th1.start();
         th2.start();
        // 等待两个线程执行结束
         th1.join();
         th2.join();
         System.out.println("---------------"+count) ;
    }

    @Test
    public void testList(){
        List<Account> locks = new ArrayList<>();
        locks.add(new Account(1000));
        locks.add(new Account(2000));
        locks.add(new Account(1000));
        log.info("----------------------------");
        locks.forEach(s->s.toString());
        locks.remove(new Account(1000));
        locks.remove(new Account(1000));
        log.info("----------------------------");
        locks.forEach(s->s.toString());
        locks.remove(new Account(2000));
        log.info("----------------------------");
        locks.forEach(s->s.toString());

    }
    @Test
    public void testThreadPool() throws InterruptedException {
        // ** 下面是使用示例 **/
        // 创建有界阻塞队列
        BlockingQueue<Runnable> workQueue =
                new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool(
                10, workQueue);
        // 提交任务
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            pool.execute(()->{
                System.out.println(finalI +"--------------------hello"+Thread.currentThread().getName());
            });
        }
    }

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        // 创建任务T2的FutureTask
        FutureTask<String> ft2
                = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask<String> ft1
                = new FutureTask<>(new T1Task(ft2));
        // 线程T1执行任务ft1
        Thread T1 = new Thread(ft1);
        T1.start();
        // 线程T2执行任务ft2
        Thread T2 = new Thread(ft2);
        T2.start();
        // 等待线程T1执行结果
        System.out.println(ft1.get());
    }
}
