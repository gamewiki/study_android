ListView使用

1.定义List

<!--
			//默认系统id
	        android:id="@id/android:list"
	        //点击某一条记录，颜色会显示在最上面，记录上的文字被遮住，所以点击文字不放，文字就看不到
	        android:drawSelectorOnTop="false"
	        //设置滚动条的方向，竖直还是水平的
	        android:scrollbars="vertical"
 -->
	    <ListView 
	        android:id="@id/android:list"
	        android:layout_width="fill_parent"
	        android:layout_height="wrap_content"
	        android:drawSelectorOnTop="false"
	        android:scrollbars="vertical"/>
	        
2.定义数据格式

    <TextView 
        android:id="@+id/username"
        android:layout_width="180dp"
        android:layout_height="30dp"
        android:textSize="10pt"
        android:singleLine="true"/>
    
    <TextView 
        android:id="@+id/userip"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:textSize="10pt"
        android:gravity="right"/>


3.activity的编写

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
		System.out.println("id is :==========="+id);
		System.out.println("position is :====="+position);
	}
}