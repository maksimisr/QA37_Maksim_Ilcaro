package tests;

import manager.DataproviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
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

    @Test(dataProvider= "userDataRegistration", dataProviderClass = DataproviderUser.class)
    public void registrationSuccess(User user){
        logger.info("Start of 'registrationSuccess' test ");
        Random random = new Random();
        int i= random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis()/1000);
        int z= (int)(System.currentTimeMillis()/1000)%3600;


        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("You are logged in success"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("End");
        logger.info("Asserts checked if user logged in and is Yalla button is active");
    }

    @Test
    public void wrongEmailRegistration(){
        logger.info("Start of 'wrongEmailRegistration' test ");
        Random random = new Random();
        int i= random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis()/1000);
        int z= (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().setFirstname("Lisa").setLastname("Snow").setEmail("snow"+i+"famil.com").setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(Boolean.parseBoolean(app.getHelperUser().getErrorText()),"wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("End");
        logger.info("Asserts checked if email format wrong and is Yalla button is active");
    }

    @Test
    public void wrongPasswordRegistration(){
        logger.info("Start of 'wrongPasswordRegistration' test ");
        Random random= new Random();
        int i= random.nextInt(1000);
        User user= new User().setFirstname("Lisa").setLastname("Snow").setEmail("snow"+i+"@gmail.com").setPassword("snopy");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        logger.info("End");
    }

    @Test
    public void emptyName(){
        logger.info("Start of 'emptyName' test ");
        Random random= new Random();
        int i= random.nextInt(1000);
        User user= new User().setFirstname("").setLastname("Snow").setEmail("snow"+i+"@gmail.com").setPassword("Snow12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Name is reqiured"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("End");
        logger.info("Asserts checked if name format wrong and is Yalla button is active");
    }

    @Test
    public void emptyLastName(){
        logger.info("Start of 'emptyLastName' test ");
        Random random= new Random();
        int i= random.nextInt(1000);
        User user= new User().setFirstname("Lisa").setLastname("").setEmail("snow"+i+"@gmail.com").setPassword("Snow12345$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Last name is required"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("End");
        logger.info("Asserts checked if Last name format wrong and is Yalla button is active");

    }

    @Test
    public void userIsAlreadyRegistered(){
        logger.info("Start of 'userIsAlreadyRegistered' test ");
        User user = new User().setFirstname("Maksim").setLastname("Israilov").setEmail("maks-skam@mail.ru").setPassword("Maksim1996$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("\"User already exists\""));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("End");
        logger.info("Asserts checked if user is alreade registered and is Yalla button is active");
    }

    @AfterTest
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}
