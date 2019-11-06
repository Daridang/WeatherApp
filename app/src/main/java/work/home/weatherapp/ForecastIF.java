package work.home.weatherapp;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import work.home.weatherapp.models.entities.forecast.WeatherList;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-10-29.
 */
public interface ForecastIF extends MvpView {

    void updateForecastList(List<WeatherList> list);
}
