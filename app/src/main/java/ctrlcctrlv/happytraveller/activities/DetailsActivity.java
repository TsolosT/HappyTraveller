package ctrlcctrlv.happytraveller.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.net.URLEncoder;

import org.apache.http.protocol.HTTP;


import java.util.ArrayList;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.R;

import static java.net.URLEncoder.encode;


public class DetailsActivity extends AppCompatActivity {
    //ui
    public WebView wiki;
    //variables
   String encoding = "UTF-8";
    private static String searchName=null;
    public String encoded=null;
    private static ArrayList<PlaceData> placeDataArrayList;

    EditText mEdit;

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
       searchName=URLEncoder.encode(searchName);
        wikipediaURL="https://en.wikipedia.org/wiki/";



      //Get the first link about Wikipedia
       String googlesearchURL="https://www.google.com/search?q="+searchName+"&num=1";
       // wiki.loadUrl(googlesearchURL);

      //Let's see what it found
       System.out.println(wikipediaURL);
        System.out.println(googlesearchURL);

        //  load the google search
       String Search= null;
        try {
            Search= URLEncoder.encode(wikipediaURL+searchName, "UTF-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
      }
        Uri uri = Uri.parse("http://www.google.com/#q=" + Search);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}





