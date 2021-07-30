package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Lap {
	ArrayList<Long> laps = new ArrayList<Long>();
	boolean completed = false;
	long totalTime = Long.MAX_VALUE;

	public Lap() {

	}

	void complete() {
		completed = true;
		totalTime = System.currentTimeMillis() - ObjectManager.startTime;
		toJson();
	}
	void toJson() {
		Gson gson = new GsonBuilder().create();
		String jsonVal = gson.toJson(laps) + "\n" + gson.toJson(GamePanel.keyInput);
		try {
			FileWriter fw = new FileWriter("lapData.json");
			fw.write(jsonVal);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}