package blue;

import java.io.IOException;

import blue.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Blue using FXML.
 */
public class Main extends Application {

    private Blue blue = new Blue("Blue.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlue(blue); // inject the Blue instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
