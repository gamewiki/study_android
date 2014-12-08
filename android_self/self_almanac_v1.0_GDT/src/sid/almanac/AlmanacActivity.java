package sid.almanac;

import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;
import com.qq.e.ads.InterstitialAd;
import com.qq.e.ads.InterstitialAdListener;

public class AlmanacActivity extends Activity {
	private WebView webView;

	private AdView bannerAD;
	private RelativeLayout bannerContainer;
	private InterstitialAd iad;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_almanac);

		webView = (WebView) findViewById(R.id.webView);
		WebSettings webSettings = webView.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		// 限制在WebView中打开网页，而不用默认浏览器
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				return super.onJsAlert(view, url, message, result);
			}

		});
		webView.addJavascriptInterface(new WebAppInterface(this), "Android");
		webView.loadUrl("file:///android_asset/view.html");// 本地
		// webView.loadUrl("http://www.baidu.com");//远程
		// String htmldata="";//网页代码
		// String targeturl="";//目标网址（具体）
		// String baseurl="";//连接目标网址失败进入的默认网址
		// webView.getSettings().setDefaultTextEncodingName("GB2312");
		// webView.loadData(htmldata, "text/html", "utf-8");
		// webView.loadDataWithBaseURL(targeturl, htmldata, "text/html",
		// "utf-8", baseurl);
		this.bannerContainer = (RelativeLayout) this.findViewById(R.id.bannercontainer);
		this.showBannerAD();
		this.showInterstitialAd();

		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.showBannerAD();
	}

	private void showBannerAD() {
		this.bannerAD = new AdView(this, AdSize.BANNER, AppConstant.APPId,
				AppConstant.BannerPosId);
		this.bannerContainer.removeAllViews();
		this.bannerContainer.addView(bannerAD);
		AdRequest adRequest = new AdRequest();
		adRequest.setTestAd(true);
		adRequest.setShowCloseBtn(true);
		bannerAD.fetchAd(adRequest);
		bannerAD.setAdListener(new AdListener() {
			@Override
			public void onNoAd() {

			}

			@Override
			public void onBannerClosed() {
				bannerAD = null;
				bannerContainer.invalidate();
			}

			@Override
			public void onAdReceiv() {

			}
		});
	}
	private void showInterstitialAd() {
		iad = new InterstitialAd(this, AppConstant.APPId,
				AppConstant.InterteristalPosId);
		iad.setAdListener(new InterstitialAdListener() {
			@Override
			public void onBack() {
				iad.loadAd();
			}
			@Override
			public void onFail() {

			}
			@Override
			public void onAdReceive() {
				Log.i("admsg:", "receive ad pic");

				iad.show(AlmanacActivity.this);
			}
		});
		iad.loadAd();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about).setIcon(
				R.drawable.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit)
				.setIcon(R.drawable.exit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(AlmanacActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
