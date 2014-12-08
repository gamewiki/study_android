package com.sid;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView;
	private CheckBox checkBox;
	private WifiManager wifiManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.myTextView);
        checkBox = (CheckBox) findViewById(R.id.myCheckBox);
        
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
			if (wifiManager.getWifiState()==WifiManager.WIFI_STATE_ENABLED) {
				checkBox.setChecked(true);
				textView.setText(R.string.wifi_off);
			}else{
				checkBox.setChecked(false);
				textView.setText(R.string.wifi_on);
			}
		}else{
			checkBox.setChecked(false);
			textView.setText(R.string.wifi_on);
		}
        
        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (checkBox.isChecked()==false) {
					try{
						if (wifiManager.isWifiEnabled()) {
							if (wifiManager.setWifiEnabled(false)) {
								textView.setText(R.string.wifi_off);
							}else{
								textView.setText(R.string.wifi_on);
							}
						}else{
							switch (wifiManager.getWifiState()) {
							case WifiManager.WIFI_STATE_ENABLING:
								break;
							case WifiManager.WIFI_STATE_DISABLING:
								break;
							case WifiManager.WIFI_STATE_DISABLED:
								break;
							case WifiManager.WIFI_STATE_UNKNOWN:
							default:
								break;
							}
							checkBox.setText(R.string.wifi_on);
						}
					}catch(Exception e){
						Log.i("HIPPO", e.toString());
						e.printStackTrace();
					}
				}else if (checkBox.isChecked()==true) {
					
				}
			}
		});
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
