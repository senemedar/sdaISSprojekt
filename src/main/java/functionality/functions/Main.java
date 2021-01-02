package functionality.functions;

import functionality.managers.PeopleInSpaceManager;
import functionality.managers.PositionManager;
import functionality.userComms.MainWindow;


public class Main {
	public static void main(String[] args) {
//		IssApiCall issApiCall = new IssApiCall();
//		String response = null;
//		try {
//			response = issApiCall.runRequest(RequestType.CURRENT_POSITION);
//		} catch (WrongNumberOfArgumentsException e) {
//			e.printStackTrace();
//		}
//		System.out.println(response);
		
		PositionManager positionManager = new PositionManager();
		Boolean positionStatus = positionManager.startIssPositionQuery();
		
		PeopleInSpaceManager pisManager = new PeopleInSpaceManager();
		Boolean peopleInSpaceStatus = pisManager.startIssPositionQuery();
		
		MainWindow.startApplication(positionManager, pisManager, positionStatus, peopleInSpaceStatus);
	}
}
