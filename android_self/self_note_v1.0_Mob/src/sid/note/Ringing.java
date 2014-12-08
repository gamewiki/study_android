package sid.note;

import sid.modle.Clock;
import sid.utils.AlarmHelper;
import sid.utils.AppConstant;
import sid.utils.DatabaseOperation;
import sid.utils.ScreenLockManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


/* 实际跳出闹铃Dialog的Activity */
public class Ringing extends Activity {
	private int id = 0;
	private ScreenLockManager screenLockManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		View view = new View(this);
		view.setBackgroundResource(R.drawable.ringbackground);
		this.setContentView(view);
		id = getIntent().getIntExtra("id", 0);
		super.onCreate(savedInstanceState);
		Clock clock = DatabaseOperation.getClock(Ringing.this,id);
		screenLockManager = new ScreenLockManager(Ringing.this);
		screenLockManager.getUnlock();
		createNotifaction(clock);
		/* 跳出的闹铃警示 */
		Builder dialog = new AlertDialog.Builder(Ringing.this);
		dialog.setIcon(R.drawable.clock)
		.setTitle(clock.getSignCate())
		.setMessage(clock.getTitle())
		.setNegativeButton(getString(R.string.cancelClock), new cancelClockListener(clock));
		if (clock.getSignCate().equals(AppConstant.signs[0])) {
			dialog.setPositiveButton(getString(R.string.intervalRingClock), new intervalRingClockListener(clock));
		}
		dialog.show();
	}
	
	/**
	 * 创建通知
	 * @param clock
	 */
	public void createNotifaction(Clock clock){
	     NotificationManager manger = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
	     Notification notification = new Notification(R.drawable.clock, clock.getTitle(), System.currentTimeMillis());
	     notification.flags = Notification.FLAG_AUTO_CANCEL;                
	     Intent i = new Intent(this, ClockActivity.class);
	     i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);           
	     //PendingIntent
	     PendingIntent contentIntent = PendingIntent.getActivity(
	    		 this, 
	             R.string.app_name, 
	             i, 
	             PendingIntent.FLAG_UPDATE_CURRENT);
	     notification.setLatestEventInfo(
	    		 this,
	             getString(R.string.app_name), 
	             clock.getTitle(), 
	             contentIntent);
	     if (AppConstant.shocks[0].equals(clock.getShockCate())) {
		     notification.sound = Uri.parse(clock.getBellsPath());
		     notification.vibrate = AppConstant.vibrate;
	     }else if (AppConstant.shocks[1].equals(clock.getShockCate())) {
		     notification.sound = Uri.parse(clock.getBellsPath());
		 }else if (AppConstant.shocks[2].equals(clock.getShockCate())) {
			 notification.vibrate = AppConstant.vibrate;
		 }
	     manger.notify(R.string.app_name, notification);
	}

	/**
	 * 取消通知
	 * @param clock
	 */
	public void cancelNotifaction(){
	     NotificationManager manger = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
	     manger.cancel(R.string.app_name);
	}
	/**
	 * 闹钟再睡一会按钮
	 * 
	 */
	class intervalRingClockListener implements DialogInterface.OnClickListener {
		Clock clock = null;
		public intervalRingClockListener(Clock clock) {
			this.clock=clock;
		}
		@Override
		public void onClick(DialogInterface dialog, int which) {
			cancelNotifaction();
			screenLockManager.releaseUnlock();
			Ringing.this.finish();
		}
	}

	/**
	 * 闹钟关闭按钮
	 * 
	 */
	class cancelClockListener implements DialogInterface.OnClickListener {
		Clock clock = null;
		public cancelClockListener(Clock clock) {
			this.clock=clock;
		}
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (id!=0) {
				AlarmHelper ah = new AlarmHelper(Ringing.this);
				ah.closeAlarm(id);
				String weeks = clock.getWeeks();
				if (!"".equals(weeks)) {
					ah.setNextRingTime(clock,ah);
				}
				cancelNotifaction();
				screenLockManager.releaseUnlock();
				Ringing.this.finish();
			}
		}
	}
}
