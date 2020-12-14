package functionality.functions;

import connection.RequestType;
import connection.HttpRequest;


public class Main {
	public static void main(String[] args) {
		HttpRequest httpRequest = new HttpRequest();
//		String response = connection.run("https://raw.github.com/square/okhttp/master/README.md");
		String response1 = httpRequest.runRequest(RequestType.CURRENT_LOCATION);
		String response2 = httpRequest.runRequest(RequestType.PEOPLE_IN_SPACE);
		String response3 = httpRequest.runRequest(RequestType.PASS_TIMES, 0.1, 0.1, 2);
		System.out.println(response1);
		System.out.println(response2);
		System.out.println(response3);
	}
}
