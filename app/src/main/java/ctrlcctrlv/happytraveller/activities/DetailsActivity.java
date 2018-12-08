package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URLEncoder;
import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.url.BingSearchApiSample;
import ctrlcctrlv.happytraveller.url.BingWebSearch;
import ctrlcctrlv.happytraveller.url.SearchResults;
import static ctrlcctrlv.happytraveller.url.BingWebSearch.SearchWeb;



public class DetailsActivity extends AppCompatActivity {
    //ui
    public WebView wiki;
    //variables
    String encoding = "UTF-8";
    private static String searchName=null;
    public String encoded=null;
    protected BingWebSearch bsw;
    protected BingSearchApiSample bsap;

    public static void setSearchTerm(String newSearchTerm) {
        searchName = newSearchTerm;
    }
    public static String getSearchTerm(){
        return searchName;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///call from mainactivity//
        Intent i = getIntent();
        searchName=i.getExtras().getString("searchtittle");

        //bingwebsearchs
        BingWebSearch bws = new BingWebSearch();
        bws.setSearchTerm(searchName);
        bws.getSearchTerm();

        Log.d("BING TERM", searchName);
        try {
            SearchResults result = SearchWeb(searchName);
            String jsonheaders = "";
            for (String header : result.relevantHeaders.keySet()) {
                jsonheaders += header + ": " + result.relevantHeaders.get(header);
            }

            String pretty = bws.prettify(result.jsonResponse);
            Log.d("Json headers", jsonheaders);
            Log.d("Prettyfiy", pretty);
        } catch (Exception e) {


            e.printStackTrace();
        }
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

        //set URL
        String wikipediaURL =null;
        searchName=URLEncoder.encode(searchName);
        wikipediaURL="https://en.wikipedia.org/wiki/";

    }

}





