package com.sid.user.location;

import java.util.List;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LocationActivity extends Activity {
	private Button all = null;
	private Button best = null;
	private Button location = null;
	private LocationManager locationManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        all = (Button) findViewById(R.id.allButton);
        best = (Button) findViewById(R.id.bestButton);
        all.setOnClickListener(new MyAllListener());
        best.setOnClickListener(new MyBestListener());
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        

        location = (Button) findViewById(R.id.locationButton);
        location.setOnClickListener(new MyLocationListener());
    }
    class MyAllListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			String ps = "";
			//获取所有provider
			List<String> providers = locationManager.getAllProviders();
			for (String provider : providers) {
				System.out.println(provider);
				ps = ps + " " + provider;
			}
			Toast.makeText(LocationActivity.this, "所有provider："+ps , Toast.LENGTH_LONG).show();
			
		}
    }
    class MyBestListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//用于查询的对象
			Criteria criteria = new Criteria();
			//设置精度是个整型常量FINE：精度高；
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			//设置电量消耗级别
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			//是否允许产生费用
			criteria.setCostAllowed(false);
			//是否需要测试海拔
			criteria.setAltitudeRequired(false);
			//设置是否需要速度
			criteria.setSpeedRequired(false);
//			//是否需要方向信息
//			criteria.setBearingRequired(false);
			//false表示：不论是否当前的provider是否处于打开状态
			//true表示：在打开的provider中寻找最合适的
			String provider = locationManager.getBestProvider(criteria, false);
			System.out.println("最佳的provider："+provider);
			Toast.makeText(LocationActivity.this, "最佳的provider："+provider , Toast.LENGTH_LONG).show();
		}
    }
    class MyLocationListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//minTime：单位是毫秒；两次更新用户位置的最短时间
			//minDistance：单位是米；两次更新用户位置的最短距离
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5000, new TestLoactionListener());
		}
    }
    
    class TestLoactionListener implements LocationListener{
		@Override
		public void onLocationChanged(Location location) {
			Toast.makeText(LocationActivity.this,"返回经度:"+location.getLongitude()+"返回纬度:"+location.getLatitude(), Toast.LENGTH_LONG).show();
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
    }
}
