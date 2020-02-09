import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.starry_sky.AppConfig;
import ru.starry_sky.dao.interfases.PrivateMessagesDao;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.User;

public class TestMessages {
    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
    PrivateMessagesDao privateMessagesDao = applicationContext.getBean(PrivateMessagesDao.class);
    UserDao userDao = applicationContext.getBean(UserDao.class);

    @Test
    public void sendMessage(){
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setMessageBody("Hello world!");
        privateMessage.setSenderID((long) 1);
        privateMessage.setRecipientID((long) 2);
        privateMessagesDao.save(privateMessage);
    }

    @Test
    public void getUserMessages(){
        User user = userDao.getByID((long) 1);
        System.out.println(user.getSentOutPrivateMessages());
        System.out.println(user.getReceivedPrivateMessages());
        System.out.println(user.getSentOutWallMessages());
        System.out.println(user.getReceivedWallMessages());
    }

    @Test
    public void getMessage(){
        System.out.println(privateMessagesDao.getByID((long) 2));
    }
}
