package com.sid.geocoding;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;

public class GeoActivity extends Activity {
	private String urlJSON = "http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=false";
	// private String urlXML =
	// "http://maps.googleapis.com/maps/api/geocode/xml?latlng=40.714224,-73.961452&sensor=false";
	// geoCoder方式存在问题；现在应用新的方式
	private String location = "SFO";
	private Button geoButton = null;
	private Button reverseGeoButton = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
		// 基于Geocoder查找
		geoButton = (Button) findViewById(R.id.geoButton);
		reverseGeoButton = (Button) findViewById(R.id.reverseGeoButton);
		geoButton.setOnClickListener(new MyGeoButton());
		reverseGeoButton.setOnClickListener(new MyReverseGeoButton());
    }
    

	/**
	 * 根据地址获取经纬度
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
	 *  根据经纬度获取地址
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
	 * 异步操作
	 * 根据经纬度获取地址
	 * @author Administrator
	 * 
	 */
	class ReverseGeocodingTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			return null;
		}
	}

	/**
	 * 获取当地的经纬度
	 * @author Administrator
	 * 
	 */
	class GeocodingTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			JSONObject josonObject = getLocationInfo(location);
			GeoPoint geoPoint = getGeoPoint(josonObject);
			System.out.println("lat------>"+geoPoint.getLatitudeE6());
			System.out.println("lon------>"+geoPoint.getLongitudeE6());
			return null;
		}
	}

	/**
	 * 根据经纬度获取地址
	 * 
	 * 这个方法和getLocationInfo是新的根据地址名或者经纬度来获取对应的信息的方法
	 * @param jsonObject
	 * @return
	 */
	public GeoPoint getGeoPoint(JSONObject jsonObject) {
		// 地点的纬度34.34567980
		Double lon = new Double(0);
		// 地点的经度113.68834870
		Double lat = new Double(0);
		try {
			lon = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location")
					.getDouble("lng");
			lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location")
					.getDouble("lat");// 上面这些参数你可以在http://maps.google.com/maps/api/geocode/json?address=平顶山学院ka&sensor=false中看到

		} catch (JSONException e) {
		} catch (Exception e) {
		}
		System.out.println(lat);
		System.out.println(lon);
		return new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
	}
	

	/**
	 * 根据地址获取经纬度
	 * 
	 * 这个方法和getGeoPoint是新的根据地址名或者经纬度来获取对应的信息的方法
	 * @param address
	 * @return
	 */
	public JSONObject getLocationInfo(String address) {// address是我所反转平顶山学院名字
		HttpGet httpGet = new HttpGet(
				"http://maps.google.com/maps/api/geocode/json?address="
						+ address + "&sensor=false");
		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			httpResponse = client.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			System.out.println("---"+httpEntity);
			InputStream inputStream = httpEntity.getContent();
			int b;
			while ((b = inputStream.read()) != -1) {
				stringBuffer.append((char) b);
			}
		} catch (ClientProtocolException e) {
		} catch (Exception e) {
		}
		JSONObject jsonObject = new JSONObject();
		try {
			System.out.println("str-----:"+stringBuffer.toString());
			jsonObject = new JSONObject(stringBuffer.toString());
		} catch (Exception e) {
		}
		return jsonObject;
	}

}
