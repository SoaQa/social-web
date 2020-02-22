package ru.starry_sky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.services.interfaces.UserServices;
import ru.starry_sky.utils.enums.Status;

import java.util.List;
import java.util.Set;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendsDao friendsDao;

    public List<User> getUsers(){
        return userDao.getAllData();
    }

    public boolean createUser(NewUser newUser){
        User user = new User();
        user.setLogin(newUser.getLogin());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        user.setStatus(Status.ACTIVE);
        userDao.save(user);
        return true;
    }

    public User getUser(Long id){
        return userDao.getByID(id);
    }

    public List<User> getUserFriends(Long id){
        Set<Long> usersID = friendsDao.getFriendsIDSetByUserID(id);
        return userDao.getUsersByIDSet(usersID);
    }

}
