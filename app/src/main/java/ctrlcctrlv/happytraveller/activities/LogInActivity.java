package ctrlcctrlv.happytraveller.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    private Button btnLogIn;
    private EditText passwordLogIn;
    private EditText emailLogIn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    public static String userEmail ;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();

       if (firebaseAuth.getCurrentUser() != null)
       {

       }

    }

    public void init()
    {
        intent=getIntent();
        btnLogIn = (Button) findViewById(R.id.btn_logIn);
        emailLogIn = (EditText) findViewById(R.id.emailTextInput);
        passwordLogIn = (EditText) findViewById(R.id.passwordLogIn);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

    }

    public void displaySignUp(View v)
    {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

private void userLogIn() {
      final  String email = emailLogIn.getText().toString().trim();
    String password = passwordLogIn.getText().toString().trim();
    if (TextUtils.isEmpty(email)) {
        //email is empty
        Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        //stopping the function executing further
        return;
    }
    if (TextUtils.isEmpty(password)) {
        //password is empty
        Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        //stopping the function executing further
        return;
    }

    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            progressDialog.dismiss();
            if(task.isSuccessful())
            {
                finish();
                userEmail=email;
            }

        }


    });
}


     public static String getUserEmail(){

        return userEmail;
}
 public void onClickLogIn(View view)
 {
     if (view==btnLogIn)
     {
         userLogIn();
     }
 }

}
