package com.eshop.inventory.eshopinventory.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.eshop.inventory.eshopinventory.request.RequestQueue;
import com.eshop.inventory.eshopinventory.request.RequestQueueList;
import lombok.extern.slf4j.Slf4j;


/**
 * 请求处理线程池：单例
 * @author Administrator
 *
 */
@Slf4j
public class RequestProcessorThreadPool {
    // 在实际项目中，你设置线程池大小是多少，每个线程监控的那个内存队列的大小是多少
    // 都可以做到一个外部的配置文件中
    // 我们这了就给简化了，直接写死了，好吧
    /**
     * 线程池
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    //初始化一个内存队列list，生成容量为100个Request对象的队列10次，依次往list添加，分别用10个线程监控
    public RequestProcessorThreadPool() {
        RequestQueueList requestQueueList = RequestQueueList.getInstance();

        for(int i = 0; i < 10; i++) {
            log.info("线程初始化开始，提交第{}个线程任务",i);
            ArrayBlockingQueue<RequestQueue> queue = new ArrayBlockingQueue<RequestQueue>(100);
            requestQueueList.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }


    /**
     * 单例有很多种方式去实现：我采取绝对线程安全的一种方式
     *
     * 静态内部类的方式，去初始化单例
     *
     * @author Administrator
     *
     */
    private static class Singleton {

        private static RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }

    }

    /**
     * jvm的机制去保证多线程并发安全
     *
     * 内部类的初始化，一定只会发生一次，不管多少个线程并发去初始化
     *
     * @return
     */
    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化的便捷方法
     */
    public static void init() {
        getInstance();
    }
}
