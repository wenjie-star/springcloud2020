package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public CommonResult<?> paymentInfoOk(Long id) {
        return new CommonResult<>(200, "--PaymentFallbackService--paymentInfoOk");
    }

    @Override
    public CommonResult<?> paymentInfoTimeOut(Long id) {
        return new CommonResult<>(200, "--PaymentFallbackService--paymentInfoTimeOut");
    }
}
