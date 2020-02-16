package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.domain_layer.PrivateMessageDTO;
import ru.starry_sky.services.interfaces.PrivateMessagesServices;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateMessagesController {
    @Autowired
    private PrivateMessagesServices privateMessagesServices;

    @PostMapping
    public boolean sendMessage(@RequestBody PrivateMessageDTO dto){
        return privateMessagesServices.sendMessage(dto);
    }

    @GetMapping
    public List<PrivateMessage> getMessages(){
        return privateMessagesServices.getMessages();
    }
}
