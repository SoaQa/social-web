import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.starry_sky.AppConfig;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friends;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;


public class TestFriendsDao {
    private AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
    @Autowired
    private FriendsDao friendsDao = applicationContext.getBean(FriendsDao.class);

    @Test
    public void beginFriendship(){
        Friends friends = new Friends();
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setFriend((long) 7);
        friendsPK.setRequester((long) 1);
        friends.setId(friendsPK);
        friends.setAccept(true);
        friendsDao.save(friends);
    }
}
