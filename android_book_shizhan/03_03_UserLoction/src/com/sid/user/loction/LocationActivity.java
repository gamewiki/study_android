package com.sid.user.loction;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LocationActivity extends Activity {
	private Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        button = (Button) findViewById(R.id.localtionButton);
        button.setOnClickListener(new MyListener());
    }

    class MyListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			LocationManager locationManager = (LocationManager) LocationActivity.this.getSystemService(LOCATION_SERVICE);
			//provider(提供的定位方式)：GPS_PROVIDER或者NETWORK
			//参数：minTime：发出通知的间隔的最小时间；不是准确值，会进行浮动
			//参数：minDistance：两次location定位直接的最小距离；单位是米
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new TestLocationListener());
		}
    	
    }
    
    /**
     * 监听根据卫星定位来进行经纬度的通知来进行相关的操作
     * @author Administrator
     *
     */
    private class TestLocationListener implements LocationListener{
		/**
		 * 当位置改变时
		 */
		@Override
		public void onLocationChanged(Location location) {
			System.out.println("返回经度:"+location.getLongitude());
			System.out.println("返回纬度:"+location.getLatitude());
		}
		/**
		 * 当数据提供者不能使用时
		 */
		@Override
		public void onProviderDisabled(String provider) {
		}
		/**
		 * 当数据提供者能使用时
		 */
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
    }
}
