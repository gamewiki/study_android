package sid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceived extends BroadcastReceiver {
	public SMSReceived(){
		System.out.println("SMSReceived is called...................");
	}

	/**
	 * 逻辑处理在这里进行操作
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("SMSReceived onReceive is called...................");
		
		//处理消息
		//接受Intent对象当中的数据
		Bundle bundle = intent.getExtras();
		//在bundle对象当中有个属性名pdus，这个数值是个object[]
		Object[] myOBJpdus = (Object[])bundle.get("pdus");
		//创建一个SmsMessage类型数组
		SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
		System.out.println("消息数组长度："+myOBJpdus.length);
		
		for (int i = 0; i < messages.length; i++) {
			//使用object数组当中的对象创建SmsMessage对象
			messages[i] = SmsMessage.createFromPdu((byte[]) myOBJpdus[i]);
			//调用SmsMessage对象的getDisplayMessageBody方法就可以得到消息的内容
			System.out.println(messages[i].getDisplayMessageBody());
		}
	}
}
