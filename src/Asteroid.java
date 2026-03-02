import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Asteroid {
	double x, y;
	double boxX, boxY;
	BufferedImage image;
	Random random;
	double toX, toY;
	double randX, randY;
	double randtoX, randtoY;
	static int asteroidWidth, asteroidHeight;
	boolean toDraw;
	Cube cube;

	Asteroid(Cube cube) {
		toDraw = true;
		this.cube = cube;
		boxX = 30;
		boxY = 30;
		asteroidWidth = 40;
		asteroidHeight = 34;
		try {
			// Load the asteroid sprite image
			image = ImageIO.read(getClass().getResourceAsStream("pixil-frame-0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setSpawn(); // Initialize starting position

		x = randtoX;
		y = randtoY;

		calcTrajectory(); // Determine movement direction
	}

	public void setSpawn() {
		random = new Random();
		// Generate random target points for trajectory
		randX = random.nextDouble() * Panel.frameWidth;
		randY = random.nextDouble() * Panel.frameHeight;
		randtoX = random.nextDouble() * Panel.frameWidth;
		randtoY = random.nextDouble() * Panel.frameHeight;

		// Check if spawn point overlaps with the player (Cube)
		boolean isColliding = (cube.x < x + Asteroid.asteroidWidth && cube.x + cube.cubeWidth > x
				&& cube.y < y + Asteroid.asteroidHeight && cube.y + cube.cubeHeight > y);

		// Re-roll spawn coordinates if they collide with player
		while (isColliding) {
			randX = random.nextDouble() * Panel.frameWidth;
			randY = random.nextDouble() * Panel.frameHeight;
		}
	}

	public void calcTrajectory() {
		double dirX = randX - x;
		double dirY = randY - y;

		// Use Pythagorean theorem to normalize the speed
		double pyth = Math.sqrt(dirX * dirX + dirY * dirY);

		if (pyth > 0) {
			toX = (dirX / pyth) * 2; // Move 2 pixels per frame along X
			toY = (dirY / pyth) * 2; // Move 2 pixels per frame along Y
		}
	}

	public void update() {
		x += toX;
		y += toY;

		// Screen wrapping logic: If asteroid leaves one side, it reappears on the other
		if (x > Panel.frameWidth) {
			x = 0 - asteroidWidth;
		}
		if (x < 0 - asteroidWidth) {
			x = Panel.frameWidth;
		}
		if (y > Panel.frameHeight) {
			y = 0 - asteroidHeight;
		}
		if (y < 0 - asteroidHeight) {
			y = Panel.frameHeight;
		}
	}

	public void draw(Graphics2D g2) {
		if (toDraw) {
			g2.drawImage(image, (int) x, (int) y, asteroidWidth, asteroidHeight, null);
		}
	}
}