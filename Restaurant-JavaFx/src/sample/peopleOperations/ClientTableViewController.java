package sample.peopleOperations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.ClientsDataBaseSingleton;
import sample.DeliverersDataBaseSingleton;
import sample.People.Client;
import sample.People.Deliverer;
import sample.People.Human;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Master Faster on 22.01.2017.
 * controller to ClientTableView.fxml
 * we put clients to the table and display on the screen
 */
public class ClientTableViewController {

    @FXML
    TableView<Human> clientsTable;
    private ObservableList<Human> peopleDataBase;
    // private ObservableList<Human> clientDataBase = FXCollections.observableList(ClientsDataBaseSingleton.getInstance().getClientDataBase());



    public void setHumanDataBase(List<Human> dataBase){
        TableColumn firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setCellValueFactory( new PropertyValueFactory<Human, String>("firstName"));

        TableColumn secondNameColumn = new TableColumn("Second Name");
        secondNameColumn.setCellValueFactory( new PropertyValueFactory<Human, String>("secondName"));

        peopleDataBase = FXCollections.observableList(dataBase);
        clientsTable.setItems(peopleDataBase);
        if(peopleDataBase.size()>0) {
            if (Client.class.isInstance(peopleDataBase.get(0))) {
                TableColumn emailColumn = new TableColumn("Email");
                emailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
                clientsTable.getColumns().addAll(firstNameColumn, secondNameColumn, emailColumn);
            } else {
                TableColumn drivingLicenseColumn = new TableColumn("Driving License");
                drivingLicenseColumn.setCellValueFactory(new PropertyValueFactory<Deliverer, Deliverer.DrivingLicense>("drivingLicense"));
                clientsTable.getColumns().addAll(firstNameColumn, secondNameColumn,drivingLicenseColumn);
            }
        }
    }

    @FXML
    void removeClient(){
        if(peopleDataBase.size()>0 && clientsTable.getSelectionModel().getSelectedIndex()>-1) {
            if (Client.class.isInstance(peopleDataBase.get(0))) {
                ClientsDataBaseSingleton.getInstance().removeClientByIndex(clientsTable.getSelectionModel().getSelectedIndex());
            }else{
                DeliverersDataBaseSingleton.getInstance().removeDelivererByIndex(clientsTable.getSelectionModel().getSelectedIndex());
            }

        }
        clientsTable.getSelectionModel().clearSelection();
        clientsTable.refresh();
    }

    @FXML
    void cancel(){
        Stage stage = (Stage) clientsTable.getScene().getWindow();
        stage.close();
    }

}
