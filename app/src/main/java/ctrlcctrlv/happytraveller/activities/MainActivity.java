package ctrlcctrlv.happytraveller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import ctrlcctrlv.happytraveller.adapters.PageFragAdapter;
import ctrlcctrlv.happytraveller.R;

// TODO: 4/11/2018 comments & test 
public class MainActivity extends AppCompatActivity
{
    //declare variables
    private Intent intent;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageFragAdapter adapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)//if ham icon clicked will display menu side bar
    {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
         return  super.onOptionsItemSelected(item);
    }

    //A method that trigged when weather(menuItem) is clicked
    public void displayWeatherPage(MenuItem item)
    {
        Intent intentWeather=new Intent(this,WeatherActivity.class);
        startActivity(intentWeather);
    }

}

