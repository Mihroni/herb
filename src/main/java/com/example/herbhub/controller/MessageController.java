package com.example.herbhub.controller;

import com.example.herbhub.dto.MessageDto;
import com.example.herbhub.model.Message;
import com.example.herbhub.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public Message create(@RequestBody MessageDto dto) {
        return messageService.create(dto);
    }

    @GetMapping()
    public List<Message> getAllByOrderId(@RequestParam String orderId) {
        return messageService.getAllByOrderId(orderId);
    }

    @GetMapping("/own")
    public List<Message> getAllOwnMessagesByOrderId(@RequestParam String orderId) {
        return messageService.getAllOwnMessagesByOrderId(orderId);
    }
}
