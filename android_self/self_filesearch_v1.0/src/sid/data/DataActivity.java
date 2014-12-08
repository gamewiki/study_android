package sid.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.baidu.mobstat.StatService;

import sid.filesearch.R;
import sid.filesearch.SearchActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends ListActivity {
	/**变量声明 items*/
	private List<String> items = null;
	/**存放显示的名称 paths*/
	private List<String> paths = null;
	/**存放文件路径 rootPath：起始目录*/
	private String rootPath = "/";
	/**当前目录位置*/
	private TextView mPath;
	private Button mButton;

	@Override
	protected void onCreate(Bundle icicle) {
		Log.w(AppConstant.TAG, "DataActivity.onCreate()");
		super.onCreate(icicle);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_data);
		/* 初始化mPath，用以显示目前路径 */
		mPath = (TextView) findViewById(R.id.mPath);
		mButton = (Button) findViewById(R.id.dButton);
		/* 将mButton添加onClickListener */
		mButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// 启动新的activity用于显示关于信息
				Intent intent = new Intent();
				String resultPath = mPath.getText().toString();
				intent.putExtra("resultPath", resultPath);
				intent.setClass(DataActivity.this,SearchActivity.class);
				startActivity(intent);
			}
		});
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		getFileDir(rootPath);
	}
	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "DataActivity.OnResume()");
		super.onResume();
		/**此处调用基本统计代码*/
		StatService.onResume(this);
	}
	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "DataActivity.onPause()");
		super.onPause();
		/**此处调用基本统计代码*/
		StatService.onPause(this);
	}

	/**
	 *  取得文件架构的方法
	 * @param filePath
	 */
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
			Toast.makeText(DataActivity.this, getString(R.string.notExistFiles), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 设置ListItem被点击时要做的动作
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(paths.get(position));
		if (file.isDirectory()) {
			/* 如果是文件夹就再运行getFileDir() */
			getFileDir(paths.get(position));
		} else {
			Toast.makeText(DataActivity.this, getString(R.string.isFile), Toast.LENGTH_SHORT).show();
		}
	}
}
