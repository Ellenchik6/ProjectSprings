import java.util.Random;
public class Task3 {
    private static final double RADIUS = 1.0;
    public static void main(String[] args) {
        Random random = new Random();
        int reflections = 100000;
        double l = 1.0;
        int numberOfBins = 10;
        int[] binCount = new int[numberOfBins];
        double x0, y0;
        do {
            x0 = (random.nextDouble() * 2) - 1;
            y0 = (random.nextDouble() * 2) - 1;
        } while (x0 * x0 + y0 * y0 > RADIUS * RADIUS);
        double px0, py0;
        do {
            px0 = (random.nextDouble() * 2) - 1;
            py0 = (random.nextDouble() * 2) - 1;
        } while (Math.sqrt(px0 * px0 + py0 * py0) > 1);
        double magnitude = Math.sqrt(px0 * px0 + py0 * py0);
        px0 /= magnitude;
        py0 /= magnitude;
        double x = x0;
        double y = y0;
        double px = px0;
        double py = py0;

        for (int i = 0; i < reflections; i++) {
            double intersectionX = x + px * (2 * RADIUS);
            double intersectionY = y + py * (2 * RADIUS);
            double dx = px * (2 * RADIUS);
            double dy = py * (2 * RADIUS);
            double tolerance = 1e-6;
            int maxIterations = 1000;
            for (int iter = 0; iter < maxIterations; iter++) {
                double f = intersectionX * intersectionX + intersectionY * intersectionY - RADIUS * RADIUS;
                double fPrime = 2 * intersectionX * dx + 2 * intersectionY * dy;

                double delta = f / fPrime;
                intersectionX -= delta * dx;
                intersectionY -= delta * dy;

                if (Math.abs(delta) < tolerance) {
                    break;
                }
            }

            String side;
            if (intersectionY > 0 && Math.abs(intersectionX) <= l / 2) {
                side = "Top Line Segment";
            } else if (intersectionY < 0 && Math.abs(intersectionX) <= l / 2) {
                side = "Bottom Line Segment";
            } else {
                side = "Semicircles";
            }

            double reflectionX, reflectionY;
            if (side.equals("Top Line Segment") || side.equals("Bottom Line Segment")) {
                reflectionX = intersectionX;
                reflectionY = -intersectionY;
            } else {
                double xc = (side.equals("Semicircles") && intersectionX < 0) ? -l / 2 : l / 2;
                reflectionX = (intersectionY * intersectionY - (intersectionX - xc) * (intersectionX - xc)) * px
                        - 2 * (intersectionX - xc) * intersectionY * py;
                reflectionY = -2 * (intersectionX - xc) * intersectionY * px
                        + ((intersectionX - xc) * (intersectionX - xc) - intersectionY * intersectionY) * py;
                px = reflectionX;
                py = reflectionY;
            }

            System.out.println("Reflection " + (i + 1) + ": (" + reflectionX + ", " + reflectionY + ")");
            System.out.println("Side Hit: " + side);
            System.out.println("Next Intersection: (" + intersectionX + ", " + intersectionY + ")");
            System.out.println("New Momentum: (" + px + ", " + py + ")");
            System.out.println();
            x = reflectionX;
            y = reflectionY;
            double[] reflectionPoint = { reflectionX, reflectionY };
            int binIndex = (int) ((reflectionX + l / 2) / l * numberOfBins);
            if (binIndex >= 0 && binIndex < numberOfBins) {
                binCount[binIndex]++;
            }
        }
        double mean = reflections / (double) numberOfBins;
        double variance = 0;
        for (int count : binCount) {
            variance += (count - mean) * (count - mean);
        }
        variance /= numberOfBins;
        System.out.println("Bin\tCount");
        System.out.println("-----------------");
        for (int i = 0; i < numberOfBins; i++) {
            System.out.println(i + "\t" + binCount[i]);
        }
        System.out.println("-----------------");
        System.out.println("Mean: " + mean);
        System.out.println("Variance: " + variance);
    }
}

