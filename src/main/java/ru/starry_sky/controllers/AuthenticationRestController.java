package ru.starry_sky.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.security.jwt.JwtTokenProvider;
import ru.starry_sky.services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@Slf4j
public class AuthenticationRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public @ResponseBody ResponseEntity saveProfileJson(HttpServletRequest request){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            User user = userService.getUserByLogin(login);
            if (user == null){
                throw new UsernameNotFoundException("User with login: " + login + " not found!");
            }

            String token = jwtTokenProvider.createToken(login, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            return ResponseEntity.ok(response);

        }catch (AuthenticationServiceException e){
            log.warn("Incorrect login or password. Login: {}, Password {}",
                    login, password);
            throw new BadCredentialsException("Invalid login or password!");
        }
    }


    @PostMapping(value = "/register")
    public ResponseEntity createUser(@RequestBody NewUser newUser){
        System.out.println(newUser);
        if (userService.createUser(newUser)){
            return ResponseEntity.ok("User registered!");
        }
        else{
            return (ResponseEntity) ResponseEntity.status(422);
        }
    }
}
