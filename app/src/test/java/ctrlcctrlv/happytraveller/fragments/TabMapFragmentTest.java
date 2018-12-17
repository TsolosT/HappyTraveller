package ctrlcctrlv.happytraveller.fragments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.model.PlaceData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class TabMapFragmentTest
{
    private TabMapFragment tbFrag;
    private TabListViewFragment lvFrag;
    private ArrayList<PlaceData> mockArray;
    private PlaceData mockPlace;
    private TabMapFragment tbInstance;
    private PlaceData plData;

    @Before
    public void setUp()
    {
        tbFrag= mock(TabMapFragment.class);
        lvFrag = mock(TabListViewFragment.class);
        //dataFromListView= lvFrag.getPlaceData();
        mockArray=mock(ArrayList.class);
        mockPlace=mock(PlaceData.class);
    }


    @After
    public void tearDown()
    {
        tbFrag=null;

    }

    @Test
    public void viewShouldNotBeNull()
    {
        assertNotNull("Failed test.Fragment's view is null..",tbInstance.getView());
    }

    @Test
    public void showSightsWithPinsNotNullData()
    {
       // assertNotNull("Failed test.Data from ListView is null..",);
    }
}