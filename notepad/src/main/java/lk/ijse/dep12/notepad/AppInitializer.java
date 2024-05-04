package lk.ijse.dep12.notepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep12.notepad.control.MainViewController;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Untitled Document");
        URL resource = getClass().getResource("/view/MainView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane container = fxmlLoader.load();
        primaryStage.setScene(new Scene(container));
        MainViewController controller = fxmlLoader.getController();
        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/icon.png"))));
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            controller.btnExit.fire();
        });
    }
}
