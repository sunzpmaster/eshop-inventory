package com.eshop.inventory.eshopinventory.service;

import com.eshop.inventory.eshopinventory.request.RequestQueue;

/**
 * 请求异步执行的service
 * @author Administrator
 *
 */
public interface RequestAsyncProcessService {
    //将请求路由到对应队列加入
    void process(RequestQueue requestQueue);
}
