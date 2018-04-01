public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;

    final double G = 6.67e-11;

    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public void update(double dt, double force_x, double force_y) {
        double ax = force_x / mass;
        double ay = force_y / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;

    }
    /**
     * calculate the square of x
     * @param x double number
     * @return square
     */
    private double square(double x) {
        return x * x;
    }

    /**
     * distance between two planets
     * @param p Planet
     * @return distance
     */
    public double calcDistance(Planet p) {
        return Math.sqrt(square(p.xxPos - xxPos) + square(p.yyPos - yyPos));
    }

    /**
     * force between two planets
     * @param p Planet
     * @return force
     */
    public double calcForceExertedBy(Planet p) {
        return G * p.mass * mass / square(calcDistance(p));
    }

    /**
     * force between two planets along x-direction
     * @param p Planet
     * @return force
     */
    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    /**
     * force between two planets along y-direction
     * @param p Planet
     * @return force
     */
    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps) {

        double force = 0;
        int i = 0;
        while (i < ps.length) {
            if (ps[i] != this) {
                force = force + calcForceExertedByX(ps[i]);
            }
            i = i + 1;
        }

        return force;

    }

    public double calcNetForceExertedByY(Planet[] ps) {

        double force = 0;
        int i = 0;
        while (i < ps.length) {
            if (ps[i] != this) {
                force = force + calcForceExertedByY(ps[i]);
            }
            i = i + 1;
        }

        return force;

    }

    public void draw() {

        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }

}
