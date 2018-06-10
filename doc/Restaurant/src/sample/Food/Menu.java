package sample.Food;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Master Faster on 05.12.2016.
 */
public class Menu {
    private Set<Drink> drinksDataBase = new HashSet<Drink>();
    private Set<MainMeal> mainMealsDataBase = new HashSet<MainMeal>();
    private Set<Soup> soupsDataBase = new HashSet<Soup>();

    public Set<Drink> getDrinksDataBase() {
        return drinksDataBase;
    }

    public void setDrinksDataBase(Set<Drink> drinksDataBase) {
        this.drinksDataBase = drinksDataBase;
    }

    public Set<MainMeal> getMainMealsDataBase() {
        return mainMealsDataBase;
    }

    public void setMainMealsDataBase(Set<MainMeal> mainMealsDataBase) {
        this.mainMealsDataBase = mainMealsDataBase;
    }

    public Set<Soup> getSoupsDataBase() {
        return soupsDataBase;
    }

    public void setSoupsDataBase(Set<Soup> soupsDataBase) {
        this.soupsDataBase = soupsDataBase;
    }
}
