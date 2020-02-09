package TestDaos;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.starry_sky.AppConfig;
import ru.starry_sky.dao.UserDaoImpl;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;



public class TestUserDao {
    private AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private UserDao userDao = applicationContext.getBean(UserDaoImpl.class);


    @Test
    public void createAndSaveNewUser(){
        User user = new User();

        user.setEmail("email@mail.ru");
        user.setLogin("Valera");
        user.setPassword("123sdd321");

        userDao.save(user);
    }

    @Test
    public void getUserByID(){
        User user = userDao.getByID((long) 7);
        System.out.println(user.getLogin());
        System.out.println( user.getFriend());
        System.out.println(user.getRequester());

    }
}
