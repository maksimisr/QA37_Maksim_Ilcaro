package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        if (text != null) {
            element.click();
            element.clear();
            clearNew(element);
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementExists(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getScreenElement(String link, By locator){
        WebElement element= wd.findElement(locator);
        File tmp= element.getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTextBox(By locator){
       WebElement element= wd.findElement(locator);
       String os= System.getProperty("os.name");
        System.out.println(os);
        if (os.startsWith("Win")){
            element.sendKeys(Keys.CONTROL,"a");
        }else {
            element.sendKeys(Keys.COMMAND,"a");
        }

       element.sendKeys(Keys.DELETE);
    }

    public boolean isYallaButtonNotActive() {
        wd.findElement(By.xpath("//button[@type='submit']"))
    }
}
