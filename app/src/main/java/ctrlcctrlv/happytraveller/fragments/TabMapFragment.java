package ctrlcctrlv.happytraveller.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.activities.MainActivity;
import ctrlcctrlv.happytraveller.animations.AnimatedButton;
import ctrlcctrlv.happytraveller.google.GPSListener;
import ctrlcctrlv.happytraveller.google.RequestDirections;
import ctrlcctrlv.happytraveller.jsonParser.DirectionsParser;
import ctrlcctrlv.happytraveller.url.CreateUrl;
// TODO: 15/11/2018 fix bug #1 need restart the app after permission request about location use
// TODO: 15/11/2018  fix bug #2 2 pin route crash


public class TabMapFragment extends Fragment implements OnMapReadyCallback
{
    private GoogleMap mMap;
    LatLng pinsLatLng ;
    private static final int LOCATION_REQUEST = 500;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private static LatLng myLocation = null;
    private static Polyline line = null ;
    //Sets location listener on/off
    private static boolean locationListenerIs = true ;
    //If polylines exists is true
    private static boolean polylineFlag = false;
    //If refresh button is long clicked returns true
    private static boolean longClickIs =false;





    public static LatLng getMyLocation() { return myLocation; }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);
        getActivity().startService(new Intent(getActivity(), GPSListener.class));

        pinsLatLng = null;

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_map, container, false);
    }




    @Override
    public void onMapReady(GoogleMap googleMap)
    {
//========================================================================================Button=================================================================================================
        final Button button = (Button) getView().findViewById(R.id.button2);

        //When button is clicked refreshes the route from user`s location and the pin
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (longClickIs == false)
                {
                    if (myLocation != null)
                    {
                        if (polylineFlag)
                            line.remove();

                        CreateUrl createUrl = new CreateUrl();
                        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();

                        taskRequestDirections.execute(createUrl.getUrl(myLocation,pinsLatLng, getActivity().getApplicationContext(), Locale.getDefault()));
                    }else
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "My location not found!",Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    longClickIs = false;
                }
            }
        });

        //When Refresh button long clicked clears the map from pins and polylines with a cool animation
        button.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                longClickIs = true ;

                button.setText("Clearing");

                AnimatedButton animatedButton = new AnimatedButton(button);
                animatedButton.makeButtonTextTo("Clearing.",500,"Clearing..",500,"Clearing...",500,"Refresh",500);

                //clears map from everything (pins , polylines)
                mMap.clear();

                locationListenerIs = false;
                pinsLatLng = null;

                return false;
            }
        });
//==========================================================================================================================================================================================

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                myLocation = new LatLng(location.getLatitude(),location.getLongitude());

                if (!locationListenerIs)
                {
                    locationManager.removeUpdates(locationListener);
                }
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

        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: 11/15/2018  copy and paste it down below in consider calling
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);

        //Code that will be executed when long click is pressed
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
        {
            @Override
            public void onMapLongClick(LatLng latLng)
            {
                //set location listener on
                locationListenerIs = true;
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,+
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

                pinsLatLng = latLng;

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                //Clears the map and add a red pin
                if (pinsLatLng != null)
                {
                    mMap.clear();
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                mMap.addMarker(markerOptions);
            }
        });


        //Code that will be executed when click is pressed
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                //Code
            }
        });
    }



    public class TaskRequestDirections extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            String responseString = "";
            try {
                // responseString = requestDirection(strings[0]);
                RequestDirections requestDirections = new RequestDirections();
                responseString = requestDirections.getDirections(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }


    public class  TaskParser extends AsyncTask<String , Void , List<List<HashMap<String, String>>>>
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings)
        {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject= new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);

            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists)
        {
            //Get list route and display it into the map
            ArrayList points = null;

            PolylineOptions polylineOptions= null;

            for (List<HashMap<String, String>> path : lists)
            {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path)
                {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions != null)
            {
              line = mMap.addPolyline(polylineOptions);
              polylineFlag = true;
            }else
            {
                Toast.makeText(getActivity().getApplicationContext(), "Direction not found!",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
