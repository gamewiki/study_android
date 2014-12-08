package com.sid.screen;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenActivity extends Activity {
	/* 声明设置图片的七个Button及启动与终止的两个Button */
	private Button mButton1;
	private Button mButton2;
	private Button setButton1;
	private Button setButton2;
	private Button setButton3;
	private Button setButton4;
	private Button setButton5;
	private Button setButton6;
	private Button setButton7;
	/* 声明显示图文件名称的七个TextView */
	private TextView mySet1;
	private TextView mySet2;
	private TextView mySet3;
	private TextView mySet4;
	private TextView mySet5;
	private TextView mySet6;
	private TextView mySet7;
	/* 声明自定义的数据库变量DailyBgDB */
	private DailyBgDB db;
	/* 声明存放设置值的Map */
	private Map<Integer, Integer> map;
	private LayoutInflater inflater;
	private int tmpWhich = 0;
	/* 声明存放图文件id的数组bg与存放图文件名称的数组bgName */
	private final int[] bg = { R.drawable.b01, R.drawable.b02, R.drawable.b03,
			R.drawable.b04, R.drawable.b05, R.drawable.b06, R.drawable.b07 };
	private final String[] bgName = { "b01.png", "b02.png", "b03.png",
			"b04.png", "b05.png", "b06.png", "b07.png" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_screen);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		/* 将数据库存放的设置值放入map中 */
		initSettingData();
		/* 初始化TextView对象 */
		mySet1 = (TextView) findViewById(R.id.mySet1);
		mySet2 = (TextView) findViewById(R.id.mySet2);
		mySet3 = (TextView) findViewById(R.id.mySet3);
		mySet4 = (TextView) findViewById(R.id.mySet4);
		mySet5 = (TextView) findViewById(R.id.mySet5);
		mySet6 = (TextView) findViewById(R.id.mySet6);
		mySet7 = (TextView) findViewById(R.id.mySet7);

		/* 设置显示的图文件名称 */
		if (!map.get(0).equals(99)) {
			mySet1.setText(bgName[map.get(0)]);
		}
		if (!map.get(1).equals(99)) {
			mySet2.setText(bgName[map.get(1)]);
		}
		if (!map.get(2).equals(99)) {
			mySet3.setText(bgName[map.get(2)]);
		}
		if (!map.get(3).equals(99)) {
			mySet4.setText(bgName[map.get(3)]);
		}
		if (!map.get(4).equals(99)) {
			mySet5.setText(bgName[map.get(4)]);
		}
		if (!map.get(5).equals(99)) {
			mySet6.setText(bgName[map.get(5)]);
		}
		if (!map.get(6).equals(99)) {
			mySet7.setText(bgName[map.get(6)]);
		}

		/* 初始化Button对象 */
		setButton1 = (Button) findViewById(R.id.setButton1);
		setButton2 = (Button) findViewById(R.id.setButton2);
		setButton3 = (Button) findViewById(R.id.setButton3);
		setButton4 = (Button) findViewById(R.id.setButton4);
		setButton5 = (Button) findViewById(R.id.setButton5);
		setButton6 = (Button) findViewById(R.id.setButton6);
		setButton7 = (Button) findViewById(R.id.setButton7);
		/* 以initButton()来设置OnClickListener */
		setButton1 = initButton(setButton1, mySet1, 0);
		setButton2 = initButton(setButton2, mySet2, 1);
		setButton3 = initButton(setButton3, mySet3, 2);
		setButton4 = initButton(setButton4, mySet4, 3);
		setButton5 = initButton(setButton5, mySet5, 4);
		setButton6 = initButton(setButton6, mySet6, 5);
		setButton7 = initButton(setButton7, mySet7, 6);

		/* 设置启动服务的Button */
		mButton1 = (Button) findViewById(R.id.myButton1);
		mButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				/* 取得服务启动后一天的0点0分0秒的millsTime */
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, 1);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				long startTime = calendar.getTimeInMillis();
				/* 重复运行的间隔时间 */
				long repeatTime = 24 * 60 * 60 * 1000;
				/* 将更换桌布的排程添加AlarmManager中 */
				Intent intent = new Intent(ScreenActivity.this,
						MyReceiver.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						ScreenActivity.this, 0, intent, 0);
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				/*
				 * setRepeating()可让排程重复运行 startTime为开始运行时间 repeatTime为重复运行间隔
				 * AlarmManager.RTC可使服务休眠时仍然会运行
				 */
				am.setRepeating(AlarmManager.RTC, startTime, repeatTime, sender);
				/* 以Toast提示已启动 */
				Toast.makeText(ScreenActivity.this, "服务已启动", Toast.LENGTH_SHORT)
						.show();
				/* 启动后马上先运行一次换桌布的程序以更换今天的桌布 */
				Intent i = new Intent(ScreenActivity.this, ChangeBgImage.class);
				startActivity(i);
			}
		});

		/* 设置终止服务的Button */
		mButton2 = (Button) findViewById(R.id.myButton2);
		mButton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ScreenActivity.this,
						MyReceiver.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						ScreenActivity.this, 0, intent, 0);
				/* 由AlarmManager中删除调度 */
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(sender);
				/* 以Toast提示已终止 */
				Toast.makeText(ScreenActivity.this, "服务已终止", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	/* 由数据库中取得设置值的方法 */
	private void initSettingData() {
		map = new LinkedHashMap<Integer, Integer>();
		db = new DailyBgDB(ScreenActivity.this);
		Cursor cur = db.select();
		while (cur.moveToNext()) {
			map.put(cur.getInt(0), cur.getInt(1));
		}
		cur.close();
		db.close();
	}

	/* 设置Button的OnClickListener的方法 */
	private Button initButton(Button b, final TextView t, final int id) {
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				/* 设置点击Button后跳出的选择图片的Dialog */
				new AlertDialog.Builder(ScreenActivity.this)
						.setIcon(R.drawable.pic_icon)
						.setTitle("请选择图片！")
						.setSingleChoiceItems(bgName, map.get(id),
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										tmpWhich = which;
										/* 选择图片后跳出预览图文件的窗口 */
										View view = inflater.inflate(
												R.layout.preview, null);
										TextView message = (TextView) view
												.findViewById(R.id.bgName);
										/* 设置预览画面的文件名与ImageView */
										message.setText(bgName[which]);
										ImageView mView01 = (ImageView) view
												.findViewById(R.id.bgImage);
										mView01.setImageResource(bg[which]);

										Toast toast = Toast.makeText(
												ScreenActivity.this, "",
												Toast.LENGTH_SHORT);
										toast.setView(view);
										toast.show();
									}
								})
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which1) {
										/* 改变画面显示的设置图文件文件名 */
										t.setText(bgName[tmpWhich]);
										/* 改变map里的值 */
										map.put(id, tmpWhich);
										/* 将更改的设置存入数据库 */
										saveData(id, tmpWhich);
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
			}
		});
		return b;
	}

	/* 存储设置值至DB的方法 */
	private void saveData(int id, int value) {
		db = new DailyBgDB(ScreenActivity.this);
		db.update(id, value);
		db.close();
	}
}
