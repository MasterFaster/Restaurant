package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Food.Menu;
import sample.People.Client;
import sample.People.Deliverer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Master Faster on 04.12.2016.
 */
public class ControlPanel {


    public static Client addClient() throws MyException{

        // create new client to add to clientDataBase
        Client client = new Client();

        Stage window = new Stage();

        // user can't use other windows, first must close current window
        window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox(10);
        Scene scene = new Scene(layout, 600, 600);

        // user type here client first name
        //setFocusTraversable to enable setPromptText
        Label labelFirstName = new Label("First name: ");
        TextField typeFirstName = new TextField();
        typeFirstName.setPromptText(client.getFirstName());
        typeFirstName.setFocusTraversable(false);

        // user type here client first name
        //setFocusTraversable to enable setPromptText
        Label labelSecondName = new Label("Second name: ");
        TextField typeSecondName = new TextField();
        typeSecondName.setPromptText(client.getSecondName());
        typeSecondName.setFocusTraversable(false);

        // button to close current window
        //check if TextField is empty or not and change value in client object
        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(e -> {
            if(!(typeFirstName.getText().equals("")))
                client.setFirstName(typeFirstName.getText());
            if(!(typeSecondName.getText().equals("")))
                client.setSecondName(typeSecondName.getText());
            [].close();
        });

        Button exitButton = new Button("Close");
        exitButton.setOnAction(e -> {
            throw new MyException();
        });


        layout.getChildren().addAll(labelFirstName, typeFirstName, labelSecondName, typeSecondName, exitButton);
        layout.setAlignment(Pos.CENTER);

        window.setScene(scene);
        window.setTitle("Adding Client");
        //window.setMaxWidth(300);
        window.showAndWait();

        return client;
    }


}





















