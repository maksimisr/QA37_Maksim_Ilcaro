package tests;


import manager.HelperUser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","Maksim1996$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }


    public void loginSuccessModel(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","Maksim1996$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("makskam@mail.ru","Maksim1996$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().wrongLoginOrPassword());
    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","MakSDVZim1996$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().wrongLoginOrPassword());
    }
}
