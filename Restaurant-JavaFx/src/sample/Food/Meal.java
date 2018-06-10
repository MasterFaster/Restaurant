package sample.Food;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Master Faster on 04.12.2016.
 * Basic class. Necessary to order something.
 */
public class Meal implements Serializable {

    public enum MealType{
        SOUP, MAIN_MEAL, DRINK
    }
    private String mealName;
    private MealType mealType;
    //private float mealPrice;
    private Float mealPrice;
    private List<String> ingredients = new ArrayList<String>();

    /**
     * Meal constructor
     * we set all values at random
     */
    public Meal(){
        Random random = new Random();
        switch(random.nextInt(3)){
            case 0:
                this.mealName = GetRandomMeal.getRandomMainMealName();
                mealType = MealType.MAIN_MEAL;
                break;
            case 1:
                this.mealName = GetRandomMeal.getRandomSoupName();
                mealType = MealType.SOUP;
                break;
            case 2:
                this.mealName = GetRandomMeal.getRandomDrinkName();
                mealType = MealType.DRINK;
                break;
        }
        this.mealPrice = GetRandomMeal.getRandomPrice();
        this.ingredients = GetRandomMeal.getRandomIngeredients();
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public float getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(float price) {
        this.mealPrice = price;
    }

    public void setMealType(int index){ this.mealType = MealType.values()[index]; }

    public MealType getMealType(){ return mealType; }

    //getMealTypeIndex returns index od enum
    public int getMealTypeIndex(){ return mealType.ordinal(); }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String stringIntroduction(){
        String showIngredients = "";
        for(String ingr : ingredients){
            showIngredients += ingr + " ";
        }
        return mealName + "\n" + mealType + " price: " + mealPrice + "\nIngredients: " + showIngredients;
    }

    //size-- I have to think about that one

}
