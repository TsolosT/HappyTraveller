package ctrlcctrlv.happytraveller.url;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
import ctrlcctrlv.happytraveller.google.RequestDirections;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.url.RoutesUrl;

import static ctrlcctrlv.happytraveller.fragments.TabListViewFragment.context;
import static ctrlcctrlv.happytraveller.fragments.TabMapFragment.getMapCoordinates;
import static ctrlcctrlv.happytraveller.fragments.TabMapFragment.passCoordinatesFromPlaces;



public class MultipleParseUrl {


    public int[][] getDistanceAndTime(LatLng onePoint, LatLng otherPoint)
    {

        Random rand = new Random();
        int[][] returnValues = new int[1][2];


        returnValues[0][0] = rand.nextInt(500) + 20; //min 20 , max 500
        returnValues[0][1] = rand.nextInt(20) + 5;


        return returnValues;
    }

    //create an arrayList that contains all different url calls for all places.
    //parse the urls
    //calculate time and meters
    public  void setUpUrlsForEveryPlace() throws IOException
    {
        HomeActivity homeActivity = new HomeActivity();
        ArrayList<PlaceData> dataPassedFromListView=null;
        TabListViewFragment tabListViewFragment;
        ArrayList<String> url_list = new ArrayList<>();
        RequestDirections requestDirections = new RequestDirections();
        //call passCoordinatesFromPlaces in order to setup dataPassedFromListView and dataPassedFromMap.
        passCoordinatesFromPlaces();
        HashMap dataPassedFromMap = getMapCoordinates();
        String myUrl;
        ArrayList<String> returnedResults=null;

        for (int j = 0; j < dataPassedFromListView.size(); j++)
        {
            RoutesUrl routes_url = new RoutesUrl("onfoot");
            LatLng place_lat_lng = (LatLng) dataPassedFromMap.get(j);
            myUrl = routes_url.getUrl(homeActivity.getUsersLocation(), place_lat_lng, context, Locale.getDefault());
            //add all url's for all different places in ArrayList
            url_list.add(myUrl);
        }

        for(int i=0;i<returnedResults.size();i++)
        {
            try {
                //parse results into jsonObject first.
                JSONObject jsonObject = new JSONObject(returnedResults.get(i));
                if(jsonObject.has("routes"))
                {
                    //System.out.println("Parsed correctly");
                    JSONArray jsonArray = jsonObject.getJSONArray("routes");
                    JSONObject routes = jsonArray.getJSONObject(0);
                    JSONArray legs   = routes.getJSONArray("legs");
                    JSONObject steps = legs.getJSONObject(0);
                    JSONObject distance = steps.getJSONObject("distance");
                    JSONObject duration = steps.getJSONObject("duration");
                    String duration_mins= duration.getString("text");
                    String distance_in_text = distance.getString("text");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
