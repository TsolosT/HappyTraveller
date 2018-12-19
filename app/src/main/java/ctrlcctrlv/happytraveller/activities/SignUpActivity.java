package ctrlcctrlv.happytraveller.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    private  EditText name ;
    private EditText passwordSignUp;
    private EditText emailSignUp;
    private Button btnSignUp ;
    private FirebaseAuth firebaseAuth;


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
        firebaseAuth= FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.nameInput);
        emailSignUp = (EditText) findViewById(R.id.emailInput);
        passwordSignUp = (EditText) findViewById(R.id.passwordLogIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

    }

    public void displayLogInPage(View v){
        Intent intent=new Intent(this,LogInActivity.class);
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

    private void registerUser()
    {
        String email = emailSignUp.getText().toString().trim();
        String password = passwordSignUp.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            //stopping the function executing further
            return;
        }
        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stopping the function executing further
            return;
        }

        if(TextUtils.getTrimmedLength(password)<6)
        {
            //if password less than 6 numbers
            Toast.makeText(this,"Please enter password with more than 6 numbers", Toast.LENGTH_SHORT).show();
            //stopping the function executing further
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    //user is successfully registered and logged in

                    Toast.makeText(SignUpActivity.this,"Registered successfully", Toast.LENGTH_SHORT).show();
                }
                else{

                    // could not register
                    Toast.makeText(SignUpActivity.this,"Could not register , please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickSignUp(View view)
    {
        if (view==btnSignUp)
        {
            registerUser();
        }
    }
}
