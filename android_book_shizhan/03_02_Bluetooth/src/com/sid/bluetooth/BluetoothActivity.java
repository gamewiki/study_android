package com.sid.bluetooth;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BluetoothActivity extends Activity {
	private BluetoothReceiver bluetoothReceiver = null;
	/** 代表本机蓝牙*/
	private BluetoothAdapter adapter= null;
	private Button button = null;
	private Button buttonDis = null;
	private Button buttonScan = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new MyButtonOnClick());
        buttonDis = (Button)findViewById(R.id.bluetoothDiscover);
        buttonDis.setOnClickListener(new MyButtonDiscoverOnClick());
        
        //扫描周围可见蓝牙
        //创建一个IntentFilter对象；将其action指定为BluetoothDevice.ACTION_FOUND
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        bluetoothReceiver = new BluetoothReceiver();
        //注册广播接收器
        registerReceiver(bluetoothReceiver, intentFilter);
        //得到adapter对象
        adapter = BluetoothAdapter.getDefaultAdapter();
        buttonScan = (Button)findViewById(R.id.bluetoothScan);
        buttonScan.setOnClickListener(new MyButtonScanOnClick());
        
    }
    private class BluetoothReceiver extends BroadcastReceiver {
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		String aciton = intent.getAction();
    		//因为在intentfilter中指定过；所以这里有点多余了
    		if (BluetoothDevice.ACTION_FOUND.equals(aciton)) {
    			//代表所获得的远程的对象
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				System.out.println(device.getAddress());
			}
    	}
    }
    /**
     * 更改监听器用于修改蓝牙设备的可见性
     * @author Administrator
     *
     */
    private class MyButtonDiscoverOnClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			//将蓝牙设置为可见
			Intent disIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			//可见状态的持续时间；单位是秒；如果超过300秒，默认会覆盖500秒；300秒后关闭
			disIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 500);
			startActivity(disIntent);
		}
    	
    }
    /**
     * 扫描可见的蓝牙设备
     * @author Administrator
     *
     */
    private class MyButtonScanOnClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			//每扫描到一个蓝牙设备，就会发送一个广播；
			//每次扫描就会消耗12秒；扫描的是异步调用
			adapter.startDiscovery();
		}
    	
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
    protected void onDestroy() {
    	unregisterReceiver(bluetoothReceiver);
    	super.onDestroy();
    }
}
