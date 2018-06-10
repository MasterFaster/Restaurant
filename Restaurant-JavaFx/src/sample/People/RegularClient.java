package sample.People;

import java.io.Serializable;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class RegularClient extends Client {
    private int awardsCredits;
    private int regularDiscount;
    private int singleDiscount;
    private final int DISCOUNT_FOR_CREDITS = 100;
    private final int CREDITS_REQUIRED = 50;    // if client has 50 or more awardsCredits he gets 100 zl singleDiscount

    public RegularClient(){
        regularDiscount = GetRandomHuman.getRandomRegularDiscount();
        //singleDiscount = GetRandomHuman.getSingleDiscount();
        awardsCredits = GetRandomHuman.getAwardsCredits();
    }

    public int getAwardsCredits() {
        return awardsCredits;
    }

    public void setAwardsCredits(int awardsCredits) {
        this.awardsCredits = awardsCredits;
    }

    public void addAwardsCredits(int credits){ this.awardsCredits+=credits; }

    public void reduceAwardsCredits(int credits){ this.awardsCredits-=credits; }

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


    public int getDISCOUNT_FOR_CREDITS() {
        return DISCOUNT_FOR_CREDITS;
    }

    public int getCREDITS_REQUIRED() {
        return CREDITS_REQUIRED;
    }
}
