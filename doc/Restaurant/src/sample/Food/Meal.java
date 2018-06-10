package sample.Food;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Master Faster on 04.12.2016.
 */
public class Meal {

    private String mealName;
    private float price;
    private Set<String> ingredients = new HashSet<String>();

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
    //size-- I have to think about that one

}
