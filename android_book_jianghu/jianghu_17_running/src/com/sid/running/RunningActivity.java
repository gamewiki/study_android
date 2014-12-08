package com.sid.running;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RunningActivity extends Activity {
	private Button mButton01;
	private ListView mListView01;
	private ArrayAdapter<String> aryAdapter1;
	private ArrayList<String> arylistTask;

	/* 类成员设置取回最多几笔的Task数量 */
	private int intGetTastCounter = 30;

	/* 类成员ActivityManager对象 */
	private ActivityManager mActivityManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_running);

		mButton01 = (Button) findViewById(R.id.myButton1);
		mListView01 = (ListView) findViewById(R.id.myListView1);

		/* 点击按钮取得正在后台运行的工作程序 */
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					/* ActivityManager对象向系统取得ACTIVITY_SERVICE */
					mActivityManager = (ActivityManager) RunningActivity.this
							.getSystemService(ACTIVITY_SERVICE);
					arylistTask = new ArrayList<String>();
					/* 以getRunningTasks方法取回正在运行中的程序TaskInfo */
					List<ActivityManager.RunningTaskInfo> mRunningTasks = mActivityManager
							.getRunningTasks(intGetTastCounter);
					int i = 1;
					/* 以循环及baseActivity方式取得工作名称与ID */
					for (ActivityManager.RunningTaskInfo amTask : mRunningTasks) {
						/* baseActivity.getClassName取出运行工作名称 */
						arylistTask.add("" + (i++) + ": "
								+ amTask.baseActivity.getClassName() + "(ID="
								+ amTask.id + ")");
					}
					aryAdapter1 = new ArrayAdapter<String>(
							RunningActivity.this, R.layout.simple_list_item_1,
							arylistTask);

					if (aryAdapter1.getCount() == 0) {
						/* 当没有任何运行的工作，则提示信息 */
						mMakeTextToast(
								getResources().getText(
										R.string.str_err_no_running_task)
										.toString(), true);
					} else {
						/* 发现后台运行的工作程序，以ListView Widget条列呈现 */
						mListView01.setAdapter(aryAdapter1);
					}
				} catch (SecurityException e) {
					/* 当无GET_TASKS权限时(SecurityException异常)提示信息 */
					mMakeTextToast(
							getResources().getText(R.string.str_err_permission)
									.toString(), true);
				}
			}
		});

		/* 当User在运行工作选择时的事件处理 */
		mListView01
				.setOnItemSelectedListener(new ListView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View v,
							int id, long arg3) {
						// TODO Auto-generated method stub
						/* 由于将运行工作以数组存放，故以id取出数组元素名称 */
						mMakeTextToast(arylistTask.get(id).toString(), false);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		/* 当User在运行工作上点击时的事件处理 */
		mListView01.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int id,
					long arg3) {
				// TODO Auto-generated method stub
				/* 由于将运行工作以数组存放，故以id取出数组元素名称 */
				mMakeTextToast(arylistTask.get(id).toString(), false);
			}
		});
	}

	public void mMakeTextToast(String str, boolean isLong) {
		if (isLong == true) {
			Toast.makeText(RunningActivity.this, str, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(RunningActivity.this, str, Toast.LENGTH_SHORT)
					.show();
		}
	}
}
