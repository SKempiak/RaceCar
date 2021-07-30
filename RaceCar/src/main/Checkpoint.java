package main;

import java.awt.Color;
import java.awt.Graphics;

public class Checkpoint extends GameObject {
	boolean triggered = false;
	boolean finishLine = false;
	long timeTriggered = 0;
	Checkpoint(int x, int y, int width, int height, boolean isLine) {
		super(x, y, width, height);
		finishLine = isLine;
	}

	void draw(Graphics g) {
		if(triggered  && !finishLine) {
			g.setColor(Color.GREEN);
		} else if(finishLine) {
			g.setColor(Color.WHITE);
		} else {
		g.setColor(Color.MAGENTA);
		}
		g.fillRect((int) x, (int) y, width, height);
	}
	boolean carCollision(Car car) {
		if(car.centerX > x && car.centerX < x+width && car.centerY > y && car.centerY < y+height) {
			return true;
		}
		return false;
	}
	void trigger() {
		if(!triggered) { 
			triggered = true;
			timeTriggered = System.currentTimeMillis() - ObjectManager.startTime;
			System.out.println((timeTriggered) / 1000);
			if(finishLine) {
				ObjectManager.startLap();
			}

		}  if(triggered && finishLine && ObjectManager.lapAllowed()) {
			ObjectManager.finishLap();
			System.out.println("lap finished: " + (double) (ObjectManager.lap.totalTime / 1000.0));
		}
	}
}