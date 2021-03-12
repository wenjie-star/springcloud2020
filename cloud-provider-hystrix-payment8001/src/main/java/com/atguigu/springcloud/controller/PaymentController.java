package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "hystrix")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/ok/{id}")
    public CommonResult<?> paymentInfoOk(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfoOk(id);
        return new CommonResult<>(200, "ok", result);
    }

    @GetMapping(value = "/payment/timeout/{id}")
    public CommonResult<?> paymentInfoTimeOut(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfoTimeOut(id);
        return new CommonResult<>(200, "ok", result);
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public CommonResult<?> paymentInfoCircuitBreaker(@PathVariable("id") Long id) {
        String result = paymentService.paymentCircuitBreaker(id);
        return new CommonResult<>(200, "ok", result);
    }
}
