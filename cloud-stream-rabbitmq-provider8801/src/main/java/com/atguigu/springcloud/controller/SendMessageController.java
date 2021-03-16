package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/message")
    public String sendMessage() {
        return messageProvider.send();
    }
}
