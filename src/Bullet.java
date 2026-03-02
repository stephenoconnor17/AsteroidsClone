import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {

	boolean isAlive;
	double toX, toY;
	double x, y;
	int mx, my; // Mouse coordinates target

	Bullet(double x, double y, int mx, int my) {
		this.x = x;
		this.y = y;
		this.mx = mx;
		this.my = my;
		calcTrajectory(); // Set direction based on click location
	}

	public void draw(Graphics2D g2) {
		g2.fillOval((int) x, (int) y, 4, 4); // Draw bullet as a small circle
	}

	public void update() {
		x += toX;
		y += toY;
	}

	public void calcTrajectory() {
		double dirX = mx - x;
		double dirY = my - y;

		// Calculate hypotenuse for direction normalization
		double pyth = Math.sqrt(dirX * dirX + dirY * dirY);

		if (pyth > 0) {
			toX = (dirX / pyth) * 5; // Bullet travel speed set to 5
			toY = (dirY / pyth) * 5;
		}
	}
}