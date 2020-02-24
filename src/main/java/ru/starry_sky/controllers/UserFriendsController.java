package ru.starry_sky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;
import ru.starry_sky.model.domain_layer.AcceptFriendshipDTO;
import ru.starry_sky.model.domain_layer.LongID;
import ru.starry_sky.services.interfaces.FriendsServices;
import ru.starry_sky.services.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserFriendsController {
    @Autowired
    private FriendsServices friendsServices;

    @Autowired
    private UserService userService;
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
    public List<Friendship> getUnacceptedFriendships(@PathVariable Long id){
        return friendsServices.getUnacceptedFriendships(id);
    }

    @DeleteMapping(value = "/{userID}/friends")
    public ResponseEntity deleteFriend(@PathVariable Long userID, @RequestParam Long id){
        if(userService.deleteFriend(userID, id)){
            return ResponseEntity.ok("Friend deleted");
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Friend not deleted");
        }
    }


}
