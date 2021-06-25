package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	final int MENU = 0;
	final int GAME = 1;
	int currentState = GAME;
	public static ObjectManager object;
	Timer frameDraw;
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font subFont = new Font("Arial", Font.PLAIN, 26);
	BufferedImage bg1;
	public static int color;

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		object = new ObjectManager();
		try {
			bg1 = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frameDraw.start();
	}

	@Override
	public void paintComponent(Graphics g) {

		if (currentState == GAME) {
			g.drawImage(bg1, 0, 0, Racing.WIDTH, Racing.HEIGHT, null);
			g.setFont(subFont);
			object.draw(g);
			if (object.isOver) {
				currentState = MENU;
			}
		} else if (currentState == MENU) {
			drawMenuState(g);
		}

	}

	private void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Racing.WIDTH, Racing.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Ai Race", 600, 100);
		g.setFont(subFont);
		g.drawString("Press ENTER to start", 640, 300);
		g.drawString("W, A, S, D to move, try to beat the robot", 530, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		update();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
			object.car.turnLeft = true;
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			object.car.turnRight = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			object.car.goForward = true;
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			object.car.goBackward = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			object.car.x = 800;
			object.car.y = 100;
			object.car.frwrdSpd = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
			object.car.turnLeft = false;
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			object.car.turnRight = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			object.car.goForward = false;
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			object.car.goBackward = false;
		}
	}
	
	public void update() {
		object.update();
		color = bg1.getRGB((int) (object.car.x + object.car.width/2),(int) (object.car.y + object.car.height/2));
	}
	
	public static void carReset() {
		System.out.println(object.car.x + "," + object.car.y);
	}
}
