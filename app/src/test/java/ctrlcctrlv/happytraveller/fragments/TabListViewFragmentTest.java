package ctrlcctrlv.happytraveller.fragments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TabListViewFragmentTest
{
    /*
 * still incomplete if fragment.view != null then gg else still incomplete
 * */

    private TabListViewFragment fragment;

    @Before
    public void setUp() throws Exception
    {
        fragment=mock(TabListViewFragment.class);
    }

    @After
    public void tearDown() throws Exception
    {
        fragment=null;
    }


    @Test
    public void viewShouldNotBeNull()
    {
        assertNotNull("Fragments view is null",fragment.getView());
    }
    @Test
    public void testInitSucceed()
    {
        fragment.init();
        assertNotNull("init() failed",fragment.listView);
    }

    @Test
    public void placeReceivedStatusFinished()
    {
        when(fragment.placesReceived()).thenReturn(true);
        assertTrue("Not finished yet",fragment.placesReceived());
    }
    @Test
    public void placeReceivedStatusUnFinished()
    {
        when(fragment.placesReceived()).thenReturn(false);
        assertTrue("finished",fragment.placesReceived());
    }
}