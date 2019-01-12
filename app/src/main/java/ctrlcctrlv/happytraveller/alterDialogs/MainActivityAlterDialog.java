package ctrlcctrlv.happytraveller.alterDialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.model.PlaceData;

/**
 * One function to serve MainActivity classes alter dialog
 */
public class MainActivityAlterDialog
{
    ArrayList<PlaceData> suggestedSights;

    /**
     * Initialize the suggested Sights in order to display them to user
     * @param suggestedSights
     */
    public void setSuggestedSights(ArrayList<PlaceData> suggestedSights) {this.suggestedSights = suggestedSights;}

    /**
     * An alter dialog in order for user to know the suggested by this application sights and witch he has to follow
     * @param context
     */
    public void showSuggestedSights(final Context context)
    {
        if (suggestedSights == null)
            Toast.makeText(context, "Set your available time below !", Toast.LENGTH_LONG).show();
        else
        {

            StringBuilder sightsForSuggestion = new StringBuilder();
            int counter = 1;

            for (PlaceData sights:suggestedSights)
            {
                sightsForSuggestion.append(counter).append(". ").append(sights.getName()).append("\n Distance:").append(sights.getDistance()).append("m").append(", Time:").append(sights.getTimeTillArrival()).append("min").append("\n\n");
                counter++;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
            builder.setTitle("Suggested Sights");
            builder.setMessage(sightsForSuggestion.toString());
            builder.setCancelable(false);

            builder.setPositiveButton("Great", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
                    builder.setTitle("Suggested Sights");
                    builder.setMessage("You must follow the route order as are shown bellow" +
                            "\n\nRed>Yellow>Green>Cyan>Blue"+"\n\n" +
                            "");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Sure!", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    AlertDialog alert = builder.create();

                    alert.show();
                }
            });
            AlertDialog alert = builder.create();

            alert.show();
        }
    }
}
