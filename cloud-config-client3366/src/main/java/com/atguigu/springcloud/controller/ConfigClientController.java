package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${com.atguigu.springcloud.alibaba.config.info}")
    private String configInfo;

    @GetMapping(value = "/configInfo")
    public CommonResult<?> getConfigInfo() {
        return new CommonResult<>(200, null, configInfo);
    }
}
