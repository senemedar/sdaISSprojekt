package connection;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IssApiCall {
	private final OkHttpClient client = HttpClient.getClient();
	
	private String run(String url) throws IOException {
		String httpResponse = null;
		Request request = new Request.Builder()
				.url(url)
				.build();
	

		Response response = client.newCall(request).execute();
		if (response.body() != null) {
			httpResponse = response.body().string();
		}
		if (httpResponse == null) {
			throw new IOException("Nieprawidłowa odpowiedź serwera lub jej brak");
		} else {
			return httpResponse;
		}
	}
	
	public String runRequest(RequestType requestType, double... args) throws WrongNumberOfArgumentsException {
		String runString = null;
		String returnString = null;
		switch (requestType) {
			case CURRENT_POSITION -> runString = ("http://api.open-notify.org/iss-now.json");
			
			case PEOPLE_IN_SPACE -> runString = ("http://api.open-notify.org/astros.json");
			
			case PASS_TIMES -> {
				if (args.length == 3) {
					runString = "http://api.open-notify.org/iss-pass.json?lat=" +
							args[0] + "&lon=" +
							args[1] + "&n=" +
							args[2];
				} else {
					throw new WrongNumberOfArgumentsException(
							"Niepoprawna liczba argumentów: oczekiwano trzech argumentów, " +
									"otrzymano: " + args.length);
				}
			}
		}
		
		try {
			returnString = run(runString);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return returnString;
	}
}
