package com.eshop.inventory.eshopinventory;

import com.eshop.inventory.eshopinventory.listener.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
@EnableCaching
@SpringBootApplication
public class EshopInventoryApplication {

    /**
     * 注册监听器
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean =
                new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }
    public static void main(String[] args) {
        SpringApplication.run(EshopInventoryApplication.class, args);
    }

}
