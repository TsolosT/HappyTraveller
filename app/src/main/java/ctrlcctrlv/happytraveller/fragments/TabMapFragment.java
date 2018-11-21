package ctrlcctrlv.happytraveller.fragments;

import android.Manifest;


import android.content.pm.PackageManager;
import android.graphics.Color;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import ctrlcctrlv.happytraveller.services.GPSListener;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.google.RequestDirections;
import ctrlcctrlv.happytraveller.jsonParser.DirectionsParser;
import ctrlcctrlv.happytraveller.url.CreateUrl;


/*
 * This class is the fragment for the tab map
 * It displays the fragment_tab_map.xml
 *
 * Sto fragment_tab_map.xml 8a pros8esete oti ui component 8elete gia na emfanisete to map
 * Sthn class auth 8a ulopoihsete tis methods pou 8elete na usarete
 * */
public class TabMapFragment extends Fragment implements OnMapReadyCallback
{
    private GoogleMap mMap;
    ArrayList<LatLng> listPoints;
    private static final int LOCATION_REQUEST = 500;





        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);
        getActivity().startService(new Intent(getActivity(),GPSListener.class)); //service of gps refresh



        listPoints = new ArrayList<>();

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_maps, container, false);
    }



    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;


        mMap.getUiSettings().setZoomControlsEnabled(true);


        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);



        //Create two points and get the route
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
        {

            @Override
            public void onMapLongClick(LatLng latLng)
            {

                //Save first point select
                listPoints.add(latLng);
                //Clear marker
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                if (listPoints.size() == 1)
                {
                    //Add first marker to the map
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }else
                {
                    //Add second marker
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                }

                mMap.addMarker(markerOptions);

                if (listPoints.size() == 2 )
                {
                    //Create URL to get request from first marker to second marker
                    CreateUrl createUrl = new CreateUrl();

                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                    taskRequestDirections.execute(createUrl.getUrl(listPoints.get(0),listPoints.get(1),getActivity().getApplicationContext(),Locale.getDefault()));
                }
            }
        });


        //Clear the points
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                listPoints.clear();
                mMap.clear();
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
                mMap.addPolyline(polylineOptions);
            }else
            {
                Toast.makeText(getActivity().getApplicationContext(), "Direction not found!",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
