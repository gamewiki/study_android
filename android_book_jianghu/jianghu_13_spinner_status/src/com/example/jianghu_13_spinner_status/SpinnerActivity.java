package com.example.jianghu_13_spinner_status;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity {
	/* 声明对象变量 */
	private NotificationManager myNotiManager;
	private Spinner mySpinner;
	private ArrayAdapter<String> myAdapter;
	private static final String[] status = { "在线中", "离开一会", "忙碌中", "一会回来",
			"离线中" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_spinner);

		/* 初始化对象 */
		myNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mySpinner = (Spinner) findViewById(R.id.mySpinner);
		myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, status);
		/* 应用myspinner_dropdown自定义下拉菜单模式 */
		myAdapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		/* 将ArrayAdapter添加Spinner对象中 */
		mySpinner.setAdapter(myAdapter);

		/* 将mySpinner添加OnItemSelectedListener */
		mySpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						/* 依照选择的item来判断要发哪一个notification */
						if (status[arg2].equals("在线中")) {
							setNotiType(R.drawable.msn, "在线中");
						} else if (status[arg2].equals("离开一会")) {
							setNotiType(R.drawable.away, "离开一会");
						} else if (status[arg2].equals("忙碌中")) {
							setNotiType(R.drawable.busy, "忙碌中");
						} else if (status[arg2].equals("一会回来")) {
							setNotiType(R.drawable.min, "一会回来");
						} else {
							setNotiType(R.drawable.offine, "离线中");
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}

	/* 发出Notification的method */
	private void setNotiType(int iconId, String text) {
		/*
		 * 创建新的Intent，作为点击Notification留言条时， 会运行的Activity
		 */
		Intent notifyIntent = new Intent(this, SpinnerToast.class);
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		/* 创建PendingIntent作为设置递延运行的Activity */
		PendingIntent appIntent = PendingIntent.getActivity(
				SpinnerActivity.this, 0, notifyIntent, 0);

		/* 创建Notication，并设置相关参数 */
		Notification myNoti = new Notification();
		/* 设置statusbar显示的icon */
		myNoti.icon = iconId;
		/* 设置statusbar显示的文字信息 */
		myNoti.tickerText = text;
		/* 设置notification发生时同时发出默认声音 */
		myNoti.defaults = Notification.DEFAULT_SOUND;
		/* 设置Notification留言条的参数 */
		myNoti.setLatestEventInfo(SpinnerActivity.this, "登录状态", text, appIntent);
		/* 送出Notification */
		myNotiManager.notify(0, myNoti);
	}
}
