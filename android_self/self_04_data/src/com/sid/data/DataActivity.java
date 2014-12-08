package com.sid.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends ListActivity {
	/*
	 * 变量声明 items：存放显示的名称 paths：存放文件路径 rootPath：起始目录
	 */
	private List<String> items = null;
	private List<String> paths = null;
	private String rootPath = "/";
	private TextView mPath;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		/* 载入main.xml Layout */
		setContentView(R.layout.activity_data);
		/* 初始化mPath，用以显示目前路径 */
		mPath = (TextView) findViewById(R.id.mPath);
		getFileDir(rootPath);
	}

	/* 取得文件架构的方法 */
	private void getFileDir(String filePath) {
		/* 设置目前所在路径 */
		mPath.setText(filePath);
		File f = new File(filePath);
		File[] files = f.listFiles();

		if(null!=files){
			items = new ArrayList<String>();
			paths = new ArrayList<String>();
			if (!filePath.equals(rootPath)) {
				/* 第一笔设置为[回到根目录] */
				items.add("b1");
				paths.add(rootPath);
				/* 第二笔设置为[回到上一层] */
				items.add("b2");
				paths.add(f.getParent());
			}
			/* 将所有文件添加ArrayList中 */
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				items.add(file.getName());
				paths.add(file.getPath());
			}
			/* 使用自定义的MyAdapter来将数据传入ListActivity */
			setListAdapter(new MyAdapter(this, items, paths));
		}else{
			Toast.makeText(DataActivity.this, "您选择的路径下没有子文件", Toast.LENGTH_SHORT).show();
		}

	}

	/* 设置ListItem被点击时要做的动作 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(paths.get(position));
		if (file.isDirectory()) {
			/* 如果是文件夹就再运行getFileDir() */
			getFileDir(paths.get(position));
		} else {
			/* 如果是文件就运行openFile() */
			OpenFileUtils.openFile(file.getPath());
		}
	}
}
