package com.idefav.springcloudzkdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * the UserController description.
 *
 * @author wuzishu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceInstance serviceInstance;


    @GetMapping("/info/{userName}")
    public String userName(@PathVariable("userName") String userName) {
        return String.format("[%s:%s]%s",serviceInstance.getHost(),serviceInstance.getPort(), userName);
    }
}
