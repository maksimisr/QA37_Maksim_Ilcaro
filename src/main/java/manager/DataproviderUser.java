package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataproviderUser {

    @DataProvider
    public Iterator<Object[]> userDataLogin(){
        List<Object[]> list=  new ArrayList<>();
        list.add(new Object[]{new User().setEmail("maks-skam@mail.ru").setPassword("Maksim1996$")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userDataRegistration(){
        Random random = new Random();
        int i = random.nextInt(1000);
        List<Object[]> list= new ArrayList<>();
        list.add( new Object[]{ new User().setFirstname("Gomer").setLastname("Simpson").setEmail("gomer196"+i+"@gmail.com").setPassword("gomer1965$")});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> userLogin() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader= new BufferedReader(new FileReader(new File("src/test/resources/loginData.csv")));
        String line= reader.readLine();
        while (line!=null){
            String[] all= line.split(",");
            list.add(new Object[]{new User().setEmail(all[0]).setPassword(all[1])});
            line= reader.readLine();
        }
        return list.iterator();

    }

}
