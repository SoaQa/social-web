package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.Community;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.UpdateUserProfileDTO;
import ru.starry_sky.model.domain_layer.UserCommunityDTO;
import ru.starry_sky.services.interfaces.CommunitiesServices;
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
    private CommunitiesServices communitiesServices;

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

    @GetMapping("/find-user")
    public List<User> findUserByName(@RequestParam String name){
        return userService.findUserByName(name);
    }

    // сообщества demo
    @PutMapping("/{id}/communities")
    public ResponseEntity inToCommunity(@RequestBody UserCommunityDTO dto){
        if (userService.inToCommunity(dto.getUserID(), dto.getCommunityID())){
            return ResponseEntity.ok("success!");
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("reject");
        }
    }

    @GetMapping("/{id}/communities")
    public List<Community> getUserCommunities(@PathVariable Long id){
        return communitiesServices.getUserCommunities(id);

    }
}
