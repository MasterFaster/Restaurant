package sample;

import sample.People.Client;
import sample.People.Deliverer;
import sample.People.Human;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Master Faster on 28.12.2016.
 * Singleton to store deliverers
 */
public class DeliverersDataBaseSingleton implements Serializable{
    private final static DeliverersDataBaseSingleton instance = new DeliverersDataBaseSingleton();
    private List<Human> delivererDataBase = new ArrayList<Human>();
    private MapSingleton mapSingleton = MapSingleton.getInstance();


    public static DeliverersDataBaseSingleton getInstance(){
        return instance;
    }

    public List<Human> getDelivererDataBase(){
        return delivererDataBase;
    }

    public void addHuman(Human human){
        delivererDataBase.add(human);
    }

    public void show(){
        for (Human person : delivererDataBase){
            person.introduction();
        }
    }

    public void stopThreads(){
        for(Human person : delivererDataBase){
            person.stop();
        }
    }

    public void removeDelivererByIndex(int index){
        if(delivererDataBase.get(index).getLocation()[0] != 14 || delivererDataBase.get(index).getLocation()[1] !=14) {
            mapSingleton.setMapTile(delivererDataBase.get(index).getLocation(),0);
        }
        for(Human client : ((Deliverer)delivererDataBase.get(index)).getServingClients()){
            ((Client)client).getClientOrder().setIsServing(false);
        }
        delivererDataBase.get(index).stop();
        delivererDataBase.remove(index);
    }

    public void saveSingleton() throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("deliverersDataBase.ser")));

        for(Human person : delivererDataBase){
            ((Deliverer)person).setServingClients(null);
            ((Deliverer)person).setServingClients(new ArrayList<Human>());
        }

        out.writeObject(delivererDataBase);
        out.close();
    }

    public void loadSingleton() throws Exception{
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("deliverersDataBase.ser")));
        delivererDataBase = (List<Human>)in.readObject();
        in.close();
        for(Human person : delivererDataBase){
            person.start();
        }
    }

}
