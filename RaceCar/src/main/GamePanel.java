package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener {
	final int MENU = 0;
	final int GAME = 1;
	int currentState = GAME;
	public static ObjectManager object;
	Timer frameDraw;
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font subFont = new Font("Arial", Font.PLAIN, 26);
	BufferedImage bg1;
	public static int color;

	int clickStartX  = 0;
	int clickStartY = 0;
	int clickEndX = 0;
	int clickEndY = 0;
	ArrayList<Checkpoint> checkpointss = new ArrayList<Checkpoint>();
	public static HashMap<Long, String> keyInput = new HashMap<Long, String>();
	
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
		for (Checkpoint checkpoint : checkpointss) {
			checkpoint.draw(g);
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
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "A:pressed");
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			object.car.turnRight = true;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "D:pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			object.car.goForward = true;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "W:pressed");
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			object.car.goBackward = true;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "S:pressed");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
			object.car.turnLeft = false;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "A:released");
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			object.car.turnRight = false;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "D:released");
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			object.car.goForward = false;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "W:released");
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			object.car.goBackward = false;
			keyInput.put(System.currentTimeMillis() - ObjectManager.startTime, "S:released");
		}
	}
	
	public void update() {
		object.update();
		color = bg1.getRGB((int) (object.car.x + object.car.width/2),(int) (object.car.y + object.car.height/2));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		clickStartX = e.getX() - 8;
		clickStartY = e.getY() - 32;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		clickEndX = e.getX() - 8;
		clickEndY = e.getY() - 32;
		System.out.println("checkpoints[] = new Checkpoint(" + clickStartX + ", " + clickStartY + ", " + (clickEndX - clickStartX) + ", " + (clickEndY - clickStartY) + ");");
//		Checkpoint checkpoint = new Checkpoint(clickStartX, clickStartY, (clickEndX - clickStartX), (clickEndY - clickStartY));
//		checkpointss.add(checkpoint);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
