package ctrlcctrlv.happytraveller.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.alterDialogs.HomeActivityAlterDialog;
import ctrlcctrlv.happytraveller.connectivity.CheckConnection;

/*
 *This is the 'main' class,it's used to
 *launch the app. and display the activity_home.xml
 *The main activity that launch the application and initialize the location
 * and basic permissions.
 *
 *@extends AppCompatActivity
 *
 *@see AppCompatActivity
 *
 *@since 5 Nov 2018
 */
public class HomeActivity extends AppCompatActivity
{
    private Context context = null;
    private  static LatLng usersLocation = null;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static int gpsRefreshTime = 5000; // 5 sec
    private Boolean mLocationPermissionsGranted = false;
    //Boolean variable that allows to send a message to user to know when is ready to press sights button
    private Boolean onReadyMessageFlag = true ;
    //Permissions
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    //After 1st boot and location permission granted the application will need to restart
    private boolean needToRestart = false ;

    //Every 5sec you can get the user`s location
    // TODO: 11/22/2018  !---USERS LOCATION---!
    public LatLng getUsersLocation()
    {
        return usersLocation;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getLocationPermission();

        //After 1st boot and location permission granted the application will need to restart
        if (needToRestart)
        {
            HomeActivityAlterDialog homeActivityAlterDialog = new HomeActivityAlterDialog();
            homeActivityAlterDialog.restartAlterDialog(context);
        }

        //If data or wifi isn`t enable ask user to enable one of them
        checkUsersDataConnectivity();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                usersLocation = new LatLng(location.getLatitude(),location.getLongitude());
                if (onReadyMessageFlag)
                {
                    Toast.makeText(getApplicationContext(), "You are ready to go !!!",Toast.LENGTH_SHORT).show();
                    onReadyMessageFlag = false ;
                }
                //System.out.println("Current Location: "+usersLocation);
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
                //Force user to open his GPS
                HomeActivityAlterDialog homeActivityAlterDialog = new HomeActivityAlterDialog();
                homeActivityAlterDialog.openGPSAlterDialog(context);
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

    /*
    *A method that when it triggers display the main page if everything is ready.
    *A public method that checks is all permissions is granted and
    * user location is granted too then it navigates to the main page.
    *
    * @param v  View object
    *
    * @see View
    */
    public void displayMainPage(View v)
    {
        if (mLocationPermissionsGranted)
        {
            if (usersLocation != null)
            {
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else
            {
                checkUsersDataConnectivity();
                Toast.makeText(getApplicationContext(), "Wait till GPS is stable",Toast.LENGTH_SHORT).show();
            }
        }

    }
    /*
     *A method that when it triggers display the log in page.
     *A public method  it navigates to the log in page.
     *
     * @param v  View object
     * @throws e If something went wrong it printstack of the error and display a toast to the user.
     * @see View
     * @see Toast
     */
    public void displayLogInPage(View v){
        Intent intent=new Intent(this,LogInActivity.class);
        try
        {
            startActivity(intent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }
    }
    /*
     *A method that when it triggers display the sign up page.
     *A public method  it navigates to the sign up page.
     *
     * @param v  View object
     * @throws e If something went wrong it printstack of the error and display a toast to the user.
     * @see View
     * @see Toast
     */
    public void displaySignUpPage(View v){
        Intent intent=new Intent(this,SignUpActivity.class);
        try
        {
            startActivity(intent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }
    }
    /*
     *A method that when it triggers display the info page.
     *A public method  it navigates to the info page.
     *
     * @param v  View object
     * @throws e If something went wrong it printstack of the error and display a toast to the user.
     * @see View
     * @see Toast
     */
    public void displayInfoPage(View v)
    {
        Intent intent=new Intent(this,InfoActivity.class);
        try
        {
            startActivity(intent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }
    }


    private void checkUsersDataConnectivity()
    {
        CheckConnection checkConnection = new CheckConnection(this);

        //If data or wifi isn`t enable ask user to enable one of them
        if(checkConnection.mobileDataIs() == false && checkConnection.wifiIs()== false && needToRestart == false)
        {
            //Mobile data is disabled here
            HomeActivityAlterDialog homeActivityAlterDialog = new HomeActivityAlterDialog();
            homeActivityAlterDialog.openDataAlterDialog(context);
        }
    }

    private void getLocationPermission()
    {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                mLocationPermissionsGranted = true;
            }
            else
                {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
            needToRestart = true;

        }
    }
}
