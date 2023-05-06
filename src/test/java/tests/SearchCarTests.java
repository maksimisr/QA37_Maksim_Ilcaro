package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
       app.getHelperCar().searchCurrentMonth("Tel Aviv,Israel","4/28/2023","5/28/2023");
       app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Tel Aviv,Israel","4/28/2023","6/28/2023");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentPeriodSuccess(){
      app.getHelperCar().searchCurrentAnyPeriod("Tel Aviv,Israel","4/27/2023","5/28/2023");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("Tel Aviv,Israel","4/28/2023","6/28/2023");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isErrorDisplaed("You can't pick date before today"));
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());

    }

    @BeforeMethod
    public void postCOndition(){
        app.getHelperCar().navigateByLogo();
    }
}
