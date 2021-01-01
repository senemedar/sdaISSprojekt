package connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssApiCallTest {
	private final IssApiCall request = new IssApiCall();
	
	@Test
	void runRequestWithCurrentLocation() throws WrongNumberOfArgumentsException {
		String response = request.runRequest(RequestType.CURRENT_POSITION);
		
		assertNotNull(response);
	}
	
	@Test
	void runRequestWithPeopleInSpace() throws WrongNumberOfArgumentsException {
		String response = request.runRequest(RequestType.PEOPLE_IN_SPACE);
		
		assertNotNull(response);
	}
	
	@Test
	void runRequestWithPassTimes() throws WrongNumberOfArgumentsException {
		String response = request.runRequest(RequestType.PASS_TIMES, 0.1, 22, 3);
		
		assertNotNull(response);
	}
	
	@Test
	void runRequestWithPassTimesAndNoLocation() {
		Assertions.assertThrows(WrongNumberOfArgumentsException.class, () ->
				request.runRequest(RequestType.PASS_TIMES)
		);
	}
	
	@Test
	void runRequestWithPassTimesAndWrongLocation1() {
		Assertions.assertThrows(WrongNumberOfArgumentsException.class, () ->
				request.runRequest(RequestType.PASS_TIMES, 0.1)
		);
	}
	
	@Test
	void runRequestWithPassTimesAndWrongLocation2() {
		Assertions.assertThrows(WrongNumberOfArgumentsException.class, () ->
				request.runRequest(RequestType.PASS_TIMES, 0.1, 0.2)
		);
	}
	
}