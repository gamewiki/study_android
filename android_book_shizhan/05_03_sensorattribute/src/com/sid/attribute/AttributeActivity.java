package com.sid.attribute;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class AttributeActivity extends Activity {
	private Button button ;
	private TextView text;
	private SensorManager sensorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute);
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textId);
        
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        Sensor liSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        
        sensorManager.registerListener(new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				String name = event.sensor.getName();
				//制造商
				String vendor = event.sensor.getVendor();
				//分辨率
				float resolution = event.sensor.getResolution();
				//功率
				float power = event.sensor.getPower();
				double timestamp = event.timestamp;
				text.setText("name------>"+name
						+"vendor------>"+vendor
						+"resolution------>"+resolution
						+"power------>"+power
						+"timestamp------>"+timestamp);
				System.out.println("name------>"+name);
				System.out.println("vendor------>"+vendor);
				System.out.println("resolution------>"+resolution);
				System.out.println("power------>"+power);
				System.out.println("timestamp------ >"+timestamp);
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
			}
		}, liSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_attribute, menu);
        return true;
    }
}
