package bsr.login;

import bsr.ServiceUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.bank.bsr.BankException;
import pl.bank.bsr.FaultBean;
import pl.bank.bsr.LoginResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onLoginAction(ActionEvent event){
        errorLabel.setText("");
        String login = loginField.getText();
        String password = passwordField.getText();
        try {
            LoginResponse response = ServiceUtil.logIn(login, password);
            System.out.println(response.getUid());
        } catch (BankException e) {
            FaultBean bean = e.getFaultInfo();
            errorLabel.setText(bean.getDetails());
        }
    }

    @FXML
    public void onCancelAction(ActionEvent event){
        Button closeButton = (Button)event.getSource();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onRegisterAction(ActionEvent event){
        double x = ((Node)(event.getSource())).getScene().getWindow().getX();
        double y = ((Node)(event.getSource())).getScene().getWindow().getY();

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("bsr/register/FXMLRegister.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Rejestracja");
            stage.setScene(new Scene(root, 450, 450));
            stage.setX(x);
            stage.setY(y);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
