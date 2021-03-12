package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/order")
@DefaultProperties(defaultFallback = "globalTimeoutHandle")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/hystrix/payment/ok/{id}")
    CommonResult<?> paymentInfoOk(@PathVariable("id") Long id) {
        return paymentHystrixService.paymentInfoOk(id);
    }

    @GetMapping(value = "/hystrix/payment/timeout/{id}")
/*    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    CommonResult<?> paymentInfoTimeOut(@PathVariable("id") Long id) {
        int age = 5 / 0;
        return paymentHystrixService.paymentInfoTimeOut(id);
    }

    CommonResult<?> paymentInfoTimeOutHandler(@PathVariable("id") Long id) {
        return new CommonResult<>(200, "支付接口繁忙，请稍后再试", null);
    }

    CommonResult<?> globalTimeoutHandle() {
        return new CommonResult<>(200, "全局兜底支付接口繁忙，请稍后再试", null);
    }
}
