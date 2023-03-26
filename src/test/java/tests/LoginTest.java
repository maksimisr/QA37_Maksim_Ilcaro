package tests;


import manager.HelperUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    public void loginSuccess1(){
        User user = new User().setEmail("maks-skam@mail.ru").setPassword("Maksim1996$");

        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","Maksim1996$");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }


    public void loginSuccessModel(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","Maksim1996$");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("makskam@mail.ru","Maksim1996$");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().wrongLoginOrPassword());
        Assert.assertTrue(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","MakSDVZim1996$");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getMessage(),"")
    }

    @AfterTest
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}
