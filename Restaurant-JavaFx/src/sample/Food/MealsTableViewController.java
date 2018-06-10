package sample.Food;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.MealsDataBaseSingleton;

import java.util.List;

/**
 * Created by Master Faster on 22.01.2017.
 * controller to MealsTableView.fxml
 * Displays all meals from Menu (MealsDataBaseSingleton)
 */
public class MealsTableViewController {
    @FXML
    TableView<Meal> mealsTable;
    private ObservableList<Meal> mealsDataBase = FXCollections.observableList(MealsDataBaseSingleton.getInstance().getMealDataBase());


    /**
     * set all tableView parameters (columns and values)
     */
    public void setMealDataBase() {
        TableColumn mealNameColumn = new TableColumn("Meal Name");
        mealNameColumn.setCellValueFactory(new PropertyValueFactory<Meal, String>("mealName"));

        TableColumn mealTypeColumn = new TableColumn("Meal Type");
        mealTypeColumn.setCellValueFactory(new PropertyValueFactory<Meal, Meal.MealType>("mealType"));

        TableColumn ingredientsColumn = new TableColumn("Ingredients");
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<Meal, List<String>>("ingredients"));

        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<Meal, Float>("mealPrice"));


        mealsTable.setItems(mealsDataBase);
        mealsTable.getColumns().addAll(mealNameColumn, mealTypeColumn,ingredientsColumn,priceColumn);

    }

    @FXML
    void removeMeal(){
        if(mealsDataBase.size()>0 && mealsTable.getSelectionModel().getSelectedIndex()>-1) {
                MealsDataBaseSingleton.getInstance().removeMealByIndex(mealsTable.getSelectionModel().getSelectedIndex());
        }
        mealsTable.getSelectionModel().clearSelection();
        mealsTable.refresh();
    }

    @FXML
    void cancel(){
        Stage stage = (Stage) mealsTable.getScene().getWindow();
        stage.close();
    }

}
