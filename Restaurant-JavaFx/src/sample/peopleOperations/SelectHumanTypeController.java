package sample.peopleOperations;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.People.Human;
import sample.People.OccasionalClient;
import sample.RestaurantController;

/**
 * Created by Master Faster on 27.12.2016.
 * controller to SelectHumanType.fxml
 * user select radioButton, which client he wants to create
 */
public class SelectHumanTypeController {

    @FXML
    Button cancelAddHumanBtn;
    //user select what type of human he wants to create
    @FXML
    RadioButton occasionalClientRadioBtn;
    @FXML
    RadioButton regularClientRadioBtn;
    @FXML
    RadioButton companyClientRadioBtn;
    @FXML
    RadioButton delivererRadioBtn;

    /**
     * method to create new window and get Human information
     * window is closed, when user click add, cancel or "X"
     * @param fxmlAddress
     * @param windowTitle
     * @param windowHeight
     * @param windowWidth
     * @throws Exception
     */
    private void createAndShowWindow(String fxmlAddress, String windowTitle,String imgPath, int windowHeight, int windowWidth) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlAddress));
        Parent root = (Parent)fxmlLoader.load();
        //GetOccasionalClientInfoController controller = fxmlLoader.<GetOccasionalClientInfoController>getController();
        //controller.setValues(human, restaurantController);
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        Image icon = new Image(imgPath);
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, windowWidth, windowHeight));
        stage.showAndWait();
    }

    @FXML
    void addHuman() throws Exception{
        if(occasionalClientRadioBtn.isSelected()){
            createAndShowWindow("GetOccasionalClientInfo.fxml", "Creating Occasional Client","clientIcon.jpg", 300, 300);
        }else if(regularClientRadioBtn.isSelected()){
            createAndShowWindow("GetRegularClientInfo.fxml", "Creating Regular Client","clientIcon.jpg", 300, 300);
        }else if(companyClientRadioBtn.isSelected()){
            createAndShowWindow("GetCompanyClientInfo.fxml", "Creating Company Client","clientIcon.jpg", 300, 300);
        }else if(delivererRadioBtn.isSelected()){
            createAndShowWindow("GetDelivererInfo.fxml", "Creating Deliverer","delivererIcon.png", 300, 300);
        }


    }

    @FXML
    public void cancelAdding(){
        Stage stage = (Stage) cancelAddHumanBtn.getScene().getWindow();
        stage.close();
    }

}
