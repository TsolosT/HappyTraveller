package ctrlcctrlv.happytraveller.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import ctrlcctrlv.happytraveller.R;
/*
 * This is the 'main' class,it's used to
 * launch the app. and display the activity_home.xml
 *
 * */
public class HomeActivity extends AppCompatActivity
{
    private static LatLng usersLocation = null;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static int gpsRefreshTime = 5000; // 5 sec


    //Every 5sec you can get the user`s location
    // TODO: 11/22/2018  !---USERS LOCATION---!
    public LatLng getUsersLocation()
    {
        return usersLocation;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                usersLocation = new LatLng(location.getLatitude(),location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }

            @Override
            public void onProviderEnabled(String provider)
            {

            }

            @Override
            public void onProviderDisabled(String provider)
            {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return ;
        }
        locationManager.requestLocationUpdates("gps", gpsRefreshTime, 0, locationListener);

    }



    public void displayMainPage(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void displayLogInPage(View v){
        Intent intent=new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
    public void displaySignUpPage(View v){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    public void displayInfoPage(View v){
        // TODO: 13/11/2018 otan ginei to info page
        //Intent intent=new Intent(this,InfoActivity.class);
        //startActivity(intent);
    }
}
