package ctrlcctrlv.happytraveller.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
//todo add more tests
public class PlaceDataTest {

    PlaceData placeDataObject;
    ArrayList<PlaceData> placeDataArray;

    @Before
    public void setUp() throws Exception {
       placeDataArray=new ArrayList<>();
       placeDataObject=new PlaceData();
    }

    @After
    public void tearDown() throws Exception {
        placeDataObject=null;
        placeDataArray.clear();
    }

    @Test
    public void getNameCorrect() {
        placeDataObject.setName("Foo Museum");
        String expected="Foo Museum";
        assertEquals(expected,placeDataObject.getName());
    }

    @Test
    public void getAddressCorrect() {
        placeDataObject.setAddress("FooHomees 12");
        String expected="FooHomees 12";
        assertEquals(expected,placeDataObject.getAddress());
    }

    @Test
    public void getNameWrong() {
        placeDataObject.setName("");
        String expected="Foo Museum";
        assertNotEquals(expected,placeDataObject.getName());
    }

    @Test
    public void getAddressWrong() {
        placeDataObject.setAddress("FooHomees 12");
        String expected="";
        assertNotEquals(expected,placeDataObject.getAddress());
    }

    @Test
    public void addDataToArrayCorrect(){
        placeDataArray.add(placeDataObject);
        placeDataArray.add(new PlaceData("gg","gg foo"));
        assertEquals(2,placeDataArray.size());

    }

    @Test
    public void addDataToArrayWrong(){
        placeDataArray.add(placeDataObject);
        assertNotEquals(2,placeDataArray.size());
    }

    @Test
    public void getIdCorrect()
    {
        placeDataObject.setId(5);
        int expected=5;
        assertEquals(expected,placeDataObject.getId());

    }

    @Test
    public void getIdWrong()
    {
        placeDataObject.setId(10);
        int expected=2;
        assertNotEquals(expected,placeDataObject.getId());
    }

    @Test
    public void getCityCountryCorrect()
    {
       placeDataObject.setCityCountry("serres");
       String expected="serres";
       assertEquals(expected,placeDataObject.getCityCountry());
    }

    @Test
    public void getCityCountryWrong()
    {
        placeDataObject.setCityCountry("serres");
        String expected="";
        assertNotEquals(expected,placeDataObject.getCityCountry());
    }

}