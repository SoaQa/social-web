package ru.starry_sky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.starry_sky.dao.*;
import ru.starry_sky.dao.interfases.*;
import ru.starry_sky.model.data_layer.*;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public UserDao userDaoImpl(){
        UserDao userDao = new UserDaoImpl();
        userDao.setGenericClass(User.class);
        return userDao;
    }

    @Bean
    public FriendsDao friendsDaoImpl(){
        FriendsDao friendsDao = new FriendsDaoImpl();
        friendsDao.setGenericClass(Friendship.class);
        return friendsDao;
    }

    @Bean
    public PrivateMessagesDao privateMessagesDao(){
        PrivateMessagesDao privateMessagesDao = new PrivateMessagesDaoImpl();
        privateMessagesDao.setGenericClass(PrivateMessage.class);
        return privateMessagesDao;
    }

    @Bean
    public WallMessagesDao wallMessagesDao(){
        WallMessagesDao wallMessagesDao = new WallMessagesDaoImpl();
        wallMessagesDao.setGenericClass(WallMessage.class);
        return wallMessagesDao;
    }

    @Bean
    public FriendsDao friendsDao(){
        FriendsDao friendsDao = new FriendsDaoImpl();
        friendsDao.setGenericClass(Friendship.class);
        return friendsDao;
    }

    @Bean
    public RoleDao roleDao(){
        RoleDao roleDao = new RoleDaoImpl();
        roleDao.setGenericClass(Role.class);
        return roleDao;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
