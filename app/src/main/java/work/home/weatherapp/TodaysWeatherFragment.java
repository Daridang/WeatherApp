package work.home.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.location.LocationResult;

import java.text.DecimalFormat;

import work.home.weatherapp.models.entities.today.WeatherResult;
import work.home.weatherapp.presenters.WeatherPresenter;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-10-25.
 */
public class TodaysWeatherFragment extends MvpAppCompatFragment implements TodaysWeatherIF {
    @InjectPresenter
    WeatherPresenter presenter;

    @ProvidePresenter
    public WeatherPresenter providePresenter() {
        return new WeatherPresenter();
    }

    private TextView cityName;
    private TextView temperature;
    private TextView description;
    private TextView dateTime;
    private TextView windSpeed;
    private TextView pressure;
    private TextView humidity;
    private EditText searchCity;
    private ImageView weatherIcon;
    private Button search;

    public TodaysWeatherFragment() {
    }

    public static TodaysWeatherFragment newInstance() {
        return new TodaysWeatherFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todays_weather_fragment, container, false);

        initViews(view);

        presenter.getTodaysWeather("Vilnius");

        return view;
    }

    private void initViews(View view) {
        cityName = view.findViewById(R.id.city_name_txt_id);
        temperature = view.findViewById(R.id.temperature_txt_id);
        description = view.findViewById(R.id.description_txt_id);
        dateTime = view.findViewById(R.id.date_time_txt_id);
        windSpeed = view.findViewById(R.id.wind_text_txt_id);
        pressure = view.findViewById(R.id.pressure_text_txt_id);
        humidity = view.findViewById(R.id.humidity_text_txt_id);
        searchCity = view.findViewById(R.id.find_city_etxt_id);
        weatherIcon = view.findViewById(R.id.weather_img_id);

        search = view.findViewById(R.id.search_btn_id);
        search.setOnClickListener(v -> {
            String city = null;
            if (!searchCity.getText().toString().isEmpty() && searchCity != null) {
                city = searchCity.getText().toString().trim();
            }
            presenter.getTodaysWeather(city);
        });
    }

    @Override
    public void updateWeatherData(WeatherResult weatherResult) {

        DecimalFormat df = new DecimalFormat("0.0");

        cityName.setText(weatherResult.getName());

        temperature.setText(df.format(weatherResult.getMain().getTemp()));
        temperature.append("℃");

        description.setText(weatherResult.getWeather().get(0).getDescription());

        String date = Global.convertUnixToDate(weatherResult.getDt());
        dateTime.setText(date);

        windSpeed.setText(df.format(weatherResult.getWind().getSpeed()));
        windSpeed.append(" m/s");

        pressure.setText(String.valueOf(weatherResult.getMain().getPressure()));
        pressure.append(" hPa");

        humidity.setText(String.valueOf(weatherResult.getMain().getHumidity()));
        humidity.append(" %");

        int iconCode = weatherResult.getWeather().get(0).getId();
        weatherIcon.setImageResource(Global.switchWeatherIcon(iconCode));

    }

    public void updateLocation(LocationResult locationResult) {
        presenter.loadWeather(locationResult.getLastLocation());
    }
}
