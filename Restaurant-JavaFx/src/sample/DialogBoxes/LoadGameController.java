package sample.DialogBoxes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.ClientsDataBaseSingleton;
import sample.DeliverersDataBaseSingleton;
import sample.MealsDataBaseSingleton;
import sample.OrdersDataBaseSingleton;

/**
 * Created by Master Faster on 22.01.2017.
 * Controller to LoadGame.fxml
 * User decide, whether he wants to load the game or not
 */
public class LoadGameController {

    @FXML
    Button yesButton;

    @FXML
    void closeWindow(){
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadGame(){
        try {
            MealsDataBaseSingleton.getInstance().loadSingleton();
            ClientsDataBaseSingleton.getInstance().loadSingleton();
            DeliverersDataBaseSingleton.getInstance().loadSingleton();
        }catch(Exception ex){
            System.out.println(ex);
        }
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }
}
