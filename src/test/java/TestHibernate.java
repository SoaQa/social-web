import config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import java.util.Set;

import static org.junit.Assert.*;


public class TestHibernate {

    private UserDao userDao;
    private FriendsDao friendsDao;

    @Before
    public void before(){
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);
        this.userDao = configApplicationContext.getBean(UserDao.class);
        this.friendsDao = configApplicationContext.getBean(FriendsDao.class);
    }

    @Test
    public void createGetDeleteUser(){

        // Создаём юзера
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("Test@.ru");

        // Сохраняем в БД
        userDao.save(user);

        // Пытаемся его получить по его логину
        User user1 = userDao.getUserByLogin("test");

        // Проверяем, что он не нуловый и с тем же логином
        assertNotNull(user1);
        assertEquals("test", user1.getLogin());

        // удаляем
        userDao.removeByID(user1.getId());

        // Проверяем что он удалён
        user1 = userDao.getUserByLogin("test");
        assertNull(user1);

    }

    @Test
    public void beginAndDeclineFriendship(){
        User requester = new User();
        requester.setEmail("teeee1st@ru.ru");
        requester.setLogin("test1");
        requester.setPassword("test");

        User friend = new User();
        friend.setEmail("teeeee1st@ru.ru");
        friend.setLogin("test2");
        friend.setPassword("test");

        // Создали и сохранили пользвоателей
        userDao.save(requester);
        userDao.save(friend);


        requester = userDao.getUserByLogin("test1");
        friend = userDao.getUserByLogin("test2");

        // Забираем их по логину и проверяем что они не нуловые
        assertNotNull(requester);
        assertNotNull(friend);

        // Создаём отношение дружбы
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setFriend(friend.getId());
        friendsPK.setRequester(requester.getId());

        Friendship friendship = new Friendship();
        friendship.setId(friendsPK);
        friendship.setAccept(true);

        friendsDao.save(friendship);

        // Првоеряем что у test1 есть один друг и это test2
        Set<Long> friendIDs =  friendsDao.getFriendsIDSetByUserID(requester.getId());
        for (Long id: friendIDs){
            assertEquals(id, friend.getId());
            break;
        }

        friendsDao.removeByID(friendsPK);
        userDao.removeByID(requester.getId());
        userDao.removeByID(friend.getId());
    }

    @After
    public void after(){
    }
}
