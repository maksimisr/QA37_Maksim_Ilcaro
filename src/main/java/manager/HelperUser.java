package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void login(){
       // WebElement logintab= wd.findElement(By.xpath("//a[@href='/login?url=%2Fsearch']"));
       // logintab.click();
click(By.xpath("//a[@href='/login?url=%2Fsearch']"));
    }

    public void fillLoginForm(String email,String password){
      ///  WebElement user= wd.findElement(By.xpath("//input[@autocomplete='username']"));
      //  user.click();
      //  user.clear();
       // user.sendKeys(email);
        type(By.xpath("//input[@autocomplete='username']"),email);
      //  WebElement pass= wd.findElement(By.xpath("//input[@autocomplete='current-password']"));
       // pass.click();
       // pass.clear();
       // pass.sendKeys(password);
        type(By.xpath("//input[@id='password']"),password);
    }

    public void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
    }

    public boolean isLogged() {

        return isElementExists(By.xpath("//a[.=' Logout ']"));
    }

    public String getMessage(){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));


        return  wd.findElement(By.cssSelector(".dialog-container>2")).getText();
    }



    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public boolean wrongLoginOrPassword(){

        return  isElementExists(By.cssSelector("h2.message"));
    }
}
