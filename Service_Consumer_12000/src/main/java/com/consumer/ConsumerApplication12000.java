package com.consumer;

import com.consumer.loadBalanced.MyLoadBalanceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@LoadBalancerClient(name="provider-server", configuration = MyLoadBalanceConfig.class)
@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication12000 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication12000.class, args);
    }
}
