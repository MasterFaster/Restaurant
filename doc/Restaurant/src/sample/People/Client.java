package sample.People;

import sample.Food.Order;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class Client extends Human {

    private int Code;
    private int phoneNr;
    private String email;
    private Order clientOrder = new Order();

    public Client(){

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

    public Order getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(Order clientOrder) {
        this.clientOrder = clientOrder;
    }
}
