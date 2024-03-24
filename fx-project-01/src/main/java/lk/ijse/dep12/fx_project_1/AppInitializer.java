package lk.ijse.dep12.fx_project_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane container = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
        Scene mainScene = new Scene(container);
        primaryStage.setTitle("Road_Trip");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
