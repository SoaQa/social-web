package ru.starry_sky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.dao.interfases.RoleDao;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.Role;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.services.interfaces.UserService;
import ru.starry_sky.utils.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendsDao friendsDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers(){
        return userDao.getAllData();
    }

    public boolean createUser(NewUser newUser){
        User user = new User();
        List<Role> roles = new ArrayList<>();
        Role role = roleDao.getRoleByName("ROLE_USER");
        roles.add(role);

        user.setLogin(newUser.getLogin());
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        user.setStatus(Status.ACTIVE);
        user.setRoles(roles);
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

    public User getUserByLogin(String login){
        return userDao.getUserByLogin(login);
    }

}
