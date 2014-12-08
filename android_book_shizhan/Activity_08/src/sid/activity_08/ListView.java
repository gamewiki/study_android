package sid.activity_08;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

public class ListView extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ArrayList<HashMap<String,String>> useriplist = new ArrayList<HashMap<String,String>>();
        for(int i = 0;i<5;i++){
            HashMap<String, String> map1 = new HashMap<String, String>();
            HashMap<String, String> map2 = new HashMap<String, String>();
            HashMap<String, String> map3 = new HashMap<String, String>();
            map1.put("username", "张阳");
            map1.put("userip", "192.168.0.1");
            map2.put("username", "里斯");
            map2.put("userip", "192.168.0.12");
            map3.put("username", "王五");
            map3.put("userip", "192.168.0.13");
            useriplist.add(map1);
            useriplist.add(map2);
            useriplist.add(map3);
        }
        
        SimpleAdapter sa = new SimpleAdapter(this, useriplist, R.layout.userlist, 
        		new String []{"username","userip"}, 
        		new int[]{R.id.username,R.id.userip});
        setListAdapter(sa);
        
    }

	@Override
	protected void onListItemClick(android.widget.ListView l, View v,
			int position, long id) {
		super.onListItemClick(l, v, position, id);
//		System.out.println("id is :==========="+id);
//		System.out.println("position is :====="+position);
//		System.out.println("l getSelectedItem :==========="+l.getSelectedItem().toString());
//		System.out.println("l getCheckedItemPosition :==========="+l.getCheckedItemPosition());
//		System.out.println("l getChildCount :==========="+l.getChildCount());
//		System.out.println("l is :==========="+l.getCheckedItemPositions().toString());
//		System.out.println("v toString :====="+v.toString());
		System.out.println();
	}
}
