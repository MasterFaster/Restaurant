package sample.People;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class CompanyClient extends Client {

    private float[] forwardingLocation = new float[2];    // coordinates for forwarding address
    private String accountNr;
    private int regon;

    public CompanyClient(){
        this.forwardingLocation[0] = 0;
        this.forwardingLocation[1] = 0;

        this.accountNr = GetRandomHuman.getRandomAccountNr();
        this.regon = GetRandomHuman.getRandomRegon();
    }

    public float[] getForwardingAddress() {
        return forwardingLocation;
    }

    public void setFirstForwardingLocation(float forwardingLocation) { this.forwardingLocation[0] = forwardingLocation;    }

    public void setSecondForwardingLocation(float forwardingLocation) { this.forwardingLocation[1] = forwardingLocation;   }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public int getRegon() {
        return regon;
    }

    public void setRegon(int regon) {
        this.regon = regon;
    }
}
