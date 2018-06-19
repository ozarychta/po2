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
import sample.Model.Account;
import sample.Model.Datasource;

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

    @FXML
    private Button okButton;

    public void initialize(){
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchScene(event);
            }
        });

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String firstName = firstNameField.getText().trim();
                if(firstName.isEmpty()) Controller.showAlert("First name field can't be empty");
                else if(!(Account.checkIfIsWord(firstName))){
                    Controller.showAlert("First name can only contain letters, space and - ");

                } else {
                    String lastName = lastNameField.getText().trim();
                    if(lastName.isEmpty()) Controller.showAlert("Last name field can't be empty");
                    else if(!(Account.checkIfIsWord(lastName))){
                        Controller.showAlert("Last name can only contain letters, space and - ");

                    } else {
                        String pesel = peselField.getText().trim();
                        if(pesel.isEmpty()) Controller.showAlert("Pesel field can't be empty");
                        else if(!(Account.checkIfIsPesel(pesel))){
                            Controller.showAlert("Pesel must contain 11 digits ");

                        } else {
                            String address = addressField.getText().trim();
                            if(address.isEmpty()) Controller.showAlert("Address field can't be empty");
                            else if(!(Account.checkIfIsAddress(address))){
                                Controller.showAlert("Address must be <City> or <City number> ");

                            } else {
                                String balance = balanceField.getText().trim();
                                if(balance.isEmpty()) Controller.showAlert("Balance field can't be empty");
                                else {
                                    double doubleBalance = Account.stringToDouble(balance);
                                    if(!(Account.checkIfIsPositiveEqualDouble(doubleBalance))) Controller.showAlert("Balance must be greater or equal to 0. Decimal point is . not , ");
                                    else{
                                        if(Controller.showConfirmation("Adding account to database")){
                                            int affectedRows = Datasource.getInstance().insertAccount(firstName, lastName, pesel, address, doubleBalance);
                                            if(affectedRows!=1){
                                                Controller.showAlert("Couldn't add account to database");
                                            }
                                            switchScene(event);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


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


}
