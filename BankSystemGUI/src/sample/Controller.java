package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.Model.Account;
import sample.Model.Datasource;

public class Controller {

    @FXML
    private TableView<Account> tableView;

    public void initialize(){
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void listAccounts() {
        Task<ObservableList<Account>> task = new GetAllArtistsTask();
        tableView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }
}

class GetAllArtistsTask extends Task {

    @Override
    public ObservableList<Account> call()  {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryAccounts(Datasource.ORDER_BY_ASC));
    }
}

