package com.sid.msg;

/*必须引用BroadcastReceiver类*/
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/*必须引用Toast类来告知用户收到短信*/

/* 自定义继承自BroadcastReceiver类,聆听系统服务广播的信息 */
public class SMSreceiver extends BroadcastReceiver {
	/*
	 * 声明静态字符串,并使用android.provider.Telephony.SMS_RECEIVED 作为Action为短信的依据
	 */
	private static final String mACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		/* 判断传来Intent是否为短信 */
		if (intent.getAction().equals(mACTION)) {
			/* 建构一字符串集合变量sb */
			StringBuilder sb = new StringBuilder();
			/* 接收由Intent传来的数据 */
			Bundle bundle = intent.getExtras();
			/* 判断Intent是有数据 */
			if (bundle != null) {
				/*
				 * pdus为 android内置短信参数 identifier 通过bundle.get("")返回一包含pdus的对象
				 */
				Object[] myOBJpdus = (Object[]) bundle.get("pdus");
				/* 构建短信对象array,并依据收到的对象长度来创建array的大小 */
				SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
				for (int i = 0; i < myOBJpdus.length; i++) {
					messages[i] = SmsMessage
							.createFromPdu((byte[]) myOBJpdus[i]);
				}

				/* 将送来的短信合并自定义信息于StringBuilder当中 */
				for (SmsMessage currentMessage : messages) {
					sb.append("接收到来自:\n");
					/* 来讯者的电话号码 */
					sb.append(currentMessage.getDisplayOriginatingAddress());
					sb.append("\n------发送的短信------\n");
					/* 取得传来信息的BODY */
					sb.append(currentMessage.getDisplayMessageBody());
				}
			}
			/* 以Notification(Toase)显示来讯信息 */
			Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();

			/* 返回主Activity */
			Intent i = new Intent(context, MsgActivity.class);
			/* 设置让以一个全新的task来运行 */
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
	}
}
