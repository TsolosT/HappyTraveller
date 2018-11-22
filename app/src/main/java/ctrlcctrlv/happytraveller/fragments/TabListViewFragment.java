package ctrlcctrlv.happytraveller.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.adapters.ListItemAdapter;
import ctrlcctrlv.happytraveller.jsonParser.PlaceParser;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.url.PlaceUrl;

import static ctrlcctrlv.happytraveller.jsonParser.PlaceParser.parseGoogleParse;

/*
 * This class is the fragment for the tab list view
 * It displays the fragment_tab_list_view.xml
 *todo more detail about class
 * */
public class TabListViewFragment extends Fragment
{
    View view;

    ListView listView;
    private static ListItemAdapter adapter;
    PlaceParser placeParser;
    ArrayList<PlaceData> placeData;
    HomeActivity homeActivity;


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
    }
    public void init()
    {
        listView= (ListView)view.findViewById(R.id.listView);

    }

    private class googleplaces extends AsyncTask<View,String,String>
    {

        String jsonCaller; 

       @Override
        protected String doInBackground(View... urls)
       {
            // make Call to the url
            PlaceUrl url = new PlaceUrl();
          // url.setLatLng(String.format(latLng.latitude+","+latLng.longitude));// TODO: 19/11/2018  malaka aimilie dwse mou mia getCurrentLocation


           url.setLatLng(homeActivity.getUsersLocation().latitude+","+homeActivity.getUsersLocation().longitude);
           url.setPlaceType("museum");  // TODO: 19/11/2018 find way to make call with all types of sights
           jsonCaller = makeCall(url.getUrl());

            return "";
        }

        @Override
        protected void onPostExecute(String result)
        {
            if (jsonCaller == null) {
                // we have an error to the call
            } else {
                // all things went right
                // parse Google places search result
                placeData = parseGoogleParse(jsonCaller);
                // set the results to the list
                adapter = new ListItemAdapter(placeData,getContext());
                listView.setAdapter(adapter);

            }
        }


        public  String makeCall(String url)
        {
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
           // System.out.println(replyString);

            // trim the whitespaces
            return replyString.trim();
        }

    }



}
