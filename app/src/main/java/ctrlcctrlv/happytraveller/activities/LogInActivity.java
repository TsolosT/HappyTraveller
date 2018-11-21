package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ctrlcctrlv.happytraveller.R;

/*
 * This is the activity that control the log in/out task,also it's used to
 * display the activity_log_in.xml
 *
 * */
public class LogInActivity  extends AppCompatActivity
{
    //declare variables
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
    }

    public void init()
    {
        intent=getIntent();

    }

    public void displaySignUp(View v)
    {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

}
