package work.home.weatherapp.models.entities.today;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 03/01/2019.
 */
@NoArgsConstructor
@Getter
@Setter
public class Wind {
    @SerializedName("speed")
    @Expose
    private float speed;

    @SerializedName("deg")
    @Expose
    private float deg;

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
