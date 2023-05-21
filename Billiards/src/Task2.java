import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {
    private static final double RADIUS = 1.0;
    private static final double DELTA = 1e-6;
    public static void main(String[] args) {
        Random random = new Random();
        double x0 = random.nextDouble() * RADIUS;
        double y0 = Math.sqrt(Math.pow(RADIUS, 2) - Math.pow(x0, 2));
        double px0 = random.nextDouble();
        double py0 = Math.sqrt(1 - Math.pow(px0, 2));
        int n = 10;
        double x = x0;
        double y = y0;
        double px = px0;
        double py = py0;
        List<double[]> reflectionPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double intersectionX = x;
            double intersectionY = y;
            double threshold = 1e-6;

            while (Math.pow(intersectionX, 2) + Math.pow(intersectionY, 2) >= Math.pow(RADIUS, 2)) {
                double f = Math.pow(intersectionX, 2) + Math.pow(intersectionY, 2) - Math.pow(RADIUS, 2);
                double dfdx = 2 * intersectionX;
                double dfdy = 2 * intersectionY;
                intersectionX -= f / dfdx;
                intersectionY -= f / dfdy;
                if (Math.abs(f) < threshold) {
                    break;
                }
            }

            double normalX = intersectionX / RADIUS;
            double normalY = intersectionY / RADIUS;
            double dotProduct = px * normalX + py * normalY;
            double reflectionX = px - 2 * dotProduct * normalX;
            double reflectionY = py - 2 * dotProduct * normalY;
            double newPx = (Math.pow(reflectionY, 2) - Math.pow(reflectionX, 2)) * px - 2 * reflectionX * reflectionY * py;
            double newPy = -2 * reflectionX * reflectionY * px + (Math.pow(reflectionX, 2) - Math.pow(reflectionY, 2)) * py;
            x = reflectionX;
            y = reflectionY;
            px = newPx;
            py = newPy;
            reflectionPoints.add(new double[]{x, y});
        }

        double reversedPx = -px;
        double reversedPy = -py;
        List<double[]> reversedPath = new ArrayList<>(reflectionPoints);
        reversedPath.add(new double[]{x0, y0});
        boolean pathsCoincide = true;
        for (int i = 0; i < reflectionPoints.size(); i++) {
            double[] point = reflectionPoints.get(i);
            double[] reversedPoint = reversedPath.get(reflectionPoints.size() - 1 - i);

            if (Math.abs(point[0] - reversedPoint[0]) > DELTA || Math.abs(point[1] - reversedPoint[1]) > DELTA) {
                pathsCoincide = false;
                break;
            }
        }

        for (int i = 0; i < reflectionPoints.size(); i++) {
            double[] point = reflectionPoints.get(i);
            System.out.println("Reflection " + (i + 1) + ": (" + point[0] + ", " + point[1] + ")");
        }
        System.out.println();
        if (pathsCoincide) {
            System.out.println("The reversed path coincides with the straight path.");
        } else {
            System.out.println("The paths deviate after " + reflectionPoints.size() + " reflections.");
        }
    }
}
