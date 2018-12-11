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
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import ctrlcctrlv.happytraveller.adapters.PageFragAdapter;
import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.checkIfIsValid.CheckIfNumberIsValidForTimePurpose;
import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
import ctrlcctrlv.happytraveller.model.PlaceData;
import ctrlcctrlv.happytraveller.suggestionsToUser.SuggestSightsToVisit;


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

    //Initialize the variables that need to be used
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //if ham icon clicked will display menu side bar
        if(mToggle.onOptionsItemSelected(item))
        {
            renewTxtViewLocation();
            return true;
        }
         return  super.onOptionsItemSelected(item);
    }

    //A method that triggered when weather(menuItem) is clicked and intent weather activity
    public void displayWeatherPage(MenuItem item)
    {
        Intent intentWeather=new Intent(this,WeatherActivity.class);
        startActivity(intentWeather);
    }
    public void displaySharePage(MenuItem item)
    {
        Intent intentShare=new Intent(this,ShareActivity.class);
        startActivity(intentShare);
    }


    public void suggestPathButton(View view)
    {
        TextView textSearch = (TextView) findViewById(R.id.editText);

        CheckIfNumberIsValidForTimePurpose checkIfNumberIsValidForTimePurpose = new CheckIfNumberIsValidForTimePurpose();

        int usersFreeTime = checkIfNumberIsValidForTimePurpose.theNumberUserGaveIs(textSearch.getText().toString());


        if ( usersFreeTime == 0)
        {
            Toast.makeText(getApplicationContext(), "invalid number...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            SuggestSightsToVisit suggestSightsToVisit = new SuggestSightsToVisit();

            suggestSightsToVisit.suggestRouteBasedOn(usersFreeTime);
        }


        textSearch.setText(null);

    }

    public void  onClickInfos(View view)
    {
       //get id button
      //  System.out.println(view.getId());
        //fetch placeDataArraylist
        placeData=tabListViewFragment.getPlaceData();
        //k= id button
        int k=view.getId();
       // System.out.println(k);
        //get the correct search tittle
        String searchtittle=placeData.get(k).getName();
       // System.out.println(searchtittle);
        //intent for DetailsActivity display
        Intent intentInfos=new Intent(this,DetailsActivity.class);
        intentInfos.putExtra("searchtittle",searchtittle);
        startActivity(intentInfos);





        // na anoigei to activity kai na pernaw ton titlo apo to antikeimeno kai ayto tha to pairnei to activity tha to bazei sto url
//
//

    }

    //Change checkbox on transport menu items
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

    //Retrieves a string  'on foot' is transport is selected onFoot and 'car' if is selected car
    public String getCheckedTransportItem(){
        return checkedTransportItem;
    }


    //Change status of sight checkbox and display pins on map with sights
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

    public static String getCheckedSightsItem()
    {
        return checkedSightsItem;
    }

    //set and  reset  nav_header txtView location with users current location
    public void renewTxtViewLocation()
    {    //get nav view then  header and then  textview
        NavigationView navView=(NavigationView)findViewById(R.id.navView);
        View header= navView.getHeaderView(0);
        TextView txtViewLocation=(TextView)header.findViewById(R.id.locationTextView);
        //get place data array
        ArrayList<PlaceData> places=TabListViewFragment.getPlaceData();
        //get location from  array
        String location=places.get(0).getCityCountry();
        //display location
        txtViewLocation.setText(location);
    }
}

