import java.awt.Graphics2D;
import java.util.ArrayList;

public class AsteroidManager {

	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	Bullet bullet;
	Panel panel;
	int spawnAmount;
	Cube cube;

	AsteroidManager(Panel panel, Cube cube) {
		this.spawnAmount = 5; // Initial number of asteroids
		this.panel = panel;
		this.cube = cube;
	}

	public void spawnAsteroids() {
		// Populate the list with new Asteroid objects
		for (int i = 0; i < spawnAmount; i++) {
			Asteroid asteroid = new Asteroid(cube);
			asteroids.add(asteroid);
		}

		spawnAmount++; // Increase difficulty for the next wave
	}

	public void update() {
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update();
		}
	}

	// Detects if a bullet has hit any active asteroid
	public boolean checkCollision(Bullet bullet) {
		boolean toRet = false;
		for (int i = 0; i < asteroids.size(); i++) {
			if ((bullet.x <= asteroids.get(i).x + Asteroid.asteroidWidth && bullet.x >= asteroids.get(i).x)
					&& (bullet.y <= asteroids.get(i).y + Asteroid.asteroidHeight && bullet.y >= asteroids.get(i).y)) {
				asteroids.get(i).toDraw = false; // Mark asteroid for removal
				toRet = true;
			}
		}
		return toRet;
	}

	// Detects if the player (Cube) has crashed into an asteroid
	public void checkCollision(Cube cube) {
		boolean isColliding = false;

		for (int i = 0; i < asteroids.size(); i++) {
			isColliding = (cube.x < asteroids.get(i).x + Asteroid.asteroidWidth
					&& cube.x + cube.cubeWidth > asteroids.get(i).x
					&& cube.y < asteroids.get(i).y + Asteroid.asteroidHeight
					&& cube.y + cube.cubeHeight > asteroids.get(i).y);

			// Apply damage if not immune
			if (cube.immunity <= 0 && isColliding) {
				cube.lives--;
				cube.immunity = 180; // Provide 3 seconds of immunity (at 60 FPS)
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g2);
		}
	}

	// Cleanup function to remove destroyed asteroids from memory
	public void removeNoDraw() {
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).toDraw == false) {
				asteroids.remove(i);
			}
		}
	}
}