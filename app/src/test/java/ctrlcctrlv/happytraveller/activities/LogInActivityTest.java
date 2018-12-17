package ctrlcctrlv.happytraveller.activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LogInActivityTest
{
private LogInActivity logInActivity;
private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

@Before
public void setUp() throws Exception
{
    logInActivity=mock(LogInActivity.class);
}

    @After
    public void tearDown() throws Exception
    {
        logInActivity=null;
    }
    @Test
    public void testInit()
    {
        logInActivity.init();
        assertEquals("init failed to initialize components",LogInActivity.userEmail,null);
    }
    @Test
    public void getUserEmail()
    {
        String userEmail = logInActivity.getUserEmail();
        assertEquals(userEmail,LogInActivity.getUserEmail());
    }


    @Test
    public void checkIfUserLoggedIn()
    {
      final String email="George";
     final  String password="Gpassword";

        // function for sign in by email and password
                assertNotEquals(email, LogInActivity.getUserEmail());
                assertNotEquals(email,logInActivity.getUserPassword());
    }


}
