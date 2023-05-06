package manager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    Logger logger= LoggerFactory.getLogger(ApplicationManager.class);
    WebDriver wd;

    HelperUser helperUser;
    HelperCar helperCar;

    String browser;

    public ApplicationManager(String browser, String s){
        this.browser= browser;
    }

    public void init(){
        if (browser.equals(Browser.CHROME.browserName())) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            wd = new ChromeDriver(options);
            logger.info("All tests run in Browser");
        } else if (browser.equals(Browser.EDGE.browserName())) {
            wd= new EdgeDriver();
        }
        WebDriverListener listener = new ListenerWD();
        wd= new EventFiringDecorator<>(listener).decorate(wd);

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        wd.navigate().to("https://ilcarro.web.app/search");
        logger.info("the link--->"+wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public void stop(){
      wd.quit();
    }
}
