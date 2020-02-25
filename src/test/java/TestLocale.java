import config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class TestLocale {
    @Autowired
    private MessageSource messageSource;

    @Before
    public void doBefore(){
        AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
        this.messageSource = applicationContext.getBean(MessageSource.class);
    }

    @Test
    public void testLocale(){
        System.out.println(messageSource.getMessage("userCreated", null, new Locale("ru", "Ru")));
    }
}
