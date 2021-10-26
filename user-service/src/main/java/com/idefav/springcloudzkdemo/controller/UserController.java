package com.idefav.springcloudzkdemo.controller;

import com.alibaba.csp.sentinel.slots.adaptive.AdaptiveRuleManager;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * the UserController description.
 *
 * @author wuzishu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostConstruct
    public void init() {
        AdaptiveRuleManager.setEnabled(true);
    }

    AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    private ServiceInstance serviceInstance;


    @GetMapping("/info/{userName}")
    public String userName(@PathVariable("userName") String userName, HttpServletRequest request) throws InterruptedException {
//        Thread.sleep(1000);
        return String.format("[%s:%s]%s", serviceInstance.getHost(), serviceInstance.getPort(), userName);
    }

    @GetMapping("/cpu/{n}")
    public Long cpu(@PathVariable("n") Long number) {
        return fibonacci(number);
    }

    @GetMapping("/io/{n}")
    public String io(@PathVariable("n") Long time) throws InterruptedException {
        counter.incrementAndGet();
        if (counter.get() <= 10) {
            Thread.sleep(100);
        } else {
            Thread.sleep(time);
        }

        return "ok";
    }

    public long fibonacci(long number) {
        if ((number == 0) || (number == 1))
            return number;
        else
            return fibonacci(number - 1) + fibonacci(number - 2);
    }
}
