public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  
  static final double G = 6.67E-11;

  public Planet(double xP, double yP, double xV,
                double yV, double m, String img){
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
                }
  
  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p){
    double x_diff = xxPos - p.xxPos;
    double y_diff = yyPos - p.yyPos;
    double dist = Math.sqrt((x_diff * x_diff + y_diff * y_diff));
    return dist;
  }

  public double calcForceExertedBy(Planet p){
    double dist = this.calcDistance(p);
    double F = G * mass * p.mass / (dist * dist);
    return F;
  }

  public double calcForceExertedByX(Planet p){
    double F = this.calcForceExertedBy(p);
    double dist = this.calcDistance(p);
    double Fx = F * (p.xxPos - xxPos)/dist;
    return Fx;
  }

  public double calcForceExertedByY(Planet p){
    double F = this.calcForceExertedBy(p);
    double dist =this.calcDistance(p);
    double Fy = F * (p.yyPos - yyPos)/dist;
    return Fy;
  }

  public double calcNetForceExertedByX(Planet[] allPlanets){
    double net_Fx = 0;
    for(int i = 0; i < allPlanets.length; i++)
      if (!this.equals(allPlanets[i])) 
      net_Fx = net_Fx + this.calcForceExertedByX(allPlanets[i]);
    return net_Fx;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets){
    double net_Fy = 0;
    for(int i = 0; i < allPlanets.length; i++)
    if (!this.equals(allPlanets[i]))  
    net_Fy = net_Fy + this.calcForceExertedByY(allPlanets[i]);
    return net_Fy;
  }

  public void update(double dt, double Fx, double Fy){
    double ax = Fx / mass;
    double ay = Fy / mass;
    xxVel = xxVel + ax * dt;
    yyVel = yyVel + ay * dt;
    xxPos = xxPos + xxVel * dt;
    yyPos = yyPos + yyVel * dt;
  }

  public void draw(){
    String full_path = "images/" + imgFileName;
    StdDraw.picture(xxPos, yyPos, full_path);
  }
}
