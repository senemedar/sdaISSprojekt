package ISS.functionality.userComms;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
import ISS.functionality.isspasses.ISSPass;
import ISS.functionality.managers.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class MainWindow {
	private static PeopleInSpaceManager peopleInSpaceManager;
	private static SpeedManager speedManager;
	private static DatabaseManager databaseManager;
	private static ISSPassesManager issPassesManager;
	private static final StringBuilder servicesStatus = new StringBuilder();

	private static final JFrame passTimesFrame = new JFrame("Czasy przelotów ISS");
	private final PassTimes passTimesWindow = new PassTimes();
	
	private JPanel mainContainer;
	private JButton issLocationButton;
	private JButton peopleInSpaceButton;
	private JButton passTimesButton;
	private JButton exitButton;
	private JTextPane outputPane;
	private JButton button_showSpeed;

	public MainWindow() {

		preparePassTimesWindow();
		outputPane.setText(servicesStatus.toString());
		
		exitButton.addActionListener(e -> System.exit(0));
		issLocationButton.addActionListener(e -> {
			Position currentPosition = databaseManager.getLastPosition();
			displayPosition(currentPosition);
		});
		
		peopleInSpaceButton.addActionListener(e -> {
			NumberOfAstronauts numberOfAstronauts = peopleInSpaceManager.getPeopleInSpace();
			displayAstronauts(numberOfAstronauts);
		});
		
		passTimesButton.addActionListener(e -> {
			passTimesFrame.setVisible(true);
			passTimesFrame.transferFocus();
		});
		
		button_showSpeed.addActionListener(e -> {
			double currentSpeed = speedManager.getSpeed();
			
			outputPane.setText(String.format(
					"Obecna prędkość stacji kosmicznej to: %.2f km/s", currentSpeed)
			);
		});
	}
	
	public void closeWindow() {
		passTimesFrame.setVisible(false);
		displayPassTimes();
	}

	private void displayPosition(Position position) {
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String formattedDate = sdf.format(position.getTimestamp()*1000L);
		String issPosition = "Pozycja stacji na dzień " + formattedDate + ":" +
				"\nSzerokość geograficzna: " + position.getLatitude() +
				"\nDługość geograficzna: " + position.getLongitude();
		outputPane.setText(issPosition);
	}

	private void displayAstronauts(NumberOfAstronauts astronauts) {
		String PiS = "Ilość ludzi przebywających obecnie w kosmosie: " +
				astronauts.getCount();
				
		outputPane.setText(PiS);
	}

	private void displayPassTimes() {
		String passTimesString = "Przeloty dla zadanych parametrów: \n";
		
		ISSPass[] issPassesArray = MainWindow.getIssPassesManager().getPasses(
				Double.parseDouble(PassTimes.getLatitude()),
				Double.parseDouble(PassTimes.getLongitude()),
				Integer.parseInt(PassTimes.getPassNo()));
				
		int count = 1;
		for (ISSPass pass : issPassesArray) {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy, HH:mm");
			String formattedDate = sdf.format(pass.getRisetime()*1000L);
			int duration = pass.getDuration()/60;
			passTimesString += "\nPrzelot " + count + ":\n" +
					formattedDate + ", czas trwania: " +
					duration + " minut";
			
			if (duration < 5)
				passTimesString += "y";
			
			count++;
		}
		
		outputPane.setText(passTimesString);
	}
	
	private void preparePassTimesWindow() {
		passTimesFrame.setContentPane(passTimesWindow.getMainContainer());
		passTimesFrame.setDefaultCloseOperation(passTimesFrame.HIDE_ON_CLOSE);
		passTimesFrame.pack();
		passTimesFrame.setLocation(200, 200);
		passTimesWindow.setMainWindow(this);
	}
	
	public static void startApplication() {
		databaseManager = new DatabaseManager();
		speedManager = new SpeedManager();
		peopleInSpaceManager = new PeopleInSpaceManager();
		issPassesManager = new ISSPassesManager();
		PositionManager positionManager = new PositionManager();
		
		boolean positionStatus = positionManager.startIssPositionQuery();
		boolean peopleInSpaceStatus = peopleInSpaceManager.startIssPositionQuery();
		
		servicesStatus.append("Usługa pobierania pozycji: ");
		if (positionStatus)
			servicesStatus.append("uruchomiona.");
		else
			servicesStatus.append("błąd usługi.");

		servicesStatus.append("\nUsługa pobierania ilości astronautów: ");
		if (peopleInSpaceStatus)
			servicesStatus.append("uruchomiona.");
		else
			servicesStatus.append("błąd usługi.");

		JFrame frame = new JFrame("ISS Project!");
		frame.setContentPane(new MainWindow().mainContainer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(100,100);
		frame.setVisible(true);
	}

	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
	
	public static ISSPassesManager getIssPassesManager() {
		return issPassesManager;
	}
}
