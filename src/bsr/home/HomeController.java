package bsr.home;

import bsr.ServiceUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.bank.bsr.AccountResponse;
import pl.bank.bsr.BankException;
import pl.bank.bsr.LoginResponse;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by marcin on 29.11.16.
 */
public class HomeController implements Initializable {
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private ComboBox accountComboBox;
    private String uid;
    private ObservableList<Object> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");
    }

    public void initData(LoginResponse loginResponse){
        this.nameLabel.setText(loginResponse.getName());
        this.surnameLabel.setText(loginResponse.getSurname());
        this.uid = loginResponse.getUid();
        if (loginResponse.getAccountList() != null && !loginResponse.getAccountList().isEmpty()){
            for (AccountResponse acc : loginResponse.getAccountList())
                observableList.add(new Account(acc.getAccNumber(), acc.getBalance()));
        }
        observableList.add("Dodaj nowe konto");
        accountComboBox.setItems(observableList);
    }

    @FXML
    public void onChangeAccount(ActionEvent event){
        System.out.println("Action");
        if (accountComboBox.getValue() != null && !accountComboBox.getValue().toString().isEmpty()){
            if (accountComboBox.getSelectionModel().getSelectedIndex() == accountComboBox.getItems().size() - 1){
                if (showConfirmationDialog()){
                    try {
                        AccountResponse repsonse = ServiceUtil.addNewAccount(this.uid);
                        observableList.add(accountComboBox.getItems().size() - 1, new Account(repsonse.getAccNumber(), repsonse.getBalance()));
                        this.accountComboBox.getSelectionModel().select(accountComboBox.getItems().size() - 2);
                    } catch (BankException e) {
                        e.printStackTrace();
                    }
                } else {
                    //clear
                    accountComboBox.setItems(null);
                    accountComboBox.setItems(this.observableList);
                }
            }
        }
    }

    private boolean showConfirmationDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Zakładanie nowego konta");
        alert.setContentText("Czy na pewno chcesz utworzyć nowe konto?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes){
            return true;
        } else {
            return false;
        }
    }
}
