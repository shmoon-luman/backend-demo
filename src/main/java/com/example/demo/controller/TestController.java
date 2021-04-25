package com.example.demo.controller;

import com.example.demo.domain.DeviceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
public class TestController {

    private int index = 0;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("")
    public String home() {
        return "index";
    }

    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity<String> signal(DeviceData deviceData) {
        simpMessagingTemplate.convertAndSend("/topic/public",deviceData);
        System.out.println(deviceData);
        return ResponseEntity.ok(String.format("%d 번째 테스트", ++index));
    }

//    @MessageMapping("/topic/tt")
//    @SendTo("/topic/public")
//    public DeviceData sendMessage(DeviceData deviceData) {
//        log.info("AAAA");
//        return deviceData;
//    }

    @MessageMapping("/app/t1")
    public String recieve(String message) {
        System.out.println(message);
        return message;
    }

}
