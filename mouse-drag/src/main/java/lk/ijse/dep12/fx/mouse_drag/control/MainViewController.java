package lk.ijse.dep12.fx.mouse_drag.control;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainViewController {
    public AnchorPane root;
    public Button btnClose;
    
    public void initialize() {

        root.setOnMousePressed(mouseEvent -> {
            root.setOnMouseDragged(mouseEvent1 -> {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setX(mouseEvent1.getScreenX()-mouseEvent.getX());
                stage.setY(mouseEvent1.getScreenY()-mouseEvent.getY());
            });
        });

        btnClose.setOnMouseClicked(mouseEvent -> { //Method to close the window
            ((Stage)root.getScene().getWindow()).close();
        });
    }


}
