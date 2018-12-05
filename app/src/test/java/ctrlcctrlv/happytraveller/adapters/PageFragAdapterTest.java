package ctrlcctrlv.happytraveller.adapters;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ctrlcctrlv.happytraveller.fragments.TabMapFragment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PageFragAdapterTest {

    private PageFragAdapter adapter;
    private  TabMapFragment tab1;
    private  TabMapFragment tab2;

    @Before
    public void setUp() throws Exception {
        tab1=mock(TabMapFragment.class);
        tab2=mock(TabMapFragment.class);
       adapter=mock(PageFragAdapter.class);
        when(adapter.getCount()).thenReturn(2);
        when(adapter.getItem(0)).thenReturn(tab1);
        when(adapter.getItem(1)).thenReturn(tab2);
    }

    @Test
    public void fragAdapterGetItemMapFragTestCorrect()
    {
        assertEquals(tab1,adapter.getItem(0));
    }

    @Test
    public void fragAdapterGetItemListFragTestCorrect()
    {
        assertEquals(tab2,adapter.getItem(1));
    }
    @Test
    public void fragAdapterGetCountTestCorrect()
    {
        assertEquals(2,adapter.getCount());
    }
}