package work.home.weatherapp.models.entities.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.home.weatherapp.models.entities.today.Clouds;
import work.home.weatherapp.models.entities.today.Main;
import work.home.weatherapp.models.entities.today.Sys;
import work.home.weatherapp.models.entities.today.Weather;
import work.home.weatherapp.models.entities.today.Wind;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-11-05.
 */
@NoArgsConstructor
@Getter
@Setter
public class WeatherList {
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    @Override
    public String toString() {
        return "WeatherList{" +
                "dt=" + dt +
                ", main=" + main +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                ", dtTxt='" + dtTxt + '\'' +
                '}';
    }
}
