package sid.lover.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DaysToRinging extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
//		System.out.println("----------DaysToRinging---------"+(Integer)intent.getExtras().get("id"));
		Intent i = new Intent(context, Ringing.class);
		i.putExtra("id", (Integer)intent.getExtras().get("id"));
//		Intent.FLAG_ACTIVITY_NEW_TASK
//		区别于默认优先启动在activity栈中已经存在的activity（如果之前启动过，并还没有被destroy的话）
//		而是无论是否存在，都重新启动新的activity
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
