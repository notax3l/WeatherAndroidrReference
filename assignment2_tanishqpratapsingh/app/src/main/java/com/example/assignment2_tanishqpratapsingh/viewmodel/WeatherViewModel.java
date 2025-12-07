package com.example.assignment2_tanishqpratapsingh.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment2_tanishqpratapsingh.model.WeatherModel;
import com.example.assignment2_tanishqpratapsingh.network.WeatherDataRepository;

public class WeatherViewModel extends ViewModel {

    private WeatherDataRepository repository = new WeatherDataRepository();

    public LiveData<WeatherModel> getWeather(String city) {
        return repository.getWeather(city);
    }
}
