package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    public void displayLogInPage(View v){
        Intent intent=new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
    public void displaySignUpPage(View v){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    public void displayInfoPage(View v){
        // TODO: 13/11/2018 otan ginei to info page
        //Intent intent=new Intent(this,InfoActivity.class);
        //startActivity(intent);
    }
}
