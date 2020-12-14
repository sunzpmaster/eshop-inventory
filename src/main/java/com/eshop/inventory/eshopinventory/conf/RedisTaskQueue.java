package com.eshop.inventory.eshopinventory.conf;

import java.util.List;

public class RedisTaskQueue {
    /**
     * 默认监听数量，对应监听zset队列前多少个元素
     */
    private static final int DEFAUT_MONITOR = 10;

    /**
     * 每次监听queue中元素的数量，可配置
     */
    private int monitorCount = DEFAUT_MONITOR;

    /**
     * 消息路由
     */
    private List<RedisRoute> routes;
    public int getMonitorCount() {
        return monitorCount;
    }

    public void setMonitorCount(int monitorCount) {
        this.monitorCount = monitorCount;
    }

    public List<RedisRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RedisRoute> routes) {
        this.routes = routes;
    }

}
