package functionality.functions;

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
		positionManager.startIssPositionQuery();
		
		MainWindow.startApplication();
	}
}
