package bsr.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Startuje aplikacje
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        primaryStage.setTitle("Logowanie");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
