package lk.ijse.dep12.video_player;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/MainView.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Video Player");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
