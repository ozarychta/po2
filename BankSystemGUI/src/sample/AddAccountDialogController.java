package sample;

import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class AddAccountDialogController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField peselField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField balanceField;

    @FXML
    private Button backButton;

    public void initialize(){
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchScene(event);
            }
        });
    }

    public void switchScene(MouseEvent e){
        try{
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent home_page_parent =loader.load();

            Scene home_page_scene = new Scene(home_page_parent);

            Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            Controller controller = loader.getController();
            controller.listAccounts();
        } catch(IOException io){
            System.out.println("Error while swiching scene");
        }

    }

    @FXML
    public void fun(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Account to add:\nFirst name : ");
        alert.setContentText("Are you sure you want to add that account?");

        Optional<ButtonType> alertResult = alert.showAndWait();
        if (alertResult.get() == ButtonType.OK){
            System.out.println("alert agreed");
        } else {
            System.out.println("Alert cancelled");
        }
    }
}
