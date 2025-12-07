package com.example.assignment2_tanishqpratapsingh;
import com.example.assignment2_tanishqpratapsingh.databinding.ViewLocationcityBinding;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2_tanishqpratapsingh.adapter.CityAdapter;
import com.example.assignment2_tanishqpratapsingh.databinding.ViewLocationcityBinding;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LocationCityActivity extends AppCompatActivity {

    private ViewLocationcityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewLocationcityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        List<String> cityList = Arrays.asList(
                "Toronto",
                "Dubai",
                "Mumbai",
                "Charlottetown"// tropical rain
        );


        // Setup RecyclerView
        binding.recyclerCities.setLayoutManager(new LinearLayoutManager(this));

        CityAdapter adapter = new CityAdapter(cityList, cityName -> {
            // When you click a city  go to DetailsCityActivity
            Intent intent = new Intent(LocationCityActivity.this, DetailsCityActivity.class);
            intent.putExtra("city_name", cityName);
            startActivity(intent);
        });

        binding.recyclerCities.setAdapter(adapter);
    }
}
