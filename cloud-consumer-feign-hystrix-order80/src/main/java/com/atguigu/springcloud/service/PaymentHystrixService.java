package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/hystrix/payment/ok/{id}")
    CommonResult<?> paymentInfoOk(@PathVariable("id") Long id);

    @GetMapping(value = "/hystrix/payment/timeout/{id}")
    CommonResult<?> paymentInfoTimeOut(@PathVariable("id") Long id);
}
