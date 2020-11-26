package com.eshop.inventory.eshopinventory.request;

/**
 * 请求接口
 * @author Administrator
 *
 */
public interface RequestQueue {
    void process();
    Long getProductId();
    boolean isForceRefresh();
}
