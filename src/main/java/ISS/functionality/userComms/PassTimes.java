package ISS.functionality.userComms;

import javax.swing.*;

public class PassTimes {
	private MainWindow mainWindow;
	private JPanel panel1;
	private JPanel mainContainer;
	private JLabel label;
	private JLabel label_latitude;
	private JTextField textField_latitude;
	private JLabel label_longitude;
	private JTextField textField_longitude;
	private JLabel label_passNo;
	private JTextField textField_PassNo;
	private JButton button_OK;
	private JLabel label_validator;
	private static String latitude;
	private static String longitude;
	private static String passNo;
	
	public PassTimes() {
		button_OK.addActionListener(e -> {
			latitude = textField_latitude.getText();
			longitude = textField_longitude.getText();
			passNo = textField_PassNo.getText();
			if (latitude.equals("") || longitude.equals("") || passNo.equals(""))
				label_validator.setText("Musisz wypełnić wszystkie pola!");
			else
				mainWindow.closeWindow();
		});
	}
	
	public static String getLatitude() {
		return latitude;
	}
	
	public static String getLongitude() {
		return longitude;
	}
	
	public static String getPassNo() {
		return passNo;
	}
	
	public JPanel getMainContainer() {
		return mainContainer;
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
}
