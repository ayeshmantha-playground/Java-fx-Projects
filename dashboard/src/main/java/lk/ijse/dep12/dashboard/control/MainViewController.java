package lk.ijse.dep12.dashboard.control;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainViewController {

    public AnchorPane detailContainer;
    public AnchorPane root;

    public Label lblTime;
    @FXML
    private Label btnLogOut;


    @FXML
    private Label btnManageCustomer;


    @FXML
    private Label btnManageStock;


    @FXML
    private Label btnPlaceOrder;


    @FXML
    private Label btnViewReport;

    public void initialize() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            lblTime.setText(formattedTime);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        btnManageCustomer.setOnMouseClicked(mouseEvent -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/MangeCustomerView.fxml"));
                detailContainer.getChildren().clear();
                detailContainer.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        btnManageStock.setOnMouseClicked(mouseEvent -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/ManageStockView.fxml"));
                detailContainer.getChildren().clear();
                detailContainer.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        btnPlaceOrder.setOnMouseClicked(mouseEvent -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/PlaceOrderView.fxml"));
                detailContainer.getChildren().clear();
                detailContainer.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnViewReport.setOnMouseClicked(mouseEvent -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/ReportsView.fxml"));
                detailContainer.getChildren().clear();
                detailContainer.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        btnLogOut.setOnMouseClicked(mouseEvent -> {
            ((Stage)root.getScene().getWindow()).close();
            Stage stage = new Stage();
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginView.fxml")));
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
