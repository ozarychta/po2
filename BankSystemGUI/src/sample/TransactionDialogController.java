package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.Account;
import sample.Model.Datasource;

import java.io.IOException;

public class TransactionDialogController {
    @FXML
    private Button backButton;

    @FXML
    private Button okButton;

    @FXML
    private TextField IDFromField;

    @FXML
    private TextField IDToField;

    @FXML
    private TextField amountField;

    public void initialize() {
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchScene(event);
            }
        });

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String id1 = IDFromField.getText().trim();
                if(id1.isEmpty()) Controller.showAlert("ID field can't be empty");
                else {
                    int intID1 = Account.stringToInt(id1);
                    if(!(Account.checkIfIsPositiveInt(intID1))) Controller.showAlert("ID must be positive integer");
                    else {
                        String id2 = IDToField.getText().trim();
                        if(id2.isEmpty()) Controller.showAlert("ID field can't be empty");
                        else {
                            int intID2 = Account.stringToInt(id2);
                            if(!(Account.checkIfIsPositiveInt(intID2))) Controller.showAlert("ID must be positive integer");
                            else {
                                String amount = amountField.getText().trim();
                                if(amount.isEmpty()) Controller.showAlert("Amount field can't be empty");
                                else {
                                    double doubleAmount = Account.stringToDouble(amount);
                                    if(!(Account.checkIfIsPositiveDouble(doubleAmount))) Controller.showAlert("Amount must be greater than 0. Decimal point is . not , ");
                                    else{
                                        if(Controller.showConfirmation("Withdrawing "+doubleAmount+ "$ from client with ID = "+intID1
                                                +" and depositing to client with id =" +intID2)){
                                            int result = Datasource.getInstance().transaction(intID1,intID2, doubleAmount);
                                            if(result==-1) Controller.showAlert("There is no ID = "+intID1+ " in database");
                                            else if(result==-3) Controller.showAlert("There is no ID = "+intID2+ " in database");
                                            else if(result==-2) Controller.showAlert("User with ID ="+intID1+ " doesn't have enough money to withdraw");
                                            else if(result==0) Controller.showAlert("Transaction couldn't be commited");
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
