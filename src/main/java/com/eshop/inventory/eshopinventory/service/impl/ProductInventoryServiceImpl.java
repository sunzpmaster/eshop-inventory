package com.eshop.inventory.eshopinventory.service.impl;

import com.eshop.inventory.eshopinventory.entity.ProductInventory;
import com.eshop.inventory.eshopinventory.mapper.ProductInventoryMapper;
import com.eshop.inventory.eshopinventory.service.ProductInventoryService;
import com.eshop.inventory.eshopinventory.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品库存实现
 */
@Slf4j
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
        log.info("========: 已修改数据库中的库存，商品id:{}, 商品库存数量:{}", productInventory.getProductId(), productInventory.getInventoryCnt());
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
        log.info("===========: 已删除redis中的缓存，key={}", key);
    }

    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisService.set(key, String.valueOf(productInventory.getInventoryCnt()));
        log.info("===========已更新商品库存的缓存，商品id={}, 商品库存数量={}, key={}",productInventory.getProductId(), productInventory.getInventoryCnt(),key);
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
