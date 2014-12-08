package com.sid.startup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StartupActivity extends Activity {
	/* 本程序只要运行一次，就会在日后开机时自动运行 */
	private TextView mTextView01;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);

		/* 为了快速示意，程序仅以欢迎的TextView文字作为展示 */
		mTextView01 = (TextView) findViewById(R.id.myTextView1);
		mTextView01.setText(R.string.str_welcome);
	}
}
