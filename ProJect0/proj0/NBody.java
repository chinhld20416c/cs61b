public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        in.readLine();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String path) {
    In in = new In(path);
    Body[] allBodies = new Body[5];
    in.readLine();
    in.readLine();
    for (int i = 0; i < 5; i++){
    double xxP = in.readDouble();
    double yyP = in.readDouble();
    double xxV = in.readDouble();
    double yyV = in.readDouble();
    double mass = in.readDouble();
    String img = in.readString();

    allBodies[i] = new Body(xxP, yyP, xxV, yyV, mass, img);
    }
    return allBodies;
    }
    
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setScale(-2.50e+11, 2.50e+11);
		// StdDraw.clear();
        // StdDraw.picture(0.5, 0.5, "images/starfield.jpg");
        // StdDraw.show();
        int lenBodies = allBodies.length;
        for (double t = 0; t < T; t = t +dt) {
            
            double[] xForces = new double[lenBodies];
            double[] yForces = new double[lenBodies];
            
            for (int i = 0; i < lenBodies; i++) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
            }
        
            for (int i = 0; i < lenBodies; i++) {
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }
            // Ve background
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-2.50e+11, 2.50e+11);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            for (Body i : allBodies) {
                i.draw();
            }
            for (int i = 0; i < lenBodies; i ++) {
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.show();

        }
        
        
        
        
    }
}
