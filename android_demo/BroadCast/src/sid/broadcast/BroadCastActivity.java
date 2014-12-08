package sid.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadCastActivity extends Activity {
	Button button = null;
	Button registReceiver = null;
	Button unReceiver = null;
	SMSReceived smsReceiver = null;
	private static String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new MyOnClickListener());
        registReceiver = (Button)findViewById(R.id.registReceiver);
        registReceiver.setOnClickListener(new MyregistReceiverOnClickListener());
        unReceiver = (Button)findViewById(R.id.unReceiver);
        unReceiver.setOnClickListener(new MyunReceiverOnClickListener());
    }
	
	class MyOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_EDIT);
			BroadCastActivity.this.sendBroadcast(intent);
		}
	}
	
	class MyregistReceiverOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//生成一个broadcastreceiver对象
			smsReceiver = new SMSReceived();
			//生成一个intentfilter对象；与xml配置文件中的作用是一样的。
			IntentFilter intentFilter = new IntentFilter();
			//为filter添加一个action
			intentFilter.addAction(SMS_ACTION);
			//将broadcastreceiver对象注册到系统当中
			BroadCastActivity.this.registerReceiver(smsReceiver, intentFilter);
		}
	}
	
	class MyunReceiverOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//解除broadcastreceiver对象的注册
			if (smsReceiver!=null) {
				BroadCastActivity.this.unregisterReceiver(smsReceiver);
			}
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_broad_cast, menu);
        return true;
    }
}
