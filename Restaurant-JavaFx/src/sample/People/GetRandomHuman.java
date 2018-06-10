package sample.People;

import sample.MapSingleton;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.String.valueOf;

/**
 * Created by Master Faster on 08.12.2016.
 * creates random values for Human
 */
public class GetRandomHuman {
    private static String[] firstName = {"Mateusz", "Roman", "Jerzy", "Dariusz", "Zuznna", "Magda", "Julia"};
    private static String[] secondName = {"Nowak", "Niemczyk", "Kowalski", "Urbaniak", "Domeracki", "Kubzdela", "Brzezinski"};

    public static String getRandomFirstName(){
        Random random = new Random();
        return firstName[random.nextInt(7)];
    }

    public static String getRandomSecondName(){
        Random random = new Random();
        return secondName[random.nextInt(7)];
    }

    public static int getRandomPhoneNr(){
        int number=0;
        Random random = new Random();
        for(int i=1;i<1000000000;i*=10) {
            number += random.nextInt(10) * i;
        }
        return number;
    }

    public static int getRandomRegularDiscount(){
            Random random = new Random();
            return random.nextInt(30);
    }

    public static int getSingleDiscount(){
        Random random = new Random();
        return random.nextInt(30);
    }

    public static int getAwardsCredits(){
        Random random = new Random();
        return random.nextInt(100);
    }

    public static int getRandomRegon(){
        int regon=0;
        Random random = new Random();
        for(int i=1;i<1000000000;i*=10) {
            regon += random.nextInt(10) * i;
        }
        return regon;
    }

    public static String getRandomAccountNr(){
        String accountNr="";
        Random random = new Random();
        for(long i=1;i<27;i+=1) {
            accountNr += String.valueOf(random.nextInt(10));
        }
        return accountNr;
    }

    public static String getRandomPesel(){
        String pesel="";
        Random random = new Random();
        for(long i=1;i<12;i+=1) {
            pesel += String.valueOf(random.nextInt(10));
        }
        return pesel;
    }

    public static int getRandomDrivingLicense(){
        Random random = new Random();
        return random.nextInt(3);
    }


}
