package bsr.login;

import bsr.ServiceUtil;
import bsr.home.HomeController;
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
import javafx.stage.Window;
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
    @FXML private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setDefaultButton(true);
    }

    @FXML
    public void onLoginAction(ActionEvent event){
        errorLabel.setText("");
        String login = loginField.getText();
        String password = passwordField.getText();
        LoginResponse response = null;
        try {
            response = ServiceUtil.logIn(login, password);
            double x = ((Node) (event.getSource())).getScene().getWindow().getX();
            double y = ((Node) (event.getSource())).getScene().getWindow().getY();

            Parent root;

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("bsr/home/FXMLHome.fxml"));
            // root = FXMLLoader.load(getClass().getClassLoader().getResource("bsr/home/FXMLHome.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bank");
            stage.setScene(new Scene(loader.load(), 550, 450));
            stage.setX(x);
            stage.setY(y);
            HomeController homeController = loader.getController();
            homeController.initData(response);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
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
        openNewWindow(((Node)(event.getSource())).getScene().getWindow(), "bsr/register/FXMLRegister.fxml", "Rejestracja");
    }

    private void openNewWindow(Window windowToClose, String name, String title){
        double x = windowToClose.getX();
        double y = windowToClose.getY();

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 450, 450));
            stage.setX(x);
            stage.setY(y);
            stage.show();
            windowToClose.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCredentials(String user, String password){
        this.loginField.setText(user);
        this.passwordField.setText(password);
    }

}
