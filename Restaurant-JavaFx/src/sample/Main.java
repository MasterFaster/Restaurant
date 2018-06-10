package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        primaryStage.setTitle("Restaurant");
        Image icon = new Image("restaurantIcon.jpg");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 822, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
        ClientsDataBaseSingleton clientsDataBaseSingleton = ClientsDataBaseSingleton.getInstance();
        clientsDataBaseSingleton.stopThreads();
        DeliverersDataBaseSingleton deliverersDataBaseSingleton = DeliverersDataBaseSingleton.getInstance();
        deliverersDataBaseSingleton.stopThreads();
    }
}
