package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.services.interfaces.AuthenticationRestServices;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
@Slf4j
public class AuthenticationRestController {

    @Autowired
    private AuthenticationRestServices authenticationRestServices;


    @PostMapping(value = "/login")
    public @ResponseBody
    ResponseEntity login(HttpServletRequest request) {
        return authenticationRestServices.login(request);
    }


    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody NewUser newUser) {
        return authenticationRestServices.register(newUser);
    }
}
