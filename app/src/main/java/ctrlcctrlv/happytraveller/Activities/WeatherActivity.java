package ctrlcctrlv.happytraveller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ctrlcctrlv.happytraveller.R;

public class WeatherActivity extends AppCompatActivity
{

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }


    public void init(){
        intent=getIntent();
    }



}
