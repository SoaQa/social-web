package ru.starry_sky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.starry_sky.dao.UserDaoImpl;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;

@Configuration
public class AppConfig {
    @Bean
    public UserDao userDaoImpl(){
        UserDao userDao = new UserDaoImpl();
        userDao.setGenericClass(User.class);
        return userDao;
    }
}
