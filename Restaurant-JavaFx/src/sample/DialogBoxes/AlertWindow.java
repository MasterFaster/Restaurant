package sample.DialogBoxes;

import javafx.scene.control.Alert;

/**
 * Created by Master Faster on 22.01.2017.
 * This class is used, when untypical action occurs
 */
public class AlertWindow {

    /**
     * Display new window with information about warning
     * @param title title of the window
     * @param header header of the window
     * @param message message, describing the warning
     */
    public static void showAlertWindow(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
