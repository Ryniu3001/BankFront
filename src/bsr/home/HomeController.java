package bsr.home;

import bsr.ServiceUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.bank.bsr.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static bsr.home.Operation.transfer;

public class HomeController implements Initializable {
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label balanceLabel;
    private SimpleDoubleProperty balanceProperty;
    @FXML private TextField nrbTf;
    @FXML private TextField amountTf;
    @FXML private TextField titleTf;
    @FXML private Label nrbLabel;
    @FXML private Label titleLabel;
    @FXML private Label errorLabel;
    @FXML private ComboBox accountComboBox;
    @FXML private ComboBox operationCb;
    private String uid;
    private ObservableList<Object> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Object> operationList = FXCollections.observableArrayList();
        operationList.addAll(transfer, Operation.deposit, Operation.withdraw);
        this.operationCb.setItems(operationList);
        this.operationCb.getSelectionModel().select(0);

        this.balanceProperty = new SimpleDoubleProperty(-1);
        this.balanceLabel.textProperty().bind(balanceProperty.asString());
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
        if (accountComboBox.getItems().size() > 1) {
            accountComboBox.getSelectionModel().select(0);
            this.balanceProperty.setValue(((Account)accountComboBox.getSelectionModel().getSelectedItem()).getBalance());
        }
    }

    @FXML
    public void onChangeAccount(ActionEvent event){
        if (accountComboBox.getValue() != null && !accountComboBox.getValue().toString().isEmpty()){
            //jesli wybrano dodaj nowe konto
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
                //jesli wybrano konto
            } else {
                Account acc = (Account) accountComboBox.getSelectionModel().getSelectedItem();
                this.balanceProperty.setValue(acc.getBalance() / 100.0);
            }
        }
    }

    @FXML
    public void onChangeOperation(ActionEvent event){
        if (((Operation)this.operationCb.getSelectionModel().getSelectedItem()).name().equals(transfer.name())){
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    private void setVisible(boolean value){
        this.titleTf.visibleProperty().setValue(value);
        this.nrbTf.visibleProperty().setValue(value);
        this.nrbLabel.visibleProperty().setValue(value);
        this.titleLabel.visibleProperty().setValue(value);
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

    @FXML
    public void onExecuteChange(ActionEvent event){
        this.errorLabel.setText("");
        try {
            if (validateInputs()) {
                Integer amount = getAmountInteger();
                String nrb = ((Account)this.accountComboBox.getSelectionModel().getSelectedItem()).getAccountNbr();
                switch (((Operation) this.operationCb.getSelectionModel().getSelectedItem())) {
                    case transfer:
                        TransferRequest request = this.prepareRequest(nrb, amount);
                        TransferResponse transferResponse = ServiceUtil.transfer(request);
                        updateBalance(transferResponse.getBalance());
                        break;
                    case deposit:
                        DepositResponse response = ServiceUtil.deposit(this.uid, nrb, amount);
                        updateBalance(response.getBalance());
                        break;
                    case withdraw:
                        System.out.println("asd");
                        break;
                    default:
                        System.out.println("default");
                        break;
                }

            }
        } catch (Exception e){
            this.errorLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    private Integer getAmountInteger(){
        String amountStr = this.amountTf.getText();
        amountStr = amountStr.replace(",", ".");
        Double amountDouble = Double.valueOf(amountStr) * 100;
        Integer amount = amountDouble.intValue();
        return amount;
    }

    private void updateBalance(Integer newBalance){
        Account acc = (Account) this.accountComboBox.getSelectionModel().getSelectedItem();
        acc.setBalance(newBalance);
        this.balanceProperty.setValue(newBalance / 100.0);
    }

    private boolean validateInputs(){
        return true;
    }

    private TransferRequest prepareRequest(String srcAccountNrb, Integer amount){
        TransferRequest request = new TransferRequest();
        request.setUuid(this.uid);
        request.setTitle(this.titleTf.getText());
        request.setSourceAccountNumber(srcAccountNrb);
        request.setTargetAccountNumber(this.nrbTf.getText());
        request.setAmount(amount);
        return request;
    }
}
