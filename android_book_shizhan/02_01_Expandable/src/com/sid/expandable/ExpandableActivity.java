package com.sid.expandable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleExpandableListAdapter;

public class ExpandableActivity extends ExpandableListActivity {

	List<Map<String, String>> groups = new ArrayList<Map<String,String>>();
	List<List<Map<String, String>>> childs = new ArrayList<List<Map<String,String>>>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        buildData();

        
        //1.
        //2.一级条目数据
        //3.用来设置一级条目的布局文件
        //4.指定一级条目的KEY
        //5.指定一级条目的显示控件的id（设置文本样式）
        SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
        		this, groups, R.layout.group, new String[]{"group"}, new int[]{R.id.groupId}, 
        		childs, R.layout.child, new String[]{"child"}, new int[]{R.id.childId});
        setListAdapter(sela);
        
    }

    /**
     * 数据初始化
     */
	private void buildData() {
		Map<String, String> group1 = new HashMap<String, String>();
        group1.put("group", "group1");
        Map<String, String> group2 = new HashMap<String, String>();
        group2.put("group", "group2");
        groups.add(group2);
        groups.add(group1);
        

    	List<Map<String, String>> childs1 = new ArrayList<Map<String,String>>();
        Map<String, String> child11 = new HashMap<String, String>();
        child11.put("child", "child11");
        Map<String, String> child12 = new HashMap<String, String>();
        child12.put("child", "child12");
        childs1.add(child12);
        childs1.add(child11);
        

        List<Map<String, String>> childs2 = new ArrayList<Map<String,String>>();
        List<Map<String, String>> childs3 = new ArrayList<Map<String,String>>();
        Map<String, String> child21 = new HashMap<String, String>();
        child21.put("child", "child21");
        Map<String, String> child22 = new HashMap<String, String>();
        child22.put("child", "child22");
        childs2.add(child21);
        childs3.add(child22);
        
        childs.add(childs1);
        childs.add(childs2);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_expandable, menu);
        return true;
    }
}
