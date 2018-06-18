package sample;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Model.Account;
import sample.Model.Datasource;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView<Account> tableView;

    @FXML
    private CheckBox checkbox;

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private TextField searchTextfield;

    @FXML
    private Button searchButton;

//    @FXML
//    private GridPane mainGridPane;

    @FXML
    private Button newAccountButton;


    public void initialize() {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    searchTextfield.setDisable(false);
                    choicebox.setDisable(false);
                    searchButton.setDisable(false);

                } else {
                    searchTextfield.setDisable(true);
                    choicebox.setDisable(true);
                    searchButton.setDisable(true);
                    listAccounts();
                }
            }
        });

        newAccountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchScene(event);
            }
        });

    }

    public void switchScene(MouseEvent e){
        try{
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("addAccountDialog.fxml"));
            Parent home_page_parent =loader.load();

            Scene home_page_scene = new Scene(home_page_parent);

            Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch(IOException io){
            System.out.println("Error while swiching scene");
        }

    }

    @FXML
    public void onSearchButtonClick() {
        String text = searchTextfield.getText().trim();
        String criteria = choicebox.getValue();
        if (!(text.equals("") || text.equals("Enter value..."))) {
            Task<ObservableList<Account>> task1 = new Task<ObservableList<Account>>() {
                @Override
                protected ObservableList<Account> call() {

                    ObservableList<Account> accountsBy = null;
                    switch (criteria) {
                        case "ClientID": {
                            accountsBy = FXCollections.observableArrayList
                                    (Datasource.getInstance().findByClientID(text));
                            break;
                        }
                        case "First Name": {
                            accountsBy = FXCollections.observableArrayList
                                    (Datasource.getInstance().findByFirstName(text));
                            break;
                        }
                        case "Last Name": {
                            accountsBy = FXCollections.observableArrayList
                                    (Datasource.getInstance().findByLastName(text));
                            break;
                        }
                        case "Pesel": {
                            accountsBy = FXCollections.observableArrayList
                                    (Datasource.getInstance().findByPesel(text));
                            break;
                        }
                        case "Address": {
                            accountsBy = FXCollections.observableArrayList
                                    (Datasource.getInstance().findByAddress(text));
                            break;
                        }
                    }
                    return accountsBy;
                }
            };

            tableView.itemsProperty().bind(task1.valueProperty());
            new Thread(task1).start();
        }
    }

    @FXML
    public void listAccounts() {
        Task<ObservableList<Account>> task = new GetAllAccountsTask();
        tableView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

//    @FXML
//    public void showNewAccountDialog() {
//        Dialog<ButtonType> dialog = new Dialog<>();
//        dialog.initOwner(mainGridPane.getScene().getWindow());
//        dialog.setTitle("Add New Account");
////        dialog.setHeaderText("Enter data for the new account :");
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("addAccountDialog.fxml"));
//        AddAccountDialogController controller = fxmlLoader.getController();
//        try {
//            dialog.getDialogPane().setContent(fxmlLoader.load());
//
//        } catch(IOException e) {
//            System.out.println("Couldn't load the dialog");
//            e.printStackTrace();
//            return;
//        }
//
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
//
//        Optional<ButtonType> result = dialog.showAndWait();
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//
////            TodoItem newItem = controller.processResults();
////            todoListView.getSelectionModel().select(newItem);
//            System.out.println("OK pressed");
////            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
////            alert.setTitle("Confirmation");
////            alert.setHeaderText("Account to add:\nFirst name : ");
////            alert.setContentText("Are you sure you want to add that account?");
////
////            Optional<ButtonType> alertResult = alert.showAndWait();
////            if (alertResult.get() == ButtonType.OK){
////                System.out.println("alert agreed");
////            } else {
////                System.out.println("Alert cancelled");
////            }
//        }
//
//    }
}

    class GetAllAccountsTask extends Task {

        @Override
        public ObservableList<Account> call() {
            return FXCollections.observableArrayList
                    (Datasource.getInstance().queryAccounts(Datasource.ORDER_BY_ASC));
//        (Datasource.getInstance().findByFirstName("Hermione"));
        }
    }





