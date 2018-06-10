package sample.People;

import java.util.Random;

/**
 * Created by Master Faster on 08.12.2016.
 */
public class GetRandom {
    private static String[] firstName = {"Mateusz", "Roman", "Jerzy", "Dariusz", "Zuznna", "Magda", "Julia"};
    private static String[] secondName = {"Nowak", "Niemczyk", "Kowalski", "Urbaniak", "Domeracki", "Kubzdela", "Brzezinski"};

    public static String getRandomFirstName(){
        Random random = new Random();
        return firstName[random.nextInt(6)];
    }

    public static String getRandomSecondName(){
        Random random = new Random();
        return secondName[random.nextInt(6)];
    }


}
