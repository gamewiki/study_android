package com.sid.shock;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ShockActivity extends Activity {
	private Vibrator mVibrator01;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shock);

		/* 设置ToggleButton的对象 */
		mVibrator01 = (Vibrator) getApplication().getSystemService(
				Service.VIBRATOR_SERVICE);

		final ToggleButton mtogglebutton1 = (ToggleButton) findViewById(R.id.myTogglebutton1);

		final ToggleButton mtogglebutton2 = (ToggleButton) findViewById(R.id.myTogglebutton2);

		final ToggleButton mtogglebutton3 = (ToggleButton) findViewById(R.id.myTogglebutton3);

		/* 短震动 */
		mtogglebutton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mtogglebutton1.isChecked()) {
					/* 设置震动的周期 */
					mVibrator01.vibrate(new long[] { 100, 10, 100, 1000 }, -1);
					/* 用Toast显示震动启动 */
					Toast.makeText(ShockActivity.this,
							getString(R.string.str_ok), Toast.LENGTH_SHORT)
							.show();
				} else {
					/* 取消震动 */
					mVibrator01.cancel();
					/* 用Toast显示震动已被取消 */
					Toast.makeText(ShockActivity.this,
							getString(R.string.str_end), Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		/* 长震动 */
		mtogglebutton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mtogglebutton2.isChecked()) {
					/* 设置震动的周期 */
					mVibrator01.vibrate(new long[] { 100, 100, 100, 1000 }, 0);

					/* 用Toast显示震动启动 */
					Toast.makeText(ShockActivity.this,
							getString(R.string.str_ok), Toast.LENGTH_SHORT)
							.show();
				} else {
					/* 取消震动 */
					mVibrator01.cancel();

					/* 用Toast显示震动取消 */
					Toast.makeText(ShockActivity.this,
							getString(R.string.str_end), Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		/* 节奏震动 */
		mtogglebutton3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mtogglebutton3.isChecked()) {
					/* 设置震动的周期 */
					mVibrator01.vibrate(
							new long[] { 1000, 50, 1000, 50, 1000 }, 0);

					/* 用Toast显示震动启动 */
					Toast.makeText(ShockActivity.this,
							getString(R.string.str_ok), Toast.LENGTH_SHORT)
							.show();
				} else {
					/* 取消震动 */
					mVibrator01.cancel();
					/* 用Toast显示震动取消 */
					Toast.makeText(ShockActivity.this,
							getString(R.string.str_end), Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}
}
