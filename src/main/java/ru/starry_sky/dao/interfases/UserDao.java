package ru.starry_sky.dao.interfases;

import ru.starry_sky.model.data_layer.User;

import java.util.List;
import java.util.Set;

public interface UserDao extends GenericDao<User, Long>{
    List<User> getUsersByIDSet(Set<Long> usersID);
    User getUserByLogin(String login);
}
