package sample.People;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class CompanyClient extends Client {

    private float forwardingAddress;    // coordinates for forwarding address
    private int accountNr;
    private int regon;

    public float getForwardingAddress() {
        return forwardingAddress;
    }

    public void setForwardingAddress(float forwardingAddress) {
        this.forwardingAddress = forwardingAddress;
    }

    public int getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(int accountNr) {
        this.accountNr = accountNr;
    }

    public int getRegon() {
        return regon;
    }

    public void setRegon(int regon) {
        this.regon = regon;
    }
}
