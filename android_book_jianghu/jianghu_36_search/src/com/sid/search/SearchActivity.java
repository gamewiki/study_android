package com.sid.search;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

public class SearchActivity extends Activity {

	private AutoCompleteTextView myAutoCompleteTextView1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		myAutoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView1);

		/* new一个自己实现的BaseAdapter */
		MyAdapter adapter = new MyAdapter(this);

		myAutoCompleteTextView1.setAdapter(adapter);
	}
}