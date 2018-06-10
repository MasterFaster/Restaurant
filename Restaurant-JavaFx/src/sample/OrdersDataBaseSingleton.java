package sample;

import sample.People.Client;
import sample.People.Human;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master Faster on 01.01.2017.
 * Here are all clients, who are waiting for delivery
 * we remove clients from this Singleton, when delivery is done
 */
public class OrdersDataBaseSingleton {
    private final static OrdersDataBaseSingleton instance = new OrdersDataBaseSingleton();
    private List<Human> clientWithOrderDataBase = new ArrayList<Human>();

    public static OrdersDataBaseSingleton getInstance(){
        return instance;
    }

    public List<Human> getClientWithOrderDataBase(){
        return clientWithOrderDataBase;
    }

    public void addClientWithOrder(Human human){
        clientWithOrderDataBase.add(human);
    }

    //remove client from this list
    public void removeClient(Human client){
        for(int i=0;i<clientWithOrderDataBase.size();i++){
            if(client.equals(clientWithOrderDataBase.get(i))){
                //((Client)clientWithOrderDataBase.get(i)).getClientOrder().setIsServing(false);
                //((Client)clientWithOrderDataBase.get(i)).getClientOrder().setIsPrepared(false);
                ((Client)clientWithOrderDataBase.get(i)).setClientOrder(null);
                clientWithOrderDataBase.remove(i);
                break;
            }
        }
    }

}
