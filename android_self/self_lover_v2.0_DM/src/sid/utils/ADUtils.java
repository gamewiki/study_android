package sid.utils;

import sid.lover.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import cn.domob.android.ads.DomobAdEventListener;
import cn.domob.android.ads.DomobAdManager.ErrorCode;
import cn.domob.android.ads.DomobAdView;
import cn.domob.android.ads.DomobInterstitialAd;
import cn.domob.android.ads.DomobInterstitialAdListener;

public class ADUtils {
	static DomobInterstitialAd mInterstitialAd;
	static Button mInterstitialBtn;
	static RelativeLayout mAdContainer;
	static DomobAdView mAdview320x50;

	static final String domob_id="56OJzpH4uNFecd0DO+";
	static final String domob_interstital="16TLmv5lApN74NUkupS9BFQz";
	static final String domob_feeds="16TLmv5lApN74NUkucGh9j9k";
	static final String domob_bar="16TLmv5lApN74NUku1EAjlts";
	

	public static void initInterAD(Context context) {
		mInterstitialAd = new DomobInterstitialAd(context, domob_id,
				domob_interstital, DomobInterstitialAd.INTERSITIAL_SIZE_300X250);
		
		mInterstitialAd.setInterstitialAdListener(new DomobInterstitialAdListener() {
			@Override
			public void onInterstitialAdReady() {
				Log.i("DomobSDKDemo", "onAdReady");
			}

			@Override
			public void onLandingPageOpen() {
				Log.i("DomobSDKDemo", "onLandingPageOpen");
			}

			@Override
			public void onLandingPageClose() {
				Log.i("DomobSDKDemo", "onLandingPageClose");
			}

			@Override
			public void onInterstitialAdPresent() {
				Log.i("DomobSDKDemo", "onInterstitialAdPresent");
			}

			@Override
			public void onInterstitialAdDismiss() {
				// Request new ad when the previous interstitial ad was closed.
				mInterstitialAd.loadInterstitialAd();
				Log.i("DomobSDKDemo", "onInterstitialAdDismiss");
			}

			@Override
			public void onInterstitialAdFailed(ErrorCode arg0) {
				Log.i("DomobSDKDemo", "onInterstitialAdFailed");				
			}

			@Override
			public void onInterstitialAdLeaveApplication() {
				Log.i("DomobSDKDemo", "onInterstitialAdLeaveApplication");
				
			}

			@Override
			public void onInterstitialAdClicked(DomobInterstitialAd arg0) {
				Log.i("DomobSDKDemo", "onInterstitialAdClicked");
			}
		});
		
		mInterstitialAd.loadInterstitialAd();
		
		mInterstitialAd.showInterstitialAd(context);
	}

	public static void initBar(final Context context,Activity activity){

		mAdContainer = (RelativeLayout) activity.findViewById(R.id.adcontainer);
		// Create ad view
		mAdview320x50 = new DomobAdView(context, domob_id, domob_bar, DomobAdView.INLINE_SIZE_320X50);
		mAdview320x50.setKeyword("game");
		mAdview320x50.setUserGender("male");
		mAdview320x50.setUserBirthdayStr("2000-08-08");
		mAdview320x50.setUserPostcode("123456");

		mAdview320x50.setAdEventListener(new DomobAdEventListener() {
						
			@Override
			public void onDomobAdReturned(DomobAdView adView) {
				Log.i("DomobSDKDemo", "onDomobAdReturned");				
			}

			@Override
			public void onDomobAdOverlayPresented(DomobAdView adView) {
				Log.i("DomobSDKDemo", "overlayPresented");
			}

			@Override
			public void onDomobAdOverlayDismissed(DomobAdView adView) {
				Log.i("DomobSDKDemo", "Overrided be dismissed");				
			}

			@Override
			public void onDomobAdClicked(DomobAdView arg0) {
				Log.i("DomobSDKDemo", "onDomobAdClicked");				
			}

			@Override
			public void onDomobAdFailed(DomobAdView arg0, ErrorCode arg1) {
				Log.i("DomobSDKDemo", "onDomobAdFailed");				
			}

			@Override
			public void onDomobLeaveApplication(DomobAdView arg0) {
				Log.i("DomobSDKDemo", "onDomobLeaveApplication");				
			}

			@Override
			public Context onDomobAdRequiresCurrentContext() {
				return context;
			}
		});
		RelativeLayout.LayoutParams layout=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mAdview320x50.setLayoutParams(layout);
		mAdContainer.addView(mAdview320x50);
	}
}
