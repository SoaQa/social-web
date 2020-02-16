package ru.starry_sky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.services.interfaces.UserServices;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserDao userDao;

    public List<User> getUsers(){
        return userDao.getAllData();
    }
}
