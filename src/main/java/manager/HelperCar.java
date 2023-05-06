package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.cssSelector("a[href$='/let-car-work']"));
    }


    public void fillCarForm(Car car) {

     type(By.cssSelector("input[placeholder$='Enter your address']"), car.getLocation());
     type(By.cssSelector("input[id$='make']"), car.getManufacture());
     type(By.cssSelector("input[id$='model']"),car.getModel());
     type(By.cssSelector("input[id$='year']"),car.getYear());

     select(By.id("fuel"),car.getFuel());
     type(By.id("seats"),String.valueOf(car.getSeats()));
     type(By.cssSelector("input[id$='class']"),car.getCarClass());
     type(By.cssSelector("input[id$='serialNumber']"),car.getCarRegNumber());
     type(By.cssSelector("input[id$='price']"),String.valueOf(car.getPrice()));
     //type(By.cssSelector("input[id$='price']"),car.getPrice());
     type(By.cssSelector("input[id$='about']"),car.getAbout());
    }

    private void select(By locator, String option) {

        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
    }

    private void typeLocation(String location)
    {
        type(By.id("pickUpPlace"),location);
        click(By.cssSelector("div.pac-item"));
    }

    public void submitCar() {
        click(By.cssSelector("button[type$='submit']"));
    }

    public void searchCurrentMonth(String city, String datefrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));


       String[] from=  datefrom.split("/");
        String locatorFrom="//div[text()=' "+from[1]+ " ']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        String locator="//div[text()=' "+to[1]+ " ']";
        click(By.xpath("//div[text()='" +to[1]+"  ']"));

    }

    private void typeCity(String city) {
        type(By.id("city"),city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared(){
        return isElementExists(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
      click(By.id("dates"));

      LocalDate now=  LocalDate.now();
        System.out.println(now);
        int year = now.getYear();
        int month= now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from= LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        LocalDate to = LocalDate.parse(dateTo,DateTimeFormatter.ofPattern("M/dd/yyyy"));
        System.out.println(from);

        int diffMonth=  from.getMonthValue()-month;
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()='" +from.getDayOfMonth()+"  ']"));

        diffMonth= to.getMonthValue()- from.getMonthValue();

        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }

        String locator = String.format("//div[text()=' %s ']",to.getDayOfMonth());
        click(By.xpath(locator));

    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label= 'Next month']"));
        }
    }

    public void searchCurrentAnyPeriod(String city, String dateFrom,String dateTo){
        typeCity(city);
        click(By.id("dates"));
        LocalDate now= LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom,DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo,DateTimeFormatter.ofPattern("M/d/yyyy"));
        int diffYear;
        int diffMonth;
        diffYear =  from.getYear()-now.getYear();
        if(diffYear==0){
            diffMonth= from.getMonthValue()-now.getMonthValue();
        }else {
            diffMonth=12-now.getMonthValue()+from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        String locator= String.format("//div[text()=' %s ']",from.getDayOfMonth());
        click(By.xpath(locator));
        diffYear= to.getYear()-from.getYear();
        if(diffYear==0){
            diffMonth=to.getMonthValue()-from.getMonthValue();
        }else {
            diffMonth=12-from.getMonthValue()+to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        locator=String.format("//div[text()=' %s ']",from.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));

    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);

       wd.findElement(By.id("dates")).sendKeys(dateFrom+"-"+dateTo);
    }

    public boolean isErrorDisplaed(String message){
        String text= wd.findElement(By.cssSelector("div.ng-star-inserted")).getText();
        return text.equals(message);
    }
}
