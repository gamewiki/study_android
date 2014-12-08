package com.sid.clock;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.Bundle;

/* 调用闹钟Alert的Receiver */
public class ClockToRinging extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		/* create Intent，调用AlarmAlert.class */
		Intent i = new Intent(context, Ringing.class);

		Bundle bundleRet = new Bundle();
		bundleRet.putString("STR_CALLER", "");
		i.putExtras(bundleRet);
//		Intent.FLAG_ACTIVITY_NEW_TASK
//		区别于默认优先启动在activity栈中已经存在的activity（如果之前启动过，并还没有被destroy的话）
//		而是无论是否存在，都重新启动新的activity
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
