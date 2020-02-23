package ru.starry_sky.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.security.jwt.JwtUser;
import ru.starry_sky.security.jwt.JwtUserFactory;
import ru.starry_sky.services.interfaces.UserService;

@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(login);
        if (user == null){
            throw new UsernameNotFoundException("User with login: " + login + " not found!");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("User with login {} in loadUserByUsername successfully created", login);
        return jwtUser;
    }
}
