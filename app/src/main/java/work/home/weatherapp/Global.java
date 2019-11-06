package work.home.weatherapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-10-24.
 */
public class Global {

    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt * 1000L);
        DateFormat sdf = SimpleDateFormat.getDateInstance(3, Locale.forLanguageTag("lt-LT"));
        return sdf.format(date);
    }

    public static String convertUnixToHour(long dt) {
        Date date = new Date(dt * 1000L);
        DateFormat sdf = SimpleDateFormat.getTimeInstance();
        return sdf.format(date);
    }


    public static int switchWeatherIcon(int iconCode) {
        switch (iconCode) {
            case 200:
            case 201:
            case 202:
            case 210:
            case 211:
            case 212:
            case 221:
            case 230:
            case 231:
            case 232:
                return IconListIF.THUNDERSTORM;

            case 300:
            case 301:
            case 302:
            case 310:
            case 311:
            case 312:
            case 313:
            case 314:
            case 321:
                return IconListIF.SHOWER_RAIN;
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 511:
            case 520:
            case 521:
            case 522:
            case 531:
                return IconListIF.RAIN;
            case 600:
            case 601:
            case 602:
            case 611:
            case 612:
            case 613:
            case 615:
            case 620:
            case 621:
            case 622:
                return IconListIF.SNOW;
            case 701:
            case 711:
            case 721:
            case 731:
            case 741:
            case 751:
            case 761:
            case 771:
            case 781:
                return IconListIF.MIST;
            case 800:
                return IconListIF.CLEAR_SKY_DAY;
            case 801:
                return IconListIF.FEW_CLOUDS_DAY;
            case 803:
                return IconListIF.BROKEN_CLOUDS;
            case 802:
            case 804:
            default:
                return IconListIF.SCATTEREDD_CLOUDS_ALL;
        }
    }

    // api end point
    // https://samples.openweathermap.org/data/2.5/weather?q=London,
    // uk&appid=40b46dbf8cbe387ae1fc7445d3c68255
}