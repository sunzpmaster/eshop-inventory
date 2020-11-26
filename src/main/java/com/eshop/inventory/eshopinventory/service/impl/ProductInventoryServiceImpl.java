package com.eshop.inventory.eshopinventory.service.impl;

import com.eshop.inventory.eshopinventory.entity.ProductInventory;
import com.eshop.inventory.eshopinventory.mapper.ProductInventoryMapper;
import com.eshop.inventory.eshopinventory.service.ProductInventoryService;
import com.eshop.inventory.eshopinventory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    /**
     * 根据商品id查询商品库存
     * @param productId 商品id
     * @return 商品库存
     */
    @Override
    public ProductInventory findProductInventory(Long productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisService.del(key);
    }

    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisService.set(key, String.valueOf(productInventory.getInventoryCnt()));
    }

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    @Override
    public ProductInventory getProductInventoryCache(Long productId) {
        Long inventoryCnt = 0L;

        String key = "product:inventory:" + productId;
        Object object = redisService.get(key);

        if(object != null && !"".equals(object)) {
            try {
                String s = String.valueOf(object);
                inventoryCnt = Long.valueOf(s);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
