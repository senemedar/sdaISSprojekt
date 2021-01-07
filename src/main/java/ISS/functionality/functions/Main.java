package ISS.functionality.functions;

import ISS.database.dao.Dao;
import ISS.database.position.daoimpl.PositionDaoImpl;
import ISS.database.position.entity.Position;
import ISS.functionality.managers.DatabaseManager;
import ISS.functionality.managers.PeopleInSpaceManager;
import ISS.functionality.managers.PositionManager;
import ISS.functionality.managers.SpeedManager;
import ISS.functionality.userComms.MainWindow;
import ISS.json.mapper.JsonMapper;


public class Main {
	public static void main(String[] args) {

		DatabaseManager dbManager = new DatabaseManager();
		SpeedManager speedManager = new SpeedManager();
		PositionManager positionManager = new PositionManager();
		PeopleInSpaceManager pisManager = new PeopleInSpaceManager();

		boolean positionStatus = positionManager.startIssPositionQuery();
		boolean peopleInSpaceStatus = pisManager.startIssPositionQuery();

		MainWindow.startApplication(positionManager, pisManager, dbManager, speedManager, positionStatus, peopleInSpaceStatus);
	}
}
