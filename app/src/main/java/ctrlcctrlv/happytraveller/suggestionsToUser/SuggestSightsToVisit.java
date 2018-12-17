package ctrlcctrlv.happytraveller.suggestionsToUser;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;

import ctrlcctrlv.happytraveller.ThemisClass;
import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.model.PlacePhoto;

public class SuggestSightsToVisit
{

    public int[] getListOfPlacesIdsToSendOnList() {return listOfPlacesIdsToSendOnList;}

    private int [] listOfPlacesIdsToSendOnList = null;

    public ArrayList<PlaceData> suggestRouteBasedOn(int usersFreeTime)
    {
        TabMapFragment.changePolylineColor = 0;

        TabListViewFragment tabListViewFragment = new TabListViewFragment();
        ArrayList<PlaceData> placeDataListTwo = new ArrayList<>(tabListViewFragment.getPlaceData());
        ArrayList<PlaceData> suggestedPlaces = new ArrayList<>(placeDataListTwo.size());
        HomeActivity homeActivity = new HomeActivity();
        ThemisClass themisClass = new ThemisClass();
        int timeToRemove;
        TabMapFragment tabMapFragment = new TabMapFragment();
        int listsId = 0;

        // TODO: 12/12/2018 #themis delete that shit
        //=======================Delete it Themi===================================
        //initialize distance and time till arrival
        int size = placeDataListTwo.size();
        if (placeDataListTwo.size() != 0)
        {
            for (int i=0 ; i < size ; i++)
            {
                placeDataListTwo.get(i).setTimeTillArrival(i+1);
                placeDataListTwo.get(i).setDistance((i+1)*10);
            }
        }
        //===============================================================

        listOfPlacesIdsToSendOnList = new int[placeDataListTwo.size()];

        boolean callingForFirstTime = true;

        while (usersFreeTime > 0 && placeDataListTwo.size() >1)
        {
            //ascending sort placeDataList based on timeTillArrival ascending
            Collections.sort(placeDataListTwo);


            //draw the route for the 1st time and take users location
            if (callingForFirstTime)
            {
                LatLng latLng = new LatLng(placeDataListTwo.get(0).getLatitude(),placeDataListTwo.get(0).getLongitude());
                tabMapFragment.drawRouteOnMap(homeActivity.getUsersLocation(),latLng,placeDataListTwo.get(0));
                callingForFirstTime = false;

                //add the first sight`s id to listOfPlacesIdsToSendOnList in order to show the on list
                listOfPlacesIdsToSendOnList[listsId] = placeDataListTwo.get(0).getId();
                //add the first sight to returning list of suggested sights
                suggestedPlaces.add(placeDataListTwo.get(0));
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
                tabMapFragment.drawRouteOnMap(latLngOfPreviousSight,latLng,placeDataListTwo.get(0));

                //add the sight to returning list of suggested sights
                suggestedPlaces.add(placeDataListTwo.get(0));
                //add the sight`s id to listOfPlacesIdsToSendOnList in order to show the on list
                listOfPlacesIdsToSendOnList[listsId] = placeDataListTwo.get(0).getId();

                //gets the time need to go to sight in order to delete it from usersFreeTime
                timeToRemove = placeDataListTwo.get(0).getTimeTillArrival();
            }

            //delete the time needed to go to sight from users available time plus 20 min in order for user to have a mini tour
            usersFreeTime -= (timeToRemove + 20 );

            //next id
            // TODO: 12/14/2018 might be a bug here
            listsId++;
        }

        if (placeDataListTwo.size() < 2)
        {
            System.out.println("================telos ta aksiotheata=============");
        }

        TabMapFragment.changePolylineColor = 0;


        return suggestedPlaces ;

    }


}
