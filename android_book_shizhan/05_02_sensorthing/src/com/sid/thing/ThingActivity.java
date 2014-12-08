package com.sid.thing;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class ThingActivity extends Activity {
	private Button button ;
	private SensorManager sensorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing);
        
        button = (Button) findViewById(R.id.button);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //第三个参数是延迟的值
        sensorManager.registerListener(new SensorEventListener() {
        	/**
        	 * 传感器的感知变化
        	 */
			@Override
			public void onSensorChanged(SensorEvent event) {
				//获取精度
				float acc = event.accuracy;
				float lux = event.values[0];
				System.out.println("acc------>"+acc);
				System.out.println("lux------>"+lux);
			}
			/**
			 * 传感器的精度发生变化时
			 */
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
			}
		}, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_thing, menu);
        return true;
    }
}
