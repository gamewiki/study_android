package com.sid.msg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MsgActivity extends Activity {
	private TextView mTextView1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg);
		/* 通过findViewById构造器创建TextView对象 */
		mTextView1 = (TextView) findViewById(R.id.myTextView1);
		mTextView1.setText("等待接收信息中...");
	}
}
