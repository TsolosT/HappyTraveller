package ctrlcctrlv.happytraveller.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.adapters.PageFragAdapter;
import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.alterDialogs.MainActivityAlterDialog;
import ctrlcctrlv.happytraveller.checkIfIsValid.CheckIfNumberIsValidForTimePurpose;
import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.suggestionsToUser.SuggestSightsToVisit;

/**
 *<h2>This class,it's used to display the map and the list UI.</h2>
 *<p>The main activity that display the map and the list view UI,
 * and triggers the most functions.Also extends AppCompatActivity.</p>
 *
 *
 *
 *@see AppCompatActivity
 *@see TabLayout
 *@see Intent
 *@see DrawerLayout
 *@see ActionBarDrawerToggle
 *@see NavigationView
 *
 *@since 5 Nov 2018
 */
public class MainActivity extends AppCompatActivity
{
    //declare variables
    private Intent intent;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageFragAdapter adapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private static String checkedTransportItem;
    private static String checkedSightsItem;
    private static NavigationView navView;
    private static ArrayList<PlaceData> placeData=null;//for onclickinfos
    protected TabListViewFragment tabListViewFragment;
    public String email;
    public String password;
    private LogInActivity logInActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        tabListViewFragment=new TabListViewFragment();
      viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
    * <h2>Initialize the variables that need to be used.</h2>
    * <p>A public void method that initialize all the components that need to be used in this class.</p>
    */
    public void init()
    {
        intent=getIntent();
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        navView = (NavigationView) findViewById(R.id.navView);
        adapter=new PageFragAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        mToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openMenu,R.string.closeMenu);
        checkedTransportItem="onFoot";
        logInActivity = new LogInActivity();
        email="";
        password="";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //if ham icon clicked will display menu side bar
        if(mToggle.onOptionsItemSelected(item))
        {
            renewTxtViewLocation();
            setUserName();
            return true;
        }
         return  super.onOptionsItemSelected(item);
    }

    public void setUserName(){
         TextView userView=(TextView) findViewById(R.id.userTextView);

        email= logInActivity.getUserEmail();
        password=logInActivity.getUserPassword();
        if (email==null)
        {
           //do nothing
        }
        else
        {
           userView.setText(email);
          setVisible();
        }
    }

    /**
     *<h2>A method that when it triggers display the weather page.</h2>
     *<p></p>A public method  it navigates to the weather page.</p>
     *
     * @param item  MenuItem object
     * @throws Exception If something went wrong it printstack of the error and display a toast to the user.
     * @see MenuItem
     * @see Toast
     */
    public void displayWeatherPage(MenuItem item)
    {
        Intent intentWeather=new Intent(this,WeatherActivity.class);
        try
        {
            startActivity(intentWeather);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *<h2>A method that when it triggers display the share page.</h2>
     *<p>A public method  it navigates to the share page.</p>
     *
     *
     * @param item  MenuItem object
     * @throws Exception If something went wrong it printstack of the error and display a toast to the user.
     * @see MenuItem
     * @see Toast
     */
    public void displaySharePage(MenuItem item)
    {
        Intent intentShare=new Intent(this,ShareActivity.class);
        try
        {
            startActivity(intentShare);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When user place his available time and press the icon :
     * <ul>
     *     <li>First is running an algorithm to check if the number is valid and if it isn`t user gets a error message</li>
     *     <li>And if the number is valid it is sending user`s free time to SuggestSightsToVisit class in order to display the sights</li>
     * </ul>
     *
     * @see SuggestSightsToVisit
     * @see CheckIfNumberIsValidForTimePurpose
     * @param view
     */
    public void suggestPathButton(View view)
    {
        TextView textSearch = (TextView) findViewById(R.id.editText);

        CheckIfNumberIsValidForTimePurpose checkIfNumberIsValidForTimePurpose = new CheckIfNumberIsValidForTimePurpose();

        int usersFreeTime = checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs(textSearch.getText().toString());


        if ( usersFreeTime == 0)
        {
            Toast.makeText(getApplicationContext(), "invalid number...",Toast.LENGTH_SHORT).show();
        }
        else {
            SuggestSightsToVisit suggestSightsToVisit = new SuggestSightsToVisit();

            MainActivityAlterDialog mainActivityAlterDialog = new MainActivityAlterDialog();

            mainActivityAlterDialog.setSuggestedSights(suggestSightsToVisit.suggestRouteBasedOn(usersFreeTime));
            mainActivityAlterDialog.showSuggestedSights(this);
        }

        textSearch.setText(null);
    }

    public void  onClickInfos(View view)
    {
        //fetch placeDataArraylist
        placeData=tabListViewFragment.getPlaceData();
        //k= id button
        int k=view.getId();
        //get the correct search tittle
        String searchtittle=placeData.get(k).getName();
        //intent for DetailsActivity display
        Intent intentInfos=new Intent(this,DetailsActivity.class);
        intentInfos.putExtra("searchtittle",searchtittle);

        try
        {
            startActivity(intentInfos);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Opps you can't navigate this page now...",Toast.LENGTH_SHORT).show();
        }


    }

    /**
    *<h2>Change checkbox on transport menu items.</h2>
    * <p>A public void method that  checks the trigger transport object if is checked
    * and uncheck all other and the opposite.</p>
    * @MenuItem item
    * @see MenuItem
     */
    public void changeTransportCheckValue(MenuItem item)
    {   NavigationView navView=(NavigationView)findViewById(R.id.navView);
        MenuItem   transport=navView.getMenu().getItem(3);
        MenuItem onFoot=transport.getSubMenu().getItem(0);
        MenuItem car=transport.getSubMenu().getItem(1);

        if(item.getItemId()==onFoot.getItemId())
        {
            onFoot.setChecked(true);
            onFoot.setIcon(R.drawable.ic_check_box_black_24dp);
            car.setChecked(false);
            car.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
            checkedTransportItem="onFoot";
        }
        else if(item.getItemId()==car.getItemId())
        {

            car.setChecked(true);
            car.setIcon(R.drawable.ic_check_box_black_24dp);
            onFoot.setChecked(false);
            onFoot.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
            checkedTransportItem="car";
        }

    }

    /**
    *<h2>A method that return the value name of the checked transport item.</h2>
    *<p>A public String method that retrieves a string  'walking' is transport is selected onFoot and 'driving' if is selected car.</p>
    *
    */
    public String getCheckedTransportItem()
    {
        String returnValue = null;
        switch (checkedTransportItem)
        {
            case "onFoot":
                returnValue = "walking";
                break;
            case "car":
                returnValue = "driving";
                break;
        }
        return returnValue;
    }

    /**
    *<h2>Change status of sight checkbox and display pins on map with sights.</h2>
    * <p>A public void method  that change the status of the menu item to
    * displayed pins on map or not,
    * when it pressed.</p>
    * @param item MenuItem
    * @see MenuItem
    */
    public void changeSightPinStatus(MenuItem item)
    {   //get menu
        NavigationView navView=(NavigationView)findViewById(R.id.navView);
        // menu item sights
        MenuItem   sight=navView.getMenu().getItem(2);
        //get checkbox sights
        MenuItem   sightPins=sight.getSubMenu().getItem(0);

        if(sightPins.isChecked())
        {
            //clear pins
            sightPins.setChecked(false);
            sightPins.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
            checkedSightsItem="true";
            TabMapFragment.mMap.clear();
        }
        else if(!sightPins.isChecked())
        {
            //set pins on map

            sightPins.setChecked(true);
            sightPins.setIcon(R.drawable.ic_check_box_black_24dp);

            TabMapFragment.showSightsWithPins();
            checkedSightsItem="false";
        }

    }

    /**
    *<h2>A method that return is the status pin item.</h2>
    *<p>It check the status of the pin menu item and return
    *if it is checked or not.</p>
     */
    public String getChangedPinStatus()
    {
        String status=null;
        switch (checkedSightsItem)
        {
            case "true":
                status="true";
                break;
            case "false":
                status="false";
                break;
        }
                return status;
    }
    public static void refreshSightButton(String result)
    {
        if (result == "false")
        {
            MenuItem sight = navView.getMenu().getItem(2);
            MenuItem sightPins = sight.getSubMenu().getItem(0);
            sightPins.setChecked(false);
            sightPins.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }
    /**
    *<h2>A get method that return the checkedSightsItem variable.</h2>
    * @return checkedSightsItem
    */
    public static String getCheckedSightsItem()
    {
        return checkedSightsItem;
    }
    /**
    *<h2>A method that checks if the variable location formated as string is ready and display it on menu bar.</h2>
    *<p>A public void method that check if the variable cityCountry is ready and display it otherwise
    * is display a proper message.</p>
    *
    */
    public void renewTxtViewLocation()
    {
        TabListViewFragment tabListViewFragment = new TabListViewFragment();
        //get nav view then  header and then  textview
        NavigationView navView=(NavigationView)findViewById(R.id.navView);
        View header= navView.getHeaderView(0);
        TextView txtViewLocation=(TextView)header.findViewById(R.id.locationTextView);
        //get place data array

        ArrayList<PlaceData> places=tabListViewFragment.getPlaceData();

        String location;
        if(TabListViewFragment.placesReceived())
        {
            //get location from  array
           location=places.get(0).getCityCountry();
        }
        else
        {
            location="currently not available";
        }
        //display location
        txtViewLocation.setText(location);
    }
    //wraio onoma
    public void setVisible()
    {
        //get nav view then  header and then  textview
        NavigationView navView=(NavigationView)findViewById(R.id.navView);
        View header= navView.getHeaderView(0);
        Button btnLogOut=(Button)header.findViewById(R.id.btnLogOut);
        btnLogOut.setVisibility(View.VISIBLE);
    }

    public void onClickLogOut(View view)
    {
        TextView userView=(TextView) findViewById(R.id.userTextView);
        NavigationView navView=(NavigationView)findViewById(R.id.navView);
        View header= navView.getHeaderView(0);
        Button btnLogOut=(Button)header.findViewById(R.id.btnLogOut);
        if(btnLogOut.isPressed())
        {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "You have successfully signed out ",Toast.LENGTH_SHORT).show();
            btnLogOut.setVisibility(View.GONE);
            userView.setText(" ");
            finish();
            startActivity(new Intent(this,HomeActivity.class));

        }
    }
}

