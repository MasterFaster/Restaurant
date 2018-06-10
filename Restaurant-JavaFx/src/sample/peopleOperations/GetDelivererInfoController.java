package sample.peopleOperations;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DeliverersDataBaseSingleton;
import sample.People.Deliverer;
import sample.Vehicles.Car;
import sample.Vehicles.Scooter;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Master Faster on 28.12.2016.
 * controller to GetDelivererInfo.fxml
 * we get values from user to create new deliverer
 */
public class GetDelivererInfoController implements Initializable{

    @FXML
    private ListView<String> drivingLicenseList;
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField secondNameField;
    @FXML
    public TextField peselField;
    private Deliverer deliverer;

    /**
     * we set values fo all TextFields for random Deliverer values
     * we set all TextFields focus on false
     * @param url
     * @param resource
     */
    @Override
    public void initialize(URL url, ResourceBundle resource){
        deliverer = new Deliverer();
        ObservableList<String> data = FXCollections.observableArrayList("CAR", "SCOOTER", "CAR & SCOOTER");
        drivingLicenseList.setItems(data);

        firstNameField.setPromptText(deliverer.getFirstName());
        secondNameField.setPromptText(deliverer.getSecondName());
        peselField.setPromptText(deliverer.getPesel());


        //setFocusTraversable to see primary values
        firstNameField.setFocusTraversable(false);
        secondNameField.setFocusTraversable(false);
        peselField.setFocusTraversable(false);

        drivingLicenseList.getSelectionModel().select(0);
    }

    /**
     * addDeliverer is method when "Add" button is clicked
     * if TextFields were changed we change clients values
     */
    public void addDeliverer(){
        if(!firstNameField.getText().equals("")){
            deliverer.setFirstName(firstNameField.getText());
        }
        if(!secondNameField.getText().equals("")){
            deliverer.setSecondName(secondNameField.getText());
        }
        if(!peselField.getText().equals("")){
            deliverer.setPesel(peselField.getText());
        }

        deliverer.setDrivingLicense(drivingLicenseList.getSelectionModel().getSelectedIndex());
        switch(drivingLicenseList.getSelectionModel().getSelectedIndex()){
            case 0:
                deliverer.setVehicle(new Car());
                break;
            case 1:
                deliverer.setVehicle(new Scooter());
                break;
            case 2:
                Random random = new Random();
                if(random.nextInt(2) == 0){
                    deliverer.setVehicle(new Car());
                }else{
                    deliverer.setVehicle(new Scooter());
                }
                break;
        }
        int[] location = {14,14};
        deliverer.setLocation(location);
        deliverer.start();
        DeliverersDataBaseSingleton deliverersDataBaseSingleton = DeliverersDataBaseSingleton.getInstance();
        deliverersDataBaseSingleton.addHuman(deliverer);


        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    public void cancelAddingDeliverer(){
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
