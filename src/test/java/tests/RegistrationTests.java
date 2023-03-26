package tests;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i= random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis()/1000);
        int z= (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().setFirstname("Lisa").setLastname("Snow").setEmail("snow"+i+"@gmail.com").setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
    }
}
