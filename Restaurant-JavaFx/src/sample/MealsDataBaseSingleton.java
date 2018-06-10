package sample;

import sample.Food.Meal;
import sample.People.Human;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master Faster on 29.12.2016.
 * It's Menu in the restaurant
 * Singleton to store Meals
 */
public class MealsDataBaseSingleton {

    private final static MealsDataBaseSingleton instance = new MealsDataBaseSingleton();
    private List<Meal> mealDataBase = new ArrayList<Meal>();

    private List<Meal> mainMealDataBase = new ArrayList<Meal>();
    private List<Meal> soupDataBase = new ArrayList<Meal>();
    private List<Meal> drinkDataBase = new ArrayList<Meal>();

    public static MealsDataBaseSingleton getInstance(){
        return instance;
    }

    public List<Meal> getMealDataBase(){
        return mealDataBase;
    }

    public List<Meal> getMainMealDataBase(){
        return mainMealDataBase;
    }
    public List<Meal> getSoupDataBase(){
        return soupDataBase;
    }
    public List<Meal> getDrinkDataBase(){
        return drinkDataBase;
    }

    public void addMeal(Meal meal){
        mealDataBase.add(meal);
    }

    public void addMainMeal(Meal meal){
        mainMealDataBase.add(meal);
    }

    public void addSoup(Meal meal){
        soupDataBase.add(meal);
    }

    public void addDrink(Meal meal){
        drinkDataBase.add(meal);
    }

    public void removeMealByIndex(int index){
        mealDataBase.remove(index);
    }



    public void saveSingleton() throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("MealsDataBase.ser")));
        out.writeObject(mealDataBase);
        out.close();
    }


    public void loadSingleton() throws Exception{
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("MealsDataBase.ser")));
        mealDataBase = (List<Meal>)in.readObject();
        in.close();
        for(Meal meal : mealDataBase){
            switch(meal.getMealType()){
                case DRINK:
                    drinkDataBase.add(meal);
                    break;
                case MAIN_MEAL:
                    mainMealDataBase.add(meal);
                    break;
                case SOUP:
                    soupDataBase.add(meal);
                    break;
            }
        }
    }

}
