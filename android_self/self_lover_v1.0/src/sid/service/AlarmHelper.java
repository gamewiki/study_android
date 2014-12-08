package sid.service;

import java.util.Calendar;
import java.util.Date;

import sid.lover.R;
import sid.lover.days.DaysToRinging;
import sid.modle.Clock;
import sid.utils.AppConstant;
import sid.utils.SelfDateUtils;
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
 
    public void newOneAlarm(Clock clock, long time) {
        Intent intent = new Intent();
        intent.putExtra("id", clock.getId());
        intent.setClass(context, DaysToRinging.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, clock.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.RTC, time, sender);
    }
 
    public void newTimesAlarm(Clock clock,int times, long time) {
        Intent intent = new Intent();
        intent.putExtra("id", clock.getId());
        intent.setClass(context, DaysToRinging.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, clock.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
		mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,times, sender);
    }
 
    public void closeAlarm(int id) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.setClass(context, DaysToRinging.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, 0);
        mAlarmManager.cancel(pi);
    }

	/**
	 * 获取下次响铃时间，并设置
	 * @param ah
	 * @param weeks
	 */
	public void setNextRingTime(Clock clock,AlarmHelper ah) {
		Calendar calendar = Calendar.getInstance();
		Date now  = new Date();
		int times = Integer.valueOf((clock.getInterval()==null||"".equals(clock.getInterval())||(context.getString(R.string.intervalClock)).equals(clock.getInterval()))?"0":clock.getInterval()) * 60 * 1000;
		long startime = clock.getStartime();
		if ((clock.getSignCate()==AppConstant.signs[1])&&clock.getStartime()<now.getTime()) {
			startime = getNextDateStartTime(clock, calendar, now);
		}else if (clock.getStartime()<now.getTime()) {
			startime = getNextStartTime(clock, calendar, now);
		}
		if(clock.getSignCate()==AppConstant.signs[0]){
			if (times==0) {
				ah.newOneAlarm(clock, startime);
			}else{
				ah.newTimesAlarm(clock,times,startime);
			}
		}else{
			ah.newOneAlarm(clock,startime);
		}		
		System.out.println("=========我设置的重复======="+clock.getWeeks());
		System.out.println("=========设置响铃时间======="+SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss", new Date(clock.getStartime())));
		System.out.println("=========下次响铃时间======="+SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss", new Date(startime)));
	}

	/**
	 * 闹钟获取下次启动时间
	 * @param clock
	 * @param calendar
	 * @param now
	 * @return
	 */
	public long getNextStartTime(Clock clock, Calendar calendar, Date now) {
		long startime;
		calendar.setTime(now);
		String[] repeate = clock.getWeeks().split(",");
		int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
		int days=0;
		boolean isWeek=true;
		for (int i = 0; i < repeate.length; i++) {
			if (!"".equals(repeate[i])) {
				int re = Integer.valueOf(repeate[i]);
				days=re-week;
				if (days>0) {
					isWeek=false;
					break;
				}
			}
		}
		if (isWeek&&(!"".equals(clock.getWeeks()))){
			days=7+(Integer.valueOf("".equals(repeate[0])?repeate[1]:repeate[0])-week);
		}
		System.out.println("---week---"+week);
		System.out.println("---repeate---"+repeate);
		System.out.println("---days---"+days);
		Date next = new Date(now.getTime()+days*24*60*60*1000);
		calendar.setTime(next);
		calendar.set(Calendar.HOUR_OF_DAY, clock.getHour());
		calendar.set(Calendar.MINUTE, clock.getMinute());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		startime = calendar.getTimeInMillis();
		return startime;
	}

	/**
	 * 纪念日获取下次启动时间
	 * @param clock
	 * @param calendar
	 * @param now
	 * @return
	 */
	public long getNextDateStartTime(Clock clock, Calendar calendar, Date now) {
		long startime;
		calendar.setTime(now);
		int year = calendar.get(Calendar.YEAR)+1;
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR_OF_DAY, clock.getHour());
		calendar.set(Calendar.MINUTE, clock.getMinute());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		startime = calendar.getTimeInMillis();
		return startime;
	}
}