import java.awt.Color;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {

	public static final int frameWidth = 600;
	public static final int frameHeight = 500;
	public static final int screenArea = frameWidth * frameHeight;
	public static final int tileSize = 25;

	final int FPS = 60;

	Cube cube;
	Thread gameThread;
	KeyManager km;
	MouseHandler mh;
	BulletSpawner bs;
	AsteroidManager asteroidManager;
	Asteroid asteroid;
	LifeCounter counter;

	Panel() {
		this.setSize(frameWidth, frameHeight);
		mh = new MouseHandler();
		km = new KeyManager();
		cube = new Cube(km, mh);
		asteroidManager = new AsteroidManager(this, cube);

		counter = new LifeCounter(cube);
		bs = new BulletSpawner(mh, cube);
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setDoubleBuffered(true); // Prevents screen flickering
		this.setBackground(Color.black);

		// Add all listeners to the panel
		this.addKeyListener(km);
		this.addMouseListener(mh);
		this.addMouseMotionListener(mh);
		this.setLayout(null);
		this.add(counter);

		asteroidManager.spawnAsteroids(); // Initial wave

		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		cube.update();
		bs.checkClick();
		asteroidManager.update();

		asteroidManager.checkCollision(cube);
		bulletAsteroidCollisionUpdate();

		// Respawn wave if all asteroids are destroyed
		if (asteroidManager.asteroids.size() < 1) {
			asteroidManager.spawnAsteroids();
		}

		if (cube.lives <= 0) {
			counter.over = true;
		}

		counter.update(cube);
	}

	// Custom logic to handle bullet movement and collision with asteroids
	public void bulletAsteroidCollisionUpdate() {
		if (bs.bullets.size() > 0) {
			for (int i = 0; i < bs.bullets.size(); i++) {
				bs.bullets.get(i).update();

				if (asteroidManager.checkCollision(bs.bullets.get(i))) {
					cube.score += 100;
					asteroidManager.removeNoDraw(); // Clean up destroyed asteroid
				}
				// Remove bullet if it leaves screen boundaries
				if (bs.bullets.get(i).x < 0 || bs.bullets.get(i).x > Panel.frameWidth || bs.bullets.get(i).y < 0
						|| bs.bullets.get(i).y > Panel.frameHeight) {
					bs.bullets.remove(i);
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		cube.draw(g2);
		asteroidManager.draw(g2);
		if (bs.bullets.size() > 0) {
			for (int i = 0; i < bs.bullets.size(); i++) {
				bs.bullets.get(i).draw(g2);
			}
		}

		counter.draw(g2);
		g2.dispose();
	}

	// The Delta Timing Game Loop
	@Override
	public void run() {
		double interval = 1_000_000_000 / FPS; // Time per frame in nanoseconds
		double delta = 0;
		long currentTime;
		long previousTime = System.nanoTime();

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - previousTime) / interval;
			previousTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				if (counter.over) {
					gameThread = null; // Exit loop on game over
				}
				delta--;

				// Reset click flag to prevent continuous firing from one click
				if (mh.click == true) {
					mh.click = false;
				}
			}
		}
	}
}