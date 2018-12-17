package ctrlcctrlv.happytraveller.activities;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
public class ShareActivityTest {

    // third parameter is set to false which means the activity is not started automatically
    @Rule
    public androidx.test.rule.ActivityTestRule<ShareActivity> rule =
            new androidx.test.rule.ActivityTestRule(ShareActivity.class, true, false);
    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.INTERNET",
                    "android.permission.ACCESS_COARSE_LOCATION");

    @Test
    public void someTest() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, ShareActivity.class);
        intent.putExtra("Name", "Value");

        rule.launchActivity(intent);
    }
    @Test
    public void customizeIntent(){
        // note instead of null, an intent object can be passed
        rule.launchActivity(null);
    }

    @Test
    public void testChangeIntent(){
        Intent intent=new Intent(rule.getActivity(),ShareActivity.class);
        rule.launchActivity(intent);
    }



}
