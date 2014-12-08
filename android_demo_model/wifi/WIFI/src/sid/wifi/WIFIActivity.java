package sid.wifi;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WIFIActivity extends Activity {
	Button start = null;
	Button stop = null;
	Button status = null;
	private WifiManager wifiManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        status = (Button)findViewById(R.id.status);
        start.setOnClickListener(new Start());
        stop.setOnClickListener(new Stop());
        status.setOnClickListener(new Status());
    }
    
    class Start implements OnClickListener{
		@Override
		public void onClick(View v) {
			wifiManager = (WifiManager) WIFIActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			System.out.println("start wifi: wifi status is："+wifiManager.getWifiState());
			Toast.makeText(WIFIActivity.this, "当前网卡的状态："+wifiManager.getWifiState(), Toast.LENGTH_LONG);
		}
    }
    
    class Stop implements OnClickListener{
		@Override
		public void onClick(View v) {
			wifiManager = (WifiManager) WIFIActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			System.out.println("stop wifi: wifi status is："+wifiManager.getWifiState());
			Toast.makeText(WIFIActivity.this, "当前网卡的状态："+wifiManager.getWifiState(), Toast.LENGTH_LONG);
		}
    }
    
    class Status implements OnClickListener{
		@Override
		public void onClick(View v) {
			wifiManager = (WifiManager) WIFIActivity.this.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi status is："+wifiManager.getWifiState());
			Toast.makeText(WIFIActivity.this, "当前网卡的状态："+wifiManager.getWifiState(), Toast.LENGTH_LONG);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_wifi, menu);
        return true;
    }
}
