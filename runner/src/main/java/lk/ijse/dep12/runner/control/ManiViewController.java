package lk.ijse.dep12.runner.control;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.security.Key;

public class ManiViewController {
    public AnchorPane root;
    public ImageView astro;
    public ImageView alien;
    public ImageView background1;
    public ImageView background2;
    public ImageView spaceShip;
    public ImageView gameOver;

    public void initialize() throws IOException {
        double astroY = astro.getLayoutY();

        Timeline timelineAlien = new Timeline(new KeyFrame(Duration.seconds(0.005), actionEvent -> {
//            System.out.println(alien.getLayoutX());
            if (astro.getLayoutY() + 100 >= alien.getLayoutY() && astro.getLayoutX() >= alien.getLayoutX()) {
                gameOver.setVisible(true);
                astro.setVisible(false);
            }
            alien.setLayoutX(alien.getLayoutX() - 1.5);
            if (alien.getLayoutX() < -100) {
                alien.setLayoutX(1350);
            }
        }));

        timelineAlien.setCycleCount(Animation.INDEFINITE);
        timelineAlien.playFromStart();

        Timeline timelineSpaceShip = new Timeline(new KeyFrame(Duration.seconds(0.005), actionEvent -> {

            if (astro.getLayoutY() < spaceShip.getLayoutY() + 65 && astro.getLayoutX() > spaceShip.getLayoutX()) {
                gameOver.setVisible(true);
                astro.setVisible(false);
            }

            spaceShip.setLayoutX(spaceShip.getLayoutX() - 3.5);
            if (spaceShip.getLayoutX() < -100) {
                spaceShip.setLayoutX(1350);
            }
        }));
        timelineSpaceShip.setCycleCount(Animation.INDEFINITE);
        timelineSpaceShip.playFromStart();

        Timeline timelineBackground = new Timeline(new KeyFrame(Duration.seconds(0.005), actionEvent -> {
            background1.setLayoutX(background1.getLayoutX() - 3);
            background2.setLayoutX(background1.getLayoutX() + background1.getFitWidth());
//            System.out.println(background1.getLayoutX());
            if (background1.getLayoutX() <= -1270) {
                background1.setLayoutX(0);
            }
        }));
        timelineBackground.setCycleCount(Animation.INDEFINITE);
        timelineBackground.playFromStart();
        
        Platform.runLater(() -> {
            root.getScene().setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.SPACE) {

                    Timeline timelineAstroUp = new Timeline(new KeyFrame(Duration.seconds(0.05), actionEvent -> {
                        if (astro.getLayoutY() > 25) {

                            astro.setLayoutY(astro.getLayoutY() - 5);
                        }
                    }));
                    timelineAstroUp.setCycleCount(1);
                    timelineAstroUp.play();

                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    Timeline timelineAstroDown = new Timeline(new KeyFrame(Duration.seconds(0.05), actionEvent -> {
                        if (astro.getLayoutY() < astroY) {
                            astro.setLayoutY(astro.getLayoutY() + 5);
                        }
                    }));

                    timelineAstroDown.setCycleCount(1);
                    timelineAstroDown.play();
                }
            });

//            root.getScene().setOnKeyReleased(keyEvent -> {
//                if (keyEvent.getCode() == KeyCode.UP){
//                    System.out.println(astro.getLayoutY()+ "Y");
//                    Timeline timelineAstroDown = new Timeline(new KeyFrame(Duration.seconds(1),actionEvent -> {
//                        if (astro.getLayoutY() > 300){
//                            astro.setLayoutY(astro.getLayoutY() +10);
//                        }
//                    }));
//                    timelineAstroDown.setCycleCount(Animation.INDEFINITE);
//                    timelineAstroDown.play();
//                }
//            });

        });
    }
}
