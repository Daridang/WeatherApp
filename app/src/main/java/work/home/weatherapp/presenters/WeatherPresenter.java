package work.home.weatherapp.presenters;

import android.location.Location;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import work.home.weatherapp.TodaysWeatherIF;
import work.home.weatherapp.models.entities.today.WeatherResult;
import work.home.weatherapp.models.retrofit.IOpenWeatherMap;
import work.home.weatherapp.models.retrofit.RetrofitClient;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-06-03.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<TodaysWeatherIF> {

    private IOpenWeatherMap iOpenWeatherMap;
    private CompositeDisposable compositeDisposable;

    public WeatherPresenter() {
        iOpenWeatherMap = RetrofitClient.getInstance().create(IOpenWeatherMap.class);
        compositeDisposable = new CompositeDisposable();
    }

    public void loadWeather(Location location) {

        Observable<WeatherResult> resultObservable = iOpenWeatherMap.getTodayWeatherByCoordinates(
                IOpenWeatherMap.API_KEY,
                String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()),
                "metric"
        ).subscribeOn(Schedulers.io());

        compositeDisposable.add(resultObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weather -> {
                            getViewState().updateWeatherData(weather);
                        },
                        throwable -> {
                        }
                )
        );
    }

    public void getTodaysWeather(String city) {
        compositeDisposable.add(iOpenWeatherMap.getTodayWeatherByCityName(
                city,
                IOpenWeatherMap.API_KEY,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherResult -> getViewState().updateWeatherData(weatherResult),
                        throwable -> {

                        }
                )
        );
    }

    public void dispose() {
        compositeDisposable.dispose();
    }
}
