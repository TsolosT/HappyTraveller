package ctrlcctrlv.happytraveller.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

public class CheckConnection
{
    private Context context;
    private NetworkInfo netInfo;



    private ConnectivityManager connManager;

    public  CheckConnection(Context context)
    {
        this.context = context;
        connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    //just for testing propose at the moment
    public  CheckConnection(Context context,NetworkInfo info)
    {
        this.context = context;
        connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.netInfo=info;
    }
    public boolean wifiIs()
    {
       // connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return mWifi.isConnected();
    }


    public boolean mobileDataIs()
    {
        boolean mobileDataEnabled = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true);

            mobileDataEnabled = (Boolean)method.invoke(cm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileDataEnabled;
    }
    public int checkSpeedConnection()
    {
        if(netInfo==null)
        {
            netInfo= connManager.getActiveNetworkInfo();
        }
        int type=-1; //default value of type_connection  "-1 = wifi type"

        if(netInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            return  type;
        }
        else if(netInfo.getType() == ConnectivityManager.TYPE_MOBILE)
        {   // check NetworkInfo subtype
            if (netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS)
            {
                // Bandwidth between 100 kbps and below
                    type=0;
                    return type;
            }
            else if(netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_0 || netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_A)
            {
                // Bandwidth between 400-1000 kbps or 600-1400kbps
                type=1;
                return type;
            }
            else if(netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_UMTS ||netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_HSUPA ){
                // Bandwidth between 600-1400 kbps or 1-23 mbps
                type=2;
                return type;
            }
            else if(netInfo.getSubtype()==TelephonyManager.NETWORK_TYPE_UNKNOWN)
            {
                type=3;
                return type;
            }
        }
        return type;
    }


}
