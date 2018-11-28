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
import android.widget.Toast;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.acl.Group;
import java.util.ArrayList;

import ctrlcctrlv.happytraveller.adapters.PageFragAdapter;
import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
import ctrlcctrlv.happytraveller.model.PlaceData;


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
    public static ArrayList<PlaceData> dataPassedFromListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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
        adapter=new PageFragAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        mToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openMenu,R.string.closeMenu);
        checkedTransportItem="onfoot";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //if ham icon clicked will display menu side bar
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
         return  super.onOptionsItemSelected(item);
    }

    //A method that trigged when weather(menuItem) is clicked and intent weather activity
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

    //Change checkbox on transport menu items
    public void changeTransportCheckValue(MenuItem item)
    {   NavigationView navView=(NavigationView)findViewById(R.id.navView);
        MenuItem   transport=navView.getMenu().getItem(3);
        MenuItem onfoot=transport.getSubMenu().getItem(0);
        MenuItem car=transport.getSubMenu().getItem(1);

        if(item.getItemId()==onfoot.getItemId())
        {
            onfoot.setChecked(true);
            onfoot.setIcon(R.drawable.ic_check_box_black_24dp);
            car.setChecked(false);
            car.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
            checkedTransportItem="onfoot";
        }
        else if(item.getItemId()==car.getItemId())
        {

            car.setChecked(true);
            car.setIcon(R.drawable.ic_check_box_black_24dp);
            onfoot.setChecked(false);
            onfoot.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
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

        if(sightPins.isChecked()) {
            //clean pins
            sightPins.setChecked(false);
            sightPins.setIcon(R.drawable.ic_check_box_outline_blank_black_24dp);
            TabMapFragment.mMap.clear();
        }
        else if(!sightPins.isChecked())
        {
            //set pins on map

            sightPins.setChecked(true);
            sightPins.setIcon(R.drawable.ic_check_box_black_24dp);

            TabMapFragment.showSightsWithPins();

        }

    }
    public static String getCheckedSightsItem(){
        return checkedSightsItem;
    }

}

