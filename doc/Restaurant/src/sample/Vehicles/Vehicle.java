package sample.Vehicles;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class Vehicle {

    private int capacity;
    private int speed;
    private int registrationNr;
    private float tankCapacity;
    private float fuelLeft;


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

    public int getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(int registrationNr) {
        this.registrationNr = registrationNr;
    }

    public float getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(float tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public float getFuelLeft() {
        return fuelLeft;
    }

    public void setFuelLeft(float fuelLeft) {
        this.fuelLeft = fuelLeft;
    }
}
