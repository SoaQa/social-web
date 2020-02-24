package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.services.interfaces.FriendsServices;
import ru.starry_sky.services.interfaces.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendsServices friendsServices;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody NewUser newUser){
        System.out.println(newUser);
        if (userService.createUser(newUser)){
            return ResponseEntity.ok("User created!");
        }
        else{
           return (ResponseEntity) ResponseEntity.status(422);
        }
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }


    @GetMapping(value = "/{id}/friends")
    public List<User> getUserFriends(@PathVariable Long id){
        return userService.getUserFriends(id);
    }

    //@PostMapping(value = "/friends")
    //public boolean friendRequest(@RequestBody FriendsPK friendsPK){
       // return friendsServices.friendRequest(friendsPK);
  // }

    @PutMapping(value = "/friends/accept")
    boolean acceptFriendship(@RequestBody FriendsPK friendsPK){
       return friendsServices.acceptFriendship(friendsPK);
    }

}
