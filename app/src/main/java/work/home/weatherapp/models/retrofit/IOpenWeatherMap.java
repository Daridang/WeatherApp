package work.home.weatherapp.models.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import work.home.weatherapp.models.entities.forecast.City;
import work.home.weatherapp.models.entities.today.WeatherResult;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 03/01/2019.
 */
public interface IOpenWeatherMap {

    String API_KEY = "40b46dbf8cbe387ae1fc7445d3c68255";

    @GET("weather")
    Observable<WeatherResult> getTodayWeatherByCityName(@Query("q") String cityName,
                                                 @Query("appid") String apiKey,
                                                 @Query("units") String unit);

    @GET("forecast")
    Observable<City> getForecastByCityName(@Query("q") String cityName,
                                       @Query("appid") String apiKey,
                                       @Query("units") String unit);

    @GET("forecast")
    Observable<City> getForecastByCoordinates(
            @Query("appid") String apiKey,
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("units") String units);

    @GET("weather")
    Observable<WeatherResult> getTodayWeatherByCoordinates(
            @Query("appid") String apiKey,
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("units") String units);
}
