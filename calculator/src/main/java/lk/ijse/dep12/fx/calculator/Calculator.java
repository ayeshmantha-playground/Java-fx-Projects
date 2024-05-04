package lk.ijse.dep12.fx.calculator;

import java.util.Stack;

public class Calculator {
    public Number evaluateExpression(String expression) {
        Stack<Number> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one position to handle the next character correctly
                numbers.push(Integer.parseInt(num.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    evaluateOperator(numbers, operators);
                }
                operators.pop(); // Pop '('
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    evaluateOperator(numbers, operators);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            evaluateOperator(numbers, operators);
        }

        return numbers.pop();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private void evaluateOperator(Stack<Number> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        Number operand2 = numbers.pop();
        Number operand1 = numbers.pop();
        Number result = (performOperation(operand1, operand2, operator));
        numbers.push( result);
    }

    private Number performOperation(Number operand1, Number operand2, char operator) {
        switch (operator) {
            case '+':
                return (operand1.intValue() + operand2.intValue());
            case '-':
                return operand1.intValue() - operand2.intValue();
            case '*':
                return operand1.intValue() * operand2.intValue();
            case '/':
                if (operand2.intValue() == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                if (operand1.intValue() %  operand2.intValue() == 0) {
                    return (operand1.intValue() / operand2.intValue());
                } else {
                    return operand1.doubleValue() / operand2.doubleValue();
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}

