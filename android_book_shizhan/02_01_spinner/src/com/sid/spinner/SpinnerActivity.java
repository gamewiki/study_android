package com.sid.spinner;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity {
	private Spinner spinner = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinner = (Spinner)findViewById(R.id.spinner);
        //创建适配器，并制定参数：上下文、数组列表、适配器的布局文件（android系统提供）
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
//										        R.array.planets_array, 
//										        android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("list1");
        list.add("list2");
        //调用adapter的构造函数来创建ArrayAdapter对象
        //第一个上下文
        //第二个是指定了下拉菜单当中每一个条目的样式
        //第三个是指定textView控件的id
        //第四个是为整个列表提供数据
        ArrayAdapter adapter = new ArrayAdapter(this, 
        										R.layout.item, 
        										R.id.textViewId, 
        										list);
        spinner.setPrompt("测试用的");
        
        
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    }

    /**
     * 用来监听用户选择列表的动作
     * 当用户选定了一个条目时，就会调用onItemSelected
     * @author Administrator
     *
     */
    class MyOnItemSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view, int position,
				long id) {
			String selected = (String) adapterView.getItemAtPosition(position);
			System.out.println("用户点击了："+selected);
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			System.out.println("用户什么都没有点击");
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_spinner, menu);
        return true;
    }
}
