package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    public void fillLoginForm(User user){

        type(By.xpath("//input[@autocomplete='username']"), user.getEmail());

        type(By.xpath("//input[@id='password']"), user.getPassword());
    }



    public boolean isLogged(){
        return isElementExists(By.xpath("//a[text()=' Logout ']"));
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

    public String getErrorText() {
        String text = wd.findElement(By.cssSelector("h2[class$='message']")).getText();
        System.out.println(text);
        return text;
    }

    public boolean isYallaButtonNotActive() {
       boolean res =isElementExists(By.cssSelector("button[disabled]"));
       WebElement element =  wd.findElement(By.cssSelector("button[type='submit']"));
       boolean result = element.isEnabled();
       return  res && !result;
    }


    public void closeWindow() {
        click(By.xpath("//button[text()='Ok']"));
        if(isElementExists(By.xpath("//button[text()='Ok']")))
            click(By.xpath("//button[text()='Ok']"));

    }
///////////registration
    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstname());
        type(By.id("lastName"), user.getLastname());
        type(By.id("email"),user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        click(By.cssSelector("label[for='terms-of-use']"));

        JavascriptExecutor js= (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click");
    }
    public void checkPolicyXY(){
        Dimension size = wd.manage().window().getSize();
        System.out.println("Width screen --->" +size.getWidth());
        WebElement label= wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Dimension dimensions= label.getSize();

        Rectangle rect = label.getRect();
        int w=rect.getWidth();
        int xoffset=-w/2;
    Actions actions= new Actions(wd);
    actions.moveToElement(label,xoffset,0).click().release().perform();
}
}
