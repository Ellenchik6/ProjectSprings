public class Spring {
    private double k = 1.0;

    public Spring() {}

    public Spring(double k) {
        this.k = k;
    }

    public double getStiffness() {
        return k;
    }

    private void setStiffness(double k) {
        this.k = k;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        int n = (int) Math.ceil(t / dt);
        double[] x = new double[n];
        double[] v = new double[n];

        x[0] = x0;
        v[0] = v0;

        for (int i = 1; i < n; i++) {
            double a = -k * x[i-1];
            v[i] = v[i-1] + a * dt;
            x[i] = x[i-1] + v[i] * dt;
        }

        return x;
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0.0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        int n = (int) Math.ceil((t1 - t0) / dt);
        double[] x = new double[n];
        double[] v = new double[n];

        x[0] = x0;
        v[0] = v0;

        for (int i = 1; i < n; i++) {
            double a = -k * x[i-1];
            v[i] = v[i-1] + a * dt;
            x[i] = x[i-1] + v[i] * dt;
        }

        return x;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        int n = (int) Math.ceil((t1 - t0) / dt);
        double[] x = new double[n];
        double[] v = new double[n];

        x[0] = x0;
        v[0] = v0;

        for (int i = 1; i < n; i++) {
            double a = -k * x[i-1] / m;
            v[i] = v[i-1] + a * dt;
            x[i] = x[i-1] + v[i] * dt;
        }

        return x;
    }
}
