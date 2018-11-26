package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ctrlcctrlv.happytraveller.R;

public class ShareActivity  extends AppCompatActivity
{

    //declare variables
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        init();
    }

    public void init()
    {
        intent=getIntent();

    }


}
