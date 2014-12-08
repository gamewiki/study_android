package com.sid.sensor;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SensorActivity extends Activity {
	private Button button ;
	private SensorManager sensorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        button = (Button) findViewById(R.id.button);
        //获取系统服务的通用方法
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String sensors = "";
				List<Sensor> lists = sensorManager.getSensorList(Sensor.TYPE_ALL);
				for (Sensor sensor : lists) {
					sensors += sensor.getName()+";";
					System.out.println(sensor.getName());
				}
				//获取光线传感器
				Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
				System.out.println(sensor.getName());
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sensor, menu);
        return true;
    }
}
