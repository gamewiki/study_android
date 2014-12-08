package org.marsdroid.oauth01;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.os.AsyncTask;

public class OAuthRequestTokenTask extends AsyncTask<Void, Void, Void>{

	private Context context;
	private OAuthConsumer consumer;
	private OAuthProvider provider;
	
	
	public OAuthRequestTokenTask(Context context, OAuthConsumer consumer,
			OAuthProvider provider) {
		super();
		this.context = context;
		this.consumer = consumer;
		this.provider = provider;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			System.out.println("请求Request Token之前" + consumer.getToken());
			//第一个参数是consumer对象；第二个参数是回调的地址（web需要）
			final String url = provider.retrieveRequestToken(consumer, "null");
			System.out.println("请求Request Toker之后" + consumer.getToken());
			System.out.println("url---->" + url);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
