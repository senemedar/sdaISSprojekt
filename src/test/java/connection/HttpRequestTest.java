package connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {
	private HttpRequest request = new HttpRequest();
	
	@Test
	void runRequestWithCurrentLocation() {
		String response = request.runRequest(RequestType.CURRENT_LOCATION);
		
		assertNotNull(response);
	}
	
	@Test
	void runRequestWithPeopleInSpace() {
		String response = request.runRequest(RequestType.PEOPLE_IN_SPACE);
		
		assertNotNull(response);
	}
	
	@Test
	void runRequestWithPassTimes() {
		String response = request.runRequest(RequestType.PASS_TIMES, 0.1, 22, 3);
		
		assertNotNull(response);
	}
	
	@Test
	void runRequestWithPassTimesAndNoLocation() {
		String response = request.runRequest(RequestType.PASS_TIMES);
		
		assertEquals("Zła liczna argumentów", response);
	}
	
	@Test
	void runRequestWithPassTimesAndWrongLocation1() {
		String response = request.runRequest(RequestType.PASS_TIMES, 0.1);
		
		assertEquals("Zła liczna argumentów", response);
	}
	
	@Test
	void runRequestWithPassTimesAndWrongLocation2() {
		String response = request.runRequest(RequestType.PASS_TIMES, 0.1, 0.2);
		
		assertEquals("Zła liczna argumentów", response);
	}
	
}