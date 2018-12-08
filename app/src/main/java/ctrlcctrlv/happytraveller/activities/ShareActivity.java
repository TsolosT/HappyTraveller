package ctrlcctrlv.happytraveller.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import ctrlcctrlv.happytraveller.R;

import static java.lang.Double.parseDouble;

public class ShareActivity  extends AppCompatActivity
{



    //declare variables
    Button shareApp;
    Button shareLoc;
    Button Submit;

    private  static LatLng latLng = null;
    private Intent intent;
    private Intent intent2;
    protected HomeActivity homeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        homeActivity=new HomeActivity();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        shareApp=(Button)findViewById(R.id.buttonShare);
        shareLoc=(Button)findViewById(R.id.buttonLoc);
        Submit=(Button)findViewById(R.id.submit);
        final RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        final TextView txtView =(TextView)findViewById(R.id.textViewHide);
        final TextView txtRating=(TextView)findViewById(R.id.textRating);
        shareApp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String d;
                d="https://github.com/texnologiesLogismikou/HappyTraveller";
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareSub = "THIS APP ROCKS :";//details
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                String rate;

                myIntent.putExtra(Intent.EXTRA_TEXT,"Love this app...I rate it for "+ratingBar.getRating()+"  Stars....please download this project" +d);
                startActivity(Intent.createChooser(myIntent, "Share using"));
                init();
            }


        });//end share app click listener
        shareLoc.setOnClickListener(new View.OnClickListener()
        {
                private LatLng latLng;
                @Override
                public void onClick(View v){
                latLng= HomeActivity.getUsersLocation2();
                public void onClick(View v) {
                homeActivity=new HomeActivity();
                latLng= homeActivity.getUsersLocation();
                double longitude=latLng.longitude;
                double latitude=latLng.latitude;
                String s;
                s="LONGITUDE"+Double.toString(longitude)+"LATITUDE:"+Double.toString(latitude);

                Intent myIntent2=new Intent(Intent.ACTION_SEND);
                myIntent2.setType("text/plain");
                String shareBody="Location";//tittle


                String shareSub="My current coordinates are :"+s;

                myIntent2.putExtra(Intent.EXTRA_SUBJECT,shareBody );
                myIntent2.putExtra(Intent.EXTRA_TEXT, shareSub);


                startActivity(Intent.createChooser(myIntent2, "Share using"));
                init2();


            }
        });//end button location click listener
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRating.setText("I gave a rating "+ratingBar.getRating());

            }
        });




    }


    public void init() {
        intent = getIntent();
    }
    public void init2() {
        intent2 = getIntent();
    }


}
