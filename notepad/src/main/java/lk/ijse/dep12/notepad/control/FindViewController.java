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
    private int caretPosition = 0;

    private boolean compiled;


    public void initialize() {
        btnFindNext.setDisable(true);
        txtFindNext.textProperty().addListener((observable, oldValue, newValue) -> {
            btnFindNext.setDisable(newValue.isBlank());
            compiled = false; // word changed
        });
    }

    public void btnCancelOnAction(ActionEvent event) {

    }

    public void btnFindNextOnAction(ActionEvent event) throws IOException {
        if (!txtFindNext.getText().isBlank()) {
            caretPosition = controller.txtContent.getCaretPosition();
            if (!compiled) { //if text or radio button selection changed
                find = txtFindNext.getText();

                if (!chkMatchCase.isSelected()) {
                    find = find.toLowerCase();
                    text = text.toLowerCase();
                }

                pattern = Pattern.compile(find);
                matcher = pattern.matcher(text);
                compiled = true; // compiled the pattern
            }

            if (matcher.find(caretPosition)) {
                System.out.println("finding next index of word");
                int startIndex = matcher.start();
                int endIndex = matcher.end();
                System.out.printf("Start Index: %d, End Index: %d", startIndex, endIndex);
                controller.selectTheFoundText(startIndex, endIndex);
                caretPosition = endIndex;
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Cannot find text '" + find + "'").show();
            }
        }


      /*  if (chkMatchCase.isSelected()) {
            System.out.println("Match case");
            if (!txtFindNext.getText().isBlank()) {
                if (!find.equals(txtFindNext.getText())) {
                    find = txtFindNext.getText();
                    System.out.println("find text changed | Matched Case");
                    pattern = Pattern.compile(find);
                    matcher = pattern.matcher(text);
                }

                if (matcher.find(caretPosition)) {
                    System.out.println("finding next index of word");
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    System.out.printf("Start Index: %d, End Index: %d", startIndex, endIndex);
                    controller.selectTheFoundText(startIndex, endIndex);
                    caretPosition = endIndex;
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Cannot find text '" + find + "'").show();
                }
            }
        } else {
            System.out.println("Not Match case");
            if (!txtFindNext.getText().isBlank()) {
                if (!find.equals(txtFindNext.getText())) {
                    find = txtFindNext.getText().toLowerCase();
                    System.out.println("find text changed | Not matched case");
                    pattern = Pattern.compile(find);
                    matcher = pattern.matcher(text.toLowerCase());
                }

                if (matcher.find(caretPosition)) {
                    System.out.println("finding next index of word");
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    System.out.printf("Start Index: %d, End Index: %d", startIndex, endIndex);
                    controller.selectTheFoundText(startIndex, endIndex);
                    caretPosition = endIndex;
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Cannot find text '" + find + "'").show();
                }
            }
        }*/


    }

    public void initData(MainViewController controller) {
        this.controller = controller;
    }

    public void setTextToFind(String text) {
        this.text = text;
    }

    private void findNext(String find, String text) {


    }

    public void chkMatchCaseOnAction(ActionEvent actionEvent) {
        compiled = false;
    }
}
