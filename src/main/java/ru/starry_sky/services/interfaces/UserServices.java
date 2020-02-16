package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;

import java.util.List;

public interface UserServices {
    List<User> getUsers();
    boolean createUser(NewUser newUser);
    User getUser(Long id);
}
