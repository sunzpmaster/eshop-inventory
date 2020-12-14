package com.eshop.inventory.eshopinventory.lock;

import java.util.HashSet;
import java.util.Set;

public class Allocator { //单例锁类
    private Allocator(){}
    private Set<Account> locks = new HashSet<>();
    public synchronized void apply(Account src,Account tag){
        while (locks.contains(src)||locks.contains(tag)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        locks.add(src);
        locks.add(tag);
    }
    public synchronized void release(Account src,Account tag){
        locks.remove(src);
        locks.remove(tag);
        this.notifyAll();
    }
    public static Allocator getInstance(){
        return AllocatorSingle.install;
    }
    static class AllocatorSingle{
        public static Allocator install = new Allocator();
    }
}
