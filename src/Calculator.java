import java.util.*;
import java.util.regex.*;
import java.util.function.Function;
import java.util.function.BiFunction;

public class Calculator {
    private static final List<String> history = new ArrayList<>();

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double squareRoot(double number) {
        return Math.sqrt(number);
    }

    public static double absolute(double number) {
        return Math.abs(number);
    }

    public static double roundNumber(double number) {
        return Math.round(number);
    }

    public static void addToHistory(String expression, double result) {
        history.add(expression + " = " + result);
    }

    public static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No calculations in history.\n");
        } else {
            System.out.println("Calculation History:");
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression;
        char choice;

        System.out.println("Welcome to the Calculator!");

        do {
            System.out.print("Please enter your arithmetic expression: ");
            expression = scanner.nextLine();

            try {
                double result = evaluateExpression(expression);
                System.out.println("Result: " + result);
                addToHistory(expression, result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Do you want to continue? (y/n): ");
            choice = scanner.next().charAt(0);
            scanner.nextLine();
        } while (choice == 'y' || choice == 'Y');

        showHistory();
        System.out.println("Thank you for using the Calculator!");
        scanner.close();
    }

    public static double evaluateExpression(String expression) {
        expression = replaceFunctions(expression, "abs\\((-?\\d+(\\.\\d+)?)\\)", (x) -> absolute(Double.parseDouble(x)));
        expression = replaceFunctions(expression, "sqrt\\((-?\\d+(\\.\\d+)?)\\)", (x) -> squareRoot(Double.parseDouble(x)));
        expression = replaceFunctions(expression, "round\\((-?\\d+(\\.\\d+)?)\\)", (x) -> roundNumber(Double.parseDouble(x)));
        expression = replaceFunctions(expression, "pow\\((-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)\\)", (x, y) -> power(Double.parseDouble(x), Double.parseDouble(y)));

        try {
            return parseExpression(expression);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    private static String replaceFunctions(String expression, String regex, Function<String, Double> func) {
        Matcher matcher = Pattern.compile(regex).matcher(expression);
        while (matcher.find()) {
            String match = matcher.group();
            String value = matcher.group(1);
            double result = func.apply(value);
            expression = expression.replace(match, String.valueOf(result));
        }
        return expression;
    }

    private static String replaceFunctions(String expression, String regex, BiFunction<String, String, Double> func) {
        Matcher matcher = Pattern.compile(regex).matcher(expression);
        while (matcher.find()) {
            String match = matcher.group();
            String value1 = matcher.group(1);
            String value2 = matcher.group(2);
            double result = func.apply(value1, value2);
            expression = expression.replace(match, String.valueOf(result));
        }
        return expression;
    }

    public static double parseExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(num.toString()));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }

    private static int precedence(char ch) {
        switch (ch) {
            case '+': case '-': return 1;
            case '*': case '/': case '%': return 2;
            default: return -1;
        }
    }

    private static double applyOperator(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': if (b == 0) throw new ArithmeticException("Division by zero"); return a / b;
            case '%': if (b == 0) throw new ArithmeticException("Modulo by zero"); return a % b;
            default: return 0;
        }
    }
}
