package work.home.weatherapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends MvpAppCompatActivity {

    private static final int PERMISSION_REQUEST = 101001011;

    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.main);

        bundle = new Bundle();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu_id);
        bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);

        loadFragment(TodaysWeatherFragment.newInstance());

        requestLocation();
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_id, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    private void requestLocation() {
        buildLocationRequest();
        buildLocationCallback();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        PERMISSION_REQUEST
                );
            }
        }

        FusedLocationProviderClient fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient
                .requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }

    private void buildLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setSmallestDisplacement(0);
    }

    private void buildLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {

                bundle.putString("lat", String.valueOf(locationResult.getLastLocation().getLatitude()));
                bundle.putString("lon", String.valueOf(locationResult.getLastLocation().getLongitude()));
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length == 2
                    && (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                requestLocation();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = item -> {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.forecast:
                fragment = ForecastFragment.newInstance();
                fragment.setArguments(bundle);
                break;
            case R.id.about:
                 fragment = AboutFragment.newInstance();
                break;
            case R.id.today:
            default:
                fragment = TodaysWeatherFragment.newInstance();
                break;
        }
        return MainActivity.this.loadFragment(fragment);
    };
}
