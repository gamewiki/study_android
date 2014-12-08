package com.sid.orientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class OrientationActivity extends Activity implements SensorEventListener{

	private SensorManager sensorManager;
	/** 方向夹角*/
	private Sensor mOrientation;
	/** 与脑袋的距离*/
	private Sensor mProximity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_orientation, menu);
        return true;
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
//		float azimuth_angle = event.values[0];
//		float pitch_angle = event.values[1];
//		float roll_angle = event.values[2];
//
//		System.out.println("azimuth_angle------>"+azimuth_angle);
//		System.out.println("pitch_angle------>"+pitch_angle);
//		System.out.println("roll_angle------>"+roll_angle);
		

		float proximity = event.values[0];
		System.out.println("proximity------>"+proximity);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		sensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}
