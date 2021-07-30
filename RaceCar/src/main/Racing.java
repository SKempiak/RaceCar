package main;

import javax.swing.JFrame;

public class Racing {
	JFrame frame;
	GamePanel panel = new GamePanel();
	public static final int WIDTH = 923;
	public static final int HEIGHT = 900;
	public static void main(String[] args) {
		Racing race = new Racing();
		race.setup();
	}
 
	public Racing() {
		frame = new JFrame();
	}
	void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(panel);
		frame.addMouseListener(panel);
	}
}