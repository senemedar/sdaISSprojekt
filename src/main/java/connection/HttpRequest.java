package connection;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequest {
	private final OkHttpClient client = HttpClient.getClient();
	
	private String run(String url) {
		String httpResponse;
		Request request = new Request.Builder()
				.url(url)
				.build();
	
		try {
			Response response = client.newCall(request).execute();
			if (response.body() != null) {
				httpResponse = response.body().string();
			} else {
				// todo: ask if return string or maybe throw an exception?
				httpResponse = "Nieprawidłowa odpowiedź serwera lub jej brak";
			}
		} catch (IOException e) {
			httpResponse = "Nieprawidłowa odpowiedź serwera lub jej brak";
		}
		
		return httpResponse;
	}
	
	public String runRequest(RequestType requestType, double... args) {
		String runString;
		switch (requestType) {
			case CURRENT_LOCATION -> runString = ("http://api.open-notify.org/iss-now.json");
			
			case PEOPLE_IN_SPACE -> runString = ("http://api.open-notify.org/astros.json");
			
			case PASS_TIMES -> {
				if (args.length == 3) {
					runString = "http://api.open-notify.org/iss-pass.json?lat=" +
							args[0] + "&lon=" +
							args[1] + "&n=" +
							args[2];
				} else {
					return "Zła liczna argumentów";
				}
			}
			
			default -> {
				return "Niepoprawne zapytanie";
			}
		}
		
		return run(runString);
	}
}
