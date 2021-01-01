package functionality.userComms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ISS.database.position.entity.Position;
import connection.*;
import functionality.managers.DatabaseManager;
import functionality.managers.PositionManager;

public class MainWindow {
	private JPanel mainContainer;
	private JButton issLocationButton;
	private JButton peopleInSpaceButton;
	private JButton passTimesButton;
	private JButton exitButton;
	private JTextPane outputPane;
	
	private final IssApiCall issApiCall = new IssApiCall();
	
	public MainWindow() {
		exitButton.addActionListener(e -> System.exit(0));
		
		issLocationButton.addActionListener(e -> {
			Position currentPosition = DatabaseManager.readLatestPositionFromDatabase();
			displayPosition(currentPosition);
		});
		
		peopleInSpaceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
	}

	private void displayPosition(Position position) {
		
		String issPosition = "Bierząca pozycja stacji to:" +
				"\nSzerokość geograficzna: " + position.getLatitude() +
				"\nDługość geograficzna: " + position.getLongitude();
		outputPane.setText(issPosition);
	}
	
	public static void startApplication() {
		JFrame frame = new JFrame("ISS Project!");
		frame.setContentPane(new MainWindow().mainContainer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
