package com.sid.localtion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.gson.Gson;

/**
 * 
 * 根据地址查询经纬度：
 * http://maps.googleapis.com/maps/api/geocode/json?address=SFO&sensor=false
 * 
 * 根据经纬度查询地址：
 * http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=false
 * 
 * bounds的作用
 * http://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&sensor=false
 * http://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&bounds
 * =34.172684,-118.604794|34.236144,-118.500938&sensor=false
 * 
 * region的作用
 * http://maps.googleapis.com/maps/api/geocode/json?address=Toledo&sensor=false
 * http://maps.googleapis.com/maps/api/geocode/json?address=Tole
 * 
 * @author Administrator
 * 
 */
public class LocationActivity extends Activity {
	private String urlJSON = "http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=false";
	// private String urlXML =
	// "http://maps.googleapis.com/maps/api/geocode/xml?latlng=40.714224,-73.961452&sensor=false";
	// geoCoder方式存在问题；现在应用新的方式
	private Button geoButton = null;
	private Button reverseGeoButton = null;
	private Button geowebButton = null;
	private Button reverseGeowebButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		// 基于Geocoder查找
		geoButton = (Button) findViewById(R.id.geoButton);
		reverseGeoButton = (Button) findViewById(R.id.reverseGeoButton);
		geoButton.setOnClickListener(new MyGeoButton());
		reverseGeoButton.setOnClickListener(new MyReverseGeoButton());

		// 基于web方式查找
		geowebButton = (Button) findViewById(R.id.geowebButton);
		reverseGeowebButton = (Button) findViewById(R.id.reverseGeowebButton);
		geowebButton.setOnClickListener(new MyGeowebButton());
		reverseGeowebButton.setOnClickListener(new MyReverseGeowebButton());
	}

	/**
	 * 通过web方式获得 web获取当地的经纬度
	 * 
	 * @author Administrator
	 * 
	 */
	class MyGeowebButton implements OnClickListener {
		@Override
		public void onClick(View v) {
			HttpClient httpClient = new DefaultHttpClient();
			String responseData = "";
			try {
				HttpResponse response = httpClient
						.execute(new HttpGet(urlJSON));
				HttpEntity entity = response.getEntity();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(entity.getContent()));
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					responseData = responseData + line;
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Gson gson = new Gson();
			TestResult testResult = gson.fromJson(responseData,
					TestResult.class);
			System.out.println(testResult);
		}
	}

	/**
	 * 通过web方式获得 web根据经纬度获取地址
	 * 
	 * @author Administrator
	 * 
	 */
	class MyReverseGeowebButton implements OnClickListener {
		@Override
		public void onClick(View v) {
		}
	}

	/**
	 * 通过Geocoder方式获取
	 * 
	 * @author Administrator
	 * 
	 */
	class MyGeoButton implements OnClickListener {
		@Override
		public void onClick(View v) {
			new GeocodingTask().execute();
		}
	}

	/**
	 * 通过Geocoder方式获取 根据经纬度获取地址
	 * 
	 * @author Administrator
	 * 
	 */
	class MyReverseGeoButton implements OnClickListener {
		@Override
		public void onClick(View v) {
			new ReverseGeocodingTask().execute();
		}
	}

	/**
	 * 异步操作 存在问题
	 * Geocoder根据经纬度获取地址
	 * @author Administrator
	 * 
	 */
	class ReverseGeocodingTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			try {
				Geocoder geocoder = new Geocoder(LocationActivity.this,
						Locale.US);
				// 参数：美国一个机场的简称；最大返回多少个结果
				List<Address> addresses = geocoder.getFromLocation(40.714224,
						-73.961452, 1);
				System.out.println(addresses.size());
				for (Address address : addresses) {
					System.out.println(address);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * 异步操作 存在问题
	 * Geocoder获取当地的经纬度
	 * @author Administrator
	 * 
	 */
	class GeocodingTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			try {
				Geocoder geocoder = new Geocoder(LocationActivity.this);
				List<Address> addresses = geocoder
						.getFromLocationName("SFO", 1);
				System.out.println(addresses.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
