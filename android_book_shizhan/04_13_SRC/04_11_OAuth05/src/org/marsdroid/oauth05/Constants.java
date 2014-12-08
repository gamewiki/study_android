package org.marsdroid.oauth05;


public class Constants {

	public static final String CONSUMER_KEY 	= "99e9494ff07e42489f4ace16b63e1f47";
	public static final String CONSUMER_SECRET 	= "154f6f9ab4c1cf527f8ad8ab1f8e1ec9";

	public static final String REQUEST_URL 		= "https://open.t.qq.com/cgi-bin/request_token";
	public static final String ACCESS_URL 		= "https://open.t.qq.com/cgi-bin/access_token";  
	public static final String AUTHORIZE_URL 	= "https://open.t.qq.com/cgi-bin/authorize";
	
	public static final String ENCODING 		= "UTF-8";
	
	public static final String	OAUTH_CALLBACK_SCHEME	= "x-oauthflow";
	public static final String	OAUTH_CALLBACK_HOST		= "callback";
	public static final String	OAUTH_CALLBACK_URL		= OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;
	class WeiBoApi{
		//主页时间线
		public static final String HOME_TIMELINE = "http://open.t.qq.com/api/statuses/home_timeline";
		//我发表的时间线
		public static final String BROADCAST_TIMELINE = "http://open.t.qq.com/api/statuses/broadcast_timeline";
		//@到我的时间线
		public static final String MENTIONS_TIMELINE = "http://open.t.qq.com/api/statuses/mentions_timeline";
		//发表一条新微博
		public static final String ADD = "http://open.t.qq.com/api/t/add";
		//删除一条微博
		public static final String DEL = "http://open.t.qq.com/api/t/del";
		
		
	}
}
