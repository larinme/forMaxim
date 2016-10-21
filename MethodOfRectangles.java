import java.util.function.DoubleUnaryOperator;

public class MethodOfRectangles {
    public static double[] leftRectagles(DoubleUnaryOperator f, double a, double b, double e) {}
    public static double[] averageRectagles(DoubleUnaryOperator f, double a, double  b, double e) {
            double[] result = new double[3];
            double[] temp = new double[2];
            double err = Double.POSITIVE_INFINITY;
            double h = 0;
            int n = 1;

            while (e < err) {
                for(int j = 0; j < 2; j++) {
                    temp[j] = 0;
                    h = (b - a) / n;
                    for (int i = 0; i < n; i++) {
                        temp[j] += f.applyAsDouble(a + h * (i + 0.5));
                    }
                    temp[j] *= h;
                    n *= 2;
                }
                err = Math.abs(temp[1] - temp[0]) / 3;
                temp[0] = n;
            }

            result[0] = temp[1];
            result[1] = n;
            result[2] = err;
            return result;
        }
    public static double[] rightRectagles(DoubleUnaryOperator f, double a, double b, double e){}
}
