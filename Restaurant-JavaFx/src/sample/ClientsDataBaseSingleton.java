package sample;

import javafx.scene.control.Alert;
import sample.People.Client;
import sample.People.Human;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master Faster on 28.12.2016.
 * Singleton to store created clients
 */
public class ClientsDataBaseSingleton implements Serializable{

    private final static ClientsDataBaseSingleton instance = new ClientsDataBaseSingleton();
    private List<Human> clientDataBase = new ArrayList<Human>();
    private MapSingleton mapSingleton = MapSingleton.getInstance();
    private OrdersDataBaseSingleton ordersDataBaseSingleton = OrdersDataBaseSingleton.getInstance();

    public static ClientsDataBaseSingleton getInstance(){
        return instance;
    }

    public List<Human> getClientDataBase(){
        return clientDataBase;
    }

    public void addHuman(Client human){
        clientDataBase.add(human);
    }

    public void show(){
        for (Human person : clientDataBase){
            person.introduction();
        }
    }

    public void stopThreads(){
        for(int i=0;i<clientDataBase.size();i++){
            clientDataBase.get(i).stop();
        }
    }

    //if client is not being served we remove him
    public void removeClientByIndex(int index){
        if(((Client)clientDataBase.get(index)).getClientOrder() != null){
            if(((Client)clientDataBase.get(index)).getClientOrder().getIsServing()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong action!");
                alert.setHeaderText("Your action is not allowed");
                alert.setContentText("You can't remove client, while he is being served");
                alert.showAndWait();
                return;
            }
            ordersDataBaseSingleton.removeClient(clientDataBase.get(index));
        }
        clientDataBase.get(index).stop();
        mapSingleton.setMapTile(clientDataBase.get(index).getLocation(), 7);
        clientDataBase.remove(index);
    }

   public void saveSingleton() throws Exception{
       ObjectOutputStream out = new ObjectOutputStream(
               new BufferedOutputStream(
                       new FileOutputStream("clientsDataBase.ser")));

       for(Human person : clientDataBase){
           ((Client)person).getClientOrder().setIsServing(false);
       }

       out.writeObject(clientDataBase);
       out.close();
   }

   public void loadSingleton() throws Exception{
       ObjectInputStream in = new ObjectInputStream(
               new BufferedInputStream(
                       new FileInputStream("clientsDataBase.ser")));
       clientDataBase = (List<Human>)in.readObject();
       in.close();
       for(Human person : clientDataBase){
           mapSingleton.setMapTile(person.getLocation(), 6);
           if(((Client)person).getClientOrder() != null){
               OrdersDataBaseSingleton.getInstance().addClientWithOrder(person);
           }
           person.start();
       }
   }

}
