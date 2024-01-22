
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

public class CarDodgePanel extends JPanel {
	private static BufferedImage mainPerson;
	private static BufferedImage car1;
	private static BufferedImage car2;
	private static BufferedImage car3;
	private BufferedImage image;
	private boolean main;
	private String jFrameString;
	private boolean gameOver;
	private int random;
	private int score;
	private MainPerson person;
	private int personSpeed;
	private int carSpeed;
	private int projectileLimit;
	private int square;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private ArrayList<Cars> cars = new ArrayList<Cars>();

	public CarDodgePanel() {
		try {
			mainPerson = ImageIO.read(new File("Photos/Person.png"));
			car1 = ImageIO.read(new File("Photos/CarForward.png"));
			car2 = ImageIO.read(new File("Photos/CarForward2.png"));
			car3 = ImageIO.read(new File("Photos/CarForward3.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		main = true;
		score = 0;
		gameOver = false;

		person = (new MainPerson(300, 275, 25, 25, mainPerson));
		personSpeed = 10;
		carSpeed = 10;
		projectileLimit = 3;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).draw(g);
		}

		if (!main) {
			person.draw(g);
		}

		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).draw(g);
		}

		if (gameOver) {
			main = true;
			clear();
			jFrameString = "You Died";
			setFont(new java.awt.Font("Times New Roman", 2, 60));
			g.drawString(jFrameString, this.getWidth() / 4, this.getHeight() / 2);

		}
	}

	public void shoot() {
		if (projectileLimit > 0) {
			projectiles.add(new Projectile(person.getX() + person.getWidth() / 2 - 3,
					person.getY() + person.getHeight() + 10, 5));
			projectileLimit--;
		}

	}

	public void projectilesUp() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).getY() > this.getHeight()) {
				projectiles.remove(i);

			}

			projectiles.get(i).setY(projectiles.get(i).getY() - 10);

		}
	}

	public int getProjectileLimit() {
		return projectileLimit;
	}

	public void carsDown() {
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).setY(cars.get(i).getY() + carSpeed);
		}
	}

	public void moveLeft() {
		person.setX(person.getX() - personSpeed);
		if (person.getX() < 0) {
			person.setX(0);
		}
	}

	public int getScore() {
		return score;
	}

	public void moveRight() {
		person.setX(person.getX() + personSpeed);
		if (person.getX() >= this.getWidth() - 10) {
			person.setX(this.getWidth() - person.getWidth());
		}
	}

	public void containsPoint() {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).containsPoint(person.getX() + person.getWidth() / 2, person.getY() + 50)) {
				gameOver();
			}
		}

	}

	public void gameOver() {
		gameOver = true;

	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void deleteCar() {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getY() > this.getHeight() - 10) {
				cars.remove(i);
				score += 10;
			}

			for (int j = 0; j < projectiles.size(); j++) {
				if (cars.get(i).containsPoint(projectiles.get(j).getX(),
						projectiles.get(j).getY() - cars.get(i).getHeight() / 2)) {
					cars.remove(i);
					projectiles.remove(j);
				}
			}

		}
	}

	public void clear() {
		cars = new ArrayList<Cars>();
	}

	public boolean openSpot(int random) {
		for (int i = cars.size() - 3; i < cars.size(); i++) {
			if (cars.get(i).containsPoint(random + cars.get(i).getWidth() / 2, cars.get(i).getY())) {
				return false;
			}
		}
		return true;
	}

	public void addCar() {
		if (main) {
			main = false;
		}
		int img = (int) (Math.random() * 3) + 1;
		if (img == 1) {
			image = car1;
		} else if (img == 2) {
			image = car2;
		} else {
			image = car3;
		}

		if (cars.size() < 4) {
			random = (int) (Math.random() * this.getWidth() / 3);
			cars.add(new Cars(random, -25, 25, 25, car1));
			random = (int) (Math.random() * this.getWidth() / 3 + this.getWidth() / 3 + 15);
			cars.add(new Cars(random, -25, 25, 25, car2));
			random = (int) (Math.random() * this.getWidth() + (2 * this.getWidth() / 3 + 15));
			cars.add(new Cars(random, -25, 25, 25, car3));
		} else {
			random = (int) (Math.random() * this.getWidth());
			while (!openSpot(random)) {
				random = (int) (Math.random() * (this.getWidth() - 35));
			}
		}

		cars.add(new Cars(random, -25, 25, 25, image));

	}
}
