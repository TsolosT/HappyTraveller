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

/**
 *<h2>This class,it's used for user log in </h2>
 *<p>This is the activity that control the log in task,also it's used to
 * display the activity_log_in.xml . Also extends AppCompatActivity.</p>
 *
 *
 *
 *@see AppCompatActivity
 *@see Intent
 */
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
    public static String userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();

    }

    /**
     * <h2>Initialize the variables that need to be used.</h2>
     * <p>A public void method that initialize all the components that need to be used in this class.</p>
     *
     * @see FirebaseAuth firebaseAuth
     * @see ProgressDialog
     */
    public void init()
    {
        intent=getIntent();
        btnLogIn = (Button) findViewById(R.id.btn_logIn);
        emailLogIn = (EditText) findViewById(R.id.emailTextInput);
        passwordLogIn = (EditText) findViewById(R.id.passwordLogIn);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

    }

    /**
     *<h2>A method that when it triggers display the Log in page.</h2>
     *<p>A public method  it navigates to the Log in  page.</p>
     *
     *@throws Exception If something went wrong it printstack of the error and display a toast to the user.
     * @see Toast
     */
    public void displaySignUp(View v)
    {
        Intent intent=new Intent(this,SignUpActivity.class);
        try
        {
            startActivity(intent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * <h2>A method that is used for the user LogIn <h2/>
     * <p>This methods get user email and user password and checks at the base if the user is registered
     * If user is successfully logged in it shows a message "You signed in successfully" , if not it shows a proper message<p/>
     *
     * @see TextUtils
     * @see Toast
     * @see
     */
    private void userLogIn() {
          final  String email = emailLogIn.getText().toString().trim();
    final String password = passwordLogIn.getText().toString().trim();
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

    // function for sign in by email and password
    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            progressDialog.dismiss();
            if(task.isSuccessful())
            {
                finish();
                userEmail=email;
                userPassword=password;
                Toast.makeText(LogInActivity.this,"You signed in successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(LogInActivity.this,"Could not sign in, please try again later ", Toast.LENGTH_SHORT).show();
            }

        }


    });
}

    /**
     *<h2>This method is used to get the user password.</h2>
     *
     *
     * @return userPassword
     */
public static String getUserPassword()
{
    return userPassword;
}

    /**
     *<h2>This method is used to get the user email.</h2>
     *
     *
     * @return userUserEmail
     *
     */
public static String getUserEmail()
{

        return userEmail;
}
    /**
     *<h2>A method used for user LogIn .</h2>
     *<p>If the button LogIn is pressed user completes the LogIn</p>
     *
     * @see View
     */
 public void onClickLogIn(View view)
 {
     if (view==btnLogIn)
     {
         userLogIn();
     }
 }

}
