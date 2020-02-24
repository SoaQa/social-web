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
 * осуществялет взаимодействие пользователей: дружбу, обмен сообщениями
 */

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendsServices friendsServices;

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

    // Дружба

    // Друзья пользователя
    @GetMapping(value = "/{id}/friends")
    public List<User> getUserFriends(@PathVariable Long id){
        return userService.getUserFriends(id);
    }

    // Создание запроса на дружбу
    @PostMapping(value = "/{requester}/friends")
    public ResponseEntity friendsRequest(@PathVariable Long requester, @RequestBody LongID id){
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setRequester(requester);
        friendsPK.setFriend(id.getId());
        if(friendsServices.friendRequest(friendsPK)){
            return ResponseEntity.ok("Friends requested");
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("request not send.");
        }

    }

    // Подверждение или отклонение дружбы
    @PutMapping(value = "/{friend}/friends")
    public ResponseEntity acceptFriendship(@PathVariable Long friend, @RequestBody AcceptFriendshipDTO dto){
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setRequester(dto.getRequester());
        friendsPK.setFriend(friend);
       if(friendsServices.acceptFriendship(dto.isAccept() ,friendsPK)){
           return ResponseEntity.ok("Friends accept or decline");
       }else{
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body("Friends not accept.");
       }
    }

    @GetMapping(value = "/{id}/friend-requests")
    public List<Friendship> getUnacceptedFriendships(Long id,@RequestParam(defaultValue = "my") String ex){
        return friendsServices.getUnacceptedFriendships(id, ex);
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
