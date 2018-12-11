package ctrlcctrlv.happytraveller.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
import ctrlcctrlv.happytraveller.adapters.ListItemAdapter;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.url.PlaceUrl;

import static ctrlcctrlv.happytraveller.jsonParser.PlaceParser.parseGoogleParse;

/*
 * This class is the fragment for the tab list view
 * It displays the fragment_tab_list_view.xml
 *
 * */
public class TabListViewFragment extends Fragment
{
    protected View view;
    private static ArrayList<PlaceData> placeData;
    protected  ListView listView;
    private static ListItemAdapter adapter;
    protected HomeActivity homeActivity;
    protected  TextView textViewHidden;
    private int delayTime;
    private int renewTime;
    private Timer timer;


    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.fragment_tab_list_view, container, false);
        init();
        homeActivity = new HomeActivity();
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        new googleplaces().execute();
        refreshPlaceList();
    }
    public void init()
    {
        listView= (ListView)view.findViewById(R.id.listView);
        textViewHidden=(TextView)view.findViewById(R.id.textViewHidden);
        delayTime=0;
        renewTime=900000; //15min in ms
        timer=new Timer();

    }

    private class googleplaces extends AsyncTask<View,String,String> {

        String jsonCallerMuseum;
        String jsonCallerParks;
        String jsonCallerChurch;

        @Override
        protected String doInBackground(View... urls) {
            // make Call to the url

            PlaceUrl urlMuseum = new PlaceUrl();
            PlaceUrl urlParks = new PlaceUrl();
            PlaceUrl urlChurch = new PlaceUrl();
            // urlMuseum.setLatLng("41.081622,23.550124");
            urlMuseum.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
            urlMuseum.setPlaceType("museum");

            urlParks.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
            // urlParks.setLatLng("41.081622,23.550124");
            urlParks.setPlaceType("park");

            urlChurch.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
            //     urlChurch.setLatLng("41.081622,23.550124");
            urlChurch.setPlaceType("church");

            jsonCallerMuseum = makeCall(urlMuseum.getUrl());
            jsonCallerParks = makeCall(urlParks.getUrl());
            jsonCallerChurch=makeCall(urlChurch.getUrl());


            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            if (jsonCallerMuseum == null && jsonCallerParks == null && jsonCallerChurch== null) {
                // we have an error to the call
            } else {
                // all things went right
                // parse Google places search result
                //todo make  photo call ...
                placeData = (parseGoogleParse(jsonCallerMuseum));
                placeData.addAll(parseGoogleParse(jsonCallerChurch));
                placeData.addAll(parseGoogleParse(jsonCallerParks));
                if (placeData.size() == 0) {
                    textViewHidden.setVisibility(View.VISIBLE);
                } else {

                    adapter = new ListItemAdapter(placeData, getContext());
                    listView.setAdapter(adapter);
                }
            }
        }

        public String makeCall(String url) {
            // string buffers the url
            StringBuffer buffer_string = new StringBuffer(url);
            String replyString = "";

            // instanciate an HttpClient
            @SuppressWarnings("deprecation") HttpClient httpclient = new DefaultHttpClient();
            // instanciate an HttpGet
            HttpGet httpget = new HttpGet(buffer_string.toString());

            try {

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
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(replyString);

            // trim the whitespaces
            return replyString.trim();
        }

    }
    //function to fetch the placeData into tabMapFragment
    public static ArrayList<PlaceData> getPlaceData() { return placeData;}

    //function to refetch near sights data  every specific time
    public void refreshPlaceList()
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
