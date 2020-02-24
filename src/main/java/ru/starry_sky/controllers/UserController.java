package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;
import ru.starry_sky.model.domain_layer.AcceptFriendshipDTO;
import ru.starry_sky.model.domain_layer.LongID;
import ru.starry_sky.model.domain_layer.PrivateMessageDTO;
import ru.starry_sky.model.domain_layer.UpdateUserProfileDTO;
import ru.starry_sky.services.interfaces.FriendsServices;
import ru.starry_sky.services.interfaces.PrivateMessagesServices;
import ru.starry_sky.services.interfaces.UserService;

import java.util.List;

/**
 * Контроллер пользователя
 * основной контроллер приложения
 */

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PrivateMessagesServices privateMessagesServices;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

   // Основные запросы для юзера

    // получить юзера по id
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    // Обновить данные пользователя
    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserInfo(@PathVariable Long id, @RequestBody UpdateUserProfileDTO dto){
        if (userService.updateUserInfo(id, dto)){
            return ResponseEntity.ok(id);
        }else {
            throw new BadCredentialsException("updateUserInfo bad request");
        }

    }


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




}
