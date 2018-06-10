package sample.Map;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ClientsDataBaseSingleton;
import sample.DeliverersDataBaseSingleton;
import sample.People.Client;
import sample.People.Deliverer;
import sample.People.Human;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Master Faster on 13.01.2017.
 * controller to ShowClientMap.fxml
 * shows client/deliverer information
 */
public class ShowClientMapController implements Initializable{

    @FXML
    Text clientIntroductionText;
    private Human human;
    private int index;
    private boolean ifUpdate;   // set on true if clientDataBase was changed and map should be updated
    @FXML
    Button returnButton;
    @FXML
    ProgressBar fuelProgressBar;
    @FXML
    Button refreshButton;
    @FXML
    Label fuelLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        ifUpdate = false;
    }

    /**
     * if human is client, we remove returnButton, fuelProgressBar, refreshButton, fuelLabel
     * @param human human, to show his info
     * @param index humans index from dataBase
     */
    public void setClient(Human human, int index){
        this.human = human;
        this.index = index;
        clientIntroductionText.setText(human.stringIntroduction());
        if(Client.class.isInstance(human)) {
            returnButton.setVisible(false);
            fuelProgressBar.setVisible(false);
            refreshButton.setVisible(false);
            fuelLabel.setVisible(false);
        }else{
            fuelProgressBar.setProgress( (((Deliverer)human).getVehicle().getTankCapacity() - ( ((Deliverer)human).getVehicle().getTankCapacity() - ((Deliverer)human).getVehicle().getFuelLeft())) / ((Deliverer)human).getVehicle().getTankCapacity());
        }

        //clientIntroductionTextArea.setEditable(false);
    }

    @FXML
    void cancelClientInfoView(){
        Stage stage = (Stage) clientIntroductionText.getScene().getWindow();
        stage.close();
    }

    @FXML
    void removeClient(){
        if(Client.class.isInstance(human)) {
            ClientsDataBaseSingleton.getInstance().removeClientByIndex(index);
        }else{
            DeliverersDataBaseSingleton.getInstance().removeDelivererByIndex(index);
        }
        ifUpdate = true;
        Stage stage = (Stage) clientIntroductionText.getScene().getWindow();
        stage.close();
    }

    @FXML
    void returnDeliverer(){
        ((Deliverer)human).setForcedReturn(true);
    }

    @FXML
    void refresh(){
        fuelProgressBar.setProgress( (((Deliverer)human).getVehicle().getTankCapacity() - ( ((Deliverer)human).getVehicle().getTankCapacity() - ((Deliverer)human).getVehicle().getFuelLeft())) / ((Deliverer)human).getVehicle().getTankCapacity());
        clientIntroductionText.setText(human.stringIntroduction());
    }

    public boolean getIfUpdate(){
        return ifUpdate;
    }
}
