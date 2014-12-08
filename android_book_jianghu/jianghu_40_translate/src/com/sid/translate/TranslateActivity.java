package com.sid.translate;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

public class TranslateActivity extends Activity {
	private EditText myEditText1;
	private WebView myWebView1;
	private Handler mHandler01 = new Handler();
	private static final String LOG_TAG = "DEBUG";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translate);

		myEditText1 = (EditText) findViewById(R.id.myEditText1);
		myWebView1 = (WebView) findViewById(R.id.myWebView1);

		/* 取得WebSettings */
		WebSettings webSettings = myWebView1.getSettings();
		/* 设置可运行JavaScript */
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSaveFormData(false);
		webSettings.setSavePassword(false);
		webSettings.setSupportZoom(false);
		myWebView1.setWebChromeClient(new MyWebChromeClient());
		/* 设置给html调用的对象及名称 */
		myWebView1.addJavascriptInterface(new runJavaScript(), "irdc");
		/* 将assets/google_translate.html载入 */
		String url = "file:///android_asset/google_translate.html";
		myWebView1.loadUrl(url);
	}

	final class runJavaScript {
		public void runOnAndroidJavaScript() {
			mHandler01.post(new Runnable() {
				public void run() {
					if (myEditText1.getText().toString() != "") {
						/* 调用google_translate.html里的javascript */
						myWebView1.loadUrl("javascript:translate('"
								+ myEditText1.getText().toString() + "')");
					}
				}
			});
		}
	}

	/**
	 * 捕捉网页里的alert javascript作为.js调试之用，并输出至LogCat
	 */
	final class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			Log.d(LOG_TAG, message);
			return super.onJsAlert(view, url, message, result);
		}
	}
}