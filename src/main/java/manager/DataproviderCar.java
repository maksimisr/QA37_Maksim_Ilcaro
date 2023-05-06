package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataproviderCar {
@DataProvider
    public Iterator<Object[]> carData(){
    List<Object[]> list= new ArrayList<>();
    int i= new Random().nextInt(1000)+1000;
    list.add(new Object[]{ Car.builder().
            location("Tel Aviv, Israel").
            manufacture("Mazda").
            model("3").
            year("2022").
            fuel("Petrol").
            seats(4).
            carClass("C").
            carRegNumber("678-900-"+i).
            price(50).
            about("Very nice car").
            build()});



return list.iterator();
}
}