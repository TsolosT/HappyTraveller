package ctrlcctrlv.happytraveller.activities;

import android.support.test.runner.AndroidJUnit4;

import android.support.test.runner.AndroidJUnitRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.rule.ActivityTestRule;
import ctrlcctrlv.happytraveller.R;

@RunWith(AndroidJUnit4.class)
public class WeatherActivityUiTest {

    @Rule
    public ActivityTestRule<WeatherActivity> mWeatherActivityActivityTestRule =
            new ActivityTestRule<WeatherActivity>(WeatherActivity.class);

    @Test
    public void clickWeatherButton_opensWeatherUi() throws Exception{
        onView(withId(R.id.button)).check(matches(isClickable()));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.weatherReport))
        .check(matches(isCompletelyDisplayed()));

    }
}
