package ru.starry_sky.services.interfaces;

import org.springframework.http.ResponseEntity;
import ru.starry_sky.model.domain_layer.NewUser;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationRestServices {
    ResponseEntity login(HttpServletRequest request);
    ResponseEntity register(NewUser newUser);
}
