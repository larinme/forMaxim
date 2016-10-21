import java.util.Scanner;

public class secondLab {
    private static boolean exitMenu() {
        Scanner sc = new Scanner(System.in);
        int  menuItem = sc.nextInt();
        if(menuItem == 1) {
            return true;
        } else {
            return false;
        }
    }
    private static void printResult(double[] result) {
        for(int i = 0; i < result.length; i++) {
            System.out.format("%6.4f\t\t  ", result[i]);
        }
        System.out.println();
    }
    private static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        int countOfComma = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == '.') {
                countOfComma++;
            }
            if(!((int)chars[i] >= 47 && (int)chars[i] <= 57 || chars[i] == ' ' || countOfComma == 1)) {
                return false;
            }
        }
        return true;
    }
    private static String init() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine().replace(',', '.');
            if(!isValid(s)) {
                System.out.println("Incorrect data");
            } else {
                return s;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MethodOfRectangles integral = new MethodOfRectangles();
        int N = 3;
        double[] resultLeft = new double[N];
        double[] resultAverage = new double[N];
        double[] resultRight = new double[N];
        boolean exit = true;

        while (exit) {
            System.out.println("Input a and b > a: ");
            System.out.print("a = ");
            double a = Double.parseDouble(init());
            System.out.print("b = ");
            double b = Double.parseDouble(init());
            if(a > b) {
                double temp = b;
                b = a;
                a = temp;
            }
            System.out.println("Input e:");
            double e = Double.parseDouble(init());
            System.out.format("Entered data: a = %.2f; b = %.2f; e = %.5f\n", a, b, e);

            System.out.println("Select function: ");
            Integral.printIntegral();
            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    resultLeft = integral.leftRectagles(x -> (-Math.sqrt(x*x+3*x-1)/x), a, b, e);
                    resultAverage = integral.averageRectagles(x -> (-Math.sqrt(x*x+3*x-1)/x), a, b, e);
                    resultRight = integral.rightRectagles(x -> (-Math.sqrt(x*x+3*x-1)/x), a, b, e);
                    break;
                }
                case 2: {
                    resultLeft = integral.leftRectagles(x -> Math.pow(x, 3), a, b, e);
                    resultAverage = integral.averageRectagles(x -> Math.pow(x, 3), a, b, e);
                    resultRight = integral.rightRectagles(x -> Math.pow(x, 3), a, b, e);
                    break;
                }
                case 3: {
                    resultLeft = integral.leftRectagles(x -> (8 + 2*x - x*x), a, b, e);
                    resultAverage = integral.averageRectagles(x -> (8 + 2*x -x*x), a, b, e);
                    resultRight = integral.rightRectagles(x -> (8 + 2*x - x*x), a, b, e);
                    break;
                }
                default: {
                    System.out.println("Incorrect n, Try again.");
                    break;
                }
            }

            System.out.println("\t\tResult\t Count of rects\t\tError");
            System.out.print("Left   \t");
            printResult(resultLeft);
            System.out.print("Average\t");
            printResult(resultAverage);
            System.out.print("Right  \t");
            printResult(resultRight);

            System.out.println("Continue with new data?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            exit = exitMenu();
        }
    }
}
