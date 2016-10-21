import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class MethodOfRectangles {
    public static Map<String, Double> leftRectagles(DoubleUnaryOperator f, double a, double b, double e) {
        return null;
    }
    public static Map<String, Double> averageRectagles(DoubleUnaryOperator f, double a, double  b, double e) {

        if (f == null) {
            return null;
        }
        Map<String, Double> resultat = new HashMap<>();
        double[] temp = new double[2];
        double err = Double.POSITIVE_INFINITY;
        double n = 1;
        double h;

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

        resultat.put("result", temp[1]);
        resultat.put("countOfRectangles", n);
        resultat.put("errors", err);

        return resultat;
    }
    public static Map<String, Double> rightRectagles(DoubleUnaryOperator f, double a, double b, double e){return null;}
}
