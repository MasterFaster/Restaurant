package sample.Food;

import sample.Food.Drink;
import sample.Food.MainMeal;
import sample.Food.Soup;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class MealDeal {
    private Soup soup;
    private MainMeal mainMeal;
    private Drink drink;
    private float regularCost;
    private float discountCost;

    public Soup getSoup() {
        return soup;
    }

    public void setSoup(Soup soup) {
        this.soup = soup;
    }

    public MainMeal getMainMeal() {
        return mainMeal;
    }

    public void setMainMeal(MainMeal mainMeal) {
        this.mainMeal = mainMeal;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public float getRegularCost() {
        return regularCost;
    }

    public void setRegularCost(float regularCost) {
        this.regularCost = regularCost;
    }

    public float getDiscountCost() {
        return discountCost;
    }

    public void setDiscountCost(float discountCost) {
        this.discountCost = discountCost;
    }
}
