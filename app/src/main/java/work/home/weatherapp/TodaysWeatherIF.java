package work.home.weatherapp;

import com.arellomobile.mvp.MvpView;

import work.home.weatherapp.models.entities.today.WeatherResult;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-10-25.
 */
public interface TodaysWeatherIF extends MvpView {

    void updateWeatherData(WeatherResult weatherResult);
}
