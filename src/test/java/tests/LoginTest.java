package tests;


import manager.HelperUser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{
    @Test
    public void loginSuccess(){
        app.getHelperUser().login();
        app.getHelperUser().fillLoginForm("maks-skam@mail.ru","Maksim1996$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
