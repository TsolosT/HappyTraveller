package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import ctrlcctrlv.happytraveller.R;
/**
 *<h2>This class is used to give the user infos About places he found from the list  </h2>
 *<p>This is the activity that shows up after using Details button from the list,also it's used to
 * display the activity_details.xml . Also extends AppCompatActivity.</p>
 *
 *
 *
 *@see AppCompatActivity
 *@see Intent
 */
public class DetailsActivity extends AppCompatActivity
{

    //ui
    static WebView wiki;
    //variables
    static String searchName = null;
    static String URLsearch = "";

    /**
     * <h2>Initialize the variables that need to be used.</h2>
     * <p>A protected void method that initialize all the components that need to be used in this class.</p>
     *
     */
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





