package com.atguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        return "nacos serverPort:" + serverPort;
    }
}
