package com.sid.startup;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* 捕捉android.intent.action.BOOT_COMPLETED的Receiver类 */
public class StartupIntentReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		/* 当收到Receiver时，指定打开此程序（EX06_16.class） */
		Intent mBootIntent = new Intent(context, StartupActivity.class);
		/* 设置Intent打开为FLAG_ACTIVITY_NEW_TASK */
		mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		/* 将Intent以startActivity传送给操作系统 */
		context.startActivity(mBootIntent);
	}
}
