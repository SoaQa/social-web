import config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;
import static org.junit.Assert.*;

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
        assertEquals(messageSource.getMessage("userCreated", new Object[] {"test"}, new Locale("en", "Us")), "User test registered!");
        assertEquals(messageSource.getMessage("userCreated", new Object[] {"test"}, new Locale("de", "Gr")), "Benutzer test registriert!");
        assertEquals(messageSource.getMessage("userCreated", new Object[] {"test"}, new Locale("ru")), "Пользователь test зарегистрирован");
    }
}
