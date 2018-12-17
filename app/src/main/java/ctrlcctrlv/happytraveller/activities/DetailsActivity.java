package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import ctrlcctrlv.happytraveller.R;

public class DetailsActivity extends AppCompatActivity
{

    //ui
    static WebView wiki;
    //variables
    static String searchName = null;
    static String URLsearch = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //ui
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        wiki = findViewById(R.id.mediaWiki);

        ///call from mainactivity//
        Intent i = getIntent();
        searchName = i.getExtras().getString("searchtittle");

        // Configure related browser settings
        wiki.getSettings().setLoadsImagesAutomatically(true);
        wiki.getSettings().setJavaScriptEnabled(true);
        wiki.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // Configure the client to use when opening URLs
        wiki.setWebViewClient(new WebViewClient());


        //Redirects to first google search result from " <name> wikipedia " search
        URLsearch="https://www.google.com/search?q={"+searchName+" σερρες wikipedia}&btnI";

        //fetch the first result
        wiki.loadUrl(URLsearch);


    }



}





