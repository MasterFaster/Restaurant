package sample.People;

import sample.MapSingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class Human extends Thread implements Serializable{

    private String firstName;
    private String secondName;
    protected int[] location = new int[2];

    public Human(){
        firstName = GetRandomHuman.getRandomFirstName();
        secondName = GetRandomHuman.getRandomSecondName();
        //location[0] = 0;
        //location[1] = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int[] getLocation() {
        return location;
    }

    /*public void setFirstLocation(int location) {
        this.location[0] = location;
    }*/

    /*public void setSecondLocation(int location) {
        this.location[1] = location;
    }*/

    public void setLocation(int[] location){
        this.location[0] = location[0];
        this.location[1] = location[1];
    }

    /**
     * introduction is method to print Human values
     */
    public void introduction(){
        System.out.println("My first name is: " + firstName + " and second name: " + secondName);
        System.out.println("My current location is: " + location[0] + " " + location[1]);
    }

    /**
     * stringIntroduction is method to return Human values as String
     * it is easier because subclasses will return additional values
     * and when we show People in new window we create also radioButtons we need String value to name Button
     */
    public String stringIntroduction(){
        return this.getClass().getSimpleName() + "\nMy first name is: " + firstName + " and second name: " + secondName;
    }

    public void run() {
        while(true){
            try{
                sleep(1000);
            }catch(Exception ex){
                System.out.println(ex);
            }
            System.out.println("siema, jestem człowiek?");
        }
    }


}
