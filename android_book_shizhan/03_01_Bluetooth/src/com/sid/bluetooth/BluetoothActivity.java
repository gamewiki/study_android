package com.sid.bluetooth;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BluetoothActivity extends Activity {
	private Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new MyButtonOnClick());
        
    }

    class MyButtonOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			//得到BluetoothAdapter对象
			BluetoothAdapter adatper = BluetoothAdapter.getDefaultAdapter();
			//判断是否为空，为空则表明本机没有蓝牙设备
			if (adatper!=null) {
				System.out.println("本机拥有蓝牙设备");
				//调用isEnabled方法，判断当前蓝牙是否可用
				if (!adatper.isEnabled()) {
					//创建一个intent对象，用于启动一个activity，提示用户开启蓝牙设备
					Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivity(intent);
				}
				//得到所有已经配对的蓝牙适配器对象
				Set<BluetoothDevice> devices = adatper.getBondedDevices();
				if (devices.size()>0) {
					for (Iterator<BluetoothDevice> iterator = devices.iterator(); iterator.hasNext();) {
						BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
						//得到远程蓝牙设备的地址
						System.out.println(bluetoothDevice.getAddress());
					}
				}
			}else{
				System.out.println("没有蓝牙设备！");
			}
		}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bluetooth, menu);
        return true;
    }
}
