package bsr.home;

import bsr.ServiceUtil;
import bsr.Util;
import bsr.history.HistoryController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import pl.bank.bsr.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static bsr.home.Operation.transfer;

/**
 * Kontroler głównego okna aplikacji
 */
public class HomeController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label balanceLabel;
    private SimpleDoubleProperty balanceProperty;
    @FXML
    private TextField nrbTf;
    @FXML
    private TextField amountTf;
    @FXML
    private TextField titleTf;
    @FXML
    private Label nrbLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox accountComboBox;
    @FXML
    private ComboBox operationCb;
    @FXML
    private TextField srcNrbTf;
    @FXML
    private Button historyBtn;
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
        this.accountComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.srcNrbTf.setText(newValue.toString());
                this.historyBtn.setDisable(false);
            } else {
                this.srcNrbTf.setText("");
                this.historyBtn.setDisable(true);
            }

        });
    }

    /**
     * Uzupełnia okno danymi
     * @param loginResponse Odpowiedź z usługi logowania
     */
    public void initData(LoginResponse loginResponse) {
        this.nameLabel.setText(loginResponse.getName());
        this.surnameLabel.setText(loginResponse.getSurname());
        this.uid = loginResponse.getUid();
        this.fillAccountsInfo(loginResponse.getAccountList());
    }

    private void fillAccountsInfo(List<AccountResponse> list) {
        Integer idx = this.accountComboBox.getSelectionModel().getSelectedIndex();
        if (idx == -1) //przy otwarciu okienka
            idx = 0;
        this.observableList.clear();
        if (list != null && !list.isEmpty()) {
            for (AccountResponse acc : list)
                observableList.add(new Account(acc.getAccNumber(), acc.getBalance()));
        }
        observableList.add("Dodaj nowe konto");
        accountComboBox.setItems(observableList);
        if (accountComboBox.getItems().size() > 1) {
            accountComboBox.getSelectionModel().select(idx.intValue());
            this.balanceProperty.setValue(((Account) accountComboBox.getSelectionModel().getSelectedItem()).getBalance() / 100.0);
        }
    }

    /**
     * Metoda odpowiedzialna obsługę operacji wykonywanym w comboBoxie z wyborem konta.
     * @param event
     */
    @FXML
    public void onChangeAccount(ActionEvent event) {
        if (accountComboBox.getValue() != null && !accountComboBox.getValue().toString().isEmpty()) {
            //jesli wybrano dodaj nowe konto
            if (accountComboBox.getSelectionModel().getSelectedIndex() == accountComboBox.getItems().size() - 1) {
                if (showConfirmationDialog()) {
                    try {
                        AccountResponse repsonse = ServiceUtil.addNewAccount(this.uid);
                        observableList.add(accountComboBox.getItems().size() - 1, new Account(repsonse.getAccNumber(), repsonse.getBalance()));
                        this.accountComboBox.getSelectionModel().select(accountComboBox.getItems().size() - 2);
                    } catch (BankException e) {
                        e.printStackTrace();
                    }
                } else {
                    //clearSelection works but throws exception.
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

    /**
     * Metoda odpowiedzialna za obsługę akcji związnej z comboBoxem zawierającym operacje do wykonania.
     * @param event
     */
    @FXML
    public void onChangeOperation(ActionEvent event) {
        if (((Operation) this.operationCb.getSelectionModel().getSelectedItem()).name().equals(transfer.name())) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    /**
     * Otworzenie okienka historii
     * @param event
     */
    @FXML
    public void onHistoryOperation(ActionEvent event) {
        String accNrb = this.srcNrbTf.getText();
        GetHistoryResponse response = null;
        try {
            response = ServiceUtil.getHistory(this.uid, accNrb);
            this.openHistory(((Node)(event.getSource())).getScene().getWindow(), "bsr/history/FXMLHistory.fxml", "Historia " +
                    ((Account)this.accountComboBox.getSelectionModel().getSelectedItem()).getAccountNbr(), response);
        } catch (BankException e) {
            printInfo(e.getMessage(), Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    public void onRefreshAccounts(ActionEvent event) {
        refreshAccounts();
    }

    private void refreshAccounts(){
        GetAccountsResponse response = null;
        try {
            response = ServiceUtil.getAccounts(this.uid);
            this.fillAccountsInfo(response.getAccountList());
        } catch (BankException e) {
            printInfo(e.getMessage(), Color.RED);
            e.printStackTrace();
        }
    }

    private void setVisible(boolean value) {
        this.titleTf.visibleProperty().setValue(value);
        this.nrbTf.visibleProperty().setValue(value);
        this.nrbLabel.visibleProperty().setValue(value);
        this.titleLabel.visibleProperty().setValue(value);
    }

    private boolean showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Zakładanie nowego konta");
        alert.setContentText("Czy na pewno chcesz utworzyć nowe konto?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void onExecuteChange(ActionEvent event) {
        this.errorLabel.setText("");
        try {
            if (validateInputs()) {
                Integer amount = getAmountInteger();
                String nrb = ((Account) this.accountComboBox.getSelectionModel().getSelectedItem()).getAccountNbr();
                switch (((Operation) this.operationCb.getSelectionModel().getSelectedItem())) {
                    case transfer:
                        TransferRequest request = this.prepareRequest(nrb, amount);
                        TransferResponse transferResponse = ServiceUtil.transfer(request);
                        refreshAccounts();
                        break;
                    case deposit:
                        DepositResponse response = ServiceUtil.deposit(this.uid, nrb, amount);
                        updateBalance(response.getBalance());
                        break;
                    case withdraw:
                        WithdrawResponse withdrawResponse = ServiceUtil.withdraw(this.uid, nrb, amount);
                        updateBalance(withdrawResponse.getBalance());
                        break;
                    default:
                        System.out.println("default");
                        break;
                }

            }

            printInfo("Operation completed successfully", Color.GREEN);

        } catch (Exception e) {
            printInfo(e.getMessage(), Color.RED);
            //e.printStackTrace();
        }
    }

    private Integer getAmountInteger() throws Exception {
        Integer amount = -1;
        try {
            String amountStr = this.amountTf.getText();
            amountStr = amountStr.replace(",", ".");
            int dotIndex = amountStr.indexOf('.');
            if (dotIndex != -1 && amountStr.length() - dotIndex - 1 > 2)
                throw new Exception("Wrong amount format.");
            Double amountDouble = Double.valueOf(amountStr) * 100;
            amount = amountDouble.intValue();
        } catch (NumberFormatException e){
            throw new NumberFormatException("Invalid amount.");
        }
        return amount;
    }

    private void updateBalance(Integer newBalance) {
        Account acc = (Account) this.accountComboBox.getSelectionModel().getSelectedItem();
        acc.setBalance(newBalance);
        this.balanceProperty.setValue(newBalance / 100.0);
    }

    private boolean validateInputs() {
        return true;
    }

    private TransferRequest prepareRequest(String srcAccountNrb, Integer amount) {
        TransferRequest request = new TransferRequest();
        request.setUuid(this.uid);
        request.setTitle(this.titleTf.getText());
        request.setSourceAccountNumber(srcAccountNrb);
        request.setTargetAccountNumber(this.nrbTf.getText());
        request.setAmount(amount);
        return request;
    }

    private void printInfo(String text, Paint color){
        this.errorLabel.setTextFill(color);
        this.errorLabel.setText(text);
    }

    private void openHistory(Window parentWindow, String name, String title, GetHistoryResponse history){
        double x = parentWindow.getX();
        double y = parentWindow.getY();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(Util.class.getClassLoader().getResource(name));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(loader.load(), 450, 450));
            stage.setX(x);
            stage.setY(y);
            stage.initModality(Modality.APPLICATION_MODAL);
            HistoryController controller = loader.getController();
            controller.fillData(history);
            stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
