package com.eshop.inventory.eshopinventory.lock;

public class Account { //账户类

    private Integer banalce;

    public Account(Integer banalce) {
        this.banalce = banalce;
    }

    public Integer getBanalce() {
        return banalce;
    }
    public void setBanalce(Integer banalce) {
        this.banalce = banalce;
    }

    /**
     * 转账方法
     * @param money
     * @param target
     */
    public void transactionToTarget(Integer money, Account target){//转账方法
        Allocator.getInstance().apply(this,target);
        this.banalce -= money;
        target.setBanalce(target.getBanalce()+money);
        Allocator.getInstance().release(this,target);
    }

    @Override
    public String toString() {
        return "Account{" +
                "banalce=" + banalce +
                '}';
    }
}
