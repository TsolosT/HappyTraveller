package ctrlcctrlv.happytraveller.activities;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {

    @Rule
    public ActivityTestRule<DetailsActivity> rule =
            new ActivityTestRule(DetailsActivity.class, true, false);
    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant("android.permission.INTERNET");

    @Test
    public void someTest2() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, DetailsActivity.class);
        intent.putExtra("Test123", "Fullvalue");

        rule.launchActivity(intent);
    }
    @Test
    public void customizeIntent2(){
        // note instead of null, an intent object can be passed
        rule.launchActivity(null);
    }
    @Test
    public void testChangeIntent2(){
        Intent intent=new Intent(rule.getActivity(),DetailsActivity.class);
        rule.launchActivity(intent);
    }
}
