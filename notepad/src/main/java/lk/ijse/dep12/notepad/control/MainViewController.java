package lk.ijse.dep12.notepad.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.*;
import java.util.Optional;

public class MainViewController {


    public Label hlpMenu;
    public MenuItem btnOpen;
    public AnchorPane anchorPane;
    public TextArea txtContent;
    public MenuItem btnSaveAsOnAction;

    public boolean isButtonClicked;
    public MenuItem btnSave;
    public MenuItem btnExit;
    public Label lnLabel;
    public Label clLabel;

    File file;

    public void initialize() {

        Platform.runLater(() -> {
            anchorPane.getScene().setOnKeyPressed(keyEvent -> {
                Stage stageMain = (Stage) anchorPane.getScene().getWindow();
                String existingTitle = stageMain.getTitle();
                System.out.println(keyEvent.getCode());

//                txtContent.setOnKeyPressed(event -> {
//                    if (existingTitle.startsWith("*")) return;
//                    stageMain.setTitle("*" + existingTitle);
//                });
                if (existingTitle.startsWith("*")) return;
                stageMain.setTitle("*" + existingTitle);

                isButtonClicked = true;

            });
        });

        txtContent.setOnMouseClicked(event -> {
            setStatusBar();
        });

        txtContent.setOnKeyReleased(event -> {
            setStatusBar();
        });
    }

    private void setStatusBar() {
        int lineNumber;
        int columnNumber;

//            if (Objects.equals(txtContent.getText(), "")) {
//
//            }

        int caretPosition = txtContent.getCaretPosition();
        lineNumber = txtContent.getText(0, caretPosition).split("\n").length;
        if (caretPosition > 0) {

            if (txtContent.getText(0, caretPosition).charAt(caretPosition - 1) == '\n') lineNumber++;
        }
        columnNumber = caretPosition - txtContent.getText(0, caretPosition).lastIndexOf("\n");
        lnLabel.setText(String.valueOf(lineNumber));
        clLabel.setText(String.valueOf(columnNumber));
//            System.out.println(Arrays.toString(txtContent.getText(0, caretPosition).split("\n")));

    }

    public void btnOpenOnAction(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        file = fileChooser.showOpenDialog(txtContent.getScene().getWindow());

        if (file == null) return;

        Stage stageMain = (Stage) txtContent.getScene().getWindow();
        stageMain.setTitle(file.toPath().getFileName().toString());

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            String content = new String(bytes);
            txtContent.setText(content);
        }

    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {

        if (file == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
            file = fileChooser.showSaveDialog(txtContent.getScene().getWindow());
        }

        Stage stageMain = (Stage) txtContent.getScene().getWindow();
        stageMain.setTitle(file.toPath().getFileName().toString());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] bytes = txtContent.getText().getBytes();
            fos.write(bytes);
        }

    }

    public void btnSaveAsOnAction(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        file = fileChooser.showSaveDialog(txtContent.getScene().getWindow());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] bytes = txtContent.getText().getBytes();
            fos.write(bytes);
        }
        Stage stageMain = (Stage) txtContent.getScene().getWindow();
        stageMain.setTitle(file.toPath().getFileName().toString());
    }

    public void txtContentOnKeyPressed(KeyEvent keyEvent) {
//        Stage stageMain = (Stage) txtContent.getScene().getWindow();
//        String existingTitle = stageMain.getTitle();
//
//        txtContent.setOnKeyPressed(event -> {
//            if (existingTitle.startsWith("*")) return;
//            stageMain.setTitle("*" + existingTitle);
//        });
//
//        int caretPosition = txtContent.getCaretPosition();
////        System.out.println(caretPosition);
//
//        isButtonClicked = true;
//
//        System.out.println(keyEvent.getCode());

    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Stage stageMain = (Stage) txtContent.getScene().getWindow();

        String title = stageMain.getTitle();

        if (!isButtonClicked) {
            stageMain.close();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Save Changes Document " + title + " before closing?"
                , ButtonType.YES, ButtonType.CANCEL, new ButtonType("Close Without Saving"));

        alert.setTitle(null);
        alert.setHeaderText(null);

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            btnSave.fire();
            stageMain.close();
        } else if (buttonType.get() == ButtonType.CANCEL) {
            alert.close();
        } else {
            stageMain.close();
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {

        Stage mainStage = (Stage) txtContent.getScene().getWindow();
        String title = mainStage.getTitle();

        if (isButtonClicked) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Do you want to save " + title + " document"
                    , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

            alert.setTitle(null);
            alert.setHeaderText(null);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.YES) {
                btnSave.fire();
                txtContent.clear();
                file = null;
            } else if (buttonType.get() == ButtonType.NO) {
                txtContent.clear();
                file = null;
            } else {
                alert.close();
            }
        }
    }

    public void btnNewWindowOnAction(ActionEvent actionEvent) {
        // todo : implement this

        Stage newStage = new Stage();
        newStage.show();
        newStage.setScene(txtContent.getScene());
        newStage.centerOnScreen();
        newStage.setTitle("untitled");

    }

    public void txtContentOnMouseClicked(MouseEvent mouseEvent) {

    }

    public void btnUndoOnAction(ActionEvent actionEvent) {
        txtContent.undo();
    }

    public void btnCutOnAction(ActionEvent actionEvent) {
        txtContent.cut();
    }

    public void btnCopyOnAction(ActionEvent actionEvent) {
        txtContent.copy();
    }

    public void btnPasteOnAction(ActionEvent actionEvent) {
        txtContent.paste();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnSelectAllOnAction(ActionEvent actionEvent) {
        txtContent.selectAll();
    }

    public void hlpMenuSetOnMouseClicked(MouseEvent mouseEvent) {
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
    }

    public void btnFindOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FindView.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            FindViewController controller = fxmlLoader.getController();
            controller.initData(this);
            controller.setTextToFind(txtContent.getText());
//            ((FindViewController)(fxmlLoader.getController())).initData(this);
            stage.setTitle("Find");
            stage.show();
            stage.centerOnScreen();
            stage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnFindNextOnAction(ActionEvent actionEvent) {

    }

    public void btnFindPreviousOnAction(ActionEvent actionEvent) {

    }

    public void btnReplaceOnAction(ActionEvent actionEvent) {

    }

    public String getTextFromTextContent() {
//        System.out.println("getTextFormTextContent() is executed");
        return txtContent.getText();
    }

    public void selectTheFoundText(int startIndex, int endIndex) {
        txtContent.selectRange(startIndex, endIndex);
    }


}
