public class TestPlanet {
    /**
     * Tests the Planet constructor to make sure it's working correctly.
     */
    public static void main(String[] args) {
        checkPlanetForce();
    }

    private static void checkPlanetForce() {
        System.out.println("Checking Planet force...");
        double xxPos = 1.0,
                yyPos = 2.0,
                xxVel = 3.0,
                yyVel = 4.0,
                mass = 5.0;

        String imgFileName = "jupiter.gif";
        Planet p1 = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        xxPos = 1.0;
        yyPos = 1.0;
        xxVel = 3.0;
        yyVel = 4.0;
        mass = 5.0;

        Planet p2 = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        System.out.println(p1.calcForceExertedBy(p2));

    }

}
