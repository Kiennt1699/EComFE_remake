package API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://reinir.mooo.com:5001"; // Change this to your API base URL

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Set the base URL for API
                    .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter
                    .build();
        }
        return retrofit;
    }
}