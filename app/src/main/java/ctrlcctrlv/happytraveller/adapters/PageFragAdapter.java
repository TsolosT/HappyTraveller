package ctrlcctrlv.happytraveller.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ctrlcctrlv.happytraveller.fragments.TabListViewFragment;
import ctrlcctrlv.happytraveller.fragments.TabMapFragment;
/**
*A class that is used as adapter among fragments and tab.
*This class is used define the adapter that will properly determine
* how many pages exist and which fragment to display
* for each page of the adapter by creating a FragmentPagerAdapter.
 * Also extend .
*
*
* @see FragmentPagerAdapter
*
* @since 5 Nov 2018
*/
public class PageFragAdapter extends FragmentPagerAdapter
{
    private  int numberOfTabs;
    /**
    *The constructor that initialize the numberOfTabs.
    *
    *@param fm FragmentManager object
    *@param numOfTabs An integer that has the number of tabs.
    *
    *@see FragmentManager
    */
    public PageFragAdapter(FragmentManager fm,int numOfTabs)
     {
        super(fm);
        this.numberOfTabs=numOfTabs;
     }
    /**
    *Returns the fragment to display for that page
     *@param position
    */
    @Override
    public Fragment getItem(int position)
     {

        switch (position)
         {
             case 0:
                 TabMapFragment tab1=new TabMapFragment();
                 return tab1;
             case 1:
                 TabListViewFragment tab2=new TabListViewFragment();
                 return tab2;
              default:
                  return null;
         }
     }


     /*
     *return the total number of pages
     */
    @Override
    public int getCount()
     {
        return numberOfTabs;
     }
}
