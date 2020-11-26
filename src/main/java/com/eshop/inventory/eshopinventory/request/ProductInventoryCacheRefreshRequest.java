package com.eshop.inventory.eshopinventory.request;

import com.eshop.inventory.eshopinventory.entity.ProductInventory;
import com.eshop.inventory.eshopinventory.service.ProductInventoryService;

public class ProductInventoryCacheRefreshRequest  implements RequestQueue {

    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品库存Service
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Long productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        // 将最新的商品库存数量，刷新到redis缓存中去
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Long getProductId() {
        return productId;
    }
}
