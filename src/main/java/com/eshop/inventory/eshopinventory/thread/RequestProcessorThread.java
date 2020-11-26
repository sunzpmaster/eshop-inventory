package com.eshop.inventory.eshopinventory.thread;

import com.eshop.inventory.eshopinventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 * @author Administrator
 *
 */
public class RequestProcessorThread  implements Callable<Boolean> {
    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<RequestQueue> queue;

    public RequestProcessorThread(ArrayBlockingQueue<RequestQueue> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while(true) {
                // ArrayBlockingQueue
                // Blocking就是说明，如果队列满了，或者是空的，那么都会在执行操作的时候，阻塞住
                RequestQueue request = queue.take();
                // 执行这个request操作
                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
