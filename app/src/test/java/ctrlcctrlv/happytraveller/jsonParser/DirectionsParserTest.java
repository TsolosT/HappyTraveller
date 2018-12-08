package ctrlcctrlv.happytraveller.jsonParser;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

// TODO: 12/7/2018 More Testing about parse function
public class DirectionsParserTest
{
    DirectionsParser directionsParser;
    LatLng latLng;

    @Before
    public void setUp()
    {
        directionsParser = new DirectionsParser();
    }

    @Test
    public void decodePolylineTestAssertEquals1()
    {
        ArrayList<LatLng> listExpected = new ArrayList<LatLng>();

        latLng = new LatLng(41.08396,23.5439);
        listExpected.add(latLng);
        latLng = new LatLng(41.0841,23.54401);
        listExpected.add(latLng);
        latLng = new LatLng(41.08443,23.54422);
        listExpected.add(latLng);
        latLng = new LatLng(41.0854,23.54491);
        listExpected.add(latLng);

        assertEquals(listExpected,directionsParser.decodePolyline("wewyFklunC[UaAi@aEiC"));
    }

    @Test
    public void decodePolylineTestRightAssertEquals2()
    {
        ArrayList<LatLng> listExpected = new ArrayList<LatLng>();

        latLng = new LatLng(41.08822,23.54255);
        listExpected.add(latLng);
        latLng = new LatLng(41.08824,23.54252);
        listExpected.add(latLng);
        latLng = new LatLng( 41.08887,23.54213);
        listExpected.add(latLng);

        assertEquals(listExpected,directionsParser.decodePolyline("k`xyF}cunCCD}BlA"));
    }

    @Test
    public void decodePolylineTestAssertNotEquals1()
    {
        ArrayList<LatLng> listExpected = new ArrayList<LatLng>();

        latLng = new LatLng(41.08822,23.54255);
        listExpected.add(latLng);
        latLng = new LatLng(41.08824,23.54252);
        listExpected.add(latLng);
        latLng = new LatLng( 41.08887,23.54213);
        listExpected.add(latLng);

        assertNotEquals(listExpected,directionsParser.decodePolyline("mdxyFiaunCw@e@"));
    }

    @Test
    public void decodePolylineTestAssertNotEquals2()
    {
        ArrayList<LatLng> listExpected = new ArrayList<LatLng>();

        latLng = new LatLng(41.0822,23.54255);
        listExpected.add(latLng);
        latLng = new LatLng(41.08824,23.5252);
        listExpected.add(latLng);
        latLng = new LatLng( 41.0888,23.54213);
        listExpected.add(latLng);

        assertNotEquals(listExpected,directionsParser.decodePolyline("k`xyF}cunCCD}BlA"));
    }
    
    
    
    
    
    
    
    
}
