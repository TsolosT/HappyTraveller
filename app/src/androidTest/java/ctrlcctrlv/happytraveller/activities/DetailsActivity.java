package ctrlcctrlv.happytraveller.activities;


import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import java.net.URL;

import androidx.test.espresso.web.model.Atom;
import androidx.test.espresso.web.sugar.Web;
import androidx.test.espresso.web.webdriver.DriverAtoms;
import androidx.test.espresso.web.webdriver.Locator;

import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.CoreMatchers.containsString;


public class DetailsActivity extends Activity {
    private static final String WEB_FORM_URL = "Macchiato";
    private static final String KEY_URL_TO_LOAD = "Doppio";
    @Rule
    public ActivityTestRule<DetailsActivity> mActivityRule =
            new ActivityTestRule<DetailsActivity>(
                    DetailsActivity.class,
                    false,
                    false) {
        @Override
        protected void afterActivityLaunched() {
            // Technically we do not need to do this - WebViewActivity has javascript turned on.
            // Other WebViews in your app may have javascript turned off, however since the only way
            // to automate WebViews is through javascript, it must be enabled.
            onWebView().forceJavascriptEnabled();
        }
    };
    /**
     * @return start {@link Intent} for the simple web form URL.
     */

    @Test
    public void typeTextInInput(){
        mActivityRule.launchActivity(withWebFormIntent());
        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys(KEY_URL_TO_LOAD))
                // Find the change text button.
                .withElement(findElement(Locator.ID, "changeTextBtn"))
                // Click on it.
                .perform(webClick())
                // Find the message element by ID
                .withElement(findElement(Locator.ID, "message"))
                // Verify that the text is displayed
                .check(webMatches(DriverAtoms.getText(), containsString(KEY_URL_TO_LOAD)));

    }



    private static Intent withWebFormIntent() {
        Intent DetailsFormIntent = new Intent();
        DetailsFormIntent.putExtra(DetailsActivity.KEY_URL_TO_LOAD, DetailsActivity.WEB_FORM_URL);
        return DetailsFormIntent;
    }

}
