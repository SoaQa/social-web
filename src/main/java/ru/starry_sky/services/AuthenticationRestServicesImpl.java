package ru.starry_sky.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.security.jwt.JwtTokenProvider;
import ru.starry_sky.services.interfaces.AuthenticationRestServices;
import ru.starry_sky.services.interfaces.UserService;
import ru.starry_sky.utils.LocaleFlag;
import ru.starry_sky.utils.valid.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@Slf4j
public class AuthenticationRestServicesImpl implements AuthenticationRestServices {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity login(HttpServletRequest request){
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

    public ResponseEntity register(NewUser newUser){
        System.out.println(newUser);
        HttpHeaders h = new HttpHeaders();
        h.add("Content-type", "text/html;charset=UTF-8");
        if (!ValidationUtils.isValidEmailAddress(newUser.getEmail())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).headers(h)
                    .body(messageSource.getMessage("badEmail",
                            new Object[] {newUser.getLogin()}, new Locale(LocaleFlag.getLocale())));
        }

        if (userService.createUser(newUser)){
            return ResponseEntity.ok().headers(h).body(messageSource.getMessage("userCreated",
                    new Object[] {newUser.getLogin()}, new Locale(LocaleFlag.getLocale())));
        }
        else{
            return (ResponseEntity) ResponseEntity.status(422);
        }
    }
}
