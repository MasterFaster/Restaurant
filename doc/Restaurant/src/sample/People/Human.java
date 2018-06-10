package sample.People;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class Human {

    private String firstName;
    private String secondName;
    private float[] location = new float[2];

    public Human(){
        firstName = GetRandom.getRandomFirstName();
        secondName = GetRandom.getRandomSecondName();
        location[0] = 0;
        location[1] = 0;
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

    public float[] getLocation() {
        return location;
    }

    public void setLocation(float[] location) {
        this.location = location;
    }

    public void introduction(){
        System.out.println("My first name is: " + firstName + " and second name: " + secondName);
        System.out.println("My current location is: " + location[0] + " " + location[1]);
    }
}
