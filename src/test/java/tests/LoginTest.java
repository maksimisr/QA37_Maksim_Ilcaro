package tests;


import manager.DataproviderUser;
import manager.HelperUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test(dataProvider = "userDataLogin", dataProviderClass = DataproviderUser.class)
    public void loginSuccess1(User user){
        logger.info("Start");
        logger.info("Data provided: "+user.toString());


        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("End");
    }

    @Test
    public void loginSuccess(){
        logger.info("Start");
        logger.info("Test data--> email: 'maks-skam@mail.ru' password: 'Maksim1996$' ");
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","Maksim1996$");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert checks is user logged");
        logger.info("End");
    }

@Test(dataProvider = "userLogin", dataProviderClass = DataproviderUser.class)
    public void loginSuccessModel(User user){

        logger.info("Start");
        logger.info("Test data--> email: 'maks-skam@mail.ru' password: 'Maksim1996$' ");
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert checks is user logged");
        logger.info("End");

    }

    @Test
    public void loginWrongEmail(){
        logger.info("Start");
        logger.info("Test data--> email: 'makskam@mail.ru' password: 'Maksim1996$' ");
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("makskam@mail.ru","Maksim1996$");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().wrongLoginOrPassword());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Asserts check is it valid email format and is 'Yalla' button active ");
        logger.info("End");
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Start");
        logger.info("Test data--> email: 'maks-skam@mail.ru' password: 'MakSDVZim1996$' ");
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","MakSDVZim1996$");
        app.getHelperUser().submit();

      //  Assert.assertTrue(app.getHelperUser().getMessage(),"");
        logger.info("End");
    }

    @AfterTest
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}
