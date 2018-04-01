public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);

        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();

        for (int i=0; i < planets.length; i++) {
            planets[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;
        int num = planets.length;
        double[] xForces = new double[num];
        double[] yForces = new double[num];

        while (time < T) {
            for (int i=0; i < num; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i=0; i < num; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }

        StdOut.printf("%d\n", num);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < num; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();

    }

    public static Planet[] readPlanets(String filename) {
        Planet[] planets = new Planet[5];

        In in = new In(filename);
        in.readInt();
        in.readDouble();

        for (int i = 0; i < 5; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }
}
