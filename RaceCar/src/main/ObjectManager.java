package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	static Car car = new Car(92, 162, 10, 20);
	static GhostRacer ghostCar = new GhostRacer();
	static Checkpoint[] checkpoints = new Checkpoint[29];
	public boolean isOver;
	static long startTime = 0;
	static ArrayList<Lap> lapTimes = new ArrayList<Lap>();
	static Lap lap = new Lap();
	
	ObjectManager() {
		createCheckpoint();
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		car.draw(g);
		ghostCar.draw(g);
		for (Checkpoint checkpoint : checkpoints) {
			checkpoint.draw(g);
		}

	}

	public void update() {
		// TODO Auto-generated method stub
		car.update();
		checkCollision();
		ghostCar.update();
	}

	public void checkCollision() {
		for (Checkpoint checkpoint : checkpoints) {
			if (checkpoint.carCollision(car)) {
				checkpoint.trigger();
			}
		}
	}

	static void resetCar() {
		car.x = 92;
		car.y = 162;
		car.angle = -90;
		car.strAng = 0;
		car.frwrdSpd = 0;
		resetCheckpoints();
		GamePanel.keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "reset");
	}

	void createCheckpoint() {
		checkpoints[0] = new Checkpoint(43, 170, 100, 15, true);
		checkpoints[1] = new Checkpoint(151, 43, 15, 95, false);
		checkpoints[2] = new Checkpoint(170, 167, 95, 15, false);
		checkpoints[3] = new Checkpoint(167, 385, 96, 15, false);//HTIS\\
		checkpoints[4] = new Checkpoint(269, 406, 15, 98, false);
		checkpoints[5] = new Checkpoint(281, 369, 100, 15, false);
		checkpoints[6] = new Checkpoint(281, 157, 98, 15, false);
		checkpoints[7] = new Checkpoint(403, 44, 15, 91, false);//HTIS\\
		checkpoints[8] = new Checkpoint(742, 46, 15, 93, false);
		checkpoints[9] = new Checkpoint(781, 154, 96, 15, false);
		checkpoints[10] = new Checkpoint(782, 222, 96, 15, false);
		checkpoints[11] = new Checkpoint(758, 245, 15, 92, false);
		checkpoints[12] = new Checkpoint(523, 245, 15, 95, false);
		checkpoints[13] = new Checkpoint(416, 342, 96, 15, false);
		checkpoints[14] = new Checkpoint(518, 355, 15, 99, false);
		checkpoints[15] = new Checkpoint(739, 359, 15, 94, false);
		checkpoints[16] = new Checkpoint(780, 472, 99, 15, false);
		checkpoints[17] = new Checkpoint(782, 729, 96, 15, false);
		checkpoints[18] = new Checkpoint(786, 583, 90, 15, false);
		checkpoints[19] = new Checkpoint(748, 755, 18, 95, false);
		checkpoints[20] = new Checkpoint(629, 620, 98, 15, false);
		checkpoints[21] = new Checkpoint(553, 485, 15, 89, false);
		checkpoints[22] = new Checkpoint(419, 632, 92, 15, false);
		checkpoints[23] = new Checkpoint(416, 731, 103, 15, false);
		checkpoints[24] = new Checkpoint(397, 761, 15, 93, false);
		checkpoints[25] = new Checkpoint(279, 653, 15, 136, false);
		checkpoints[26] = new Checkpoint(144, 520, 15, 129, false);
		checkpoints[27] = new Checkpoint(44, 452, 92, 15, false);
		checkpoints[28] = new Checkpoint(40, 306, 94, 15, false);
	}
	static boolean lapAllowed() {
		for(Checkpoint checkpoint : checkpoints) {
			if(checkpoint.triggered != true) {
				return false;
			}
		}
		return true;
	}
	static void finishLap() {
		resetCheckpoints();
		lap.complete();
		lapTimes.add(lap);
	}
	static void startLap() {
		startTime = System.currentTimeMillis();
		lap = new Lap();
	}
	static void resetCheckpoints() {
		
		for(Checkpoint checkpoint : checkpoints) {
			checkpoint.triggered = false;
			lap.laps.add(checkpoint.timeTriggered);
			checkpoint.timeTriggered = Long.MAX_VALUE;
		}
	}
}