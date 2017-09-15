import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	public static void main(String[] args){
		double totalTime = 157788000.0; //total time
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		double radius = readRadius("data/planets.txt");
		Planet[] newplanet = readPlanets("data/planets.txt"); 
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Planet each: newplanet) { //loops through each planet in array
			each.draw();
		}
		
		for(double i=0;i<totalTime; i = i + dt) { //time loop based on increment
			double[] xForces = new double[newplanet.length]; //creates array for x forces
			double[] yForces = new double[newplanet.length]; //creates array for y forces
			for(int k=0;k<newplanet.length;k++) { //loops through each planet again
				double xForce = newplanet[k].calcNetForceExertedByX(newplanet);
				double yForce = newplanet[k].calcNetForceExertedByY(newplanet);
				xForces[k] = xForce; //adds x force to array
				yForces[k] = yForce; //adds y force to array
				
			}
			for(int l=0;l<newplanet.length;l++) { //updates planets
				newplanet[l].update(dt,xForces[l],yForces[l]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg"); //draw background
			for(Planet each: newplanet) { //draws each planet
				each.draw();
			}
			StdDraw.show(10); //pause
		
			
		
//			
			
		}
		System.out.printf("%d\n", newplanet.length); //prints numbers at end
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < newplanet.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		    		newplanet[i].myXPos, newplanet[i].myYPos, 
		    		newplanet[i].myXVel, newplanet[i].myYVel, 
		    		newplanet[i].myMass, newplanet[i].myFileName);	
		
		}
		


		
		
		
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}
	}
		
		
//		String fname= "./data/planets.txt";
//		Planet[] planets = null; // readPlanets(fname);
//		double radius1 = 0.0; // readRadius(fname);
//	
//		System.out.printf("%d\n", planets.length);
//		System.out.printf("%.2e\n", radius1);
//		for (int i = 0; i < planets.length; i++) {
//		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
//		   		              planets[i].myXPos, planets[i].myYPos, 
//		                      planets[i].myXVel, planets[i].myYVel, 
//		                      planets[i].myMass, planets[i].myFileName);	
//		}
//		
//		StdDraw.setScale(-radius1, radius1);
//		StdDraw.picture(0,0,"images/starfield.jpg");
//		
//		for(double t = 0.0; t < totalTime; t += dt) {
//	
//		}
//	}
	public static double readRadius(String fname) { //reads radius of universe
		try {
			Scanner scan = new Scanner(new File(fname)); //scanner
				double planetCount = scan.nextInt();
				double value = scan.nextDouble();
				scan.close();
				return value;   // must return a double here
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			System.exit(0); // print error message, call System.exit()
			return 0.0;
		}
}
	public static Planet[] readPlanets(String fname) { //turns file into array of planets
		Planet[] planets = null;
		try {
		Scanner scan = new Scanner(new File(fname)); //new scanner
		int planetNumber = scan.nextInt();
		scan.nextDouble();
		planets = new Planet[planetNumber];
		for(int i=0;i<planetNumber;i++){ //loops through each line in file 
			double xp = scan.nextDouble();
			double yp = scan.nextDouble();
			double xv = scan.nextDouble();
			double yv = scan.nextDouble();
			double mass = scan.nextDouble();
			String filename = scan.next();
			Planet newPlanet = new Planet(xp, yp, xv, yv, mass, filename); //create new planet
			planets[i]= newPlanet;
				}
		scan.close();
} catch (FileNotFoundException e) {
	System.out.println("Error");
	System.exit(0); // print error message, call System.exit()
}
		return planets;
	}
	
}

