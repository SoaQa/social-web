import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ru.starry_sky.AppConfig;
import ru.starry_sky.utils.enums.Status;

public class Test {
    //@org.junit.Test
    public void test(){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();

    }
}
