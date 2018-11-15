package ctrlcctrlv.happytraveller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.adapters.ListItemAdapter;
import ctrlcctrlv.happytraveller.model.PlaceData;

/*
 * This class is the fragment for the tab list view
 * It displays the fragment_tab_list_view.xml
 *
 * */
public class TabListViewFragment extends Fragment
{
    View view;
    ArrayList<PlaceData> placeData;
    ListView listView;
    private static ListItemAdapter adapter;


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.fragment_tab_list_view, container, false);
        listView= (ListView)view.findViewById(R.id.listView);
//test example douleuei
//        placeData=new ArrayList<>();
//        placeData.add(new PlaceData("lili","lalalla la","info info info"));
//        placeData.add(new PlaceData("lili","lalalla la","info info info"));





        adapter=new ListItemAdapter(placeData,getContext());



        listView.setAdapter(adapter);


        return view;
    }








}
