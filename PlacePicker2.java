package com.example.user.placepickertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlacePicker2 extends AppCompatActivity
{
    //return value of place picker
    int PLACE_PICKER_REQUEST = 1;
    TextView tvPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);
        tvPlace = findViewById(R.id.tvPlace);
    }



    //function that calls PlacePicker and new Activity (place Picker opens)
    public void callPlacePicker(View view)
    {
        //Auto ginetai giati exw PlacePicker activity kai placepicker api :P
        com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder intentBuilder = new com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder();
        try
        {
            startActivityForResult(intentBuilder.build(PlacePicker2.this),PLACE_PICKER_REQUEST);
        }
        catch (GooglePlayServicesNotAvailableException e)
        {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == PLACE_PICKER_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                Place place = com.google.android.gms.location.places.ui.PlacePicker.getPlace(PlacePicker2.this,data);
                // just sets into textView the selected place's exact address.
                tvPlace.setText(place.getAddress());
            }
        }
    }
}
