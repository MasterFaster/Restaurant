package sample.Food;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DialogBoxes.AlertWindow;
import sample.ClientsDataBaseSingleton;
import sample.MealsDataBaseSingleton;
import sample.OrdersDataBaseSingleton;
import sample.People.Client;
import sample.People.Human;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master Faster on 22.01.2017.
 * controller to TakeOrder.fxml
 * user creates order on his own
 */
public class TakeOrderController {
    @FXML
    TableView<Meal> mealsTable;
    @FXML
    TableView<Meal> mainMealTable;
    @FXML
    TableView<Meal> soupTable;
    @FXML
    TableView<Meal> drinkTable;
    @FXML
    TableView<Human> clientsWithoutOrderTable;
    @FXML
    TextArea orderIntroductionTextArea;
    private List<Human> clientsDatabase = ClientsDataBaseSingleton.getInstance().getClientDataBase();
    private List<Human> clientsWithoutOrderDataBase = new ArrayList<Human>();
    private TableColumn mealNameColumn;
    private TableColumn mealTypeColumn;
    private TableColumn ingredientsColumn;
    private TableColumn priceColumn;
    private ObservableList<Meal> mealsDataBase = FXCollections.observableList(MealsDataBaseSingleton.getInstance().getMealDataBase());
    private ObservableList<Meal> mainMealDataBase = FXCollections.observableList(MealsDataBaseSingleton.getInstance().getMainMealDataBase());
    private ObservableList<Meal> soupDataBase = FXCollections.observableList(MealsDataBaseSingleton.getInstance().getSoupDataBase());
    private ObservableList<Meal> drinkDataBase = FXCollections.observableList(MealsDataBaseSingleton.getInstance().getDrinkDataBase());
    private Order order;

    //create cells is also to refresh cells, because of reusing this cells
    private void createMealCells(){
        mealNameColumn = new TableColumn("Meal Name");
        mealNameColumn.setCellValueFactory(new PropertyValueFactory<Meal, String>("mealName"));

        mealTypeColumn = new TableColumn("Meal Type");
        mealTypeColumn.setCellValueFactory(new PropertyValueFactory<Meal, Meal.MealType>("mealType"));

        ingredientsColumn = new TableColumn("Ingredients");
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<Meal, List<String>>("ingredients"));

        priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<Meal, Float>("mealPrice"));
    }

    /**
     * we pause all clients without orders, because they can't take order during the user creates his own order
     * we add all users without order to clientsWithoutOrderDataBase and set tableViews for meals and clients
     */
    public void setMealDataBase() {

        for(Human person : clientsDatabase){
            if(((Client)person).getClientOrder() == null){
                ((Client)person).pauseThread();
                clientsWithoutOrderDataBase.add(person);
            }
        }

        orderIntroductionTextArea.setEditable(false);

        order = new Order();

        createMealCells();
        mealsTable.getColumns().addAll(mealNameColumn, mealTypeColumn,ingredientsColumn,priceColumn);
        mealsTable.setItems(mealsDataBase);

        createMealCells();
        mainMealTable.getColumns().addAll(mealNameColumn, mealTypeColumn,ingredientsColumn,priceColumn);
        mainMealTable.setItems(mainMealDataBase);

        createMealCells();
        soupTable.getColumns().addAll(mealNameColumn, mealTypeColumn,ingredientsColumn,priceColumn);
        soupTable.setItems(soupDataBase);

        createMealCells();
        drinkTable.getColumns().addAll(mealNameColumn, mealTypeColumn,ingredientsColumn,priceColumn);
        drinkTable.setItems(drinkDataBase);

        TableColumn firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setCellValueFactory( new PropertyValueFactory<Human, String>("firstName"));

        TableColumn secondNameColumn = new TableColumn("Second Name");
        secondNameColumn.setCellValueFactory( new PropertyValueFactory<Human, String>("secondName"));
        TableColumn emailColumn = new TableColumn("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));

        clientsWithoutOrderTable.getColumns().addAll(firstNameColumn, secondNameColumn, emailColumn);
        clientsWithoutOrderTable.setItems(FXCollections.observableList(clientsWithoutOrderDataBase));
    }

    @FXML
    void addMeal(){
        if(mealsDataBase.size()>0 && mealsTable.getSelectionModel().getSelectedIndex()>-1) {
            order.addMeal(mealsDataBase.get(mealsTable.getSelectionModel().getSelectedIndex()));
            orderIntroductionTextArea.appendText(mealsDataBase.get(mealsTable.getSelectionModel().getSelectedIndex()).getMealName()+"\n\n");
        }
        mealsTable.getSelectionModel().clearSelection();
    }

    @FXML
    void addMealDeal(){
        if(mainMealDataBase.size()>0 && soupDataBase.size()>0 && drinkDataBase.size()>0 && mainMealTable.getSelectionModel().getSelectedIndex()>-1 &&
                soupTable.getSelectionModel().getSelectedIndex()>-1 && drinkTable.getSelectionModel().getSelectedIndex()>-1) {

            MealDeal mealDeal = new MealDeal();
            mealDeal.setMainMeal(mainMealDataBase.get(mainMealTable.getSelectionModel().getSelectedIndex()));
            mealDeal.setSoup(soupDataBase.get(soupTable.getSelectionModel().getSelectedIndex()));
            mealDeal.setDrink(drinkDataBase.get(drinkTable.getSelectionModel().getSelectedIndex()));
            mealDeal.countCost();
            order.addMealDeal(mealDeal);
            orderIntroductionTextArea.appendText("Meal deal: "+mealDeal.mealDealIntroduction()+"\n\n");
        }else{
            AlertWindow.showAlertWindow("Wrong action!", "Your action is not allowed", "You can't add mealDeal without choosing main meal, soup and drink");
        }
        mealsTable.getSelectionModel().clearSelection();
    }

    /**
     * shows AlertWindow if client isn't chosen
     * add order to the client and add client to OrdersDataBaseSingleton
     */
    @FXML
    void takeOrder(){
        if(clientsWithoutOrderDataBase.size()>0 && clientsWithoutOrderTable.getSelectionModel().getSelectedIndex()>-1) {
            ((Client)clientsWithoutOrderDataBase.get(clientsWithoutOrderTable.getSelectionModel().getSelectedIndex())).setClientOrder(order);
            OrdersDataBaseSingleton.getInstance().addClientWithOrder(clientsWithoutOrderDataBase.get(clientsWithoutOrderTable.getSelectionModel().getSelectedIndex()));
        }else{
            AlertWindow.showAlertWindow("Wrong action!", "Your action is not allowed", "You can't take order without choosing client");
            return;
        }
        for(int i=0;i<clientsWithoutOrderDataBase.size();i++){
            if(i != clientsWithoutOrderTable.getSelectionModel().getSelectedIndex()){
                ((Client)clientsWithoutOrderDataBase.get(i)).resumeThread();
            }
        }
        Stage stage = (Stage) orderIntroductionTextArea.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancel(){
        for(Human person : clientsWithoutOrderDataBase){
            ((Client)person).resumeThread();
        }
        Stage stage = (Stage) orderIntroductionTextArea.getScene().getWindow();
        stage.close();
    }


}
