package work.home.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import work.home.weatherapp.adapter.WeatherResultAdapter;
import work.home.weatherapp.models.entities.forecast.WeatherList;
import work.home.weatherapp.presenters.ForecastPresenter;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-10-29.
 */
public class ForecastFragment extends MvpAppCompatFragment implements ForecastIF {

    @InjectPresenter
    ForecastPresenter presenter;

    @ProvidePresenter
    public ForecastPresenter providePresenter() {
        return new ForecastPresenter();
    }

    public ForecastFragment() {
    }

    public static ForecastFragment newInstance() {
        return new ForecastFragment();
    }

    private RecyclerView recyclerView;
    private WeatherResultAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forecast_fragment, container, false);

        recyclerView = view.findViewById(R.id.list_forecast);
        adapter = new WeatherResultAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            String lat = getArguments().getString("lat");
            String lon = getArguments().getString("lon");
            presenter.getForecastByCoords(lat, lon);
        } else {
            presenter.getForecastByCoords("23.4445", "55.9283");
        }

        return view;
    }

    @Override
    public void updateForecastList(List<WeatherList> list) {
        adapter.getWeatherList().clear();
        adapter.getWeatherList().addAll(list);
        adapter.notifyDataSetChanged();
    }
}
