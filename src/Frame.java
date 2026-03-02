import javax.swing.JFrame;

public class Frame extends JFrame {

	public final int frameWidth = 400;
	public final int frameHeight = 400;
	public final int screenArea = frameWidth * frameHeight;
	Panel panel;

	Frame() {
		panel = new Panel(); // Initialize the game engine panel

		this.setSize(panel.frameWidth, panel.frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Asteroid Clone");

		this.add(panel);
		this.setVisible(true);

		panel.startGameThread(); // Start the main game loop
	}
}