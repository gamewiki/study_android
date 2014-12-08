package com.sid.screen;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenActivity extends Activity {
	private TextView mTextView01;
	private Button mButton01;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen);

		mButton01 = (Button) findViewById(R.id.myButton1);
		mTextView01 = (TextView) findViewById(R.id.myTextView1);

		if (getRequestedOrientation() == -1) {
			mTextView01.setText(getResources().getText(R.string.str_err_1001));
		}

		/* 当点击按钮旋转屏幕画面 */
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 方法一：重写getRequestedOrientation */

				System.out.println("获取方向值："+getRequestedOrientation());
				/* 若无法取得screenOrientation属性 */
				if (getRequestedOrientation() == -1) {
					/* 提示无法进行画面旋转功能，因无法判别Orientation */
					mTextView01.setText(getResources().getText(
							R.string.str_err_1001));
				} else {
					if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
						/* 若当下为横排，则更改为竖排呈现 */
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					} else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
						/* 若当下为竖排，则更改为横排呈现 */
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					}
				}
			}
		});
	}

	@Override
	public void setRequestedOrientation(int requestedOrientation) {
		/* 判断要更改的方向，以Toast提示 */
		switch (requestedOrientation) {
		/* 更改为LANDSCAPE */
		case (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE):
			mMakeTextToast(
					getResources().getText(R.string.str_msg1).toString(), false);
			break;
		/* 更改为PORTRAIT */
		case (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT):
			mMakeTextToast(
					getResources().getText(R.string.str_msg2).toString(), false);
			break;
		}
		super.setRequestedOrientation(requestedOrientation);
	}

	@Override
	public int getRequestedOrientation() {
		/* 此重写getRequestedOrientation方法，可取得当下屏幕的方向 */
		return super.getRequestedOrientation();
	}

	public void mMakeTextToast(String str, boolean isLong) {
		if (isLong == true) {
			Toast.makeText(ScreenActivity.this, str, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(ScreenActivity.this, str, Toast.LENGTH_SHORT).show();
		}
	}
}
