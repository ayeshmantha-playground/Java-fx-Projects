package lk.ijse.dep12.notepad.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindViewController {
    public Button btnCancel;
    public Button btnFindNext;
    public CheckBox chkMatchCase;
    public CheckBox chkWrapAround;
    public ToggleGroup grpUpDown;
    public TextField txtFindNext;
    private MainViewController controller;
    private String text;
    String find = "";
    //    private String find;
    private Pattern pattern = null;
    private Matcher matcher = null;


    public void initialize() {
        txtFindNext.textProperty().addListener((observable, oldValue, newValue) -> {
            //todo: This is not working
            if (newValue.isEmpty()) {
                btnFindNext.setDisable(true);
            }
        });

//        new Thread(() -> {
//            pattern = Pattern.compile(text);
//        }).start();

    }

    public void btnCancelOnAction(ActionEvent event) {

    }

    public void btnFindNextOnAction(ActionEvent event) throws IOException {

        if (!txtFindNext.getText().isBlank()) {
            if (!find.equals(txtFindNext.getText())) {
                find = txtFindNext.getText();
                System.out.println("find text changed");
                pattern = Pattern.compile(find);
                matcher = pattern.matcher(text);
            }

            if (matcher.find()) {
//                while (matcher.find()) {
//                }
                System.out.println("finding next index of word");
                int startIndex = matcher.start();
                int endIndex = matcher.end();
                System.out.printf("Start Index: %d, End Index: %d", startIndex, endIndex);
                controller.selectTheFoundText(startIndex, endIndex);

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Cannot find text '" + find + "'").show();
            }
        }
    }

    public void initData(MainViewController controller) {
        this.controller = controller;
    }

    public void setTextToFind(String text) {
        this.text = text;
    }
}
