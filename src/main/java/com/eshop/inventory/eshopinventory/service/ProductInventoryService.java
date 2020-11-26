package com.eshop.inventory.eshopinventory.service;

import com.eshop.inventory.eshopinventory.entity.ProductInventory;

/**
 * 商品库存Service接口
 */
public interface ProductInventoryService {
    /**
     * 更新商品库存
     * @param productInventory 商品库存
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 根据商品id查询商品库存
     * @param productId 商品id
     * @return 商品库存
     */
    ProductInventory findProductInventory(Long productId);

    /**
     * 删除Redis中的商品库存的缓存
     * @param productInventory 商品库存
     */
    void removeProductInventoryCache(ProductInventory productInventory);


    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    void setProductInventoryCache(ProductInventory productInventory);

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    ProductInventory getProductInventoryCache(Long productId);

}
