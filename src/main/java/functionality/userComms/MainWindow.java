package functionality.userComms;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
import connection.IssApiCall;
import functionality.managers.DatabaseManager;
import functionality.managers.PeopleInSpaceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import ISS.database.position.entity.*;

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
				NumberOfAstronauts numberOfAstronauts = PeopleInSpaceManager.getPeopleInSpace();
				displayAstronauts(numberOfAstronauts);
			}
		});
	}

	private void displayPosition(Position position) {
		String issPosition = "Bierząca pozycja stacji to:" +
				"\nSzerokość geograficzna: " + position.getLatitude() +
				"\nDługość geograficzna: " + position.getLongitude();
		outputPane.setText(issPosition);
	}

	private void displayAstronauts(NumberOfAstronauts astronauts) {
		String PiS = "Ilość ludzi przebywających obecnie w kosmosie: " +
				astronauts.getCount();
				
		outputPane.setText(PiS);
	}
	
	public static void startApplication() {
		JFrame frame = new JFrame("ISS Project!");
		frame.setContentPane(new MainWindow().mainContainer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
