package sample.Vehicles;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Master Faster on 05.12.2016.
 * every Deliverer has vehicle
 * depending on driving license it's car or scooter
 */
public class Vehicle implements Serializable{

    private int capacity;
    private int speed;
    private String registrationNr;
    private int tankCapacity;
    private int fuelLeft;

    public Vehicle(){
        Random random = new Random();
        capacity = random.nextInt(50-20) + 20;
        capacity = random.nextInt(200-50) + 50;
        registrationNr = "";
        for(int i=0;i<8;i+=1) {
            if(i==0 || i==1|| i == 6 || i == 7){
                registrationNr+= Character.toString((char) (90 -random.nextInt(26)));
            }else {
                registrationNr += random.nextInt(10);
            }
        }
        tankCapacity = random.nextInt(200-100) + 100;
        fuelLeft = tankCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public float getFuelLeft() {
        return fuelLeft;
    }

    public void setFuelLeft(int fuelLeft) {
        this.fuelLeft = fuelLeft;
    }

    public void fuelDecrease(){ fuelLeft--; }

    public String stringIntroduction(){ return "Capacity: "+ capacity + " Registration: " + registrationNr + "\nFuel: " + fuelLeft; }
}
