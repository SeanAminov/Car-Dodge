import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;

public class CarDodgeListener implements MouseListener, MouseMotionListener, KeyListener, ActionListener {

	private CarDodgePanel gamePanel;
	private boolean startOnce;
	private Timer t;
	private JButton start;
	private JButton info;
	private JTextField spawn;

	private JPanel northContainer;
	private JPanel southContainer;
	private JMenuBar scoreBar;
	private JMenu scoreMenu;
	private JMenu projectilesMenu;
	private int time;

	private JFrame window;

//	private MainCar car;

	public CarDodgeListener(CarDodgePanel panel, JFrame window) {

		this.window = window;

		this.gamePanel = panel;
		gamePanel.addMouseMotionListener(this);
		gamePanel.addMouseListener(this);
		gamePanel.addKeyListener(this);

		spawn = new JTextField("", 15);
		spawn.setBackground(Color.WHITE);

		start = new JButton("Start");
		info = new JButton("Info");

		start.addActionListener(this);
		info.addActionListener(this);

		gamePanel.setLayout(new BorderLayout());

		northContainer = new JPanel();

		northContainer.add(start);
		northContainer.add(info);

		southContainer = new JPanel();
		southContainer.add(new JLabel("Time: "));
		southContainer.add(spawn);

		spawn.addActionListener(this);

		gamePanel.add(southContainer, BorderLayout.SOUTH);
		gamePanel.add(northContainer, BorderLayout.NORTH);

		scoreBar = new JMenuBar();
		scoreMenu = new JMenu("Score: 0");

		projectilesMenu = new JMenu("Projectiles: 0");
		scoreBar.add(projectilesMenu);
		scoreBar.add(scoreMenu);

		window.setJMenuBar(scoreBar);
		scoreBar.setVisible(false);

		time = 500;

		spawn.setText("500");
		t = new Timer(time, this);
		startOnce = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!startOnce) {

			String action = e.getActionCommand();
			// System.out.println(action);

			if (action.equals("Start")) {
				// main.show

				// layout.show(main, "Car Dodge");
				t.start();
				gamePanel.remove(northContainer);
				gamePanel.remove(southContainer);
				// gamePanel.setLayout(new BorderLayout());
				// gamePanel.add(scoreBar);
				window.getJMenuBar().setVisible(true);

				startOnce = true;
				gamePanel.requestFocusInWindow();
			}

			if (action.equals("Info")) {
				System.out.println("Use the arrows keys to move left and right");
				System.out.println(
						"Input below what you want the spawn rate of the cars to be, the higher the number, the slower the cars");
				System.out.println("Try to dodge as many cars as possible and rack up the points");
				System.out.println("You can also press 'c' to clear all the cars on the screen");
				System.out.println("Use 'Space' to fire a defensive pellet that destroys cars above you");
			}

			try {
				String spawnString = spawn.getText();
				if (spawnString == "") {
					return;
				} else {
					time = Integer.parseInt(spawnString);
				}
				if (time <= 1) {
					spawn.setText("Must enter a positive integer");
					spawn.selectAll();
					spawn.requestFocusInWindow();
				} else {
					t = new Timer(time, this);
					spawn.selectAll();
					spawn.requestFocusInWindow();
				}
			} catch (NumberFormatException ev) {
				if (spawn.getText() == "") {
					return;
				} else {
					spawn.setText("Must enter a positive integer");
					spawn.selectAll();
					spawn.requestFocusInWindow();
					return;

				}
			}
		}

		if (!gamePanel.isGameOver() && startOnce) {
			gamePanel.addCar();
			// TODO Auto-generated method stub
			gamePanel.carsDown();

			gamePanel.deleteCar();

			gamePanel.projectilesUp();

			scoreMenu.setText("Score:" + gamePanel.getScore());
			projectilesMenu.setText("Projectiles:" + gamePanel.getProjectileLimit());

			gamePanel.requestFocusInWindow();

			gamePanel.containsPoint();

			gamePanel.repaint();

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == 'c') {
			gamePanel.clear();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (!gamePanel.isGameOver() && startOnce) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_RIGHT) {
				gamePanel.moveRight();
				gamePanel.repaint();
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				gamePanel.moveLeft();
				gamePanel.repaint();
			}

			if (keyCode == KeyEvent.VK_SPACE) {
				gamePanel.shoot();
				gamePanel.repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
