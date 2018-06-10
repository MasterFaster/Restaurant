package sample.Food;

import sample.Exceptions.NoDataException;
import sample.MealsDataBaseSingleton;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by Master Faster on 05.12.2016.
 * User can match mainMeal, soup and drink into MealDeal, which is cheaper
 */
public class MealDeal implements Serializable {
    private Meal mainMeal;
    private Meal soup;
    private Meal drink;
    private float regularCost;
    private float discountCost;
    private final float DISCOUNT = 0.2f;    //percentage of discount



    public MealDeal(){
        regularCost = 0;
        discountCost = 0;
    }

    /**
     * we set all values at random
     * @param random
     * @throws NoDataException when there is no mainMeal, drink or soup in Menu (MealsDataBaseSingleton)
     */
    public MealDeal(Random random) throws NoDataException{
        //order regular meal
        //get Menu (MealDataBase) from MeasDataBaseSingleton and get random meal
        List<Meal> mainMealsInSingleton = MealsDataBaseSingleton.getInstance().getMainMealDataBase();
        List<Meal> soupsInSingleton = MealsDataBaseSingleton.getInstance().getSoupDataBase();
        List<Meal> drinksInSingleton = MealsDataBaseSingleton.getInstance().getDrinkDataBase();

        int mainMealDataBaseSize = mainMealsInSingleton.size();
        int soupDataBaseSize = soupsInSingleton.size();
        int drinkDataBaseSize = drinksInSingleton.size();

        if(mainMealDataBaseSize == 0 || soupDataBaseSize ==0 || drinkDataBaseSize == 0){
            throw new NoDataException("There is no main meal, soup or drink in data base and MealDeal can't be ordered");
        }else {
            regularCost = 0;
            mainMeal = mainMealsInSingleton.get(random.nextInt(mainMealDataBaseSize));
            regularCost += mainMeal.getMealPrice();
            soup = soupsInSingleton.get(random.nextInt(soupDataBaseSize));
            regularCost += soup.getMealPrice();
            drink = drinksInSingleton.get(random.nextInt(drinkDataBaseSize));
            regularCost += drink.getMealPrice();
            discountCost = regularCost * (1-DISCOUNT);
        }
    }

    public void setMainMeal(Meal mainMeal){ this.mainMeal = mainMeal; }

    public Meal getMainMeal(){ return mainMeal; }

    public void setSoup(Meal soup){ this.soup = soup; }

    public Meal getSoup(){ return soup; }

    public void setDrink(Meal drink){ this.drink = drink; }

    public Meal detDrink(){ return drink; }

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

    public String mealDealIntroduction(){ return mainMeal.getMealName() + " " + soup.getMealName() + " " + drink.getMealName() + " "; }

    public void countCost(){
        regularCost += mainMeal.getMealPrice();
        regularCost += drink.getMealPrice();
        regularCost += soup.getMealPrice();
        discountCost = regularCost * (1-DISCOUNT);
    }
}
