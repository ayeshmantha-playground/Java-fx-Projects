package lk.ijse.dep12.dashboard.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    public AnchorPane detailContainer;
    public AnchorPane root;
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
