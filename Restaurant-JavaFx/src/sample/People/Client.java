package sample.People;

import sample.Exceptions.NoDataException;
import sample.Food.Order;
import sample.MapSingleton;
import sample.OrdersDataBaseSingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Master Faster on 05.12.2016.
 * every client is a new Thread
 * order some Meals at random time
 */
public class Client extends Human{

    private int Code;
    private int phoneNr;
    private String email;
    private Order clientOrder;
    // viabilityDeliveryOrder deliverer use it to order clients for delivery
    // viabilityDeliveryOrder is function: location[1] *100 + location[0]
    // biggest viabilityDeliveryOrder = deliverer goes there first
    private int viabilityDeliveryOrder;
    private float distanceFromRestaurant;
    private boolean ifPause;    //variable to make thread wait or run again
    private final ThreadLock pauseLock = new ThreadLock();  //object to pause or resume thread

    public Client(){
        Random random = new Random();
        phoneNr = GetRandomHuman.getRandomPhoneNr();
        email = this.getFirstName() + "." + this.getSecondName() + random.nextInt(999) + "@gmail.com";
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public synchronized Order getClientOrder() {
        return clientOrder;
    }

    public synchronized void setClientOrder(Order clientOrder) {
        this.clientOrder = clientOrder;
    }

    public void setViabilityDeliveryOrder(int viabilityDeliveryOrder){  this.viabilityDeliveryOrder = viabilityDeliveryOrder; }

    public int getViabilityDeliveryOrder(){ return viabilityDeliveryOrder; }

    public void countViabilityDeliveryOrder(){  viabilityDeliveryOrder = location[1] *100 + location[0];}

    public void countDistanceFromRestaurant(){ distanceFromRestaurant = (float)Math.sqrt(Math.pow((location[1] - 14),2) + Math.pow(location[0] - 14,2));
    System.out.println("Distance from rest: " + distanceFromRestaurant); }

    @Override
    public void introduction(){
        super.introduction();
        System.out.println("My number is: " + phoneNr);
        System.out.println("My email is: " + email);
    }

    @Override
    public String stringIntroduction(){
        if(clientOrder == null){
            return super.stringIntroduction() + "\n phone number: " + phoneNr + " email: " + email + "\n No Order";
        }else {
            return super.stringIntroduction() + "\n phone number: " + phoneNr + " email: " + email + "\nCustom Order: " + clientOrder.mealsOrderIntroduction()
                    + "\nMeal Deal Order: " + clientOrder.mealDealsOrderIntroduction() +"\nOrder is prepared: " + clientOrder.getIsPrepared() + " Is being served: " + clientOrder.getIsServing();
        }
    }

    public synchronized void orderRandomMeal() throws NoDataException{
            clientOrder = new Order(distanceFromRestaurant);
            OrdersDataBaseSingleton.getInstance().addClientWithOrder(this);
            System.out.println("Meals Ordered!");
    }

    public void pauseThread(){
        ifPause = true;
    }

    public void resumeThread(){
        synchronized (pauseLock){
            ifPause = false;
            pauseLock.notify();
        }
    }

    /**
     * at random order Meals
     * Thread waits for a delivery after order
     */
    @Override
    public void run() {
        Random random = new Random();
        int index = 0;
        while(true){
            try{
                synchronized (pauseLock) {
                    while (ifPause) {
                        pauseLock.wait();
                    }
                }
                sleep(random.nextInt(6) * 1000);
            }catch(Exception ex){
                System.out.println(ex);
            }
            if(getClientOrder() == null && !ifPause) {
                try {
                    orderRandomMeal();
                    System.out.println("Meal is ordered");
                }catch(NoDataException ex){
                    System.out.println("There is no data in menu to take order");
                    System.out.println(ex);
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }else if(!getClientOrder().getIsPrepared()){
                try {
                    sleep(clientOrder.getPreparationTime() * 1000);
                    clientOrder.setIsPrepared(true);
                    System.out.println("Meal is prepared");
                    pauseThread();
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
            System.out.println(index);
            index++;
        }
    }


}
