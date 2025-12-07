package com.example.assignment2_tanishqpratapsingh;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment2_tanishqpratapsingh.model.WeatherModel;
import com.example.assignment2_tanishqpratapsingh.viewmodel.WeatherViewModel;

public class DetailsCityActivity extends AppCompatActivity {

    // UI variables for all the fields displayed on the details page
    private TextView textCity, textCondition, textTemperature,
            textHumidity, textWind, textFeelsLike;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detailscity);

        // Handle back button click to return to the main page
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Receive city name from previous activity
        String cityName = getIntent().getStringExtra("city_name");

        // Bind all views
        textCity = findViewById(R.id.textCity);
        textCondition = findViewById(R.id.textCondition);
        textTemperature = findViewById(R.id.textTemperature);
        textHumidity = findViewById(R.id.textHumidity);
        textWind = findViewById(R.id.textWind);
        textFeelsLike = findViewById(R.id.textFeelsLike);
        btnBack = findViewById(R.id.btnBack);

        // Display the selected city right away
        textCity.setText(cityName);

        // Back button closes the activity
        btnBack.setOnClickListener(v -> finish());

        // Get ViewModel to fetch weather data (MVVM)
        WeatherViewModel viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Observe weather data from ViewModel → updates UI automatically
        viewModel.getWeather(cityName).observe(this, weather -> {

            if (weather == null) return; // safety check

            ConstraintLayout layout = findViewById(R.id.weatherLayout);

            // Pick background image based on the weather condition
            String condition = weather.condition.toLowerCase();

            if (condition.contains("sun") || condition.contains("clear")) {
                layout.setBackgroundResource(R.drawable.sun);
            }
            else if (condition.contains("cloud")) {
                layout.setBackgroundResource(R.drawable.cloud);
            }
            else if (condition.contains("rain") || condition.contains("drizzle")) {
                layout.setBackgroundResource(R.drawable.rain);
            }
            else if (condition.contains("snow") || condition.contains("blizzard")) {
                layout.setBackgroundResource(R.drawable.snow);
            }
            else {
                layout.setBackgroundResource(R.drawable.cloud); // fallback background
            }

            // Set all the weather values in their TextViews
            textCondition.setText(weather.condition);
            textTemperature.setText(weather.tempC + "°C / " + weather.tempF + "°F");
            textHumidity.setText("Humidity\n" + weather.humidity + "%");
            textWind.setText("Wind\n" + weather.windKph + " km/h");
            textFeelsLike.setText("Feels Like\n" + weather.feelsLikeC + "°C");
        });
    }
}
