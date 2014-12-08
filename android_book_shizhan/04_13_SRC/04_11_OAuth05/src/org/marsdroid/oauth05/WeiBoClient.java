package org.marsdroid.oauth05;

import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.marsdroid.utils.ApacheUtils;
import org.marsdroid.utils.HttpUtils;
import org.marsdroid.utils.OAuthUtils;
import org.marsdroid.utils.StringUtils;
import org.marsdroid.utils.UrlUtils;

public class WeiBoClient {
	private OAuthConsumer consumer;

	public WeiBoClient() {

	}

	public WeiBoClient(String consumerKey, String consumerSecret,
			String oauthToken, String oauthTokenSecret) {
		// ����һ��OAuthConsumer����
		consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
		// ����OAuth_Token��OAuth_Token_Secret
		consumer.setTokenWithSecret(oauthToken, oauthTokenSecret);
	}

	public String doGet(String url, Map<String, String> addtionalParams) {
		String result = null;
		url = UrlUtils.buildUrlByQueryStringMapAndBaseUrl(url, addtionalParams);
		String signedUrl = null;
		try {
			System.out.println("ǩ��֮ǰ��URL--->" + url);
			signedUrl = consumer.sign(url);
			System.out.println("ǩ��֮���URL--->" + signedUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpGet getRequest = new HttpGet(signedUrl);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = ApacheUtils.parseStringFromEntity(response.getEntity());
		return result;
	}

	public String doPost(String url, Map<String, String> addtionalParams,
			List<String> decodeNames) {
		// ����һ��HttpPost����
		HttpPost postRequest = new HttpPost(url);
		consumer = OAuthUtils.addAddtionalParametersFromMap(consumer,
				addtionalParams);
		try {
			consumer.sign(postRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Header oauthHeader = postRequest.getFirstHeader("Authorization");
		System.out.println(oauthHeader.getValue());
		String baseString = oauthHeader.getValue().substring(5).trim();
		Map<String, String> oauthMap = StringUtils
				.parseMapFromString(baseString);
		oauthMap = HttpUtils.decodeByDecodeNames(decodeNames, oauthMap);
		addtionalParams = HttpUtils.decodeByDecodeNames(decodeNames,
				addtionalParams);
		List<NameValuePair> pairs = ApacheUtils
				.convertMapToNameValuePairs(oauthMap);
		List<NameValuePair> weiboPairs = ApacheUtils
				.convertMapToNameValuePairs(addtionalParams);
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
