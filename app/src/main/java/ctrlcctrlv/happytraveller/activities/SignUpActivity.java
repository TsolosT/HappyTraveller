package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ctrlcctrlv.happytraveller.R;


/*
 * This is the activity that control the sign up task,also it's used to
 * display the activity_sign_up.xml
 *
 * */
public class SignUpActivity extends AppCompatActivity
{
    //declare variables
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    public void init()
    {
        intent=getIntent();

    }


    public void displayLogInPage(View v){
        Intent intent=new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
}
