package org.marsdroid.oauth03;

import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.marsdroid.oauth03.utils.ApacheUtils;
import org.marsdroid.oauth03.utils.HttpUtils;
import org.marsdroid.oauth03.utils.OAuthUtils;
import org.marsdroid.oauth03.utils.StringUtils;

public class WeiBoClient {
	private OAuthConsumer consumer;
	public WeiBoClient(){
		
	}
	
	public WeiBoClient(String consumerKey,String consumerSecret,String oauthToken,String oauthTokenSecret){
		//生成一个OAuthConsumer对象
		consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
		//设置OAuth_Token和OAuth_Token_Secret
		consumer.setTokenWithSecret(oauthToken, oauthTokenSecret);
	}
	public String doPost(String url,Map<String,String> addtionalParams,List<String> decodeNames){
		//生成一个HttpPost对象
		HttpPost postRequest = new HttpPost(url);
		consumer = OAuthUtils.addAddtionalParametersFromMap(consumer, addtionalParams);
		try {
			consumer.sign(postRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Header oauthHeader = postRequest.getFirstHeader("Authorization");
		System.out.println(oauthHeader.getValue());
		String baseString = oauthHeader.getValue().substring(5).trim();
		Map<String,String> oauthMap = StringUtils.parseMapFromString(baseString);
		oauthMap = HttpUtils.decodeByDecodeNames(decodeNames, oauthMap);
		addtionalParams = HttpUtils.decodeByDecodeNames(decodeNames, addtionalParams);
		List<NameValuePair> pairs = ApacheUtils.convertMapToNameValuePairs(oauthMap);
		List<NameValuePair> weiboPairs = ApacheUtils.convertMapToNameValuePairs(addtionalParams);
		pairs.addAll(weiboPairs);
		
		HttpEntity entity = null;
		HttpResponse response = null;
		try {
			entity = new UrlEncodedFormEntity(pairs);
			postRequest.setEntity(entity);
			response = new DefaultHttpClient().execute(postRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String result = ApacheUtils.getResponseText(response); 
		
		return result;
	}
}
