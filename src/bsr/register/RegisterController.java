package bsr.register;

import bsr.ServiceUtil;
import bsr.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.bank.bsr.BankException;
import pl.bank.bsr.FaultBean;

import javax.xml.ws.WebServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna rejestracji
 * Created by marcin on 01.12.16.
 */
public class RegisterController implements Initializable {

    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Rejestruje użytkownika.
     * @param event
     */
    @FXML
    public void onRegisterAction(ActionEvent event){
        errorLabel.setText("");
        String login = loginField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        try {
            ServiceUtil.register(name, surname, login, password);
            goToLoginPage(event, login, password);
        } catch (BankException e) {
            FaultBean bean = e.getFaultInfo();
            errorLabel.setText(bean.getDetails());
        } catch (WebServiceException e){
            errorLabel.setText("Połączenie odrzucone");
        }

    }

    @FXML
    public void onCancelAction(ActionEvent event){
        goToLoginPage(event, null, null);
    }

    private void goToLoginPage(ActionEvent event, String login, String password){
        double x = ((Node)(event.getSource())).getScene().getWindow().getX();
        double y = ((Node)(event.getSource())).getScene().getWindow().getY();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("bsr/login/FXMLLogin.fxml"));
            root = loader.load();
            if (login != null){
                LoginController controller = loader.getController();
                controller.setCredentials(login, password);
            }
            Stage stage = new Stage();
            stage.setTitle("Logowanie");
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
