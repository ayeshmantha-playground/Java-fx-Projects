package lk.ijse.dep12.dashboard.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInViewController {
    public Rectangle btnSignIn;
    public TextField txtAreaUserName;
    public TextField txtAreaPassword;
    public AnchorPane root;

    public void initialize() {
        btnSignIn.setOnMouseClicked(mouseEvent -> {
            if ((txtAreaUserName.getText().equals("admin")) && (txtAreaPassword.getText().equals("admin"))) {
                ((Stage)root.getScene().getWindow()).close();
                Stage mainStage = new Stage();
                Scene scene ;
                try {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/view/MainView.fxml")));
                    mainStage.setTitle("DashBoard");
                    mainStage.centerOnScreen();
                    mainStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mainStage.setScene(scene);
            }
        });
    }
}
