package ctrlcctrlv.happytraveller.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.activities.MainActivity;
import ctrlcctrlv.happytraveller.adapters.ListItemAdapter;
import ctrlcctrlv.happytraveller.connectivity.CheckConnection;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.url.PlaceUrl;

import static ctrlcctrlv.happytraveller.jsonParser.PlaceParser.getCompountCode;
import static ctrlcctrlv.happytraveller.jsonParser.PlaceParser.parseGoogleParse;

/**
 * This class is the fragment for the tab list view ,it displays the fragment_tab_list_view.xml .
 * It display the ListView UI with or without the place data,
 * and controls the functions tha make the place api call ,display it on ui.Also extends Fragment.
 *
 *@see Context
 *@see ListView
 *@see Fragment
 *@see View
 *@see TextView
 *@see Timer
 *
 *@since 5 Nov 2018
 *
 */
public class TabListViewFragment extends Fragment
{
    protected View view;
    private static   ArrayList<PlaceData> placeData;
    protected  ListView listView;
    private static ListItemAdapter adapter;
    protected HomeActivity homeActivity;
    protected  TextView textViewHidden;
    private int delayTime;
    private int renewTime;
    private Timer timer;
    public static Context context;
    private static googleplaces gplaces;
    private CheckConnection checkCon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.fragment_tab_list_view, container, false);
        init();

        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        gplaces.execute();
        refreshPlaceList(checkCon.checkSpeedConnection());
    }

    /**
     * Initialize the variables that need to be used.
     * A public void method that initialize all the components that need to be used in this class.
     */
    public void init()
    {
        listView= (ListView)view.findViewById(R.id.listView);
        textViewHidden=(TextView)view.findViewById(R.id.textViewHidden);
        delayTime=30000;//1min delay
        renewTime=900000; //15min in ms
        timer=new Timer();
        context = getContext();
        checkCon=new CheckConnection(getContext());
        homeActivity = new HomeActivity();
        gplaces = new googleplaces();

    }


    /**
    *A method that check the download status.
    *A public static boolean method that checks if the download status is finished or not
    * and return the status.
    * @return true/false
    */
    public static boolean placesReceived()
    {
        if(gplaces.getStatus()==AsyncTask.Status.FINISHED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
    *An class that extend AsyncTask and makes the api call.
    *It prepares all need to make the api call and retrieve the data
    *to be ready to display and display it.Also extends AsyncTask.
    *
    *
    *@see AsyncTask
    *
    *@since 15 Nov 2018
    */
    private class googleplaces extends AsyncTask<View,String,String> 
    {

        String jsonCallerMuseum;
        String jsonCallerParks;
        String jsonCallerChurch;

        @Override
        protected String doInBackground(View... urls) {
            // make Call to the url

            PlaceUrl urlMuseum = new PlaceUrl();
            PlaceUrl urlParks = new PlaceUrl();
            PlaceUrl urlChurch = new PlaceUrl();

            urlMuseum.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
            urlMuseum.setPlaceType("museum");

            urlParks.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
            urlParks.setPlaceType("park");

            urlChurch.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
            urlChurch.setPlaceType("church");

            jsonCallerMuseum = makeCall(urlMuseum.getUrl());
            jsonCallerParks = makeCall(urlParks.getUrl());
            jsonCallerChurch=makeCall(urlChurch.getUrl());


            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            if (jsonCallerMuseum == null && jsonCallerParks == null && jsonCallerChurch== null)
            {
                Toast.makeText(getContext(),"Could not get list of sights due to bad speed connection.",Toast.LENGTH_LONG).show();
                // we have an error to the call
            }
            else
                {
                // all things went right
                // parse Google places search result
                placeData = (parseGoogleParse(jsonCallerMuseum));
                placeData.addAll(parseGoogleParse(jsonCallerChurch));
                placeData.addAll(parseGoogleParse(jsonCallerParks));

                if (placeData.size() == 0 || getContext()==null)
                {
                    textViewHidden.setVisibility(View.VISIBLE);
                }
                else
                    {
                        //if(getActivity()!=null)
                            adapter = new ListItemAdapter(placeData, getContext());
                            listView.setAdapter(adapter);
                 }
            }
        }
        /**
        *An method that make the call for the api.
        *It prepares the connection and makes the call for google place api
        *and retrieves the response.
        *
        *@param url The url that need to call.
        *
        *@return replyString The json response as string buffed.
        */
        public String makeCall(String url)
        {
            // string buffers the url
            StringBuffer buffer_string = new StringBuffer(url);
            String replyString = "";

            // instanciate an HttpClient
            @SuppressWarnings("deprecation") HttpClient httpclient = new DefaultHttpClient();
            // instanciate an HttpGet
            HttpGet httpget = new HttpGet(buffer_string.toString());

            try
            {

                // get the responce of the httpclient execution of the url
                HttpResponse response = httpclient.execute(httpget);
                InputStream is = response.getEntity().getContent();

                // buffer input stream the result
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(20);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }
                // the result as a string is ready for parsing
                replyString = new String(baf.toByteArray());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return replyString.trim();
            }

            // trim the whitespaces
            return replyString.trim();
        }

    }
    /**
    *The method that shows the places on the list.
    * It initialize the adapter and prepare it for displaying the data
    *
    *@param listWithSights An ArrayList<PlaceData> that have the parsed place data.
    *
     */
    public void showSightOnList(ArrayList<PlaceData> listWithSights)
    {
        adapter = new ListItemAdapter(listWithSights, context);
        listView.setAdapter(adapter);
    }


    /** getter function to fetch the placeData which contains all information for all places
     *
     * @return placeData Object.
     */
    public  ArrayList<PlaceData> getPlaceData() { return placeData;}

    /**
    *Function to refetch near sights data  every specific time.
    *A method that depend on status type it renew the place data list
    * or not.
    *
    *@param status An integer that hold the speed connection type.
     */
    public void refreshPlaceList(int status)
    {
        String msgBad="Couldn't refresh place list due to bad speed connection";
       switch(status)
       {
           case -1:
                   runRefreshTimer();
                   break;
           case 0:
                   Toast.makeText(getContext(),msgBad,Toast.LENGTH_LONG).show();
                    break;
           case 1:
                     Toast.makeText(getContext(),msgBad,Toast.LENGTH_LONG).show();
                     break;
           case 2:
                     runRefreshTimer();
                     break;
           case 3:
                     runRefreshTimer();
                     break;

       }

    }
    /**
    *A method that executes the timer for refresh time.
     */
    public void runRefreshTimer()
    {
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                new googleplaces().execute();
            }
        }, delayTime, renewTime);
    }
}
