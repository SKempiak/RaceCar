package main;

import java.awt.Graphics;

public class ObjectManager {
	Car car = new Car(100, 200, 10, 20);
	public boolean isOver;

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		car.draw(g);
	}

	public void update() {
		// TODO Auto-generated method stub
		car.update();
	}

}