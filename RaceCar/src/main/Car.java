package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Car extends GameObject{

	
	public double frwrdSpd = 0;
	public double angle = 0;
	public double strAng = 0;
	public boolean goForward = false;
	public boolean goBackward = false;
	public boolean turnLeft = false;
	public boolean turnRight = false;
	public double maxStrAng = 10;
	double centerX = 0;
	double centerY = 0;
	
	public Car(int x, int y, int width, int height) {
		// TODO Auto-generated constructor stub
		super(x, y, width, height);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		double rads = Math.toRadians(angle-90);
		g2d.rotate(rads, x + (width/2), y + (height/2));
		g2d.setColor(Color.RED);
		g2d.fillRect((int) x,(int) y, width, height);
		g2d.rotate(-rads, x + (width/2), y + (height/2));
		g2d.dispose();
	}
	public void changeAngle(double angle) {
		this.strAng += angle;
		if(strAng > maxStrAng) {
			this.strAng = maxStrAng;
		}
		if(strAng < -maxStrAng) {
			this.strAng = -maxStrAng;
		}
	}
	public void update() {
		if(frwrdSpd != 0) {
		angle += strAng;
		}
		if(strAng < 0) {
			strAng += 0.1;
		}
		if(strAng > 0) {
			strAng -= 0.1;
		}
		frwrdSpd *= 0.99;
		y+=frwrdSpd*Math.sin(Math.toRadians(angle));
		x+=frwrdSpd*Math.cos(Math.toRadians(angle));
		
		if(GamePanel.color != -10987432) {
			ObjectManager.resetCar();
		}
		handleTurning();
		handleAccel();
		centerX = x + width/2;
		centerY = y + height/2;
	}
	public void accelerate(double accelerate) {
		if(frwrdSpd < 10 && frwrdSpd > -10) {
			frwrdSpd += accelerate;
			maxStrAng = 10 - frwrdSpd/2;
		}
	}
	public void handleTurning() {
		if(turnLeft) {
			changeAngle(-0.18);
		}
		if(turnRight) {
			changeAngle(0.18);
		}
	}
	public void handleAccel() {
		if(goForward) {
			accelerate(0.05);
		}
		if(turnRight) {
			accelerate(-0.05);
		}
	}
}
