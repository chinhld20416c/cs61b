public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Constructer 1 tạo một íntance với các thông số mong muốn */
    public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
              }
    
    /** Constructer 2 tạo một instance với các thuộc tính copy từ một instance khác */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    
    /** Return the distance between two instace */
    public double calcDistance(Body b) {
        double dx = (b.xxPos - this.xxPos);
        double dy = (b.yyPos - this.yyPos);
        double rSquar = (dx*dx + dy*dy);
        return Math.sqrt(rSquar);
    }
    public static final double g = 6.67e-11;
    /** Return the Force that a instance exert to current instance */
    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        return (g * this.mass * b.mass) / (r * r) ;
    }
     
    /** Tinh lực thành phần trên phương x gây bởi a instance lên current instance */
    public double calcForceExertedByX(Body b) {
        double dx = (b.xxPos - this.xxPos);
        double r = calcDistance(b);
        double f = calcForceExertedBy(b);
        return (f * dx) / r;

    }

    /** Tinh lực thành phần trên phương y gây bởi a instance lên current instance */
    public double calcForceExertedByY(Body b) {
        double dy = (b.yyPos - this.yyPos);
        double r = calcDistance(b);
        double f = calcForceExertedBy(b);
        return (f * dy) / r;
    }
    /** Tính tổng hợp lực gây cho curent instance trên phương x  */
    public double calcNetForceExertedByX(Body[] all) {
        double sumFx = 0;
        for (Body i : all) {
            if (i.equals(this)) {
                continue;
            }
            sumFx += calcForceExertedByX(i);
        }
        return sumFx;
    }

    /** Tính tổng hợp lực gây cho curent instance trên phương y  */
    public double calcNetForceExertedByY(Body[] all) {
        double sumFy = 0;
        for (Body i : all) {
            if (i.equals(this)) {
                continue;
            }
            sumFy += calcForceExertedByY(i);
        }
        return sumFy;
    }

    /** Function for updating position and velocity of current instance in a bit perior time*/
    public void update(double t, double fx, double fy) {
        /** Caculating acceleration repectively causing by forces in direct x and forces in direct y */
        double ax = fx / (this.mass);
        double ay = fy / (this.mass);
        this.xxVel = this.xxVel + ax * t;
        this.yyVel = this.yyVel + ay * t;
        this.xxPos = this.xxPos + this.xxVel * t;
        this.yyPos = this.yyPos + this.yyVel * t;

    }

    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-2.50e+11, 2.50e+11);
		//StdDraw.clear();
        //sStdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        //StdDraw.show();
		StdDraw.pause(10);
        
    }
   
   
}