package com.example.assignment2_tanishqpratapsingh.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment2_tanishqpratapsingh.model.WeatherModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherDataRepository {

    // Base API URL with my API key already included
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json?key=a2a9d142a43c4aa3b0a190750251711&q=";

    // OkHttp client to make the network request
    private OkHttpClient client = new OkHttpClient();

    // Main function that fetches weather data for a given city
    public LiveData<WeatherModel> getWeather(String city) {

        // MutableLiveData lets me post the API data back to the ViewModel
        MutableLiveData<WeatherModel> weatherData = new MutableLiveData<>();


        String url = API_URL + city;

        // Making the request object
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Enqueuing the request (runs asynchronously)
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                // If something goes wrong, log it and return null
                e.printStackTrace();
                weatherData.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Getting the raw JSON response as a string
                String result = response.body().string();

                // Converting the string into a JSON object
                JsonObject root = JsonParser.parseString(result).getAsJsonObject();

                // Breaking the JSON down to get the specific fields we need
                JsonObject location = root.getAsJsonObject("location");
                JsonObject current = root.getAsJsonObject("current");
                JsonObject conditionObj = current.getAsJsonObject("condition");

                // Creating a WeatherModel object using the API data
                WeatherModel model = new WeatherModel(
                        location.get("name").getAsString(),
                        conditionObj.get("text").getAsString(),
                        current.get("temp_c").getAsDouble(),
                        current.get("temp_f").getAsDouble(),
                        current.get("feelslike_c").getAsDouble(),
                        current.get("humidity").getAsInt(),
                        current.get("wind_kph").getAsDouble()
                );

                // Posting the data so the ViewModel can observe it
                weatherData.postValue(model);
            }
        });

        // Returning the LiveData so Activities/Fragments can observe the results
        return weatherData;
    }
}
