package org.marsdroid.oauth02;


public class Constants {

	//腾讯所分配的APP_KEY
	public static final String CONSUMER_KEY 	= "99e9494ff07e42489f4ace16b63e1f47";
	//腾讯所分配的APP_SECRET
	public static final String CONSUMER_SECRET 	= "154f6f9ab4c1cf527f8ad8ab1f8e1ec9";
	//用于获取未授权的request token
	public static final String REQUEST_URL 		= "https://open.t.qq.com/cgi-bin/request_token";
	//用于获取access token
	public static final String ACCESS_URL 		= "https://open.t.qq.com/cgi-bin/access_token";
	//用于对未授权的request token进行授权
	public static final String AUTHORIZE_URL 	= "https://open.t.qq.com/cgi-bin/authorize";
	
	public static final String ENCODING 		= "UTF-8";
	
	public static final String	OAUTH_CALLBACK_SCHEME	= "x-oauthflow";
	public static final String	OAUTH_CALLBACK_HOST		= "callback";
	//回调地址
	public static final String	OAUTH_CALLBACK_URL		= OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;

}
