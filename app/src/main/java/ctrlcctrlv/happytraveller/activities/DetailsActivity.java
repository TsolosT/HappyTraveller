package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ctrlcctrlv.happytraveller.R;

public class DetailsActivity extends AppCompatActivity {

    //ui
    public WebView wiki;
    //variables
    private static String searchName = null;
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

        URLsearch="https://www.google.com/search?q={"+searchName+" wikipedia}&btnI";


        //fetch the first result
        wiki.loadUrl(URLsearch);






    }


}





