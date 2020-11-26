package com.eshop.inventory.eshopinventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求内存队列list
 * @author Administrator
 *
 */
public class RequestQueueList {

    /**
     * 内存队列
     */
    private List<ArrayBlockingQueue<RequestQueue>> queues = new ArrayList<ArrayBlockingQueue<RequestQueue>>();

    /**
     * 单例有很多种方式去实现：我采取绝对线程安全的一种方式
     *
     * 静态内部类的方式，去初始化单例
     *
     * @author Administrator
     *
     */
    private static class Singleton {

        private static RequestQueueList instance;

        static {
            instance = new RequestQueueList();
        }

        public static RequestQueueList getInstance() {
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
    public static RequestQueueList getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 添加一个内存队列
     * @param queue
     */
    public void addQueue(ArrayBlockingQueue<RequestQueue> queue) {
        this.queues.add(queue);
    }

    /**
     * 获取内存队列的数量
     * @return
     */
    public int queueSize() {
        return queues.size();
    }

    /**
     * 获取内存队列
     * @param index
     * @return
     */
    public ArrayBlockingQueue<RequestQueue> getQueue(int index) {
        return queues.get(index);
    }

}
