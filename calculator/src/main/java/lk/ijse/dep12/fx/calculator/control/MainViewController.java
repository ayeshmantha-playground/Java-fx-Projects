package lk.ijse.dep12.fx.calculator.control;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.dep12.fx.calculator.Calculator;

import java.util.Objects;

public class MainViewController {
    public AnchorPane root;
    public ImageView btnClose;
    public ImageView btnMinimize;
    public Pane btn0;
    public Pane btn1;
    public Pane btn2;
    public Pane btn3;
    public Pane btn4;
    public Pane btn5;
    public Pane btn6;
    public Pane btn7;
    public Pane btn8;
    public Pane btn9;
    public Pane btnClear;
    public Pane btnEqual;
    public Pane btnMinus;
    public Pane btnMultiply;
    public Pane btnPlus;
    public Pane btnDivide;
    public Label lblResult;
    public Pane mainPane;
    public Label lblCalculation;

    int num1;
    int num2;

    String operator = "";

    public void initialize() {

        lblCalculation.setText(" ");
        root.setOnMousePressed(mouseEvent -> { // To  enable the drag feature of the window
            root.setOnMouseDragged(mouseEvent1 -> {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setX(mouseEvent1.getScreenX() - mouseEvent.getX());
                stage.setY(mouseEvent1.getScreenY() - mouseEvent.getY());
            });
        });

        btnClose.setOnMouseClicked(mouseEvent -> { //To close the window
            ((Stage) root.getScene().getWindow()).close();
        });

        btnMinimize.setOnMouseClicked(mouseEvent -> { // To minimize the window
            ((Stage) root.getScene().getWindow()).setIconified(true);
        });

        Platform.runLater(() -> {
            root.getScene().setOnKeyPressed(keyEvent -> {
//                System.out.println(keyEvent.getCode());

                KeyCodeCombination shiftPlusDigit8 = new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN);
                KeyCodeCombination shiftPlusEquals = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);

                if (shiftPlusDigit8.match(keyEvent)) {
                    setOperator("*");
                    keyEvent.consume();
                } else if (shiftPlusEquals.match(keyEvent)) {
                    setOperator("+");
                    keyEvent.consume();
                }
//                System.out.println(keyEvent.getCode());
                switch (keyEvent.getCode()) {
                    case DIGIT0, NUMPAD0 -> handleNumberPress(0, keyEvent);
                    case DIGIT1, NUMPAD1 -> handleNumberPress(1, keyEvent);
                    case DIGIT2, NUMPAD2 -> handleNumberPress(2, keyEvent);
                    case DIGIT3, NUMPAD3 -> handleNumberPress(3, keyEvent);
                    case DIGIT4, NUMPAD4 -> handleNumberPress(4, keyEvent);
                    case DIGIT5, NUMPAD5 -> handleNumberPress(5, keyEvent);
                    case DIGIT6, NUMPAD6 -> handleNumberPress(6, keyEvent);
                    case DIGIT7, NUMPAD7 -> handleNumberPress(7, keyEvent);
                    case DIGIT8, NUMPAD8 -> handleNumberPress(8, keyEvent);
                    case DIGIT9, NUMPAD9 -> handleNumberPress(9, keyEvent);
                    case ESCAPE -> {
                        lblResult.setText("0");
                        lblCalculation.setText("");
                    }
                    case SUBTRACT, MINUS -> setOperator("-");
                    case SLASH, DIVIDE -> setOperator("/");
                    case STAR, MULTIPLY -> setOperator("*");
                    case PLUS, ADD -> setOperator("+");
                    case ENTER, EQUALS -> {

                        if (Character.isDigit(lblResult.getText().charAt(lblResult.getText().length() - 1))) {
                            lblCalculation.setText(lblResult.getText() + "=");
                            Calculator calculator = new Calculator();
                            Number result = calculator.evaluateExpression(lblResult.getText());
                            lblResult.setText(String.valueOf(result));
                        }

//                        num2 = Integer.parseInt(lblResult.getText());
//                        switch (operator) {
//                            case "+" -> lblResult.setText(String.valueOf(num1 + num2));
//                            case "*" -> lblResult.setText(String.valueOf(num1 * num2));
//                            case "/" -> lblResult.setText(String.valueOf(num1 / num2));
//                            case "-" -> lblResult.setText(String.valueOf(num1 - num2));
//                        }
                    }
                    case BACK_SPACE -> {
                        if (!lblResult.getText().isEmpty() && !lblResult.getText().equals("0")) {
                            lblResult.setText(lblResult.getText().substring(0, lblResult.getText().length() - 1));
                        }
                        if (lblResult.getText().isEmpty()) {
                            lblResult.setText("0");
                        }
                    }

                }
            });
        });

    }


    private void handleNumberPress(int value, KeyEvent keyEvent) { // To get the relevant value when press the number
//        System.out.println("Pressed "+ value);
//        lblResult.setText(Long.parseLong(lblResult.getText()) == 0 ? String.valueOf(value) : String.valueOf(Long.parseLong(lblResult.getText()) * 10 + value));
//        lblResult.setText(Objects.equals(lblResult.getText(), "0") ? String.valueOf(value) : lblResult.getText() + value);

//        if (lblResult.getText().equals("0")) {
//            lblResult.setText(String.valueOf(value));
//        } else if (keyEvent.getCode().isDigitKey() && Character.isDigit(lblResult.getText().charAt(lblResult.getText().length() -1)) && (Character.isDigit(lblCalculation.getText().charAt(0)))) {
//            lblCalculation.setText(" "); //set lblCalculation to a value prevent out of bound error
//            lblResult.setText( lblResult.getText() + value);
//        } else {
//            lblResult.setText(lblResult.getText() + value);
//        }

        if (lblResult.getText().equals("0")) {
            lblResult.setText(String.valueOf(value));
        }else {
            lblResult.setText(lblResult.getText() + value);
        }
    }

    public void btnSymbolOnClicked(MouseEvent mouseEvent) {
        String symbol = ((Pane) mouseEvent.getSource()).getId().replace("btn", "");

        switch (symbol) {
            case "Clear" -> {
                lblResult.setText("0");
                lblCalculation.setText("");
            }
            case "Plus" -> {
                operator = "+";
                lblCalculation.setText(lblCalculation.getText() + lblResult.getText() + "+");
                num1 = Integer.parseInt(lblResult.getText());
                lblResult.setText("0");
            }
            case "Minus" -> {
                operator = "-";
                lblCalculation.setText(lblCalculation.getText() + lblResult.getText() + "-");
                num1 = Integer.parseInt(lblResult.getText());
                lblResult.setText("0");
            }

            case "Multiply" -> {
                operator = "*";
                lblCalculation.setText(lblCalculation.getText() + lblResult.getText() + "x");
                num1 = Integer.parseInt(lblResult.getText());
                lblResult.setText("0");
            }
            case "Divide" -> {
                operator = "/";
                lblCalculation.setText(lblCalculation.getText() + lblResult.getText() + "/");
                num1 = Integer.parseInt(lblResult.getText());
                lblResult.setText("0");
            }
            case "Equal" -> {

                lblCalculation.setText(lblCalculation.getText() + lblResult.getText());
                Calculator calculator = new Calculator();
                Number result = calculator.evaluateExpression(lblCalculation.getText());
                lblResult.setText(String.valueOf(result));


//                num2 = Integer.parseInt(lblResult.getText());
//                switch (operator) {
//                    case "+" -> lblResult.setText(String.valueOf(num1 + num2));
//                    case "*" -> lblResult.setText(String.valueOf(num1 * num2));
//                    case "/" -> lblResult.setText(String.valueOf(num1 / num2));
//                    case "-" -> lblResult.setText(String.valueOf(num1 - num2));
//                }
            }
        }
    }

    private void setOperator(String sign) { //Todo: Looks like this method doesn't work correctly.Check again
        operator = sign;
        lblResult.setText(lblResult.getText() + sign);
//        num1 = Integer.parseInt(lblResult.getText()); // No longer need this code , Calculation class do it
//        lblResult.setText("0");
    }

    public void btnNumberOnClicked(MouseEvent mouseEvent) {
        int value = Integer.parseInt(((Pane) mouseEvent.getSource()).getId().replace("btn", ""));//get the value of the number
        lblResult.setText(Long.parseLong(lblResult.getText()) == 0 ? String.valueOf(value) : String.valueOf(Long.parseLong(lblResult.getText()) * 10 + value));
        //set the number to the result label
    }
}
