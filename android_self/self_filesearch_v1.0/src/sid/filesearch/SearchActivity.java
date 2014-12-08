package sid.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sid.data.DataActivity;
import sid.modle.FileList;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import sid.utils.OpenFileUtils;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

public class SearchActivity extends ListActivity implements OnGestureListener {
	private Button mButton;
	private EditText mKeyword;
	private TextView mResult;
	private List<FileList> fileNameList = new ArrayList<FileList>();
	private String SDCardRoot = null;
	private String dir = "";
	private RadioGroup radioGroup;
	private RadioButton radiosdcard;
	private RadioButton radioall;
	private GestureDetector detector;
	long fileSum = 0l;
	long fileNum = 0l;
	boolean fileReturn = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_search);

		/* 初始化对象 */
		mKeyword = (EditText) findViewById(R.id.mKeyword);
		mButton = (Button) findViewById(R.id.mButton);
		mResult = (TextView) findViewById(R.id.mResult);
		mResult.setText(getString(R.string.sdcardChecked));
		SDCardRoot = Environment.getExternalStorageDirectory() + File.separator;
		dir = SDCardRoot + File.separator;
		/* 将mButton添加onClickListener */
		mButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				fileSum = 0l;
				fileNum = 0l;
				fileReturn = false;
				fileNameList = new ArrayList<FileList>();
				/* 取得输入的关键字 */
				String keyword = mKeyword.getText().toString();
				if (null == keyword || "".equals(keyword)) {
					mResult.setText(getString(R.string.input_blank));
					Toast.makeText(SearchActivity.this, getString(R.string.input_blank),Toast.LENGTH_SHORT).show();
				} else {
					searchFileList(dir, keyword, fileNameList);
					if (!(null != fileNameList && fileNameList.size() > 0)) {
						fileNameList = new ArrayList<FileList>();
						mResult.setText(getString(R.string.fileNotExist));
						Toast.makeText(SearchActivity.this, getString(R.string.fileNotExist),Toast.LENGTH_SHORT).show();
					}
					SimpleAdapter simpleAdapter = buildSimpleAdapter(fileNameList);
					// 将这个simpleAdapter对象设置到ListAdapter当中
					setListAdapter(simpleAdapter);
				}
			}
		});

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radiosdcard = (RadioButton) findViewById(R.id.radiosacard);
		radioall = (RadioButton) findViewById(R.id.radioall);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						String text = "";
						if (radioall.getId() == checkedId) {
							// 启动新的activity用于显示关于信息
							Intent intent = new Intent();
							intent.setClass(SearchActivity.this, DataActivity.class);
							startActivity(intent);
							text = getString(R.string.allChecked);
						} else if (radiosdcard.getId() == checkedId) {
							text = getString(R.string.sdcardChecked);
							dir = SDCardRoot + File.separator;
						}
						Toast.makeText(SearchActivity.this, text,
								Toast.LENGTH_LONG).show();
					}
				});

		getIntentParam();
		// 绑定新的分析用户的动作类
		detector = new GestureDetector(this);
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		//设置默认软键盘不弹出
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "SearchActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
		getIntentParam();
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "SearchActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}

	/* 以下是点击菜单按钮执行的方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(SearchActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
			// 如果只是调用以下其中的一个方法，并不会完全退出应用
			// android.os.Process.killProcess(android.os.Process.myPid());
			// System.exit(0);
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 获取返回的数据
	 */
	private void getIntentParam(){
		//获取传递过来啊Intent
		Intent intent = getIntent();
		String value = intent.getStringExtra("resultPath");
		if (null!=value) {
			dir = value;
			mResult.setText(getString(R.string.search_dir1)+dir+getString(R.string.search_dir2));
			if ("/".equals(dir)) {
				Toast.makeText(SearchActivity.this, getString(R.string.baseChecked),Toast.LENGTH_SHORT).show();
			}
		}
	}

	/** 搜索文件的method */
	private void searchFileList(String dir, String keyword,
			List<FileList> fileNameList) {
		File[] files = new File(dir).listFiles();
		if (null != files) {
			for (File f : files) {
				if (fileReturn) {
					return;
				}else if (fileNum>AppConstant.NUM_MAX_SIZE) {
					fileReturn = true;
					Toast.makeText(SearchActivity.this, getString(R.string.num_maxSize),Toast.LENGTH_LONG).show();
					return;
				}else if (fileSum>AppConstant.SUM_MAX_SIZE) {
					fileReturn = true;
					String text = getString(R.string.sum_maxSize1)+AppConstant.SUM_MAX_SIZE+getString(R.string.sum_maxSize2);
					Toast.makeText(SearchActivity.this, text,Toast.LENGTH_LONG).show();
					return;
				}
				fileSum++;
				if (f.isFile() && f.getName().indexOf(keyword) >= 0) {
					fileNum++;
					FileList fileList = new FileList(
							OpenFileUtils.getFileName(f.getPath()), f.getPath());
					fileNameList.add(fileList);
				} else if (f.isDirectory()) {
					searchFileList(f.getPath(), keyword, fileNameList);
				}
				mResult.setText(getString(R.string.search_result1) + fileSum
						+ getString(R.string.search_result2) + fileNum
						+ getString(R.string.search_result3));
			}
		}
//		return fileNameList;
	}

	/**
	 * 点击列表的对应数据调用的方法
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// 根据用户点击的位置来获取相应的对象
		if (fileNameList != null && !fileNameList.isEmpty()) {
			FileList file = fileNameList.get(position);
			Intent intent = OpenFileUtils.openFile(file.getFilepath());
			startActivity(intent);
		}
		super.onListItemClick(l, v, position, id);
	}

	/**
	 * 生成一个simpleAdapter对象 用于动态的生成列表
	 * 
	 * @return
	 */
	private SimpleAdapter buildSimpleAdapter(List<FileList> InfoList) {
		// 生成一个List对象，并按照simpleAdapter的标准，将MP3Info信息放入list当中
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (Iterator<FileList> iterator = InfoList.iterator(); iterator
				.hasNext();) {
			FileList fileName = (FileList) iterator.next();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("file_pic", R.drawable.doc);
			map.put("filename", fileName.getFilename());
			list.add(map);
		}
		// 创建一个simpleAdapter对象
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.file_row, new String[] { "file_pic" ,"filename"},
				new int[] { R.id.icon,R.id.text });
		return simpleAdapter;
	}

	/* 以下是监听左右滑动事件 ；需要继承OnGestureListener */

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}

	/**
	 * 解决ScrollView后不执行左右移动监听事件OnGestureListener
	 * 在Activity中添加ScrollView实现滚动activity的效果后，activity的滑动效果却无法生效了
	 * 原因是因为activity没有处理滑动效果，重写以下方法即可解决。
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		detector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 监听滑动
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 120) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(SearchActivity.this, AboutActivity.class);
			this.startActivity(intent);
		}
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
}
