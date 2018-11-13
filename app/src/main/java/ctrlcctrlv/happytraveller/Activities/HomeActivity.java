package ctrlcctrlv.happytraveller.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import ctrlcctrlv.happytraveller.R;
/*
* This is the 'main' class,it's used to
* launch the app. and display the activity_home.xml
*
* */
public class HomeActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }



    public void displayMainPage(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
