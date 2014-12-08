package com.sid.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpTest {

	/**
	 * @param args
	 */
	public void httpTest() {
		//post方式提交
		try {
			NameValuePair nameValuePair1 = new BasicNameValuePair("name", "zhangsan");
			NameValuePair nameValuePair2 = new BasicNameValuePair("age", "12");
			List<NameValuePair> lists = new ArrayList<NameValuePair>();
			lists.add(nameValuePair2);
			lists.add(nameValuePair1);
			HttpEntity httpEntity = new UrlEncodedFormEntity(lists);
			HttpPost post = new HttpPost("http://www.baidu.com");
			post.setEntity(httpEntity);
			HttpClient client = new DefaultHttpClient();
			client.execute(post);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//get方式提交
		String url = "http://www.baidu.com?name=aaa&age=12";

		HttpGet get = new HttpGet("http://www.baidu.com");
		HttpClient client = new DefaultHttpClient();
		InputStream in = null;
		try {
			HttpResponse response =  client.execute(get);
			HttpEntity entity =  response.getEntity();
			in = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			String result = "";
			while((line=reader.readLine())!=null){
				result +=line;
			}
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
