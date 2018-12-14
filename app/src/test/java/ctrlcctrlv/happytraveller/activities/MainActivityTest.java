package ctrlcctrlv.happytraveller.activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest
{
    private MainActivity main;

    @Before
    public void setUp() throws Exception
    {
        main=mock(MainActivity.class);
    }

    @After
    public void tearDown() throws Exception
    {
        main=null;
    }


    @Test
    public void testInit()
    {
        main.init();
        assertEquals("init failed to initialize components",main.email,null);
    }
    @Test
    public void yetNotInitCheckBoxSight()
    {
        String checkedSightPins=main.getCheckedSightsItem();

        assertNull("sights pin ...failed",checkedSightPins);
    }

    @Test
    public void getCheckedTransportOnFoot()
    {
        when(main.getCheckedTransportItem()).thenReturn("onFoot");
        String checkedTransport=main.getCheckedTransportItem();
        assertThat("car...failed",checkedTransport,is("onFoot"));
    }
    @Test
    public void getUnCheckedTransportCar()
    {
        when(main.getCheckedTransportItem()).thenReturn("car");
        String checkedTransport=main.getCheckedTransportItem();
        assertThat("on foot...failed",checkedTransport,is("car"));
    }
}