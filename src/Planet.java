
public class Planet {

	double myXPos;            // current x position
	double myYPos;            // current y position
	double myXVel;            // current velocity in x direction 
	double myYVel;            // current velocity in y direction
	double myMass;            // mass of planet
	String myFileName;        // file name (in images folder) 
	
	public Planet(double xp, double yp, double xv,
            double yv, double mass, String filename) {
		myXPos = xp; 
		myYPos = yp; 
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Planet(Planet p) { //creates copy
		this.myXPos = p.myXPos; 
		this.myYPos = p.myYPos; 
		this.myXVel = p.myXVel;
		this.myYVel = p.myYVel;
		this.myMass = p.myMass;
		this.myFileName = p.myFileName;
		
	}
	
	public double calcDistance(Planet p) { //uses distance formula, returns distance
		double distance = Math.sqrt(Math.pow(p.myXPos-this.myXPos,2) + Math.pow(p.myYPos-this.myYPos,2));
		return distance;
	}
	public void draw() { //draws planet
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
		
	}
	
	public double calcForceExertedBy(Planet p) { //returns force
		double distance = this.calcDistance(p);
		double G = 6.67 * Math.pow(10, -11);	
		double F = G * (p.myMass * this.myMass) / Math.pow(distance,2);
		double r2 = (p.myXPos - this.myXPos) + (p.myYPos - this.myYPos);
		return F;
	}
	
	public double calcForceExertedByX(Planet p) { //returns x force
		double Fx = (this.calcForceExertedBy(p) * (p.myXPos - this.myXPos)) / this.calcDistance(p);
		return Fx;
	}
	
public double calcForceExertedByY(Planet p) { //returns y force
	double Fy = (this.calcForceExertedBy(p) * (p.myYPos - this.myYPos)) / this.calcDistance(p);
	return Fy;
	}

public double calcNetForceExertedByX(Planet[] p) { //returns net x force
	double sum = 0;
	for(int i=0;i<p.length;i++){
		if(! p[i].equals(this)) {
			sum += calcForceExertedByX(p[i]);
		}
	}
	return sum;
	
}

public double calcNetForceExertedByY(Planet[] p) { //returns net y force
	double sum = 0;
	for(int i=0;i<p.length;i++){
		if(! p[i].equals(this)) {
			sum += calcForceExertedByY(p[i]);
		}
	}
	return sum; 
}



public void update(double seconds, double xforce, double yforce) { //calculates new position and velocity of planet
	double accelerationX = xforce / myMass;
	double accelerationY = yforce / myMass;
	double newVelocityX = (myXVel + seconds * accelerationX); //updates x velocity
	double newVelocityY = (myYVel + seconds * accelerationY); //updates y velocity
	double newXPos = (myXPos + seconds * newVelocityX); //updates pos
	double newYPos = (myYPos + seconds * newVelocityY); //updates pos
	myXPos = newXPos;
	myYPos = newYPos;
	myXVel = newVelocityX;
	myYVel = newVelocityY;
}
}
