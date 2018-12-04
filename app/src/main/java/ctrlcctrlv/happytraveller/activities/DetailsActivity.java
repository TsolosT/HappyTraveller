package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;

import com.google.android.gms.location.places.Place;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import ctrlcctrlv.happytraveller.adapters.ListItemAdapter;
import ctrlcctrlv.happytraveller.jsonParser.PlaceParser;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.R;


public class DetailsActivity extends AppCompatActivity {
    //ui
    public WebView wiki;
    //variables
    public String encoding = "UTF-8";
    public String searchName;
    public String encoded=null;
    private static ArrayList<PlaceData> placeDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();//call from mainactivity
        searchName=i.getExtras().getString("searchtittle");

        //ui
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        wiki = (WebView) findViewById(R.id.mediaWiki);

        // Configure related browser settings
        wiki.getSettings().setLoadsImagesAutomatically(true);
        wiki.getSettings().setJavaScriptEnabled(true);
        wiki.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // Configure the client to use when opening URLs
        wiki.setWebViewClient(new WebViewClient());


        //get list from fragment


        //id = list


        //
        //set title from listitemadapter with intent
       // searchName=ListItemAdapter.getWikiTittle();

        //wikipediaURl
        String wikipediaURL = "https://en.wikipedia.org/wiki/"+searchName;
      //  wiki.loadUrl(wikipediaURL); //search = tittle

       // encode URL

        try {
            encoded= URLEncoder.encode(wikipediaURL,encoding);
           wiki.loadUrl(encoded); //search = tittle
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
      }
    }
}