package ctrlcctrlv.happytraveller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ctrlcctrlv.happytraveller.R;

/*
 * This class is the fragment for the tab list view
 * It displays the fragment_tab_list_view.xml
 *
 * Sto fragment_tab_list.xml 8a pros8esete oti ui component 8elete gia na emfanisete ta info pio analutika
 * an usare webview gia na emfaniste img text etc klp prepei na kanete new folder assets meta mesa ekei
 * create html h jsp klp gia pio analutika gia auth thn me8odo miliste me emena(teo)
 * an uparxh allos tropos kante to opws to vreite
 * Sthn class auth 8a ulopoihsete tis methods pou 8elete na usarete
 * */
public class TabListViewFragment extends Fragment
{

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_tab_list_view,container,false);
    }
}
