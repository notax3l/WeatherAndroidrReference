package com.example.assignment2_tanishqpratapsingh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2_tanishqpratapsingh.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    // This interface helps me detect when a city card is clicked
    public interface OnCityClickListener {
        void onCityClick(String cityName);
    }

    // List of city names to show in the RecyclerView
    private List<String> cityList;

    // Listener that sends the clicked city name back to the activity
    private OnCityClickListener listener;

    // Constructor to pass the list of cities and the click listener
    public CityAdapter(List<String> cityList, OnCityClickListener listener) {
        this.cityList = cityList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Here I made the layout for each city card (item_city.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        // Get the city name for the current position
        String cityName = cityList.get(position);

        // Set the title and subtitle on the card
        holder.textCityName.setText(cityName);
        holder.textCitySubtitle.setText("Tap to view weather");

        // When a city card is clicked, I send the city name back to the listener
        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCityClick(cityName);
            }
        });
    }

    @Override
    public int getItemCount() {
        // RecyclerView needs to know how many items to show
        return cityList.size();
    }

    // ViewHolder class holds references to all views inside the city card
    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView textCityName;
        TextView textCitySubtitle;
        CardView cardView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            // The whole card is a CardView, so I just cast it directly
            cardView = (CardView) itemView;

            // Getting the TextViews from the layout
            textCityName = itemView.findViewById(R.id.textCityName);
            textCitySubtitle = itemView.findViewById(R.id.textCitySubtitle);
        }
    }
}
