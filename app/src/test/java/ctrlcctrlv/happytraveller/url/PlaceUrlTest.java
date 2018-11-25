package ctrlcctrlv.happytraveller.url;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceUrlTest {

    PlaceUrl urlTest;

    @Before
    public void setUp(){
        urlTest=new PlaceUrl();
        urlTest.setLatLng("41.0943488,23.5544576");
        urlTest.setPlaceType("museum");
    }

    @Test
    public void getUrlCorrect() {

        String urlExpected="https://maps.googleapis.com/maps/api/place/search/json?location=41.0943488,23.5544576&type=museum&radius=1000&sensor=true&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        assertEquals(urlExpected,urlTest.getUrl());
    }

    @Test
    public void getUrlWrong() {

        String urlExpected="https://maps.googleapis.com/maps/api/place/search/json?location=41.0945488,23.5545576&type=museum&radius=1000&sensor=true&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        assertNotEquals(urlExpected,urlTest.getUrl());
    }

    @Test
    public void getPlaceTypeCorrect() {
        String expected="museum";
        assertEquals(expected,urlTest.getPlaceType());
    }
    @Test
    public void getPlaceTypeWrong() {
        String expected="museum";
        urlTest.setPlaceType("park");
        assertNotEquals(expected,urlTest.getPlaceType());
    }

    @Test
    public void setPlaceTypeCorrect() {
        String expected="museum";
        assertEquals(expected,urlTest.getPlaceType());
    }
    @Test
    public void setPlaceTypeWrong() {
        String expected="museum";
        urlTest.setPlaceType("park");
        assertNotEquals(expected,urlTest.getPlaceType());
    }

    @Test
    public void getLatLngCorrect() {
        String expected="41.0943488,23.5544576";
        assertEquals(expected,urlTest.getLatLng());
    }
    @Test
    public void getLatLngWrong() {
        String expected="41.0943488,23.5544576";
        urlTest.setLatLng(null);
        assertNotEquals(expected,urlTest.getLatLng());
    }

    @Test
    public void setLatLngCorrect() {
        urlTest.setLatLng("22.22323,11.232323");
        assertEquals("22.22323,11.232323",urlTest.getLatLng());
    }
    @Test
    public void setLatLngWrong() {
        urlTest.setLatLng("22.21123,11.232323");
        assertNotEquals("22.22323,11.232323",urlTest.getLatLng());
    }
    @After
    public void clearObject()
    {
        urlTest=null;
    }
}