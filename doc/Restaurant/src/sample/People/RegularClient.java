package sample.People;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class RegularClient extends Client {
    private int awardsCredits;
    private int regularDiscount;
    private int singleDiscount;

    public int getAwardsCredits() {
        return awardsCredits;
    }

    public void setAwardsCredits(int awardsCredits) {
        this.awardsCredits = awardsCredits;
    }

    public int getRegularDiscount() {
        return regularDiscount;
    }

    public void setRegularDiscount(int regularDiscount) {
        this.regularDiscount = regularDiscount;
    }

    public int getSingleDiscount() {
        return singleDiscount;
    }

    public void setSingleDiscount(int singleDiscount) {
        this.singleDiscount = singleDiscount;
    }
}
