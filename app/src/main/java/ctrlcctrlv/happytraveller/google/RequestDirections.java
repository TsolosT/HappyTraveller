package ctrlcctrlv.happytraveller.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// TODO: 12/10/2018 Change name
public class RequestDirections
{

    public String getDirections(String givenUrl) throws IOException
    {

        String responseString = "";
        InputStream inputStream = null ;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(givenUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line ="";

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStream.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if (inputStream != null)
            {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;

    }

}