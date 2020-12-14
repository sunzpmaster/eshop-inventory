package com.eshop.inventory.eshopinventory.entity;

public class BatpayTask {

    private Long id;  //任务id
    private Long orderId; // 消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长
    private int ttl; //消息存活时间

    public BatpayTask(Long id, Long orderId, long excuteTime, int ttl) {
        this.id = id;
        this.orderId = orderId;
        this.excuteTime = excuteTime;
        this.ttl = ttl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
