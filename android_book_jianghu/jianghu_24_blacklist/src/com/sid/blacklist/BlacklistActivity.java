package com.sid.blacklist;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BlacklistActivity extends Activity {
	private TextView mTextView01;
	private TextView mTextView03;
	private EditText mEditText1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blacklist);

		/* 设置PhoneCallListener */
		mPhoneCallListener phoneListener = new mPhoneCallListener();
		/* 用TelephonyManager抓取Telephony Severice */
		TelephonyManager telMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		/* 设置Listen Call */
		telMgr.listen(phoneListener, mPhoneCallListener.LISTEN_CALL_STATE);

		/* 查找TextViewEditText */
		mTextView01 = (TextView) findViewById(R.id.myTextView1);
		mTextView03 = (TextView) findViewById(R.id.myTextView3);
		mEditText1 = (EditText) findViewById(R.id.myEditText1);

	}

	/* 判断PhoneStateListener当前状态 */
	public class mPhoneCallListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			switch (state) {
			/*获取手机待机状态 */
			case TelephonyManager.CALL_STATE_IDLE:
				mTextView01.setText(R.string.str_CALL_STATE_IDLE);

				try {
					AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
					if (audioManager != null) {
						/*设置手机为待机时响铃正常 */
						audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
						audioManager.getStreamVolume(AudioManager.STREAM_RING);
					}
				} catch (Exception e) {
					mTextView01.setText(e.toString());
					e.printStackTrace();
				}
				break;

			/* 获取手机状态为通话中*/
			case TelephonyManager.CALL_STATE_OFFHOOK:
				mTextView01.setText(R.string.str_CALL_STATE_OFFHOOK);
				break;

			/* 获取手机状态为来点 */
			case TelephonyManager.CALL_STATE_RINGING:
				/* 显示来电*/
				mTextView01.setText(getResources().getText(
						R.string.str_CALL_STATE_RINGING)
						+ incomingNumber);

				/* 判断输入电话是否一致，一致时用静音*/
				if (incomingNumber.equals(mTextView03.getText().toString())) {
					try {
						AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						if (audioManager != null) {
							/* 设置响铃为静音*/
							audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
							audioManager.getStreamVolume(AudioManager.STREAM_RING);
							Toast.makeText(BlacklistActivity.this,
									getString(R.string.str_msg),
									Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						mTextView01.setText(e.toString());
						e.printStackTrace();
						break;
					}
				}
			}

			super.onCallStateChanged(state, incomingNumber);

			mEditText1.setOnKeyListener(new EditText.OnKeyListener() {

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					/* 设置EditText的输入数据显示在TextView */
					mTextView03.setText(mEditText1.getText());
					return false;
				}
			});
		}
	}
}