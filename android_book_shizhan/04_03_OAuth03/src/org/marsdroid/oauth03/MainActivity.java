package org.marsdroid.oauth03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuth;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	final String TAG = getClass().getName();
	private SharedPreferences prefs;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Button launchOauth = (Button) findViewById(R.id.btn_launch_oauth);
        Button sendWeiBoButton = (Button)findViewById(R.id.btn_sendWeiBo);
 
        sendWeiBoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//收集需要向腾讯微博服务器端发送的数据
				Map<String,String> map = new HashMap<String,String>();
				map.put("content", "test");
				map.put("clientip", "127.0.0.1");
				map.put("format", "json");
				//URL编码
				List<String> decodeNames = new ArrayList<String>();
				decodeNames.add("oauth_signature");
				//生成WeiboClient对象需要四个参数:Consumer_key,Consumer_key_secret,Oauth_tokent,OAuth_token_secret
				String OAuth_token = prefs.getString(OAuth.OAUTH_TOKEN, "");
				String OAuth_token_secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
				WeiBoClient weiBoClient = new WeiBoClient(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, OAuth_token, OAuth_token_secret);
				weiBoClient.doPost("http://open.t.qq.com/api/t/add",map,decodeNames);
			}
		});
        
        
        launchOauth.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	startActivity(new Intent().setClass(v.getContext(), PrepareRequestTokenActivity.class));
            }
        });
	}
	
/*	private OAuthConsumer getConsumer(SharedPreferences prefs) {
		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		consumer.setTokenWithSecret(token, secret);
		return consumer;
	}
	private String doGet(String url,OAuthConsumer consumer,String format,int pageflag,int pagetime,int reqnum,int lastid){
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		Map<String,String> keyValues = new HashMap<String,String>();
		keyValues.put("format", format);
		keyValues.put("pageflag", pageflag + "");
		keyValues.put("pagetime", pagetime + "");
		keyValues.put("reqnum", reqnum + "");
		keyValues.put("lastid", lastid + "");
		url = UrlUtils.buildUrlByQueryStringMapAndBaseUrl(url, keyValues);
		String signedUrl = null;
		try {
			signedUrl = consumer.sign(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}*/
}