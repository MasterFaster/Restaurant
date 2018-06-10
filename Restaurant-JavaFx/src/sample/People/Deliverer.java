package sample.People;

import sample.MapSingleton;
import sample.OrdersDataBaseSingleton;
import sample.Vehicles.Vehicle;

import java.util.*;

/**
 * Created by Master Faster on 05.12.2016.
 * Every deliverer is a new Thread
 * check if there is client, who is not being served at the moment
 * drive to the client
 */
public class Deliverer extends Human {

    public enum DrivingLicense{
        CAR, SCOOTER, CAR_SCOOTER
    }
    private String pesel;
    private DrivingLicense drivingLicense;
    private List<Human> servingClients;    //clients, who are being served by single deliverer
    private boolean forcedReturn;
    private int moneyFromOrders;
    private Vehicle vehicle;


    //deliverer constructor, all values are random created
    public Deliverer(){
        moneyFromOrders = 0;
        forcedReturn = false;
        this.pesel = GetRandomHuman.getRandomPesel();
        this.drivingLicense = DrivingLicense.values()[GetRandomHuman.getRandomDrivingLicense()];
        servingClients = new ArrayList<Human>();
    }

    public void setPesel(String pesel){ this.pesel = pesel; }

    public String getPesel(){ return pesel; }

    public void setDrivingLicense(int index){ this.drivingLicense = DrivingLicense.values()[index]; }

    public DrivingLicense getDrivingLicense(){ return drivingLicense; }

    public void setServingClients(List<Human> servingClients){ this.servingClients = servingClients; }

    public List<Human> getServingClients() { return servingClients; }

    public void setForcedReturn(boolean forcedReturn){ this.forcedReturn = forcedReturn; }

    public boolean getForcedReturn(){ return forcedReturn; }

    public void setVehicle(Vehicle vehicle){ this.vehicle = vehicle; }

    public Vehicle getVehicle(){ return vehicle; }

    @Override
    public String stringIntroduction(){
        return super.stringIntroduction() + "\nDriving License: " + drivingLicense + "\n" + vehicle.stringIntroduction();
    }

    private void move_up() throws Exception{
        if(MapSingleton.getInstance().getMap()[location[0]-1][location[1]] <= 1) {
            MapSingleton.getInstance().decreaseMapTile(location);
            location[0]--;
            MapSingleton.getInstance().increaseMapTile(location);
            vehicle.fuelDecrease();
            sleep(1000);
        }else{
            sleep(1000);
        }
    }

    private void move_left() throws Exception{
        if(MapSingleton.getInstance().getMap()[location[0]][location[1]-1] <=1) {
            MapSingleton.getInstance().decreaseMapTile(location);
            location[1]--;
            MapSingleton.getInstance().increaseMapTile(location);
            vehicle.fuelDecrease();
            sleep(1000);
        }else{
            sleep(1000);
        }
    }

    private void move_right() throws Exception{
        if(MapSingleton.getInstance().getMap()[location[0]][location[1]+1] <=1) {
            MapSingleton.getInstance().decreaseMapTile(location);
            location[1]++;
            MapSingleton.getInstance().increaseMapTile(location);
            vehicle.fuelDecrease();
            sleep(1000);
        }else{
            sleep(1000);
        }
    }

    private void move_down() throws Exception{
        if (MapSingleton.getInstance().getMap()[location[0]+1][location[1]] <=1 ) {
            MapSingleton.getInstance().decreaseMapTile(location);
            location[0]++;
            MapSingleton.getInstance().increaseMapTile(location);
            vehicle.fuelDecrease();
            sleep(1000);
        }else {
            sleep(1000);
        }
    }

    private void moveToClient(Human client) throws Exception{
        if(servingClients.get(0).getLocation()[1] == location[1]-1){
            while(location[0] != client.getLocation()[0] && !forcedReturn){  //ruch do góry mapy
                move_up();
            }
        }else{
            if(location[0]!=14){
                while(location[0]<14 && !forcedReturn){
                    move_down();
                }
            }else {
                while (location[1]-1 != client.getLocation()[1] && !forcedReturn) {  //ruch w lewo mapy
                    move_left();
                }
            }
        }
    }

    /**
     * movement to the client
     * count how much money he has to take from regular client or
     * just take money if client isn't regular client
     * @throws Exception
     */
    private void serveClient() throws Exception{
        Collections.sort(servingClients, new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                if(((Client)o1).getViabilityDeliveryOrder() >  ((Client)o2).getViabilityDeliveryOrder()) return -1;
                if(((Client)o1).getViabilityDeliveryOrder() == ((Client)o2).getViabilityDeliveryOrder()) return 0;
                return 1;
            }
        });
        location[0] = 14;
        location[1] = 13;
        if(MapSingleton.getInstance().getMap()[location[0]][location[1]] <=1 ) {
            MapSingleton.getInstance().increaseMapTile(location);
        }else{
            sleep(1000);
        }
        while(servingClients.size()>0){
            System.out.println("SERVING CLIENTS: " +servingClients.size());
            while(!(location[0] == servingClients.get(0).getLocation()[0] && location[1]-1 == servingClients.get(0).getLocation()[1])) {
                moveToClient(servingClients.get(0));
                if(forcedReturn)
                    return;
            }
            //client from servingClients with index 0 was served
            if(servingClients.get(0) instanceof RegularClient){     //get money from client, if regular we have to update awards Credits and add credits and so on
                if(((RegularClient) servingClients.get(0)).getSingleDiscount() < ((RegularClient) servingClients.get(0)).getClientOrder().getOrderCost()) {
                    int moneyFromRegularClient = 0;
                    moneyFromRegularClient+=((RegularClient) servingClients.get(0)).getClientOrder().getOrderCost() * ((RegularClient) servingClients.get(0)).getRegularDiscount();
                    moneyFromRegularClient-=((RegularClient) servingClients.get(0)).getSingleDiscount();
                    moneyFromOrders += moneyFromRegularClient;
                    ((RegularClient) servingClients.get(0)).setSingleDiscount(0);   //we set single discount for 0, because his discount was used, if was bigger than 0
                    if(((RegularClient) servingClients.get(0)).getAwardsCredits() >= ((RegularClient) servingClients.get(0)).getCREDITS_REQUIRED()){
                        ((RegularClient) servingClients.get(0)).setSingleDiscount(((RegularClient) servingClients.get(0)).getDISCOUNT_FOR_CREDITS());
                        ((RegularClient) servingClients.get(0)).reduceAwardsCredits(((RegularClient) servingClients.get(0)).getCREDITS_REQUIRED());
                    }
                    ((RegularClient) servingClients.get(0)).addAwardsCredits(20);
                }
            }else{
                moneyFromOrders+=((Client)servingClients.get(0)).getClientOrder().getOrderCost();
            }
            OrdersDataBaseSingleton.getInstance().removeClient(servingClients.get(0));
            ((Client)servingClients.get(0)).resumeThread();
            servingClients.remove(0);
        }


    }

    private void moveBackToRestaurant() throws Exception{
        while(location[0]<14){
            move_down();
        }
        while(location[1]<13){
            move_right();
        }
        MapSingleton.getInstance().decreaseMapTile(location);
        location[1]++;
        vehicle.setFuelLeft(vehicle.getTankCapacity());
    }

    /**
     * check if there is client, who is not being served at the moment
     */
    @Override
    public void run() {
        //OrdersDataBaseSingleton ordersDataBaseSingleton = OrdersDataBaseSingleton.getInstance();
        //List<Human> clientWithOrderDataBase = ordersDataBaseSingleton.getClientWithOrderDataBase();
        while(true){
            System.out.println("checking for not served clients");
            try{
                sleep(2000);
            }catch(Exception ex){
                System.out.println(ex);
            }
            for(Human person : OrdersDataBaseSingleton.getInstance().getClientWithOrderDataBase()){
                if(((Client)person).getClientOrder().getIsPrepared() && !((Client)person).getClientOrder().getIsServing()){     //we check if order for client is prepared and if some other deliverer is serving this client
                    ((Client) person).getClientOrder().setIsServing(true);
                    servingClients.add(person);
                }
            }
            try {
                if(servingClients.size()>0) {
                    serveClient();
                }
                if(forcedReturn){
                    for(int i=0;i<servingClients.size();i++){
                        ((Client)servingClients.get(i)).getClientOrder().setIsServing(false);      // we set all his client as not being served
                        servingClients.remove(i);
                        forcedReturn = false;   // deliverer will return to restaurant and then he will look for next possible delivery
                    }
                }
                if(location[0] != 14 || location[1] != 14)
                    moveBackToRestaurant();
                MapSingleton.getInstance().addCashInRestaurant(moneyFromOrders);
                moneyFromOrders = 0;
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
}
