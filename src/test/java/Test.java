import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ru.starry_sky.WebConfig;

public class Test {
    //@org.junit.Test
    public void test(){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);
        applicationContext.refresh();

    }
}
