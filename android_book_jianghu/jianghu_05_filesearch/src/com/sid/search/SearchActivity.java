package com.sid.search;

import java.io.File;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends Activity {
	/* 声明对象变量 */
	private Button mButton;
	private EditText mKeyword;
	private TextView mResult;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_search);

		/* 初始化对象 */
		mKeyword = (EditText) findViewById(R.id.mKeyword);
		mButton = (Button) findViewById(R.id.mButton);
		mResult = (TextView) findViewById(R.id.mResult);

		/* 将mButton添加onClickListener */
		mButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				/* 取得输入的关键字 */
				String keyword = mKeyword.getText().toString();
				if (keyword.equals("")) {
					mResult.setText("输入为空!!");
				} else {
					mResult.setText(searchFile(keyword));
				}
			}
		});
	}

	/* 搜索文件的method */
	private String searchFile(String keyword) {
		String result = "";
		File[] files = new File("/").listFiles();
		for (File f : files) {
			if (f.getName().indexOf(keyword) >= 0) {
				result += f.getPath() + "\n";
			}
		}
		if (result.equals(""))
			result = "搜索不到这个文件!!";
		return result;
	}
}
