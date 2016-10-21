import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class secondLab {

    private static final String INFO_PARAMS = "Entered data: a = {0}, b = {1}, c = {2}";

    public static void main(String[] args) {

        beginProgram();
    }

    private static void beginProgram() {

        boolean doRepeat = true;

        while (doRepeat) {

            Map<String, Number> inputParams = getInputParams();

            double a = (double) inputParams.get("a");
            double b = (double) inputParams.get("b");
            double e = (double) inputParams.get("e");
            int n = (int) inputParams.get("functionNumber");

            System.out.println(MessageFormat.format(INFO_PARAMS, a, b, e));

            DoubleUnaryOperator doubleUnaryOperator = getFunction(n);
            Map<String, Double> resultLeft = MethodOfRectangles.leftRectagles(doubleUnaryOperator, a, b, e);
            Map<String, Double> resultAverage = MethodOfRectangles.averageRectagles(doubleUnaryOperator, a, b, e);
            Map<String, Double> resultRight = MethodOfRectangles.rightRectagles(doubleUnaryOperator, a, b, e);

            printResults(resultAverage);

            System.out.println("Continue with new data?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            doRepeat = exitMenu();
        }
    }

    private static DoubleUnaryOperator getFunction(int n) {
        DoubleUnaryOperator doubleUnaryOperator = null;
        switch (n) {
            case 1: {
                doubleUnaryOperator = x -> (-Math.sqrt(x * x + 3 * x - 1) / x);
                break;
            }
            case 2: {
                doubleUnaryOperator = x -> Math.pow(x, 3);
                break;
            }
            case 3: {
                doubleUnaryOperator = x -> (8 + 2 * x - x * x);
                break;
            }
            default: {
                System.out.println("Incorrect n, Try again.");
                break;
            }
        }
        return doubleUnaryOperator;
    }

    private static Map<String, Number> getInputParams() {

        Map<String, Number> values = new HashMap<>();

        System.out.println("Input a and b > a: ");
        System.out.print("a = ");
        Double aParameter = Double.parseDouble(readParameter(System.in));
        System.out.print("b = ");
        Double bParameter = Double.parseDouble(readParameter(System.in));
        if (aParameter > bParameter) {
            double temp = bParameter;
            bParameter = aParameter;
            aParameter = temp;
        }
        System.out.println("Input e:");
        Double epsilon = Double.parseDouble(readParameter(System.in));
        System.out.println("Select function: ");
        Integral.printIntegral();
        Integer functionNumber = Integer.parseInt(readParameter(System.in));

        values.put("a", aParameter);
        values.put("b", bParameter);
        values.put("e", epsilon);
        values.put("functionNumber", functionNumber);

        return values;
    }

    private static String readParameter(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        boolean isNotNumber = true;
        String inputString = "";
        while (isNotNumber) {
            inputString = scanner.nextLine().replace(',', '.');
            if (!isValid(inputString)) {
                System.out.println("Incorrect data");
            } else {
                isNotNumber = false;
            }
        }
        return inputString;
    }

    private static boolean isValid(String inputString) {
        char[] chars = inputString.toCharArray();

        int countOfComma = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                countOfComma++;
            }
            if (!((int) chars[i] >= 47 && (int) chars[i] <= 57 || chars[i] == ' ' || countOfComma == 1)) {
                return false;
            }
        }
        return true;
    }

    private static void printResults(Map<String, Double> result) {
        System.out.println("\t\tResult\t Count of rects\t\tError");
        System.out.print("Left   \t");
        //printInfo(resultLeft);
        System.out.print("Average\t");
        printInfo(result);
        System.out.print("Right  \t");
        //printInfo(resultRight);
    }

    private static void printInfo(Map<String, Double> result) {

        for (Double value : result.values()) {
            System.out.format("%6.4f\t\t  ", value);
        }
    }

    private static boolean exitMenu() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt() == 1;
    }
}
