package com.example.herbhub.service;

import com.example.herbhub.dto.MessageDto;
import com.example.herbhub.model.Message;
import com.example.herbhub.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final AuthenticationService authenticationService;

    public List<Message> getAllByOrderId(String orderId) {
        return messageRepository.findAllByGroupId(orderId);
    }

    public Message create(MessageDto dto) {
        Message result = new Message();
        result.setMessage(dto.getMessage());
        result.setGroupId(dto.getGroupId());
        result.setSenderId(authenticationService.getLoggedUserId());
        result.setSendAt(LocalDateTime.now());
        return messageRepository.save(result);
    }

    public List<Message> getAllOwnMessagesByOrderId(String orderId) {
        return messageRepository.findAllByUserIdAndGroupId(authenticationService.getLoggedUserId(), orderId);
    }
}
