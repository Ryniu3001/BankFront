package bsr;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Created by marcin on 29.12.16.
 */
public class Util {
    public static FXMLLoader openNewWindow(Window parentWindow, String name, String title, boolean modality){
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
            if (modality) {
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } else {
                parentWindow.hide();
                stage.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }
}
