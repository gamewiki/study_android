package sid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadCastReceived extends BroadcastReceiver {
	public BroadCastReceived(){
		System.out.println("BroadCastReceived is called...................");
	}

	/**
	 * 逻辑处理在这里进行操作
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive is called...................");
	}
}
