package ctrlcctrlv.happytraveller.alterDialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import ctrlcctrlv.happytraveller.R;

public class HomeActivityAlterDialog
{

    public void restartAlterDialog(final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
        builder.setTitle("Restart");
        builder.setMessage("Application has to reboot");
      //  builder.setIcon(R.drawable.places_ic_search);
        builder.setCancelable(false);

        builder.setPositiveButton("Sure!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = context.getApplicationContext().getPackageManager()
                        .getLaunchIntentForPackage(context.getApplicationContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        });
        AlertDialog alert = builder.create();

        alert.show();
    }



    public void openDataAlterDialog(final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AlertDialog);
        builder.setTitle("Error");
        builder.setMessage("No mobile data connection detected.");
        builder.setCancelable(false);

        builder.setNegativeButton("Data \uD83D\uDE22 ", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                context.startActivity(i);
            }

        });
        builder.setPositiveButton("Wi-Fi \uD83D\uDE0A", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(i);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }





    public void openGPSAlterDialog(final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
        builder.setTitle("You have to open your GPS");
        builder.setMessage("Please Enable your GPS ");
        builder.setIcon(R.drawable.places_ic_search);
        builder.setCancelable(false);

        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(i);
            }
        });
        AlertDialog alert = builder.create();

        alert.show();
    }


}
