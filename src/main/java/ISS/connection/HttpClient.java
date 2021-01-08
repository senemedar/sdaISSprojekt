package ISS.connection;

import okhttp3.OkHttpClient;

public class HttpClient {
	private static OkHttpClient client;

	public static OkHttpClient getClient() {
		if (client == null) {
			client = new OkHttpClient();
		}
		return client;
	}
}
