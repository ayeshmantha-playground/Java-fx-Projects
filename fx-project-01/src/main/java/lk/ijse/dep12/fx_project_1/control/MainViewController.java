package lk.ijse.dep12.fx_project_1.control;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class MainViewController {
    public AnchorPane root;
    public ImageView car;
    public ImageView road;
//    public ImageView road2;

    public void initialize() {
        Platform.runLater(()->{
            root.getScene().setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    if (car.getLayoutX() < 380.0) {
                        car.setLayoutX(car.getLayoutX() + 10);
                    }
                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    if (car.getLayoutX() > 110) {
                        car.setLayoutX(car.getLayoutX() - 10);
                    }
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    road.setLayoutY(road.getLayoutY() + 10);

                    if(road.getLayoutY() > 0 ){
                        ImageView newRoad = new ImageView(new Image("/image/road_edited.jpeg"));
                        newRoad.setFitWidth(road.getFitWidth());
                        newRoad.setLayoutX(road.getLayoutX());
                        newRoad.setLayoutY(road.getLayoutY()- road.getImage().getHeight());
                        root.getChildren().add(newRoad);
                        car.toFront();
                    }
                }
            });
        });
    }
}
