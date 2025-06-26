package org.example.controller;

import org.example.service.DeadLockMock;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {
    private DeadLockMock deadLockMock = new DeadLockMock();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/deadlock")
    public String deadlock() {
        deadLockMock.execute();
        return "出发死锁成功";
    }

    @PostMapping("/message")
    public MessageResponse postMessage(@RequestBody MessageRequest request) {
        return new MessageResponse("收到消息：" + request.getMessage());
    }
}

class MessageRequest {
    private String message;

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

class MessageResponse {
    private String response;

    public MessageResponse(String response) {
        this.response = response;
    }

    // Getter and Setter
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}