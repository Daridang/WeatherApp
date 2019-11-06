package work.home.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import work.home.weatherapp.Global;
import work.home.weatherapp.R;
import work.home.weatherapp.models.entities.forecast.WeatherList;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-05-26.
 */
public class WeatherResultAdapter extends
        RecyclerView.Adapter<WeatherResultAdapter.WeatherViewHolder> {

    private List<WeatherList> list = new ArrayList<>();

    public WeatherResultAdapter() {}

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherList weatherList = list.get(position);
        DecimalFormat df = new DecimalFormat("0.0");

        holder.temperature.setText(df.format(weatherList.getMain().getTemp()));
        holder.temperature.append("℃");

        String hours = Global.convertUnixToHour(weatherList.getDt());
        holder.time.setText(hours);

        String date = Global.convertUnixToDate(weatherList.getDt());
        holder.date.setText(date);

        holder.image.setImageResource(Global.switchWeatherIcon(
                weatherList.getWeather().get(0).getId()
        ));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<WeatherList> getWeatherList() {
        return list;
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {

        public TextView temperature;
        public TextView date;
        public TextView time;
        public ImageView image;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            temperature = itemView.findViewById(R.id.item_temperature_txt_id);
            time = itemView.findViewById(R.id.item_time_txt_id);
            date = itemView.findViewById(R.id.date_txt_id);
            image = itemView.findViewById(R.id.weather_icon_img_id);
        }
    }
}
