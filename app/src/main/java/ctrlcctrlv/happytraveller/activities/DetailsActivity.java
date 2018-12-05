package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import java.net.URLEncoder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.R;

import static java.net.URLEncoder.encode;


public class DetailsActivity extends AppCompatActivity {
    //ui
    public WebView wiki;
    //variables
    private static final String encoding = "UTF-8";
    private static String searchName=null;
    public String encoded=null;
    private static ArrayList<PlaceData> placeDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ///call from mainactivity//
        Intent i = getIntent();
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
      //  wiki.loadUrl(wikipediaURL); //search = tittle

       // encode URL
        String encoded= null;
        System.out.format("'%s'\n", encoded);

        //set URL
        String wikipediaURL =null;

        //Search the google for Wikipedia Links
        Document google = null;
        wiki.getSettings().setUserAgentString("Mozilla/5.0");
     //   google = (Document) Jsoup.connect("https://www.google.com/search?q="+searchName).userAgent("Mozilla");

      //Get the first link about Wikipedia
      // google.getElementsByTag("cite").get(0).text();

        //Use Wikipedia API to get JSON File
        String wikipediaApiJSON ="https://www.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";

        //set wikiURL
       wikipediaURL="https://en.wikipedia.org/wiki/"+searchName;
      //Let's see what it found
        System.out.println(wikipediaURL);
       System.out.println(wikipediaApiJSON);



       //load url
        wiki.loadUrl(wikipediaURL);

        //fetch the api




    }
}