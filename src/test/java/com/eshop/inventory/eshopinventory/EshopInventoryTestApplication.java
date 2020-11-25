package com.eshop.inventory.eshopinventory;

import com.eshop.inventory.eshopinventory.listener.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EshopInventoryTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopInventoryTestApplication.class, args);
    }
    @Bean
    public JedisCluster JedisClusterFactory() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.111.110", 6379));
//        jedisClusterNodes.add(new HostAndPort("192.168.31.19", 7004));
//        jedisClusterNodes.add(new HostAndPort("192.168.31.227", 7006));

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        return jedisCluster;
    }

//    @Bean
//    public Jedis createJedisPool(){
//        JedisPool pool =new JedisPool();
//
//        // 建立连接池配置参数
//        JedisPoolConfig config = new JedisPoolConfig();
//        // 设置最大连接数
////        config.setsetMaxActive(100);
//        // 设置最大阻塞时间，记住是毫秒数millisecond
////        config.setMaxWait(1000);
//        // 设置空间连接
//        config.setMaxIdle(10);
//
//        // 创建连接池
//        pool = new JedisPool(config, "192.168.111.110", 6379);
//        return pool.getResource();
//    }

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
}
