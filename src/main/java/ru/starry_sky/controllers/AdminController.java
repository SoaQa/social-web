package ru.starry_sky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.starry_sky.services.interfaces.UserService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity disActiveUser(@PathVariable Long id){
        if (userService.disActive(id)){
            return ResponseEntity.ok("user deleted!");
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("user not found!");
        }
    }
}
