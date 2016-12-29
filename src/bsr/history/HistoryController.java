package bsr.history;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.bank.bsr.GetHistoryResponse;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HistoryController implements Initializable {
    @FXML
    private TableView<Operation> tableView;
    @FXML
    private TableColumn<Operation, String> dateColumn;
    @FXML
    private TableColumn<Operation, String> titleColumn;
    @FXML
    private TableColumn<Operation, String> typeColumn;
    @FXML
    private TableColumn<Operation, String> sourceColumn;
    @FXML
    private TableColumn<Operation, String> amountColumn;
    @FXML
    private TableColumn<Operation, String> balanceColumn;

    private final ObservableList<Operation> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.prepareColumns();

    }

    public void fillData(GetHistoryResponse reponse){
        List<Operation> operations = reponse.getOperations().stream().map(operation -> new Operation(operation)).collect(Collectors.toList());
        this.data.addAll(operations);
        this.tableView.getItems().addAll(data);
    }

    private void prepareColumns(){
        this.dateColumn.prefWidthProperty().bind(this.tableView.widthProperty().multiply(0.2));
        this.titleColumn.prefWidthProperty().bind(this.tableView.widthProperty().multiply(0.2));
        this.typeColumn.prefWidthProperty().bind(this.tableView.widthProperty().multiply(0.1));
        this.sourceColumn.prefWidthProperty().bind(this.tableView.widthProperty().multiply(0.2));
        this.amountColumn.prefWidthProperty().bind(this.tableView.widthProperty().multiply(0.2));
        this.balanceColumn.prefWidthProperty().bind(this.tableView.widthProperty().multiply(0.1));

        this.dateColumn.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            property.setValue(dateFormat.format(param.getValue().getDate()));
            return property;
        });
        this.titleColumn.setCellValueFactory(param -> param.getValue().titleProperty());
        this.typeColumn.setCellValueFactory(param -> param.getValue().typeProperty());
        this.sourceColumn.setCellValueFactory(param -> param.getValue().sourceNrbProperty());
        this.amountColumn.setCellValueFactory(param -> param.getValue().amountProperty().asString());
        this.balanceColumn.setCellValueFactory(param -> param.getValue().balanceProperty().asString());
    }
}
