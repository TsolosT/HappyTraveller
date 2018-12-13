package ctrlcctrlv.happytraveller.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckConnectionTest
{
    //variables
    private CheckConnection checkCon;
    private Context context;
    private NetworkInfo networkInfo;

    @Before
    public void setUp() throws Exception
    {
        context=mock(Context.class);
        networkInfo=mock(NetworkInfo.class);
    }

    @After
    public void tearDown() throws Exception {
        //clear variables
    }

    @Test
    public void checkSpeedConnectionTypeWifiHigh()
    {
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_WIFI);


        checkCon=new CheckConnection(context,networkInfo);

        int subtype=checkCon.checkSpeedConnection();
        int exp=-1; //-1 default type value of wifi normal speed
        assertEquals(exp,subtype);
    }

    @Test
    public void checkSpeedConnectionTypeMobileLow()
    {
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        when(networkInfo.getSubtype()).thenReturn(1);

        checkCon=new CheckConnection(context,networkInfo);
        //check for mobile  << value 0 is type_mobile>>
        assertEquals(0,networkInfo.getType());
        //check low speed less than 100 kbps
        int subtype=checkCon.checkSpeedConnection();
        int exp=0; //0 default type value of wifi normal speed
        assertEquals(exp,subtype);
    }
    @Test
    public void checkSpeedConnectionTypeMobileMedium()
    {
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        when(networkInfo.getSubtype()).thenReturn(5);

        checkCon=new CheckConnection(context,networkInfo);
        //check for mobile  << value 0 is type_mobile>>
        assertEquals(0,networkInfo.getType());
        //check low speed less than 100 kbps
        int subtype=checkCon.checkSpeedConnection();
        int exp=1; //0 default type value of wifi normal speed
        assertEquals(exp,subtype);
    }
    @Test
    public void checkSpeedConnectionTypeMobileHigh()
    {
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        when(networkInfo.getSubtype()).thenReturn(3);

        checkCon=new CheckConnection(context,networkInfo);
        //check for mobile  << value 0 is type_mobile>>
        assertEquals(0,networkInfo.getType());
        //check low speed less than 100 kbps
        int subtype=checkCon.checkSpeedConnection();
        int exp=2; //0 default type value of wifi normal speed
        assertEquals(exp,subtype);
    }
    @Test
    public void checkSpeedConnectionTypeMobileUknown()
    {
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        when(networkInfo.getSubtype()).thenReturn(0);

        checkCon=new CheckConnection(context,networkInfo);
        //check for mobile  << value 0 is type_mobile>>
        assertEquals(0,networkInfo.getType());
        //check low speed less than 100 kbps
        int subtype=checkCon.checkSpeedConnection();
        int exp=3; //0 default type value of wifi normal speed
        assertEquals(exp,subtype);
    }
}