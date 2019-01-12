package ctrlcctrlv.happytraveller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.activities.HomeActivity;
import ctrlcctrlv.happytraveller.model.PlaceData;

import static ctrlcctrlv.happytraveller.fragments.TabMapFragment.getPolylineState;
import static ctrlcctrlv.happytraveller.fragments.TabMapFragment.mMap;

/**
*An List Item adapter that adapt the data from each place object to the UI.
*An adapter class that display alla place data object in proper format on
*ListView tab on UI.Also extend ArrayAdapter<PlaceData> & implements View.OnClickListener.
*
*@see ArrayAdapter<PlaceData>
*@see View.OnClickListener
*@see Context
*
*@since 15 Nov 2018
*/
public class ListItemAdapter extends ArrayAdapter<PlaceData> implements View.OnClickListener
{

    private ArrayList<PlaceData> dataSet;
    private static final ctrlcctrlv.happytraveller.fragments.TabMapFragment TabMapFragment = ctrlcctrlv.happytraveller.fragments.TabMapFragment.getTabMap_instance();
    Context mContext;
    protected HomeActivity homeActivity = new HomeActivity();

    /**
     *View lookup cache class
     *
     */
    private static class ViewHolder
    {
        TextView txtName;
        TextView txtAddress;
        ImageView imgView;
        Button btn;
        Button btnShowRoute;
        //add more data var-components px : img info etc..
    }

    /**
    *The constructor that initialize the basic variables
    *And display the list_view.xml .
    *@param data An ArrayList<PlaceData> that will pass value to the dataSet.
    *@param context An Context object that will pass value to the mContext.
    */
    public ListItemAdapter(ArrayList<PlaceData> data, Context context)
    {
        super(context, R.layout.list_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View view)
    {

        Polyline polyline = getPolylineState();
        if(polyline!=null)
        {
            polyline.remove();
            mMap.clear();
        }

        TabMapFragment.passCoordinatesFromPlaces();
        int position = (Integer) view.getTag();
       // System.out.println(position);
        HashMap dataPassedFromHash = TabMapFragment.getMapCoordinates();
        double users_current_latitude = (homeActivity.getUsersLocation().latitude);
        double users_current_longitude = (homeActivity.getUsersLocation().longitude);
        LatLng user_coordinates = new LatLng(users_current_latitude,users_current_longitude);
        LatLng selected_place_coordinates = (LatLng) dataPassedFromHash.get(position);
        mMap.addMarker(new MarkerOptions().position(selected_place_coordinates));
        TabMapFragment.getTabMap_instance().drawRouteOnMap(user_coordinates,selected_place_coordinates);
    }

    /**
    *The main method that will adapt the data to the list xml.
     * It overrrides the View.getView() method , to adapt the data to the list and display it.
     * @param position The position of each data item.
     * @param convertView
     * @param parent
    *@see View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlaceData placeData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view, parent, false);
            //init ta components apo to list_view.xml ,add more pio meta
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtAddress=(TextView) convertView.findViewById(R.id.address);
            viewHolder.imgView=(ImageView)convertView.findViewById(R.id.imageView);
            viewHolder.btn=(Button)convertView.findViewById(R.id.btnDetails);
            viewHolder.btnShowRoute=(Button)convertView.findViewById(R.id.btnShowRoute);

            result=convertView;

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(placeData.getName());
        viewHolder.txtAddress.setText(placeData.getAddress());
        viewHolder.btn.setId(position);

        //check if no default img show error img
        if(placeData.getDefaultImg().getPhotoReference()==null)
        {
            Picasso.with(getContext()).load(R.drawable.no_photo_available).into(viewHolder.imgView);
        }
        else
         { //else load default place img
             Picasso.with(getContext()).load(placeData.getDefaultImg().getImgUrl()).into(viewHolder.imgView);
        }

        viewHolder.btnShowRoute.setOnClickListener(this);
        viewHolder.btnShowRoute.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }


}
