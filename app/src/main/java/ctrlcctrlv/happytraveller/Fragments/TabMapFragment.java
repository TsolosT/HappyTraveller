package ctrlcctrlv.happytraveller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ctrlcctrlv.happytraveller.R;


/*
* This class is the fragment for the tab map
* It displays the fragment_tab_map.xml
*
* Sto fragment_tab_map.xml 8a pros8esete oti ui component 8elete gia na emfanisete to map
* Sthn class auth 8a ulopoihsete tis methods pou 8elete na usarete
* */
public class TabMapFragment extends Fragment {



 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_tab_map,container,false);
    }


}
