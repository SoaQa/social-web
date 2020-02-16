package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.User;

import java.util.List;

public interface UserServices {
    List<User> getUsers();
}
