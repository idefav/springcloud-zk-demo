package com.idefav.springcloudzkdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * the OrderController description.
 *
 * @author wuzishu
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private ServiceInstance serviceInstance;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/info/{orderId}")
    public String orderInfo(@PathVariable("orderId") Long orderId){
        String result = restTemplate.getForObject("http://user-service.svc.zk:18081/user/info/idefav", String.class);
        return String.format("orderId: %s, userInfo: %s", orderId,result);
    }
}
