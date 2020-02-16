package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.services.interfaces.UserServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<User> getUsers(){
        return userServices.getUsers();
    }

    @PostMapping
    public boolean createUser(@RequestBody NewUser newUser){
        return userServices.createUser(newUser);
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){
        return userServices.getUser(id);
    }
}
