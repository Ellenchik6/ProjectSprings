import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 {
    private static final double EPSILON = 1e-6;
    private static final double DELTA = 1e-4;
    public static void main(String[] args) {
        Random random = new Random();
        double radius = 1.0;
        int n = 10;
        double x0 = random.nextDouble() * 2 * radius - radius;
        double y0 = random.nextDouble() * 2 * radius - radius;
        double px0 = random.nextDouble() - 0.5;
        double py0 = random.nextDouble() - 0.5;
        double magnitude = Math.sqrt(px0 * px0 + py0 * py0);
        px0 /= magnitude;
        py0 /= magnitude;
        double x = x0;
        double y = y0;
        double px = px0;
        double py = py0;
        List<double[]> reflectionPoints = new ArrayList<>();
        reflectionPoints.add(new double[]{x, y});

        for (int i = 0; i < n - 1; i++) {
            double t = findIntersection(x, y, px, py, radius);
            double nextX = x + t * px;
            double nextY = y + t * py;
            double ppx = (nextY * nextY - nextX * nextX) * px - 2 * nextX * nextY * py;
            double ppy = -2 * nextX * nextY * px + (nextX * nextX - nextY * nextY) * py;

            reflectionPoints.add(new double[]{nextX, nextY});
            x = nextX;
            y = nextY;
            px = ppx;
            py = ppy;
        }

        System.out.println("Reflection Points:");
        for (int i = 0; i < reflectionPoints.size(); i++) {
            double[] point = reflectionPoints.get(i);
            System.out.println("Reflection " + (i + 1) + ": (" + point[0] + ", " + point[1] + ")");
        }

        double reversedPx = -px;
        double reversedPy = -py;
        for (int i = n - 1; i > 0; i--) {
            double[] reflectionPoint = reflectionPoints.get(i);
            double pxPrev = reversedPx;
            double pyPrev = reversedPy;
            reversedPx = (reflectionPoint[1] * reflectionPoint[1] - reflectionPoint[0] * reflectionPoint[0]) * pxPrev
                    - 2 * reflectionPoint[0] * reflectionPoint[1] * pyPrev;
            reversedPy = -2 * reflectionPoint[0] * reflectionPoint[1] * pxPrev
                    + (reflectionPoint[0] * reflectionPoint[0] - reflectionPoint[1] * reflectionPoint[1]) * pyPrev;
        }

        boolean pathsCoincide = true;
        for (int i = 0; i < reflectionPoints.size(); i++) {
            double[] originalPoint = reflectionPoints.get(i);
            double[] reversedPoint = reflectionPoints.get(reflectionPoints.size() - 1 - i);
            double dx = Math.abs(originalPoint[0] - reversedPoint[0]);
            double dy = Math.abs(originalPoint[1] - reversedPoint[1]);
            if (dx > DELTA || dy > DELTA) {
                pathsCoincide = false;
                break;
            }
        }

        if (pathsCoincide) {
            System.out.println("The reversed path coincides with the straight path.");
        } else {
            System.out.println("The reversed path deviates from the straight path.");
            int deviationIndex = -1;
            for (int i = 0; i < reflectionPoints.size(); i++) {
                double[] originalPoint = reflectionPoints.get(i);
                double[] reversedPoint = reflectionPoints.get(reflectionPoints.size() - 1 - i);
                double dx = Math.abs(originalPoint[0] - reversedPoint[0]);
                double dy = Math.abs(originalPoint[1] - reversedPoint[1]);
                if (dx > DELTA || dy > DELTA) {
                    deviationIndex = i;
                    break;
                }
            }

            if (deviationIndex >= 0) {
                System.out.println("The paths deviate more than delta after " + (deviationIndex + 1) + " reflections.");
            } else {
                System.out.println("The paths deviate more than delta from the start.");
            }
        }
    }

    private static double findIntersection(double x, double y, double px, double py, double radius) {
        double t = 0.0;
        while (true) {
            double f = x + t * px - radius;
            double df = px;
            double deltaT = -f / df;
            t += deltaT;
            if (Math.abs(deltaT) < EPSILON) {
                break;
            }
        }
        return t;
    }
}

