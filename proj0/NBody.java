public class NBody{
    public static double readRadius(String path){
        In in = new In(path);
        in.readInt();
		double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int num = in.readInt();
        in.readDouble();
        Planet[] allPlanets = new Planet[num];
        for (int i = 0; i < num; i ++){
            double x0 = in.readDouble();
            double y0 = in.readDouble();
            double vx0 = in.readDouble();
            double vy0 = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(x0, y0, vx0, vy0, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
        for (int i = 0; i < allPlanets.length; i++) allPlanets[i].draw();

        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T){
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            for (int i = 0; i < allPlanets.length; i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i < allPlanets.length; i++){
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
            for (int i = 0; i < allPlanets.length; i++) allPlanets[i].draw();
            StdDraw.show();
            StdDraw.pause(10);
            t = t + dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                          allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
}
    }
}