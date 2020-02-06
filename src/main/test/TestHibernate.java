import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.starry_sky.HibernateConfig;
import ru.starry_sky.dao.UserDaoImpl;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;


public class TestHibernate {


    @Test
    public void upSessionFactory(){
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = applicationContext.getBean(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Long id = Long.valueOf(1);
        User user = session.get(User.class, id);
        System.out.println(user);

    }
}
