package sample.peopleOperations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ClientsDataBaseSingleton;
import sample.MapSingleton;
import sample.People.OccasionalClient;
import sample.People.RegularClient;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Master Faster on 28.12.2016.
 * controller to GetRegularClientInfo.fxml
 * we get values from user to create new client
 */
public class GetRegularClientInfoController implements Initializable{

    @FXML
    TextField firstNameField;
    @FXML
    TextField secondNameField;

    @FXML
    TextField codeField;
    @FXML
    TextField phoneNrField;
    @FXML
    TextField emailField;
    @FXML
    TextField awardsCreditsField;
    @FXML
    TextField regularDiscountField;
    @FXML
    ListView possibleLocationsListView;


    private RegularClient client;

    private MapSingleton mapSingleton;
    private List<int[]> possibleClientLocations;    // we get possible locations to set client location from mapSingleton

    /**
     * we set values fo all TextFields for random Client values
     * we set all TextFields focus on false
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        client = new RegularClient();
        mapSingleton = MapSingleton.getInstance();
        possibleClientLocations = mapSingleton.getPossibleClientLocations();
        firstNameField.setPromptText(client.getFirstName());
        secondNameField.setPromptText(client.getSecondName());
        phoneNrField.setPromptText(String.valueOf(client.getPhoneNr()));
        emailField.setPromptText(client.getEmail());

        //list to set listView
        ObservableList<String> stringPossibleLocations = FXCollections.observableArrayList();
        for(int i=0;i<possibleClientLocations.size();i++){
            stringPossibleLocations.add(String.valueOf(possibleClientLocations.get(i)[0]) +" "+String.valueOf(possibleClientLocations.get(i)[1]));

        }
        possibleLocationsListView.setItems(stringPossibleLocations);
        possibleLocationsListView.getSelectionModel().select(0);

        codeField.setPromptText(String.valueOf(client.getCode()));
        awardsCreditsField.setPromptText(String.valueOf(client.getAwardsCredits()));
        regularDiscountField.setPromptText(String.valueOf(client.getRegularDiscount()));
        //setFocusTraversable to see primary values
        firstNameField.setFocusTraversable(false);
        secondNameField.setFocusTraversable(false);
        phoneNrField.setFocusTraversable(false);
        emailField.setFocusTraversable(false);
        codeField.setFocusTraversable(false);

        awardsCreditsField.setFocusTraversable(false);
        regularDiscountField.setFocusTraversable(false);
    }

    /**
     * addClient is method when "Add" button is clicked
     * if TextFields were changed we change clients values
     */
    public void addClient(){
        if(!firstNameField.getText().equals("")){
            client.setFirstName(firstNameField.getText());
        }
        if(!secondNameField.getText().equals("")){
            client.setSecondName(secondNameField.getText());
        }
        if(!codeField.getText().equals("")){
            client.setCode(Integer.valueOf(codeField.getText()));
        }
        if(!emailField.getText().equals("")){
            client.setEmail(emailField.getText());
        }
        if(!phoneNrField.getText().equals("")) {
            client.setPhoneNr(Integer.valueOf(phoneNrField.getText()));
        }
        if(!awardsCreditsField.getText().equals("")) {
            client.setAwardsCredits(Integer.valueOf(awardsCreditsField.getText()));
        }
        if(!regularDiscountField.getText().equals("")) {
            client.setRegularDiscount(Integer.valueOf(regularDiscountField.getText()));
        }
        //set client location field
        client.setLocation(possibleClientLocations.get(possibleLocationsListView.getSelectionModel().getSelectedIndex()));
        client.countViabilityDeliveryOrder();
        client.countDistanceFromRestaurant();
        ClientsDataBaseSingleton clientsDataBaseSingleton = ClientsDataBaseSingleton.getInstance();
        clientsDataBaseSingleton.addHuman(client);

        //set location on the map, so the map shows client house
        mapSingleton.setMapTile(possibleClientLocations.get(possibleLocationsListView.getSelectionModel().getSelectedIndex()), 6);
        ;
        
        //run company client thread
        client.start();
        //client.setDaemon(false);

        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    /**
     * cancelAddingClient is method to discard all changes
     * and return to RestaurantController/Restaurant.fxml window
     */
    public void cancelAddingClient(){
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
