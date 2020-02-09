package ru.starry_sky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import ru.starry_sky.dao.FriendsDaoImpl;
import ru.starry_sky.dao.PrivateMessagesDaoImpl;
import ru.starry_sky.dao.UserDaoImpl;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.dao.interfases.PrivateMessagesDao;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.User;

@Configuration
@ComponentScan
@TestPropertySource(locations="classpath:test.properties")
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

}
