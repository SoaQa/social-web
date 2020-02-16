package ru.starry_sky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.starry_sky.dao.FriendsDaoImpl;
import ru.starry_sky.dao.PrivateMessagesDaoImpl;
import ru.starry_sky.dao.UserDaoImpl;
import ru.starry_sky.dao.WallMessagesDaoImpl;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.dao.interfases.PrivateMessagesDao;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.dao.interfases.WallMessagesDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.data_layer.WallMessage;

@Configuration
@ComponentScan
@EnableWebMvc
//@TestPropertySource(locations="classpath:test.properties")
public class AppConfig implements WebMvcConfigurer {
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
