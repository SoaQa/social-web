package ru.starry_sky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.WallMessage;
import ru.starry_sky.model.data_layer.WallMessageComment;
import ru.starry_sky.model.domain_layer.PrivateMessageDTO;
import ru.starry_sky.model.domain_layer.WallCommentDTO;
import ru.starry_sky.model.domain_layer.WallMessageDTO;
import ru.starry_sky.services.interfaces.PrivateMessagesServices;
import ru.starry_sky.services.interfaces.WallMessagesServices;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class MessagesController {

    @Autowired
    private PrivateMessagesServices privateMessagesServices;

    @Autowired
    private WallMessagesServices wallMessagesServices;

    // Приватные сообщения

    //получить все сообщения пользователя
    @GetMapping(value = "/{id}/messages")
    public List<PrivateMessage> getPrivateMessages(@PathVariable Long id){
        return privateMessagesServices.getUserMessages(id);
    }

    //Отправить личное сообщение
    @PostMapping(value = "/{id}/messages")
    public boolean sendMessage(@PathVariable Long id, @RequestBody PrivateMessageDTO dto){
        return privateMessagesServices.sendMessage(id, dto);
    }


    // Сообщения на стене

    // получить все сообщения пользователя
    @GetMapping(value = "/{id}/wall")
    public List<WallMessage> getWallMessages(@PathVariable Long id){
        return wallMessagesServices.getUserMessages(id);
    }

    // отправить сообщение
    @PostMapping(value = "/{id}/wall")
    public boolean sendWallMessage(@RequestBody WallMessageDTO dto){
        return wallMessagesServices.sendMessage(dto);
    }


    // посмотреть комментарии под сообщением на стене
    @GetMapping(value = "/{id}/wall/comments")
    public List<WallMessageComment> getWallMessageComments(@RequestParam(required = false) Long id){
        if (id == null){
            return wallMessagesServices.getAllWallComments();
        }else{
            return wallMessagesServices.getMessageComments(id);
        }
    }

    // написать коммент для сообщения стены
    @PostMapping(value = "/{id}/wall/comments")
    public ResponseEntity writeWallMessageComment(@RequestBody WallCommentDTO dto){
        if(wallMessagesServices.postComment(dto)){
            return ResponseEntity.ok("comment posted");
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("comment not posted");
        }

    }
}
