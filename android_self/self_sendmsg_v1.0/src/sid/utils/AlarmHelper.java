package sid.utils;

import java.util.Calendar;
import java.util.Date;

import sid.modle.SendMsg;
import sid.modle.StartToSendding;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHelper {
    private Context context;
    private AlarmManager mAlarmManager;
    public AlarmHelper(Context context) {
        this.context = context;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void newOneAlarm(SendMsg sendMsg, long time) {
        Intent intent = new Intent();
        intent.putExtra("id", sendMsg.getId());
        intent.setClass(context, StartToSendding.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, sendMsg.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.RTC, time, sender);
    }
    
    public void closeAlarm(int id) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.setClass(context, StartToSendding.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, 0);
        mAlarmManager.cancel(pi);
    }

	/**
	 * 获取下次发送时间，并设置
	 * @param ah
	 * @param weeks
	 */
	public void setNextRingTime(SendMsg sendMsg,AlarmHelper ah) {
		Calendar calendar = Calendar.getInstance();
		Date now  = new Date();
		calendar.setTime(now);
		Date next = new Date(now.getTime()+1*24*60*60*1000);
		calendar.setTime(next);
		calendar.set(Calendar.HOUR_OF_DAY, sendMsg.getHour());
		calendar.set(Calendar.MINUTE, sendMsg.getMinute());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long startime = calendar.getTimeInMillis();
		ah.newOneAlarm(sendMsg,startime);
	}
}