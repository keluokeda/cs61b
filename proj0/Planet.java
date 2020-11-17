public class Planet {
    private static final double G = 6.67 * 1e-11; // Gravitational constant
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet planet) {
        this.xxPos = planet.xxPos;
        this.yyPos = planet.yyPos;
        this.xxVel = planet.xxVel;
        this.yyVel = planet.yyVel;
        this.mass = planet.mass;
        this.imgFileName = planet.imgFileName;
    }

    public double calcDistance(Planet p) {
        double diffX = xxPos - p.xxPos;
        double diffY = yyPos - p.yyPos;
        double dist = Math.sqrt(diffX * diffX + diffY * diffY);
        return dist;
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double F =  G * mass * p.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffX = p.xxPos - xxPos;
        double fX = F * diffX / r;
        return fX;
    }

    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffY = p.yyPos - yyPos;
        double fY = F * diffY / r;
        return fY;
    }

    private Boolean equals(Planet p) {
        if (xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel &&
                yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName) {
            return true;
        } else {
            return false;
        }
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double fXNet = 0.0;
        for (Planet p: allPlanets) {
            if (equals(p)) {
                continue;
            } else {
                fXNet += calcForceExertedByX(p);
            }
        }
        return fXNet;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double fYNet = 0.0;
        for (Planet p: allPlanets) {
            if (equals(p)) {
                continue;
            } else {
                fYNet += calcForceExertedByY(p);
            }
        }
        return fYNet;
    }

    public void update(double dt, double fX, double fY) {
        double aXNet = fX / this.mass;
        double aYNet = fY / this.mass;

        this.xxVel += dt * aXNet;
        this.yyVel += dt * aYNet;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    /**
     Draws this.Planet at its position
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
