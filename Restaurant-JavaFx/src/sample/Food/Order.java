package sample.Food;

import sample.Exceptions.NoDataException;
import sample.MealsDataBaseSingleton;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Master Faster on 05.12.2016.
 * Clients take orders, which contains Meals and MealDeals
 */
public class Order implements Serializable{
    private List<Meal> mealsOrdered = new ArrayList<Meal>();
    private List<MealDeal> mealDealsOrdered = new ArrayList<MealDeal>();
    private float orderCost;    //orderCost contain deliveryCost
    private float deliveryCost;
    private int preparationTime;
    private long orderTime;    //System.currentTimeMillis()/1000
    private boolean isPrepared; // is set to true if preparationTime is ended
    private boolean isServing; // when deliverer drive to client with this order isServing is set on true
    private final float MAX_FREE_DISTANCE = 15; //when client is farther than MAX_FREE_DISTANCE delivery cost is added
    private final float DELIVERY_COST = 10;

    public Order(){
        setOrderTime(System.currentTimeMillis()/1000);
        setPreparationTime(0);
        setIsPrepared(true);
    }

    /**
     * all values at random
     * adds order costs
     * @param distanceFromRestaurant it's Client distance from restaurant, if>MAX_FREE_DISTANCE client has to pay for delivery
     * @throws NoDataException
     */
    public Order(float distanceFromRestaurant) throws NoDataException{
        orderCost = 0;
        Random random = new Random();
        //set random number of meals/mealDeals to order
        for(int i=0; i<=random.nextInt(5); i++) {
            if (random.nextInt(10) > 2) {
                //order regular meal
                //get Menu (MealDataBase) from MeasDataBaseSingleton and get random meal
                List <Meal> mealsInSingleton = MealsDataBaseSingleton.getInstance().getMealDataBase();
                if(mealsInSingleton.size() == 0) throw new NoDataException();
                mealsOrdered.add(mealsInSingleton.get(random.nextInt(mealsInSingleton.size())));
                orderCost += mealsOrdered.get(mealsOrdered.size()-1).getMealPrice();
            } else {
                //order mealDeal
                mealDealsOrdered.add(new MealDeal(new Random()));
                orderCost += mealDealsOrdered.get(mealDealsOrdered.size()-1).getDiscountCost();
            }
        }
        if(distanceFromRestaurant>MAX_FREE_DISTANCE){
            orderCost += DELIVERY_COST;
        }
        preparationTime = random.nextInt(4);
        orderTime = System.currentTimeMillis()/1000;
        isPrepared = false;
        isServing = false;
    }

    public List<Meal> getMealsOrdered() {
        return mealsOrdered;
    }

    public void setMealsOrdered(List<Meal> mealsOrdered) {
        this.mealsOrdered = mealsOrdered;
    }

    public void addMeal(Meal meal){
        this.mealsOrdered.add(meal);
        orderCost+=meal.getMealPrice();
    }

    public List<MealDeal> getMealDealsOrdered() {
        return mealDealsOrdered;
    }

    public void setMealDealsOrdered(List<MealDeal> mealDealsOrdered) {
        mealDealsOrdered = mealDealsOrdered;
    }

    public void addMealDeal(MealDeal mealDeal){
        this.mealDealsOrdered.add(mealDeal);
        orderCost+=mealDeal.getDiscountCost();
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

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public synchronized boolean getIsPrepared(){ return isPrepared; }

    public synchronized void setIsPrepared(boolean isPrepared) { this.isPrepared = isPrepared; }

    public synchronized void setIsServing(boolean isServing){ this.isServing = isServing; }

    public synchronized boolean getIsServing(){ return isServing; }

    public String mealsOrderIntroduction(){
        String wholeOrder = "";
        for (Meal meal : mealsOrdered){
            wholeOrder+= meal.getMealName() + " ";
        }
        return wholeOrder;
    }

    public String mealDealsOrderIntroduction(){
        String wholeOrder = "";
        for (MealDeal mealDeal : mealDealsOrdered){
            wholeOrder += mealDeal.mealDealIntroduction();
        }
        return wholeOrder;
    }

}
