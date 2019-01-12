package ctrlcctrlv.happytraveller.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.activities.MainActivity;
import ctrlcctrlv.happytraveller.animations.AnimatedButton;
import ctrlcctrlv.happytraveller.google.RequestDirections;
import ctrlcctrlv.happytraveller.jsonParser.DirectionsParser;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.url.RoutesUrl;

import static ctrlcctrlv.happytraveller.activities.MainActivity.getCheckedSightsItem;


public class TabMapFragment extends Fragment implements OnMapReadyCallback
{
    public static GoogleMap mMap;
    private static Context context;
    LatLng pinsLatLng;
    private static Polyline line = null;
    //If polylines exists is true
    private static boolean polylineFlag = false;
    //If refresh button is long clicked returns true
    private static boolean longClickIs = false;
    private static HomeActivity homeActivity = null;
    public static ArrayList<PlaceData> dataPassedFromListView;
    public static ArrayList<LatLng> marker_coordinates = new ArrayList<>();
    // static HashMap<String,LatLng> mapCoordinates = new HashMap<>();
    public static TabMapFragment tabMap_instance = null;
    public static Iterator it = null;
    static HashMap<Integer,LatLng> mapCoordinates = new HashMap<>();
    public static String marker_name = null;

    public static int changePolylineColor = 0 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);

        pinsLatLng = null;
        homeActivity = new HomeActivity();
        context = getActivity().getApplicationContext();
        tabMap_instance = this;

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_map, container, false);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeActivity.getUsersLocation(), 15));


        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

//========================================================================================Button=================================================================================================
        final Button refreshButton = (Button) getView().findViewById(R.id.refreshButton);

        //When refreshButton is clicked refreshes the route from user`s location and the pin
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!longClickIs) {
                    //code
                } else {
                    longClickIs = false;
                }
            }
        });

        //When Refresh refreshButton long clicked clears the map from pins and polyLines with a cool animation
        refreshButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickIs = true;

                refreshButton.setText("Clearing");

                AnimatedButton animatedButton = new AnimatedButton(refreshButton);
                animatedButton.makeButtonTextTo("Clearing.", 500, "Clearing..", 500, "Clearing...", 500, "Refresh", 500);

                //clears map from everything (pins , polyLines)
                mMap.clear();

                //when long click is pressed it refreshes the Sights button from navBar too.
                MainActivity.refreshSightButton(getCheckedSightsItem());


                pinsLatLng = null;

                return false;
            }
        });
//==========================================================================================================================================================================================

        //Code that will be executed when long click is pressed
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng current_latLng) {
                //set location listener on

                pinsLatLng = current_latLng;


                //MarkerOptions markerOptions = new MarkerOptions();
                //markerOptions.position(current_latLng);

//                //Clears the map and add a red pin
//                if (pinsLatLng != null)
//                {
//                    clearMap();
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                }
//                mMap.addMarker(markerOptions);
            }
        });


        //Code that will be executed when click is pressed
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Code
            }

        });


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker)
            {

                if (marker.getSnippet() != null)
                {
                    if (marker.getSnippet().equals("Suggested Sight"))
                    {
                        marker.showInfoWindow();
                    }
                }
                     else
                        {
                        //check if a route already exists
                        if (line != null)
                            line.remove();

                        marker_name = marker.getTitle();
                        double users_current_latitude = (homeActivity.getUsersLocation().latitude);
                        double users_current_longitude = (homeActivity.getUsersLocation().longitude);
                        LatLng user_coordinates = new LatLng(users_current_latitude, users_current_longitude);
                        LatLng markerPosition;
                        markerPosition = marker.getPosition();
                        drawRouteOnMap(user_coordinates, markerPosition);

                    }
                return false;
            }

        });
    }


    public static class TaskRequestDirections extends AsyncTask<String,Void,String>
    {
        /**
         * Extracts an JSONObject
         * @see RequestDirections
         * @param strings
         * @return
         */
        @Override
        protected String doInBackground(String... strings)
        {
            String responseString = "";
            try {
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


    public static class  TaskParser extends AsyncTask<String , Void , List<List<HashMap<String, String>>>>
    {

        /**
         * @see DirectionsParser
         * @param strings
         * @return a list of containing latitude and longitude
         */
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

        /**
         * Draws the polylines on map
         * @param lists
         */
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists)
        {
            //Get list route and display it into the map
            ArrayList points = null;

            PolylineOptions polylineOptions= null;

            MainActivity mainActivit = new MainActivity();

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
                switch (changePolylineColor)
                {
                    case 0: polylineOptions.color(Color.RED);
                        changePolylineColor ++ ;
                        break;
                    case 1: polylineOptions.color(Color.YELLOW);
                        changePolylineColor ++;
                        break;
                    case 2: polylineOptions.color(Color.GREEN);
                        changePolylineColor ++ ;
                        break;
                    case 3: polylineOptions.color(Color.CYAN);
                        changePolylineColor ++;
                        break;
                    case 4: polylineOptions.color(Color.BLUE);
                        changePolylineColor =0 ;
                        break;
                }
                polylineOptions.geodesic(true);
            }

            if (polylineOptions != null)
            {
                line = mMap.addPolyline(polylineOptions);
                polylineFlag = true;
            }else
            {
                  Toast.makeText(getTabMap_instance().getActivity().getApplicationContext(), "Direction not found!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Draws a route in map between two points
     * @param origin
     * @param destination
     */
    public void drawRouteOnMap(LatLng origin, LatLng destination)
    {
        MainActivity mainActivity = new MainActivity();
        RoutesUrl routesUrl = new RoutesUrl(mainActivity.getCheckedTransportItem());
        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();

        taskRequestDirections.execute(routesUrl.getUrl(origin,destination, context, Locale.getDefault()));
    }

    /**
     * A function called by SuggestSightsToVisit class in order to display the suggested places with pins and names
     *
     * @see ctrlcctrlv.happytraveller.suggestionsToUser.SuggestSightsToVisit
     * @param origin
     * @param destination
     * @param sight
     */
    public void drawRouteOnMap(LatLng origin, LatLng destination,PlaceData sight)
    {
        MainActivity mainActivity = new MainActivity();

        RoutesUrl routesUrl = new RoutesUrl(mainActivity.getCheckedTransportItem());
        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();

        mMap.addMarker(new MarkerOptions().position(origin));
        mMap.addMarker(new MarkerOptions().position(destination).title(sight.getName()).snippet("Suggested Sight"));

        taskRequestDirections.execute(routesUrl.getUrl(origin,destination, context, Locale.getDefault()));
    }


    /**
     * Clears everything map has (pins , routes etc.)
     */
    public void clearMap()
    {
        mMap.clear();
    }


    public static void showSightsWithPins()
    {
        TabListViewFragment tabListViewFragment = new TabListViewFragment();

        dataPassedFromListView = tabListViewFragment.getPlaceData();

        if (dataPassedFromListView == null)
        {
            Toast.makeText(context, "Cannot display pins on map(List of sights empty)",Toast.LENGTH_SHORT).show();
        }
        else {
            for(int i=0;i< dataPassedFromListView.size();i++ )
            {
                Object obj = dataPassedFromListView.get(i);

                Double lat = dataPassedFromListView.get(i).getLatitude();
                Double lng = dataPassedFromListView.get(i).getLongitude();
                LatLng final_location = new LatLng(lat,lng);

                mMap.addMarker(new MarkerOptions().position(final_location).title(((PlaceData) obj).getName()));
            }
        }
    }

    public static void passCoordinatesFromPlaces()
    {
        TabListViewFragment tabListViewFragment = new TabListViewFragment();

        dataPassedFromListView = tabListViewFragment.getPlaceData();

        for(int i=0;i< dataPassedFromListView.size();i++)
        {

            Double lat = dataPassedFromListView.get(i).getLatitude();
            Double lng = dataPassedFromListView.get(i).getLongitude();
            LatLng final_location = new LatLng(lat, lng);

            //list for all marker coordinates
            marker_coordinates.add(final_location);

            //pass coordinates to hashMap
            mapCoordinates.put(i, final_location);
        }
    }

    public static TabMapFragment getTabMap_instance() { return tabMap_instance;}

    public static HashMap<Integer,LatLng> getMapCoordinates() { return mapCoordinates;}

    public static Polyline getPolylineState() { return line;}
}
