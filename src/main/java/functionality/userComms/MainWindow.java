package functionality.userComms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connection.*;

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
			
			try {
				String response = issApiCall.runRequest(RequestType.CURRENT_LOCATION);
				outputPane.setText(response);
			} catch (WrongNumberOfArgumentsException wrongNumberOfArgumentsException) {
				wrongNumberOfArgumentsException.printStackTrace();
			}
		});
		
		peopleInSpaceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
	}
	
	public JPanel getMainContainer() {
		return mainContainer;
	}
	
	public static void startApplication() {
		JFrame frame = new JFrame("ISS Project!");
		frame.setContentPane(new MainWindow().mainContainer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
