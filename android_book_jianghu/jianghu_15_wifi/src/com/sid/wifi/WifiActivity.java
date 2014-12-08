package com.sid.wifi;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * WifiManager.WIFI_STATE_ENABLED：已经打开无法再打开 WifiManager.WIFI_STATE_ENABLING：已经打开
 * WifiManager.WIFI_STATE_DISABLING：正在关闭而无法再关闭
 * WifiManager.WIFI_STATE_DISABLED：已经关闭 WifiManager.WIFI_STATE_UNKNOWN：无法识别
 * 
 * @author Administrator
 * 
 */
public class WifiActivity extends Activity {
	private TextView mTextView01;
	private CheckBox mCheckBox01;

	/* 创建WiFiManager对象 */
	private WifiManager mWiFiManager01;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi);

		mTextView01 = (TextView) findViewById(R.id.myTextView1);
		mCheckBox01 = (CheckBox) findViewById(R.id.myCheckBox1);

		/* 以getSystemService取得WIFI_SERVICE */
		mWiFiManager01 = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);

		/* 判断运行程序后的WiFi状态是否打开或打开中 */
		if (mWiFiManager01.isWifiEnabled()) {
			/* 判断WiFi状态是否“已打开” */
			if (mWiFiManager01.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
				/* 若WiFi已打开，将选取项打勾 */
				mCheckBox01.setChecked(true);
				/* 更改选取项文字为关闭WiFi */
				mCheckBox01.setText(R.string.str_uncheck);
			} else {
				/* 若WiFi未打开，将选取项勾选择消 */
				mCheckBox01.setChecked(false);
				/* 更改选取项文字为打开WiFi */
				mCheckBox01.setText(R.string.str_checked);
			}
		} else {
			mCheckBox01.setChecked(false);
			mCheckBox01.setText(R.string.str_checked);
		}

		/* 捕捉CheckBox的点击事件 */
		mCheckBox01.setOnClickListener(new CheckBox.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* 当选取项为取消选取状态 */
				if (mCheckBox01.isChecked() == false) {
					/* 尝试关闭Wi-Fi服务 */
					try {
						/* 判断WiFi状态是否为已打开 */
						if (mWiFiManager01.isWifiEnabled()) {
							/* 关闭WiFi */
							if (mWiFiManager01.setWifiEnabled(false)) {
								mTextView01
										.setText(R.string.str_stop_wifi_done);
							} else {
								mTextView01
										.setText(R.string.str_stop_wifi_failed);
							}
						} else {
							/* WiFi状态不为已打开状态时 */
							switch (mWiFiManager01.getWifiState()) {
							/* WiFi正在打开过程中，导致无法关闭... */
							case WifiManager.WIFI_STATE_ENABLING:
								mTextView01.setText(getResources().getText(
										R.string.str_stop_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_enabling));
								break;
							/* WiFi正在关闭过程中，导致无法关闭... */
							case WifiManager.WIFI_STATE_DISABLING:
								mTextView01.setText(getResources().getText(
										R.string.str_stop_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_disabling));
								break;
							/* WiFi已经关闭 */
							case WifiManager.WIFI_STATE_DISABLED:
								mTextView01.setText(getResources().getText(
										R.string.str_stop_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_disabled));
								break;
							/* 无法取得或辨识WiFi状态 */
							case WifiManager.WIFI_STATE_UNKNOWN:
							default:
								mTextView01.setText(getResources().getText(
										R.string.str_stop_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_unknow));
								break;
							}
							mCheckBox01.setText(R.string.str_checked);
						}
					} catch (Exception e) {
						Log.i("HIPPO", e.toString());
						e.printStackTrace();
					}
				} else if (mCheckBox01.isChecked() == true) {
					/* 尝试打开Wi-Fi服务 */
					try {
						/* 确认WiFi服务是关闭且不在打开作业中 */
						if (!mWiFiManager01.isWifiEnabled()
								&& mWiFiManager01.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
							if (mWiFiManager01.setWifiEnabled(true)) {
								switch (mWiFiManager01.getWifiState()) {
								/* WiFi正在打开过程中，导致无法打开... */
								case WifiManager.WIFI_STATE_ENABLING:
									mTextView01.setText(getResources().getText(
											R.string.str_wifi_enabling));
									break;
								/* WiFi已经为打开，无法再次打开... */
								case WifiManager.WIFI_STATE_ENABLED:
									mTextView01.setText(getResources().getText(
											R.string.str_start_wifi_done));
									break;
								/* 其它未知的错误 */
								default:
									mTextView01.setText(getResources().getText(
											R.string.str_start_wifi_failed)
											+ ":"
											+ getResources().getText(
													R.string.str_wifi_unknow));
									break;
								}
							} else {
								mTextView01
										.setText(R.string.str_start_wifi_failed);
							}
						} else {
							switch (mWiFiManager01.getWifiState()) {
							/* WiFi正在打开过程中，导致无法打开... */
							case WifiManager.WIFI_STATE_ENABLING:
								mTextView01.setText(getResources().getText(
										R.string.str_start_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_enabling));
								break;
							/* WiFi正在关闭过程中，导致无法打开... */
							case WifiManager.WIFI_STATE_DISABLING:
								mTextView01.setText(getResources().getText(
										R.string.str_start_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_disabling));
								break;
							/* WiFi已经关闭 */
							case WifiManager.WIFI_STATE_DISABLED:
								mTextView01.setText(getResources().getText(
										R.string.str_start_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_disabled));
								break;
							/* 无法取得或识别WiFi状态 */
							case WifiManager.WIFI_STATE_UNKNOWN:
							default:
								mTextView01.setText(getResources().getText(
										R.string.str_start_wifi_failed)
										+ ":"
										+ getResources().getText(
												R.string.str_wifi_unknow));
								break;
							}
						}
						mCheckBox01.setText(R.string.str_uncheck);
					} catch (Exception e) {
						Log.i("HIPPO", e.toString());
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void mMakeTextToast(String str, boolean isLong) {
		if (isLong == true) {
			Toast.makeText(WifiActivity.this, str, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(WifiActivity.this, str, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		/* 在onResume重写事件为取得打开程序当下WiFi的状态 */
		try {
			switch (mWiFiManager01.getWifiState()) {
			/* WiFi已经在打开状态... */
			case WifiManager.WIFI_STATE_ENABLED:
				mTextView01.setText(getResources().getText(
						R.string.str_wifi_enabling));
				break;
			/* WiFi正在打开过程中状态... */
			case WifiManager.WIFI_STATE_ENABLING:
				mTextView01.setText(getResources().getText(
						R.string.str_wifi_enabling));
				break;
			/* WiFi正在关闭过程中... */
			case WifiManager.WIFI_STATE_DISABLING:
				mTextView01.setText(getResources().getText(
						R.string.str_wifi_disabling));
				break;
			/* WiFi已经关闭 */
			case WifiManager.WIFI_STATE_DISABLED:
				mTextView01.setText(getResources().getText(
						R.string.str_wifi_disabled));
				break;
			/* 无法取得或识别WiFi状态 */
			case WifiManager.WIFI_STATE_UNKNOWN:
			default:
				mTextView01.setText(getResources().getText(
						R.string.str_wifi_unknow));
				break;
			}
		} catch (Exception e) {
			mTextView01.setText(e.toString());
			e.getStackTrace();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}
