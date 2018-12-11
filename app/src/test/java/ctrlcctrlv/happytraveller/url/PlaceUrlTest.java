package ctrlcctrlv.happytraveller.url;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceUrlTest {

    PlaceUrl urlTest;
    PlaceUrl urlTestAll;

    @Before
    public void setUp(){
        urlTest=new PlaceUrl();
        urlTest.setLatLng("41.0943488,23.5544576");
        urlTest.setPlaceType("museum");
    }

    @Before
    public void newSetUp()
    {
        urlTestAll=new PlaceUrl();
        urlTestAll.setLatLng("41.082100,23.549980");
    }
    @Test
    public void getUrlWithThousandRadiusCorrect() {

        String urlExpected="https://maps.googleapis.com/maps/api/place/search/json?location=41.0943488,23.5544576&type=museum&radius=10000&sensor=true&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        assertEquals(urlExpected,urlTestAll.getUrl());
    }
    @Test
    public void getUrlWithThousandRadiusWrong() {

        String urlExpected="https://maps.googleapis.com/maps/api/place/search/json?location=41.0943488,23.5544576&type=museum&radius=10000&sensor=true&key=AIzaSyDI0zKd22JBJEGco2k9Thg2CZWLLsWfq7k";
        assertNotEquals(urlExpected,urlTestAll.getUrl());
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
    public void getPlaceTypeMuseumCorrect() {
        String expected="museum";
        assertEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void getPlaceTypeMuseumWrong() {
        String expected="museum";
        urlTest.setPlaceType("park");
        assertNotEquals(expected,urlTestAll.getPlaceType());
    }


    @Test
    public void getPlaceTypeParkCorrect() {
        String expected="park";
        assertEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void getPlaceTypeParkWrong() {
        String expected="park";
        urlTestAll.setPlaceType("museum");
        assertNotEquals(expected,urlTestAll.getPlaceType());
    }

    @Test
    public void getPlaceTypeChurchCorrect() {
        String expected="church";
        assertEquals(expected,urlTest.getPlaceType());
    }
    @Test
    public void getPlaceTypeChurchWrong() {
        String expected="church";
        urlTestAll.setPlaceType("park");
        assertNotEquals(expected,urlTestAll.getPlaceType());
    }


    @Test
    public void setPlaceTypeMuseumCorrect() {
        String expected="museum";
        assertEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void setPlaceTypeMuseumWrong() {
        String expected="museum";
        assertNotEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void setPlaceTypeParkCorrect() {
        String expected="park";
        assertEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void setPlaceTypeParkWrong() {
        String expected="park";
        assertNotEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void setPlaceTypeChurchCorrect() {
        String expected="church";
        assertEquals(expected,urlTestAll.getPlaceType());
    }
    @Test
    public void setPlaceTypeChurchWrong() {
        String expected="church";
        assertNotEquals(expected,urlTestAll.getPlaceType());
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

    @Test
    public void getMyLatLngCorrect() {
        String expected="41.082100,23.549980";
        assertEquals(expected,urlTestAll.getLatLng());
    }
    @Test
    public void getMyLatLngWrong() {
        String expected="41.082100,23.549980";
        urlTestAll.setLatLng(null);
        assertNotEquals(expected,urlTestAll.getLatLng());
    }

    @Test
    public void setMyLatLngCorrect() {
        urlTestAll.setLatLng("25.24323,51.234423");
        assertEquals("25.22323,51.232323",urlTestAll.getLatLng());
    }
    @Test
    public void setMyLatLngWrong() {
        urlTestAll.setLatLng("25.21123,51.232323");
        assertNotEquals("25.22323,51.232323",urlTestAll.getLatLng());
    }
    @After
    public void clearObject()
    {
        urlTest=null;
    }
    @After
    public void clearObjectAll (){urlTestAll=null;}
}