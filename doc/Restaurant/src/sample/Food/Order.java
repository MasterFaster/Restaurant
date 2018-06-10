package sample.Food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class Order {
    private List<Meal> mealsOrdered = new ArrayList<Meal>();
    private List<MealDeal> MealDealsOrdered = new ArrayList<MealDeal>();
    private float orderCost;
    private float deliveryCost;
    private float preparationTime;
    private SimpleDateFormat orderTime;

    public List<Meal> getMealsOrdered() {
        return mealsOrdered;
    }

    public void setMealsOrdered(List<Meal> mealsOrdered) {
        this.mealsOrdered = mealsOrdered;
    }

    public List<MealDeal> getMealDealsOrdered() {
        return MealDealsOrdered;
    }

    public void setMealDealsOrdered(List<MealDeal> mealDealsOrdered) {
        MealDealsOrdered = mealDealsOrdered;
    }

    public float getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(float orderCost) {
        this.orderCost = orderCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public float getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(float preparationTime) {
        this.preparationTime = preparationTime;
    }

    public SimpleDateFormat getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(SimpleDateFormat orderTime) {
        this.orderTime = orderTime;
    }
}
