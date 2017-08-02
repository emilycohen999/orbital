package Orbital;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import polyfun.Polynomial;

import javax.swing.JPanel;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.BoundedShape;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

/*
 * Annabel Strauss, Emily Cohen, Karina Shah
 * April/May 2015
 * Comp Sci X
 * Oribtal -- Kepler's Laws:
 *  1. Planets orbit in elliptical orbits (graph the ellipse it should be)
 *  2. Equal areas are swept away in equal times (i.e. max velocity will be closest to the sun and min will be furthest)
 *  3. T^2(years) = a^3 (AU -> semi-major axis of the earth's orbit of the sun)
 * Version 2.6
 */
public class OrbitalSafe extends AbstractSimulation {
  ArrayList<Particle> planet = new ArrayList<Particle>();
  ArrayList<Trail> trails = new ArrayList<Trail>();
  Trail asymp = new Trail();
  Trail asymp2 = new Trail();
  Trail hyperbola = new Trail();
  Trail hyperbolanegative = new Trail();
  Trail tracemoon = new Trail();
  DisplayFrame frame = new DisplayFrame("X", "Y", "Orbital Simulation");
  DisplayFrame framemoon = new DisplayFrame("X", "Y", "Earth-Moon Simulation");
  double time = 0;
  double timestep = 100;
  double timestepmoon = 100;
  double distance = 0;
  double xdist = 0;
  double ydist = 0;
  double xforce = 0;
  double yforce = 0;
  double [] xspaces = new double [2];
  double [] yspaces = new double [2];
  int locationx = 0;
  int locationy = 0;
  double height = 0;
  double width = 0;
  double centerx = 0;
  double centery = 0;
  double maxY = 0;
  double topX = 0;
  double B = 0;
  double A = 0;
  double C = 0;
  boolean passedMaxY = false;
  boolean graphedEllipse=false;
  double minVel = 10E100;
  double minVelx = 0;
  double minVely = 0;
  double periodtime = 0;
  double real;
  boolean periodtracker = false;
  boolean periodalready = false;
  double previousx = 0;
  double previousy = 0;
  double sidea = 0;
  double sideb = 0;
  double sidec = 0;
  double s = 0;
  double signofx = 0;
  double counter = 0;
  double x1 = 0;
  double x2 = 0;
  double x3  = 0;
  double y1  = 0;
  double y2 =  0;
  double y3 =  0;
  double denom = 0;
  double polya = 0;
  double polyb = 0;
  double polyc = 0;
  int planetnum = 1;
  double xmoon =0;
  double prevslope = 0;
  double xold =1;
  double yold = 1;
  double mold = 0;
  double slope =0;
  double hyper = 0;


  protected void doStep() {
    time += timestep; // this is the timestep
    counter++;
    double distancex = 0;
    double distancey = 0;
    double force = 0;
    ArrayList<Double> forcesX = new ArrayList<Double>();
    ArrayList<Double> forcesY = new ArrayList<Double>();
    if(control.getBoolean("moon?")==true){
      if (counter%100==0){
        slope = ((planet.get(2).getY()-yold)/(planet.get(2).getX()-xold));
        control.println(""+slope/mold);
          if(slope/mold <1.000001 && slope/mold>.999999 && hyper==0){          
            hyper++;
            x1 = xold;
            y1 = yold;
            x2 = planet.get(2).getX();
            y2 = planet.get(2).getY();

            //calculations
            double k = 0;
            double m = (y1-y2)/(x1-x2); //the slope
            //plotting the line
            for (double i=-50E11; i<50E11; i+=.5E11){
              asymp.addPoint(i, y2+m*(i-x2));
              asymp2.addPoint(i, -1*y2+m*(i-x2));
            }
            asymp.color = Color.green;
            asymp2.color = Color.green;
            double h = (m*x2-y2)/m;
            control.println("h "+h);
            double a = h-xmoon;
            control.println("a "+a);
            double b = y2 + m*(xmoon-x2);
            control.print("b "+b);
            control.print("xmoon "+xmoon);
            hyperbola.color= Color.red;
            hyperbolanegative.color= Color.red;
            for (double i=xmoon; i>-50E11; i-=.005E11){
              hyperbola.addPoint(i, (b/a)*(Math.sqrt((i-h)*(i-h)-(a*a)))+k);
              hyperbolanegative.addPoint(i, -1*(b/a)*(Math.sqrt((i-h)*(i-h)-(a*a)))+k);
            }
            frame.addDrawable(asymp);
            frame.addDrawable(asymp2);
            frame.addDrawable(hyperbola);
            frame.addDrawable(hyperbolanegative);
            control.println("we here!!");
        }
        xold = planet.get(2).getX();
        yold = planet.get(2).getY();
        mold= slope;
      }


    }

    if(control.getDouble("x earth") > 0){
      signofx = 1;
    }
    else if(control.getDouble("x earth") < 0){
      signofx = -1;
    }

    previousx = planet.get(1).getX();
    previousy = planet.get(1).getY();

    //calculate forces
    for(int i=0; i<planet.size(); i++){
      for(int x=0; x<planet.size(); x++){
        if(x!=i&&planet.get(i).getX()!= planet.get(x).getX()){
          distancex = planet.get(x).getX()- planet.get(i).getX();
          force = planet.get(i).Fg(planet.get(i), planet.get(x));
          //System.out.println("Forces (i, x), (" + i + ", " + x + ") " + force);
          forcesX.add(force*(distancex)/Math.abs((planet.get(i).distance(planet.get(i), planet.get(x)))));
        }
      }//xforces
      for (int y = 0; y < planet.size(); y++) {
        if(y!=i&&planet.get(i).getY()!= planet.get(y).getY()){
          distancey = planet.get(y).getY() - planet.get(i).getY();
          force = planet.get(i).Fg(planet.get(i), planet.get(y));
          //System.out.println("Forces (i, y), (" + i + ", " + y + ") " + force);
          forcesY.add(force*(distancey)/Math.abs((planet.get(i).distance(planet.get(i), planet.get(y)))));
        }
      }//yforces
      planet.get(i).step(timestep, forcesX, forcesY);
      planet.get(i).updatePosition(timestep);

      if( i != 0){
        trails.get(i).addPoint(planet.get(i).getX(), planet.get(i).getY()); //adds the points to the trail
        frame.addDrawable(trails.get(i)); //adds the trail to the frame
      }

      //tracemoon.addPoint(planet.get(2).getX(), planet.get(2).getY());
      framemoon.addDrawable(tracemoon);

      forcesX.clear();
      forcesY.clear();
    }//moves each planet, loops through the number of planets   

    if(control.getBoolean("Kepler's Laws") == true){

      /*
       * records the max Y point (planet at top of it's orbit) to calculate other values (axes and foci)
       * then it can plot the ellipse (and its foci)
       */
      if(planet.get(1).getY() > maxY){
        maxY = planet.get(1).getY();
        topX = planet.get(1).getX();
      }
      if(planet.get(1).getVy() < 0) passedMaxY = true; //this means it has passed the max Y point
      if(passedMaxY && !graphedEllipse){ //this ensures that it doesn't keep drawing more ellipses
        centerx = topX;
        centery = 0;
        B = maxY;
        A = Math.abs(control.getDouble("x earth") - centerx);
        C = Math.sqrt(A*A - B*B); //c^2 + b^2 = a^2
        BoundedShape ellipse = BoundedShape.createBoundedEllipse(centerx, centery, 2*A, 2*B); //draws the ellipse
        Color clear = new Color(0, 0, 0, 1);
        ellipse.setMarkerColor(clear, Color.green);
        if(signofx > 0){
          Circle foci1 = new Circle(control.getDouble("x earth")-(A - C), 0, 2); //draws one foci
          Circle foci2 = new Circle(control.getDouble("x earth") -(A + C), 0, 2);//draws other foci
          frame.addDrawable(foci1);
          frame.addDrawable(foci2);
        }
        else if(signofx < 0){
          Circle foci1 = new Circle(control.getDouble("x earth")+(A - C), 0, 2); //draws one foci
          Circle foci2 = new Circle(control.getDouble("x earth")+(A + C), 0, 2);//draws other foci
          frame.addDrawable(foci1);
          frame.addDrawable(foci2);
        }

        //adds them to the frame
        frame.addDrawable(ellipse);
        graphedEllipse=true;
      }

      if(signofx > 0){
        if(planet.get(1).getY() >= 0 && previousy < 0 && planet.get(1).getX() > 0 ){
          if(periodalready == false){
            periodtime=time;
            periodalready = true;
          }
          control.println("Kepler's Third Law: "+ ((periodtime*periodtime)/(A*A*A)) + " = " + (4*Math.PI*Math.PI)/(6.67E-11*control.getDouble("mass sun"))); //THE LAW
        }
      }
      else if(signofx < 0){
        if(planet.get(1).getY() >= 0 && previousy < 0 && planet.get(1).getX() < 0 ){
          if(periodalready == false){
            periodtime=time;
            periodalready = true;
          }
          control.println("Kepler's Third Law: "+ ((periodtime*periodtime)/(A*A*A)) + " = " + (4*Math.PI*Math.PI)/(6.67E-11*control.getDouble("mass sun"))); //THE LAW
        }
      }

      sidea = Math.sqrt(Math.pow(previousx - planet.get(1).getX(), 2) + Math.pow(previousy - planet.get(1).getY(), 2));
      sideb = Math.sqrt(Math.pow(previousx - planet.get(0).getX(), 2) + Math.pow(previousy - planet.get(0).getY(), 2));
      sidec = Math.sqrt(Math.pow(planet.get(1).getX() - planet.get(0).getX(), 2) + Math.pow(planet.get(1).getY() - planet.get(0).getY(), 2));
      s = (sidea + sideb + sidec)/2;
      System.out.println("Kepler's Second Law: " + Math.sqrt(s*(s-sidea)*(s-sideb)*(s-sidec)));
    }

    framemoon.setPreferredMinMax(planet.get(1).getX()-40E8, planet.get(1).getX()+40E8, planet.get(1).getY()-40E8, planet.get(1).getY()+40E8); //adjusts the frame as the ball travels
  }// do step

  /**
   * This function resets the simulation. It is called if you press the
   * "reset" button the control panel. It just wipes the screen clean and lets
   * you enter in new values in the control panel. With the reset button, you
   * don't need to close the window and re-run your program, you can just
   * reset it. It returns all the values to zero
   */
  public void reset() {
    planet.clear(); // clears the planets so it can start fresh
    control.setValue("time step", 1000);   //1000
    control.setValue("time step moon", 100);
    control.setValue("mass sun", 1.989E30);
    control.setValue("mass earth", 5.972E24);
    control.setValue("radius sun", 6.958E8);
    control.setValue("radius earth", 6.371E6);
    control.setValue("distance", 1.496E11);
    control.setValue("x sun", 0);
    control.setValue("y sun", 0);
    control.setValue("x earth", 1.496E11);
    control.setValue("y earth", 0);
    control.setValue("earth tangential velocity", 2.955E4); //29747 this should be this
    control.setValue("mass moon", 7.34E22);
    control.setValue("x moon", 3.844E8); //1017 moons tangential velocity around earth //1439 should be the escape velocity
    //30989 moons escape of earth
    //4.21E4 //earths escape of sun
    control.setValue("y moon", 0);
    control.setValue("moon tangential velocity", 4.21E4);  //30500 -> moon real velocity around earth - SIMIULATES MOON GOING AROUND EARTH AND NOT ESCAPING 
    control.setValue("moon?", true);
    control.setValue("Kepler's Laws", false);  //3.08E4
    control.setValue("Solar System", false);  //3.08E4
    frame.setPreferredMinMaxX(-3E11, 3E11);
  }

  public void initialize() {
    // makes the display frame
    time = 0;
    timestep = control.getDouble("time step");
    timestepmoon = control.getDouble("time step moon");
    planet.clear();
    trails.clear();

    //sets up the main frame
    frame.setLocation(0, -300);
    frame.setSize(750, 550);
    frame.setPreferredMinMax(-3E11, 3E11, -3E11, 3E11);
    frame.clearDrawables();
    frame.setVisible(true);
    //sets up the main frame
    framemoon.setLocation(0, 400);
    framemoon.setSize(350, 350);
    framemoon.setPreferredMinMax(-3E8, 3E8, -3E8, 3E8);
    framemoon.clearDrawables();
    framemoon.setVisible(true);

    setDelayTime(0);
    setStepsPerDisplay(100);

    //planet 0 == sun
    planet.add(new Particle());
    planet.get(0).pixRadius = 5;
    planet.get(0).color = Color.YELLOW;
    planet.get(0).setXY(control.getDouble("x sun"), control.getDouble("y sun"));
    planet.get(0).setMass(control.getDouble("mass sun"));
    frame.addDrawable(planet.get(0));

    //planet 1 == earth
    planet.add(new Particle());
    planet.get(1).pixRadius = 3;
    planet.get(1).color = Color.BLUE;
    planet.get(1).setXY(control.getDouble("x earth"), control.getDouble("y earth"));
    planet.get(1).setVy(control.getDouble("earth tangential velocity"));
    planet.get(1).setMass(control.getDouble("mass earth"));
    frame.addDrawable(planet.get(1));
    framemoon.addDrawable(planet.get(1));

    if(control.getBoolean("moon?")==true){
      //planet 2 == moon
      planet.add(new Particle());
      planet.get(2).pixRadius = 3;
      planet.get(2).color = Color.GRAY;
      planet.get(2).setXY(control.getDouble("x moon")+control.getDouble("x earth"), control.getDouble("y moon"));
      xmoon= control.getDouble("x moon")+control.getDouble("x earth");
      planet.get(2).setVy(control.getDouble("moon tangential velocity"));
      planet.get(2).setMass(control.getDouble("mass moon"));
      frame.addDrawable(planet.get(2));
      framemoon.addDrawable(planet.get(2));
      tracemoon.addPoint(control.getDouble("x moon")+control.getDouble("x earth"), control.getDouble("y moon"));
      framemoon.addDrawable(tracemoon);
      planetnum = 0;
    }

    //planetnum is a variable that deals with whether the moon is present or not, it's default is to be one 
    if (control.getBoolean("Solar System")==true){
      // //planet 3 == mercury
      planet.add(new Particle());
      planet.get(3-planetnum).pixRadius = 3;
      planet.get(3-planetnum).setXY(-5.791E10, 0);
      planet.get(3-planetnum).setVy(-47361);
      planet.get(3-planetnum).setMass(3.285E23);
      planet.get(3-planetnum).color = Color.ORANGE;
      frame.addDrawable(planet.get(3-planetnum));
      // //planet 4 == venus
      planet.add(new Particle());
      planet.get(4-planetnum).pixRadius = 3;
      planet.get(4-planetnum).setXY(1.08209475E11, 0);
      planet.get(4-planetnum).setVy(35020);
      planet.get(4-planetnum).setMass(4.867E24);
      planet.get(4-planetnum).color = Color.PINK;
      frame.addDrawable(planet.get(4-planetnum)); 
      // //planet 5 == mars
      planet.add(new Particle());
      planet.get(5-planetnum).pixRadius = 3;
      planet.get(5-planetnum).setXY(-2.279E11, 0);
      planet.get(5-planetnum).setVy(-24076);
      planet.get(5-planetnum).setMass(6.41E23);
      planet.get(5-planetnum).color = Color.RED;
      frame.addDrawable(planet.get(5-planetnum));   
      // //planet 6 == jupiter
      planet.add(new Particle());
      planet.get(6-planetnum).pixRadius = 3;
      planet.get(6-planetnum).setXY(7.783E11, 0);
      planet.get(6-planetnum).setVy(13056);
      planet.get(6-planetnum).setMass(1.898E27);
      planet.get(6-planetnum).color = Color.GREEN;
      frame.addDrawable(planet.get(6-planetnum));  
      //planet 7 == saturn
      planet.add(new Particle());
      planet.get(7-planetnum).pixRadius = 3;
      planet.get(7-planetnum).setXY(-1.427E12, 0);
      planet.get(7-planetnum).setVy(-9639);
      planet.get(7-planetnum).setMass(5.683E27);
      planet.get(7-planetnum).color = Color.ORANGE;
      frame.addDrawable(planet.get(7-planetnum));
      //planet 8 == uranus
      planet.add(new Particle());
      planet.get(8-planetnum).pixRadius = 3;
      planet.get(8-planetnum).setXY(2.870E12, 0);
      planet.get(8-planetnum).setVy(6799);
      planet.get(8-planetnum).setMass(639E21);
      planet.get(8-planetnum).color = Color.CYAN;
      frame.addDrawable(planet.get(8-planetnum));
      //planet 9 == neptune
      planet.add(new Particle());
      planet.get(9-planetnum).pixRadius = 3;
      planet.get(9-planetnum).setXY(-4.498E12, 0);
      planet.get(9-planetnum).setVy(-5435);
      planet.get(9-planetnum).setMass(8.681E25);
      planet.get(9-planetnum).color = new Color(128,0,128);
      frame.addDrawable(planet.get(9-planetnum));
    }

    //trail
    for (int i = 0; i < planet.size(); i++){
      trails.add(new Trail());
      trails.get(i).addPoint(planet.get(i).getX(), planet.get(i).getY()); //adds the first two points to the trail
      //trails.get(i).addPoint(control.getDouble("x earth"), control.getDouble("y earth")); //adds the first two points to the trail
      frame.addDrawable(trails.get(i)); //adds the trail to the frame
    }
    framemoon.setPreferredMinMax(planet.get(1).getX()-40E11, planet.get(1).getX()+40E11, planet.get(1).getY()-40E11, planet.get(1).getY()+40E11);
    //polyk.addPoint(1.5E11, 0);
    hyperbola.addPoint(1.53648E11, 0);
    hyperbolanegative.addPoint(1.53648E11, 0);
    frame.addDrawable(asymp);
    frame.addDrawable(asymp2);
    frame.addDrawable(hyperbola);
    frame.addDrawable(hyperbolanegative);

  }// initialize

  /**
   * gives the simulation the ability to run
   *
   * @param args
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new OrbitalSafe());
  }

}// class