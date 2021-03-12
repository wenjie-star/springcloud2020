package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping(value = {"", "/"})
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult<>(200, "OK,serverPort=" + serverPort, result);
        }
        return new CommonResult<>(444, "error", null);
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        if (result != null) {
            return new CommonResult<>(200, "OK,serverPort=" + serverPort, result);
        }
        return new CommonResult<>(444, "error", null);
    }

    @GetMapping(value = "/discovery")
    public CommonResult<?> discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(s -> {
            log.info("****services==" + s);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(ins -> {
            log.info("***host==" + ins.getHost());
            log.info("***port==" + ins.getPort());
            log.info("***uri==" + ins.getUri());
        });

        return new CommonResult<>(200, "ok", instances);
    }

    @GetMapping(value = "/feign/timeout")
    public CommonResult<?> paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new CommonResult<>(200, "ok", serverPort);
    }
}
