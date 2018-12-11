package ctrlcctrlv.happytraveller.suggestionsToUser;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;

import ctrlcctrlv.happytraveller.ThemisClass;
import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.activities.MainActivity;
import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
import ctrlcctrlv.happytraveller.model.PlaceData;

public class SuggestSightsToVisit
{

    public void suggestRouteBasedOn(int usersFreeTime)
    {
        TabMapFragment.changePolylineColor = 0;

        TabListViewFragment tabListViewFragment = new TabListViewFragment();
        ArrayList<PlaceData> placeDataList = tabListViewFragment.getPlaceData();
        HomeActivity homeActivity = new HomeActivity();
        ThemisClass themisClass = new ThemisClass();
        int timeToRemove;
        TabMapFragment tabMapFragment = new TabMapFragment();

        // TODO: 12/12/2018 #themis delete that shit
        //=======================Delete it Themi===================================
        //initialize distance and time till arrival
        int size = placeDataList.size();
        if (placeDataList.size() != 0)
        {
            for (int i=0 ; i < size ; i++)
            {
                placeDataList.get(i).setTimeTillArrival(i+1);
                placeDataList.get(i).setDistance((i+1)*10);
            }
        }
        //===============================================================

        ArrayList<PlaceData> placeDataListTwo = placeDataList;

        boolean callingForFirstTime = true;

        while (usersFreeTime > 0 && placeDataListTwo.size() >1)
        {
            //ascending sort placeDataList based on timeTillArrival ascending
            Collections.sort(placeDataListTwo);


            //draw the route for the 1st time and take users location
            if (callingForFirstTime)
            {
                LatLng latLng = new LatLng(placeDataListTwo.get(0).getLatitude(),placeDataListTwo.get(0).getLongitude());
                tabMapFragment.drawRouteOnMap(homeActivity.getUsersLocation(),latLng,true);
                callingForFirstTime = false;

                //gets the time need to go to sight in order to delete it from usersFreeTime
                timeToRemove = placeDataListTwo.get(0).getTimeTillArrival();
            }
            else
            //draw the route for one sight to the next one
            {
                LatLng latLngOfPreviousSight = new LatLng(placeDataListTwo.get(0).getLatitude(),placeDataListTwo.get(0).getLongitude());

                //get new distances and times
                for (int i = 1 ; i<placeDataListTwo.size() ; i++)
                {
                    int [] [] distanceAndTime = new int [1][2];
                    LatLng latLng = new LatLng(placeDataListTwo.get(i).getLatitude(),placeDataListTwo.get(i).getLongitude());

                    // TODO: 12/12/2018 #themis replace themisClass.getDistanceAndTime with yours and set the array distanceAndTime like this ->      distanceAndTime[0][0] = yourClass.getDistance(latLngOfPreviousSight,latLng)
                    // TODO: 12/12/2018 #themis                     (tha to tsekaroume kai mazi an einai)                                             distanceAndTime[0][1] = yourClass.getTime(latLngOfPreviousSight,latLng)
                    distanceAndTime  = themisClass.getDistanceAndTime(latLngOfPreviousSight,latLng);
                    placeDataListTwo.get(i).setDistance(distanceAndTime[0][0]);
                    placeDataListTwo.get(i).setTimeTillArrival(distanceAndTime[0][1]);
                }

                //deletes the sight tha already went
                placeDataListTwo.remove(0);

                //ascending sort placeDataList based on timeTillArrival ascending
                Collections.sort(placeDataListTwo);

                //gets the closest sight and draw a path
                LatLng latLng = new LatLng(placeDataListTwo.get(0).getLatitude(),placeDataListTwo.get(0).getLongitude());
                tabMapFragment.drawRouteOnMap(latLngOfPreviousSight,latLng,true);

                //gets the time need to go to sight in order to delete it from usersFreeTime
                timeToRemove = placeDataListTwo.get(0).getTimeTillArrival();
            }

            //delete the time needed to go to sight from users available time plus 20 min in order for user to have a mini tour
            usersFreeTime -= (timeToRemove + 20 );
            
        }


        if (placeDataListTwo.size() < 2)
        {
            System.out.println("================telos ta aksiotheata gamw to spiti mou =============");
        }

        TabMapFragment.changePolylineColor = 0;

    }
}
