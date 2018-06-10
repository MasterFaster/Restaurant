package sample.Food;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.MealsDataBaseSingleton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Master Faster on 29.12.2016.
 * controller to GetMealInfo.fxml
 * User creates new meal to add to the MealsDataBaseSingleton
 */
public class GetMealInfoController implements Initializable{

    @FXML
    TextField mealNameField;
    @FXML
    ListView<String> mealTypeList;
    @FXML
    TextField mealPriceField;

    //  ingredientTextField to add new ingredient to the list
    @FXML
    TextField ingredientTextField;

    // ingredientsTextArea to show random or chosen ingredients
    @FXML
    TextArea ingredientsTextArea;
    @FXML
    Button addBtn;
    @FXML
    Button cancelBtn;
    private List<String> ingredients;
    private Meal meal;

    /**
     * Initialize window with meal parameters
     * All meal attributes are random set
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        meal = new Meal();
        mealNameField.setPromptText(meal.getMealName());
        mealPriceField.setPromptText(String.valueOf(meal.getMealPrice()));
        mealNameField.setFocusTraversable(false);
        mealPriceField.setFocusTraversable(false);

        //show random ingredients in Text area, which isn't editable
        ingredientsTextArea.setEditable(false);
        ingredients = meal.getIngredients();
        for(String ingr : ingredients){
            ingredientsTextArea.appendText(ingr + "\n" );
        }

        ObservableList<String> mealTypeData = FXCollections.observableArrayList("SOUP", "MAIN_MEAL", "Drink");
        mealTypeList.setItems(mealTypeData);
        mealTypeList.getSelectionModel().select(meal.getMealTypeIndex());
    }

    @FXML
    void addIngredient(){
        if(!ingredientTextField.equals("")){
            ingredientsTextArea.appendText(ingredientTextField.getText() + "\n");
            ingredients.add(ingredientTextField.getText());
        }
    }

    @FXML
    void removeIngredients(){
        ingredients.clear();
        ingredientsTextArea.setText("");
    }

    @FXML
    void addMeal(){
        if(!mealNameField.getText().equals("")){
            meal.setMealName(mealNameField.getText());
        }
        if(!mealPriceField.getText().equals("")){
            meal.setMealPrice(Integer.valueOf(mealPriceField.getText()));
        }
        meal.setMealType(mealTypeList.getSelectionModel().getSelectedIndex());

        MealsDataBaseSingleton mealDataBaseSingleton = MealsDataBaseSingleton.getInstance();
        mealDataBaseSingleton.addMeal(meal);

        switch(mealTypeList.getSelectionModel().getSelectedIndex()){
            case 0:
                mealDataBaseSingleton.addSoup(meal);
                break;
            case 1:
                mealDataBaseSingleton.addMainMeal(meal);
                break;
            case 2:
                mealDataBaseSingleton.addDrink(meal);
                break;
        }

        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();


    }

    @FXML
    void cancelAddingMeal(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
