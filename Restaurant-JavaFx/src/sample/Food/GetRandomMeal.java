package sample.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Master Faster on 29.12.2016.
 * GetRandomMeal generates meal values at random
 */
public class GetRandomMeal {

    private static String[] country = {"Italian", "Swiss", "Chinese", "Russian", "Greek", "Norwegian", "Polish", "Spanish", "English"};
    private static String[] mainMealName = {"Pasta", "Chicken", "Pork", "Hamburger", "Lasagne", "Risotto", "Pizza"};
    private static String[] soupName = {"Borscht", "Callaloo", "Chlodnik", "Chowder", "Erwtensoep", "Fasolada", "Gazpacho"};
    private static String[] drinkName = {"Coca-Cola", "Sprite", "Fanta", "Frugo", "Nestea", "Pepsi", "Mountain Dew", "Tonic", "Beer", "Sunkist", "7Up", "Coffee", "Tea"};
    private static String[] ingredients = {"Meat", "Fish", "Cucumber", "Potatoes","Cheese","Butter", "Eggs", "Nuts", "Garlic", "Ginger", "Pepper","Sugar"};


    public static String getRandomMainMealName(){
        Random random = new Random();
        return country[random.nextInt(9)] + " " + mainMealName[random.nextInt(7)];
    }

    public static String getRandomSoupName(){
        Random random = new Random();
        return country[random.nextInt(9)] + " " + soupName[random.nextInt(7)];
    }

    public static String getRandomDrinkName(){
        Random random = new Random();
        return drinkName[random.nextInt(13)];
    }

    public static float getRandomPrice(){
        Random random = new Random();
        return random.nextInt(200);
    }

    public static List<String>getRandomIngeredients(){
        Random random = new Random();
        int number = random.nextInt(10);
        List<String> ingredientsList = new ArrayList<String>();
        ingredientsList.add(ingredients[number]);
        ingredientsList.add(ingredients[number+1]);
        ingredientsList.add(ingredients[number+2]);
        return ingredientsList;
    }
}
