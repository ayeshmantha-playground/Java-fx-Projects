package lk.ijse.dep12.notepad.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainViewController {


    public Label hlpMenu;

    public void initialize(){
    hlpMenu.setOnMouseClicked(mouseEvent -> {

        Stage stage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/HelpView.fxml")));
            stage.setScene(scene);
            stage.setTitle("HELP");
            stage.show();
            stage.centerOnScreen();
            stage.setResizable(false);
//            stage.initStyle(StageStyle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    }
}
