package bsr.history;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import pl.bank.bsr.GetHistoryResponse;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Kontroler okna historii
 */
public class HistoryController implements Initializable {
    @FXML
    private TableView<Operation> tableView;
    @FXML
    private TableColumn<Operation, MyDate> dateColumn;
    @FXML
    private TableColumn<Operation, String> titleColumn;
    @FXML
    private TableColumn<Operation, String> typeColumn;
    @FXML
    private TableColumn<Operation, String> sourceColumn;
    @FXML
    private TableColumn<Operation, Double> amountColumn;
    @FXML
    private TableColumn<Operation, Double> balanceColumn;

    private final ObservableList<Operation> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.prepareColumns();

    }

    /**
     * Wypełnia okienko historii danymi z usługi
     * @param reponse
     */
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

/*        this.dateColumn.setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            property.setValue(dateFormat.format(param.getValue().getDate()));
            return property;
        });*/
        this.dateColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(new MyDate(param.getValue().getDate().getTime())));
        this.titleColumn.setCellValueFactory(param -> param.getValue().titleProperty());
        this.typeColumn.setCellValueFactory(param -> param.getValue().typeProperty());
        this.sourceColumn.setCellValueFactory(param -> param.getValue().sourceNrbProperty());
        this.amountColumn.setCellValueFactory(param -> param.getValue().amountProperty().asObject());
        this.balanceColumn.setCellValueFactory(param -> param.getValue().balanceProperty().asObject());

        this.amountColumn.setCellFactory(param -> {
            return new TableCell<Operation, Double>(){
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);
                    TableRow<Operation> currentRow = getTableRow();

                    if (!isEmpty()) {
                        if(item < 0.0)
                            //currentRow.setStyle("-fx-background-color:lightcoral");
                             currentRow.getStyleClass().add("ujemne");
                        else
                            //currentRow.setStyle("-fx-background-color:lightgreen");
                            currentRow.getStyleClass().add("dodatnie");
                    }
                }
            };
        });
    }
}
