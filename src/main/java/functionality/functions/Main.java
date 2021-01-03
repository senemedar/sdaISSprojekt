package functionality.functions;

import functionality.managers.DatabaseManager;
import functionality.managers.PeopleInSpaceManager;
import functionality.managers.PositionManager;
import functionality.userComms.MainWindow;


public class Main {
	public static void main(String[] args) {
		PositionManager positionManager = new PositionManager();
		boolean positionStatus = positionManager.startIssPositionQuery();
		
		PeopleInSpaceManager pisManager = new PeopleInSpaceManager();
		boolean peopleInSpaceStatus = pisManager.startIssPositionQuery();
		
		MainWindow.startApplication(positionManager, pisManager, positionStatus, peopleInSpaceStatus);
		
		System.out.println(Maths.calculateSpeed());
	}
}
