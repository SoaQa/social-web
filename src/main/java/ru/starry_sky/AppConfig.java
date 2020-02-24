package ru.starry_sky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.starry_sky.dao.*;
import ru.starry_sky.dao.interfases.*;
import ru.starry_sky.model.data_layer.*;

@Configuration
@EnableWebMvc
@ComponentScan
@PropertySource(value = { "classpath:application.properties" })
//@TestPropertySource(locations="classpath:test.properties")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public UserDao userDaoImpl(){
        UserDao userDao = new UserDaoImpl();
        userDao.setGenericClass(User.class);
        return userDao;
    }

    @Bean
    public PrivateMessagesDao privateMessagesDao(){
        PrivateMessagesDao privateMessagesDao = new PrivateMessagesDaoImpl();
        privateMessagesDao.setGenericClass(PrivateMessage.class);
        return privateMessagesDao;
    }

    @Bean
    public WallMessageCommentDao wallMessageCommentDao(){
        WallMessageCommentDao wallMessageCommentDao = new WallMessageCommentDaoImpl();
        wallMessageCommentDao.setGenericClass(WallMessageComment.class);
        return wallMessageCommentDao;
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
    public CommunitiesDao CommunitiesDao(){
        CommunitiesDao communitiesDao = new CommunitiesDaoImpl();
        communitiesDao.setGenericClass(Community.class);
        return communitiesDao;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
