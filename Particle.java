package Orbital;

import java.util.ArrayList;

import org.opensourcephysics.display.Circle;

/*
 * Annabel Strauss, Emily Cohen, Karina Shah
 * April/May 2015
 * Comp Sci X
 * Orbital 
 * Version 1.9
 */
public class Particle extends Circle {

  // declarations
  double mass;
  double radius;
  double angle;
  double velocity;
  double vx;
  double vy;
  double xpos;
  double ypos;
  double xacc;
  double yacc;
  double timestep = 1000;
  double k;
  double G;

  // getters and setters
  public double getG() {
    return G;
  }

  public void setG(double g) {
    G = g;
  }

  public double getMass() {
    return mass;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getAngle() {
    return angle;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public double getVelocity() {
    return velocity;
  }

  public void setVelocity(double velocity) {
    this.velocity = velocity;
  }

  public double getVx() {
    return vx;
  }

  public void setVx(double vx) {
    this.vx = vx;
  }

  public double getVy() {
    return vy;
  }

  public void setVy(double vy) {
    this.vy = vy;
  }

  public double getXpos() {
    return xpos;
  }

  public void setXpos(double xpos) {
    this.xpos = xpos;
  }

  public double getYpos() {
    return ypos;
  }

  public void setYpos(double ypos) {
    this.ypos = ypos;
  }

  public double getXacc() {
    return xacc;
  }

  public void setXacc(double xacc) {
    this.xacc = xacc;
  }

  public double getYacc() {
    return yacc;
  }

  public void setYacc(double yacc) {
    this.yacc = yacc;
  }

  public double getTimestep() {
    return timestep;
  }

  public void setTimestep(double timestep) {
    this.timestep = timestep;
  }

  public void step(double timestep, ArrayList<Double> forcesx, ArrayList<Double> forcesy) {
    double forcex = 0;
    double forcey = 0;
    for (int i = 0; i < forcesx.size(); i++) {
      forcex += forcesx.get(i);
    }//adds up all the x forces
    for (int i = 0; i < forcesy.size(); i++) {
      forcey += forcesy.get(i);
    }//adds up all the y forces

    double accx = forcex / mass;
    double accy = forcey / mass;
    
    setXacc(accx);
    setYacc(accy);
    //System.out.println("acceleration x: "+accx);
    //System.out.println("acceleration y: "+accy);
    
    setVx(getVx() +xacc*timestep); //sets the x velocity 
    setVy(getVy() +yacc*timestep);//sets the y velocity 
    
  }
  
  public void updatePosition(double timestep) {
    setX(getX() + (getVx() * timestep));
    setY(getY() + (getVy() * timestep));
    //System.out.println("X, Y: " +getX()+", "+getY());
  }
  public double angle (Particle one, Particle two){
    return Math.atan((one.getY()-two.getY())/(one.getX()-two.getX()));
  }
  public double distance (Particle one, Particle two){
    return Math.sqrt((one.getY()-two.getY())*(one.getY()-two.getY()) +(one.getX()-two.getX())*(one.getX()-two.getX()));
  }
  public double Fg (Particle one, Particle two){
    return ((6.674E-11*one.getMass()*two.getMass())/((distance(one, two)*distance(one, two))));
  }
  public double Acceleration(double m, double v, double r){
    return (v*v)/r;
  }
 

}// class

