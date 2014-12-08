package com.sid.screencolor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ColorActivity extends Activity {
	private boolean ifLocked = false;
	private PowerManager.WakeLock mWakeLock;
	private PowerManager mPowerManager;
	private LinearLayout mLinearLayout;
	/* 独几无又的menu选项identifier，用北识?事件 */
	static final private int M_CHOOSE = Menu.FIRST;
	static final private int M_EXIT = Menu.FIRST + 1;
	/* 颜色选单的颜色与文?阵在 */
	private int[] color = { R.drawable.white, R.drawable.blue, R.drawable.pink,
			R.drawable.green, R.drawable.orange, R.drawable.yellow };
	private int[] text = { R.string.str_white, R.string.str_blue,
			R.string.str_pink, R.string.str_green, R.string.str_orange,
			R.string.str_yellow };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* 必须?setContentView之前呼?回屏幕显示 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_color);

		/*
		 * ?Activity启动时将屏幕亮调整为最亮 否程序为SDK1.5的新叫能，仅?1.5环境兀适用
		 */
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.screenBrightness = 1.0f;
		getWindow().setAttributes(lp);

		/* 初始化mLinearLayout */
		mLinearLayout = (LinearLayout) findViewById(R.id.myLinearLayout1);

		/* 取得PowerManager */
		mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		/* 取得WakeLock */
		mWakeLock = mPowerManager.newWakeLock(
				PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "BackLight");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* menu群组ID */
		int idGroup1 = 0;
		/* menuItemID */
		int orderMenuItem1 = Menu.NONE;
		int orderMenuItem2 = Menu.NONE + 1;
		/* 建立menu */
		menu.add(idGroup1, M_CHOOSE, orderMenuItem1, R.string.str_title);
		menu.add(idGroup1, M_EXIT, orderMenuItem2, R.string.str_exit);
		menu.setGroupCheckable(idGroup1, true, true);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (M_CHOOSE):
			/* 跳叨选择背后颜色的AlertDialog */
			new AlertDialog.Builder(ColorActivity.this)
					.setTitle(getResources().getString(R.string.str_title))
					.setAdapter(new MyAdapter(this, color, text), listener1)
					.setPositiveButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
			break;
		case (M_EXIT):
			/* 离开程序 */
			this.finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/* 选择背后颜色的AlertDialog的OnClickListener */
	OnClickListener listener1 = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			/* 更改背景颜色 */
			mLinearLayout.setBackgroundResource(color[which]);
			/* 北Toast显示设定的颜色 */
			Toast.makeText(ColorActivity.this,
					getResources().getString(text[which]), Toast.LENGTH_LONG)
					.show();
		}
	};

	@Override
	protected void onResume() {
		/* onResume()时呼?wakeLock() */
		wakeLock();
		super.onResume();
	}

	@Override
	protected void onPause() {
		/* onPause()时呼?wakeUnlock() */
		wakeUnlock();
		super.onPause();
	}

	/* 唤起WakeLock的method */
	private void wakeLock() {
		if (!ifLocked) {
			ifLocked = true;
			mWakeLock.acquire();
		}
	}

	/* 释放WakeLock的method */
	private void wakeUnlock() {
		if (ifLocked) {
			mWakeLock.release();
			ifLocked = false;
		}
	}
}
