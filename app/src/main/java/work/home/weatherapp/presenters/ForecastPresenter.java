package work.home.weatherapp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import work.home.weatherapp.ForecastIF;
import work.home.weatherapp.models.retrofit.IOpenWeatherMap;
import work.home.weatherapp.models.retrofit.RetrofitClient;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-11-05.
 */
@InjectViewState
public class ForecastPresenter extends MvpPresenter<ForecastIF> {

    private IOpenWeatherMap iOpenWeatherMap;
    private CompositeDisposable compositeDisposable;

    public ForecastPresenter() {
        iOpenWeatherMap = RetrofitClient.getInstance().create(IOpenWeatherMap.class);
        compositeDisposable = new CompositeDisposable();
    }

    public void getForecastByCity(String city) {
        compositeDisposable.add(iOpenWeatherMap.getForecastByCityName(
                city,
                IOpenWeatherMap.API_KEY,
                "metric"
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(c -> {
                                    Log.d("TAGGG", c.getList().get(0).toString());
                                }
                        )
        );
    }

    public void getForecastByCoords(String lat, String lon) {
        compositeDisposable.add(
                iOpenWeatherMap.getForecastByCoordinates(
                        IOpenWeatherMap.API_KEY,
                        lat,
                        lon,
                        "metric"
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                city -> {
                                    getViewState().updateForecastList(city.getList());
                                },
                                Throwable::fillInStackTrace
                        )
        );
    }

    public void dispose() {
        compositeDisposable.dispose();
    }
}
