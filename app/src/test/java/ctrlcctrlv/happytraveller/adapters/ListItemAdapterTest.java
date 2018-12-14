package ctrlcctrlv.happytraveller.adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;

import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.model.PlaceData;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class ListItemAdapterTest
{
    private ListItemAdapter adapter;
    private ArrayList<PlaceData> data;
    private Context context;
    private View view;
    private ListView listView;


    @Before
    public void setUp() throws Exception
    {
        init();
        prepareData();
         /*
            still incomplete
         */
        adapter=new ListItemAdapter(data,this.context);

    }
    private void prepareData()
    {
        data.add(new PlaceData("Foo church","Foo address"));
        data.add(new PlaceData("Fee Museum","Fee address"));
        data.add(new PlaceData("Faa Park","Faa address"));
    }
    private void init()
    {
        data=new ArrayList<PlaceData>();
        context=mock(Context.class);
        view=mock(View.class);
    }
    @After
    public void tearDown() throws Exception
    {
        data.clear();
        adapter.clear();
        context=null;
    }
    @Test
    public void testDataNotNull()
    {
        assertNotNull("PlaceData array null",data.get(0).getName());
    }
    @Test
    public void testAdapterNotNull()
    {
        assertNotNull("Adapter null",adapter);
    }
    @Test
    public void testGetItem()
    {
        assertThat(adapter.getItem(0),is(data.get(0)));
        assertThat(adapter.getItem(1),is(data.get(1)));
    }
    @Test
    public void testGetPosition()
    {
        PlaceData sight=new PlaceData("Foo church","Foo address");
        sight.setId(0);
        assertEquals("Wrong Item",data.get(0).getId(),adapter.getPosition(sight));
    }
    @Test
    public void testGetItemId()
    {
            assertEquals("Wrong ID.", 0,adapter.getItemId(0));
    }

    @Test
    public void testGetCount()
    {
        assertEquals("PlaceData amount incorrect.", data.size(), adapter.getCount());
    }

    @Test
    public void testGetView()
    {
        /*
            still incomplete
         */
        view = adapter.getView(0,null, null);
//        listView=(ListView)view.findViewById(R.id.listView);
//        listView.setAdapter(adapter);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView address = (TextView) view.findViewById(R.id.address);
        ImageView photo = (ImageView) view.findViewById(R.id.imageView);
        Button btnShowRoute=(Button)view.findViewById(R.id.btnShowRoute);
        Button btnDetails=(Button)view.findViewById(R.id.btnDetails);

        assertNotNull("View is null. ", view);
        assertNotNull("Name TextView is null. ", name);
        assertNotNull("Address TextView is null. ", address);
        assertNotNull("Photo ImageView is null. ", photo);
        assertNotNull("ShowRoute Button is null. ", btnShowRoute);
        assertNotNull("Details Button is null. ", btnDetails);

        assertEquals("Names doesn't match.", data.get(0).getName(), name.getText());
        assertEquals("Address doesn't match.", data.get(0).getAddress(), address.getText());
    }

}