package functionality.userComms;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
import connection.IssApiCall;
import functionality.managers.PeopleInSpaceManager;
import functionality.managers.PositionManager;

import javax.swing.*;

//import ISS.database.position.entity.*;

public class MainWindow {
	private static PositionManager positionManager;
	private static PeopleInSpaceManager peopleInSpaceManager;
	private static StringBuilder servicesStatus = new StringBuilder();
	
	private static final JFrame passTimesFrame = new JFrame("Czasy przelotów ISS");
	private final PassTimes passTimesWindow = new PassTimes();
	private JPanel mainContainer;
	private JButton issLocationButton;
	private JButton peopleInSpaceButton;
	private JButton passTimesButton;
	private JButton exitButton;
	private JTextPane outputPane;
	
//	private final IssApiCall issApiCall = new IssApiCall();
	
	public MainWindow() {
		preparePassTimesWindow();
		outputPane.setText(servicesStatus.toString());
		System.out.println(servicesStatus.toString());
		
		exitButton.addActionListener(e -> System.exit(0));
		issLocationButton.addActionListener(e -> {
			Position currentPosition = positionManager.getCurrentPosition();
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
	}
	
	private void preparePassTimesWindow() {
				passTimesFrame.setContentPane(passTimesWindow.getMainContainer());
				passTimesFrame.setDefaultCloseOperation(passTimesFrame.HIDE_ON_CLOSE);
				passTimesFrame.pack();
				passTimesFrame.setLocation(200, 200);
				passTimesWindow.setMainWindow(this);
//				passTimesFrame.setVisible(true);
	}
	
	public void closeWindow() {
		passTimesFrame.setVisible(false);
		calculatePassTimes();
	}
	
	private void calculatePassTimes() {
		Double latitude = Double.parseDouble(passTimesWindow.getLatitude());
		Double longitude = Double.parseDouble(passTimesWindow.getLongitude());
		Integer passNo = Integer.parseInt(passTimesWindow.getPassNo());
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(passNo);
	}

	private void displayPosition(Position position) {
		String issPosition = "Bieżąca pozycja stacji to:" +
				"\nSzerokość geograficzna: " + position.getLatitude() +
				"\nDługość geograficzna: " + position.getLongitude();
		outputPane.setText(issPosition);
	}

	private void displayAstronauts(NumberOfAstronauts astronauts) {
		String PiS = "Ilość ludzi przebywających obecnie w kosmosie: " +
				astronauts.getCount();
				
		outputPane.setText(PiS);
	}
	
	public static void startApplication(PositionManager pm, PeopleInSpaceManager pism, boolean positionStatus, boolean pisStatus) {
		positionManager = pm;
		peopleInSpaceManager = pism;
		
		servicesStatus.append("Usługa pobierania pozycji: ");
		if (positionStatus)
			servicesStatus.append("uruchomiona.");
		else
			servicesStatus.append("błąd usługi.");

		servicesStatus.append("\nUsługa pobierania ilości astronautów: ");
		if (pisStatus)
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
}
