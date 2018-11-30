package ctrlcctrlv.happytraveller.jsonParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.model.PlaceData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//akoma 8ema den trexei...need study more mockito & junit
public class PlaceParserTest {

    private String jsonReply;
    private ArrayList<PlaceData> mockArray;
    private  PlaceParser parser;
    private PlaceData mockPlace;

    @Before
    public void setUp()
    {
            jsonReply="{\n" +
                    "   \"html_attributions\" : [],\n" +
                    "   \"results\" : [\n" +
                    "      {\n" +
                    "         \"geometry\" : {\n" +
                    "            \"location\" : {\n" +
                    "               \"lat\" : 41.0909417,\n" +
                    "               \"lng\" : 23.549367\n" +
                    "            },\n" +
                    "            \"viewport\" : {\n" +
                    "               \"northeast\" : {\n" +
                    "                  \"lat\" : 41.09217073029149,\n" +
                    "                  \"lng\" : 23.5509312802915\n" +
                    "               },\n" +
                    "               \"southwest\" : {\n" +
                    "                  \"lat\" : 41.08947276970849,\n" +
                    "                  \"lng\" : 23.54823331970849\n" +
                    "               }\n" +
                    "            }\n" +
                    "         },\n" +
                    "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/museum-71.png\",\n" +
                    "         \"id\" : \"bc6ea3cd688cfa05ac606c4a5c9fedf121bfc725\",\n" +
                    "         \"name\" : \"Αρχαιολογικό Μουσείο Μπεζεστένι\",\n" +
                    "         \"opening_hours\" : {\n" +
                    "            \"open_now\" : false\n" +
                    "         },\n" +
                    "         \"photos\" : [\n" +
                    "            {\n" +
                    "               \"height\" : 4140,\n" +
                    "               \"html_attributions\" : [\n" +
                    "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/100595518368873677708/photos\\\"\\u003eAlexandra Andreou\\u003c/a\\u003e\"\n" +
                    "               ],\n" +
                    "               \"photo_reference\" : \"CmRaAAAATdvKwhQcPJg5q4r0gmkbai0Piych1w35_26JfJlvMPNcMH2_hkO_G0Xq8J_-giAB1Agt754Oz0QppCcy9Iae8ckDofqj96CoObWRldelMsMvjMfk1pDtku_mvZdVLScGEhCLyv-kgdySddrIbasCeLIrGhTUtrUacsyLqsrR72FmgUulspZHXQ\",\n" +
                    "               \"width\" : 5520\n" +
                    "            }\n" +
                    "         ],]}";

           mockArray=mock(ArrayList.class);
           mockPlace=mock(PlaceData.class);
           when(mockArray.get(0)).thenReturn(mockPlace);
           when(mockArray.get(0).getName()).thenReturn("Αρχαιολογικό Μουσείο Μπεζεστένι");
           when(mockArray.get(0).getAddress()).thenReturn("Λεωφόρος Μεραρχίας 858, Σέρρες");
            parser=new PlaceParser();
    }
    @Test
    public void doPlaceParse()
    {
        ArrayList<PlaceData> testPlaceDataArray=new ArrayList<>();
        testPlaceDataArray= parser.parseGoogleParse(jsonReply.trim());
        assertEquals(mockArray.get(0).getName(),testPlaceDataArray.get(0).getName());

    }

    @After
    public void tearDown()
    {
        jsonReply=null;
       mockArray=null;
    }
}