import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class CarDodgeMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("CarDodge");
		window.setSize(600, 400);
		window.setLocation(100, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CarDodgePanel carDodgePanel = new CarDodgePanel();

		new CarDodgeListener(carDodgePanel, window);
		window.setContentPane(carDodgePanel);
		window.setResizable(false);
		window.setVisible(true);
		carDodgePanel.requestFocusInWindow();
	}
}
