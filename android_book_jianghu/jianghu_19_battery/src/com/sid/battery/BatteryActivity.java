package com.sid.battery;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class BatteryActivity extends Activity {
	/* 变量声明 */
	private int intLevel;
	private int intScale;
	private Button mButton01;

	/* 创建BroadcastReceiver */
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			/*
			 * 如果捕捉到的action是ACTION_BATTERY_CHANGED， 就运行onBatteryInfoReceiver()
			 */
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
				intLevel = intent.getIntExtra("level", 0);
				intScale = intent.getIntExtra("scale", 100);
				onBatteryInfoReceiver(intLevel, intScale);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_battery);
		/* 初始化Button，并设置点击后的动作 */
		mButton01 = (Button) findViewById(R.id.myButton1);
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* 注册一个系统 BroadcastReceiver，作为访问电池计量之用 */
				registerReceiver(mBatInfoReceiver, new IntentFilter(
						Intent.ACTION_BATTERY_CHANGED));
			}
		});
	}

	/* 捕捉到ACTION_BATTERY_CHANGED时要运行的method */
	public void onBatteryInfoReceiver(int intLevel, int intScale) {
		/* create 跳出的对话窗口 */
		final Dialog d = new Dialog(BatteryActivity.this);
		d.setTitle(R.string.str_dialog_title);
		d.setContentView(R.layout.mydialog);

		/* 创建一个背景模糊的Window，且将对话窗口放在前景 */
		Window window = d.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		/* 将取得的电池计量显示于Dialog中 */
		TextView mTextView02 = (TextView) d.findViewById(R.id.myTextView2);
		mTextView02.setText(getResources().getText(R.string.str_dialog_body)
				+ String.valueOf(intLevel * 100 / intScale) + "%");

		/* 设置返回主画面的按钮 */
		Button mButton02 = (Button) d.findViewById(R.id.myButton2);
		mButton02.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* 反注册Receiver，并关闭对话窗口 */
				unregisterReceiver(mBatInfoReceiver);
				d.dismiss();
			}
		});
		d.show();
	}
}
