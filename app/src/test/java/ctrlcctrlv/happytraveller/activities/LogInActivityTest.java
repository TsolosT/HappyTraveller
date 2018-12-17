package ctrlcctrlv.happytraveller.activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LogInActivityTest
{
private LogInActivity logInActivity;

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

//    @Test
//    public void getUserEmail()
//    {
//        String userEmail = logInActivity.getUserEmail();
//        assertEquals("Correct",getUserEmail());
//    }

}