package com.sid.method;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MethodActivity extends Activity {
	private SensorManager sensorManager;
	private Sensor acceierometerSensor;
	/** 三个方向的重力加速度*/
	private float[] gravity = new float[3];
	/** 三个方向的加速度*/
	private float[] linear_acceleration = new float[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acceierometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				System.out.println("0x------->"+event.values[0]);
				System.out.println("1y------->"+event.values[1]);
				System.out.println("2z------->"+event.values[2]);
				
				//引入滤波器；去除重力加速度
				final float alpha = 0.8f;

				gravity[0] = alpha*gravity[0] + (1-alpha)*event.values[0];
				gravity[1] = alpha*gravity[1] + (1-alpha)*event.values[1];
				gravity[2] = alpha*gravity[2] + (1-alpha)*event.values[2];

				linear_acceleration[0] = event.values[0]-gravity[0];
				linear_acceleration[1] = event.values[1]-gravity[1];
				linear_acceleration[2] = event.values[2]-gravity[2];

				System.out.println("gravity0x------->"+gravity[0]);
				System.out.println("gravity1y------->"+gravity[1]);
				System.out.println("gravity2z------->"+gravity[2]);
				System.out.println("linear_acceleration0x------->"+linear_acceleration[0]);
				System.out.println("linear_acceleration1y------->"+linear_acceleration[1]);
				System.out.println("linear_acceleration2z------->"+linear_acceleration[2]);
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
			}
		}, acceierometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
