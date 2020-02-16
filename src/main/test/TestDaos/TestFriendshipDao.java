package TestDaos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.starry_sky.AppConfig;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;


public class TestFriendshipDao {
    private AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
    @Autowired
    private FriendsDao friendsDao = applicationContext.getBean(FriendsDao.class);

    //@Test
    public void beginFriendship(){
        Friendship friendship = new Friendship();
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setFriend((long) 2);
        friendsPK.setRequester((long) 1);
        friendship.setId(friendsPK);
        friendship.setAccept(true);
        friendsDao.save(friendship);
    }
}
