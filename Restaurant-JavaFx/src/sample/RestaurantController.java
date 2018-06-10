package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Food.MealsTableViewController;
import sample.Food.TakeOrderController;
import sample.Map.MapDisplay;
import sample.peopleOperations.ClientTableViewController;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller to Restaurant.fxml
 * Main class.
 * Displays map on the screen.
 * Contains button to addClient, addDeliverer etc.
 */
public class RestaurantController implements Initializable {

    //private Set<Human> clientDataBase = new HashSet<Human>();
    //private List<Human> clientDataBase = new ArrayList<Human>();
    @FXML
    Canvas mapCanvas;
    @FXML
    ImageView clientImageView;
    @FXML
    ImageView delivererImageView;
    @FXML
    ImageView moneyImageView;
    @FXML
    Text clientCountText;
    @FXML
    Text delivererCountText;
    @FXML
    Text moneyCountText;
    private MapDisplay mapDisplay;
    private boolean loaded = false;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxes/LoadGame.fxml"));
        try{
            Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Loading Game");
        //Image icon = new Image("meal.png");
        //stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 450, 90));
        stage.showAndWait();
        }catch(Exception ex){
            System.out.println(ex);
        }
        mapDisplay = new MapDisplay(mapCanvas, clientImageView, delivererImageView, moneyImageView, clientCountText,delivererCountText,moneyCountText);
    }
    /**
     * addClient is method for "Add client" button
     * It creates new window to get random or type values for new client
     * @throws Exception
    */
    @FXML
    void addHuman() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("peopleOperations/SelectHumanType.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Selecting Human Type");
        Image icon = new Image("selectHumanTypeIcon.jpg");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    @FXML
    void addMeal() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Food/GetMealInfo.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Creating new Meal");
        Image icon = new Image("meal.png");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    /**
     * clientsIntroduction is method to see all clients in ClientDataBase
     */
    @FXML
    void takeOrder() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Food/TakeOrder.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        TakeOrderController controller = fxmlLoader.<TakeOrderController>getController();
        controller.setMealDataBase();
        Stage stage = new Stage();
        stage.setTitle("Taking Order");
        Image icon = new Image("meal.png");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 600, 480));
        stage.showAndWait();
    }


    @FXML
    void clientsIntroductionWindow() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("peopleOperations/ClientTableView.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        ClientTableViewController controller = fxmlLoader.<ClientTableViewController>getController();
        controller.setHumanDataBase(ClientsDataBaseSingleton.getInstance().getClientDataBase());
        Stage stage = new Stage();
        stage.setTitle("Clients in dataBase");
        Image icon = new Image("clientIcon.jpg");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    @FXML
    void showClientsWithOrder() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("peopleOperations/ClientTableView.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        ClientTableViewController controller = fxmlLoader.<ClientTableViewController>getController();
        controller.setHumanDataBase(OrdersDataBaseSingleton.getInstance().getClientWithOrderDataBase());
        Stage stage = new Stage();
        stage.setTitle("Clients in dataBase");
        Image icon = new Image("clientIcon.jpg");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    @FXML
    void deliverersIntroductionWindow() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("peopleOperations/ClientTableView.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        ClientTableViewController controller = fxmlLoader.<ClientTableViewController>getController();
        controller.setHumanDataBase(DeliverersDataBaseSingleton.getInstance().getDelivererDataBase());
        Stage stage = new Stage();
        stage.setTitle("Clients in dataBase");
        Image icon = new Image("clientIcon.jpg");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    @FXML
    void mealsIntroductionWindow() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Food/MealsTableView.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        MealsTableViewController controller = fxmlLoader.<MealsTableViewController>getController();
        controller.setMealDataBase();
        Stage stage = new Stage();
        stage.setTitle("Meals in dataBase");
        Image icon = new Image("meal.png");
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    @FXML
    void save(){
        try {
            ClientsDataBaseSingleton.getInstance().saveSingleton();
            DeliverersDataBaseSingleton.getInstance().saveSingleton();
            MealsDataBaseSingleton.getInstance().saveSingleton();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }



}
