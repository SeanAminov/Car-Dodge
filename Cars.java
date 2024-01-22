import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Cars {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private Random rand = new Random();

	private BufferedImage image;

	public Cars(int x, int y, int width, int height, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;

	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean containsPoint(int x, int y) {
		return (x >= this.x && x <= this.x + width && y >= this.y - height && y <= this.y + height / 2);
	}

	public boolean containsCar(int x, int y) {
		return (x >= this.x && x <= this.x + width && y >= this.y - height / 2 && y <= this.y + height + 10);
	}
}
