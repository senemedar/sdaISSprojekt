package functionality.functions;

import connection.IssApiCall;
import connection.RequestType;
import connection.WrongNumberOfArgumentsException;
import functionality.userComms.MainWindow;

import javax.swing.*;


public class Main {
	public static void main(String[] args) {
		IssApiCall issApiCall = new IssApiCall();
//		String response = connection.run("https://raw.github.com/square/okhttp/master/README.md");
//		String response1 = issApiCall.runRequest(RequestType.CURRENT_LOCATION);
//		String response2 = null;
//		try {
//			response2 = issApiCall.runRequest(RequestType.PASS_TIMES, 0.1);
//		} catch (WrongNumberOfArgumentsException e) {
//			e.printStackTrace();
//		}
//		String response3 = issApiCall.runRequest(RequestType.PASS_TIMES, 0.1, 0.1, 2);
//		System.out.println(response1);
//		System.out.println(response2);
//		System.out.println(response3);
		
//		JFrame frame = new JFrame("ISS Project!");
//		frame.setContentPane(new MainWindow().getMainContainer());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);

		MainWindow.startApplication();
	}
}
