package sample;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Food.Menu;
import sample.People.Client;
import sample.People.Deliverer;
import sample.People.Human;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    private Set<Human> clientDataBase = new HashSet<Human>();
    private Set<Deliverer> delivererDataBase = new HashSet<Deliverer>();
    Menu menu;
    Set<Client> witingClients = new HashSet<Client>();

    Stage window;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window = primaryStage;

        // Button to create new Client
        Button button1 = new Button("Add client");
        button1.setOnAction(e -> {
            try {
                clientDataBase.add(ControlPanel.addClient());
            }catch(MyException ex){
                ex.printStackTrace();
                System.out.println("Stupid bitch");
            }
        });

        //Button to get information about all people in ClientDataBase
        Button button2 = new Button("Introduction");
        button2.setOnAction(e ->{
            for (Human person : clientDataBase){
                person.introduction();
            }
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(button1, button2);

        scene = new Scene(layout, 400,400);

        window.setTitle("Restaurant");
        window.setScene(scene);
        window.show();

    }


    public static void main(String[] args) {

        launch(args);
    }


}
