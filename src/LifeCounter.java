import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JLabel;

public class LifeCounter extends JLabel {
	int livesDisplay;
	int scoreDisplay;
	String score = "Score: 0";
	boolean over = false; // Tracks if game over state is active

	LifeCounter(Cube cube) {
		this.scoreDisplay = cube.score;
		this.setBounds(15, 15, 60, 30);
		this.livesDisplay = cube.lives;
		this.setFont(new Font("Consolas", Font.BOLD, 25));
		this.setText("Lives: " + this.livesDisplay);
		this.setOpaque(true);
	}

	public void update(Cube cube) {
		this.livesDisplay = cube.lives;
		this.setText("Lives: " + this.livesDisplay);
		score = "Score: " + cube.score;
	}

	public void draw(Graphics2D g2) {
		g2.setFont(new Font("Consolas", Font.BOLD, 25));
		g2.setColor(Color.WHITE);
		// Draw HUD elements
		g2.drawString(this.getText(), 10, 30);
		g2.drawString(score, Panel.frameWidth - 175, 30);

		// Draw Game Over screen if player loses all lives
		if (over == true) {
			g2.setFont(new Font("Consolas", Font.BOLD, 50));
			g2.setColor(Color.WHITE);
			g2.drawString("Game Over", Panel.frameWidth / 2 - 125, Panel.frameHeight / 2);
			g2.setFont(new Font("Consolas", Font.BOLD, 25));
			g2.drawString(score, Panel.frameWidth / 2 - 125, Panel.frameHeight / 2 + 50);
		}
	}
}