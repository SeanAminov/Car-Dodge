import java.awt.Graphics;

public class Projectile {

	private int x;
	private int y;
	private int rad;

	public Projectile(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.rad = radius;
	}

	public void draw(Graphics g) {
		g.drawOval(x, y, rad, rad);
		g.fillOval(x, y, rad, rad);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRad() {
		return rad;
	}

	public void setRad(int rad) {
		this.rad = rad;
	}

}
