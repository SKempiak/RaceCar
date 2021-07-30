package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GhostRacer extends Car {
	public static HashMap<Long, String> drivingLine = new HashMap<Long, String>();
	GhostRacer() {
		super(92, 162, 10, 20);
		fromJson();
		angle = -90;
	}

	void fromJson() {
		Gson gson = new GsonBuilder().create();
		try {
			Reader reader = new InputStreamReader(
					GhostRacer.class.getClassLoader().getResourceAsStream("drivingLine.json"));
			drivingLine = gson.fromJson(reader, HashMap.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		double rads = Math.toRadians(angle-90);
		g2d.rotate(rads, x + (width/2), y + (height/2));
		g2d.setColor(Color.BLUE);
		g2d.fillRect((int) x,(int) y, width, height);
		g2d.rotate(-rads, x + (width/2), y + (height/2));
		g2d.dispose();
	}
	public void update(){
		super.update();
		System.out.println(drivingLine.keySet().size());
		for(Long time : drivingLine.keySet()) {
			if(time <= System.currentTimeMillis() - ObjectManager.startTime) {
				String action = drivingLine.get(time);
				if(action.equals("W:pressed")) {
					goForward = true;
					drivingLine.replace(time, "");
				}
				if(action.equals("A:pressed")) {
					turnLeft = true;
					drivingLine.replace(time, "");
				}
				if(action.equals("S:pressed")) {
					goBackward = true;
					drivingLine.replace(time, "");
				}
				if(action.equals("D:pressed")) {
					turnRight = true;
					drivingLine.replace(time, "");
				}
				if(action.equals("W:released")) {
					goForward = false;
					drivingLine.replace(time, "");
				}
				if(action.equals("A:released")) {
					turnLeft = false;
					drivingLine.replace(time, "");
				}
				if(action.equals("S:released")) {
					goBackward = false;
					drivingLine.replace(time, "");
				}
				if(action.equals("D:released")) {
					turnRight = false;
					drivingLine.replace(time, "");
				}
			}
		}
	}
}