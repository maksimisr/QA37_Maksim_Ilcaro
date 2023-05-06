package tests;

import manager.DataproviderCar;
import models.Car;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
@Test(dataProvider = "carData",dataProviderClass = DataproviderCar.class)
    public void addNewCarSuccess(Car car){
    logger.info("Start addition of new car");
    logger.info("Start test with name 'addNewCarSuccess' ");
    logger.info("TEst data---> " +car.toString());

app.getHelperCar().openCarForm();
app.getHelperCar().fillCarForm(car);
app.getHelperCar().submit();
    logger.info("End");

    }


}
