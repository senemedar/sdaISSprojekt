package functionality.managers;

import ISS.database.position.entity.Position;

public class DatabaseManager {
	
	public static Position readLatestPositionFromDatabase() {
		// TODO: 01/01/2021
		//  tutaj trzeba dodać implementację czytania ostatniej pozycji z bazy danych
		//  poprzez PositionDaoImpl czy jakoś tak
		
		return new Position(1609533964L, -18.7746, -132.2104);	// chwilowo zwracamy "z palca" obiekt typu Position z danymi
		
	}
	
	public static void savePositionIntoDatabase(String jsonPositionString) {
		// TODO: 01/01/2021 Zamienić System.out na odpowiednią implementację wrzucania do bazy danych
		System.out.println(jsonPositionString);
	}
}
