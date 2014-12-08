package sid.lover.helpers;

import java.util.ArrayList;

import sid.lover.R;
import sid.lover.tools.ToolsActivity;
import sid.modle.Clock;
import sid.service.AlarmHelper;
import sid.service.ClockOperation;
import sid.utils.AppConstant;
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
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;


/* 实际跳出闹铃Dialog的Activity */
public class Ringing extends Activity {
	private int id = 0;
	private ScreenLockManager screenLockManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = new View(this);
		view.setBackgroundResource(R.drawable.ringbackground);
		this.setContentView(view);
		id = getIntent().getIntExtra("id", 0);
		super.onCreate(savedInstanceState);
		Clock clock = ClockOperation.getClock(Ringing.this,id);
//		System.out.println("======Ringing.clock========"+clock);
		if (clock.getSignCate()==AppConstant.signs[2]) {
	        SmsManager smsManager = SmsManager.getDefault();
	        ArrayList<String> texts = smsManager.divideMessage(clock.getTitle());//拆分短信,短信字数多要分几次发
	        for(String text : texts){
	         smsManager.sendTextMessage(clock.getBellsPath(), null, text, null, null);//mobile为要发送的号码
	        }
//	        System.out.println("----------短信发送完成---------");
			String weeks = clock.getWeeks();
			if (!"".equals(weeks)||clock.getSignCate()==AppConstant.signs[1]) {
				AlarmHelper ah = new AlarmHelper(Ringing.this);
				ah.setNextRingTime(clock,ah);
			}
	        this.finish();
		}else{
			screenLockManager = new ScreenLockManager(Ringing.this);
			screenLockManager.getUnlock();
			String title = getString(R.string.cancelClock);
			createNotifaction(clock);
			/* 跳出的闹铃警示 */
			Builder dialog = new AlertDialog.Builder(Ringing.this);
			dialog.setIcon(R.drawable.clock).setTitle(clock.getBells()).setMessage(clock.getTitle()).setNegativeButton(title, new cancelClockListener(clock));
			if (clock.getSignCate()==AppConstant.signs[0]) {
				dialog.setPositiveButton(getString(R.string.intervalRingClock), new intervalRingClockListener(clock));
			}
			dialog.show();
		}
	}
	
	/**
	 * 创建通知
	 * @param clock
	 */
	public void createNotifaction(Clock clock){
	     NotificationManager manger = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
	     Notification notification = new Notification(R.drawable.clock, clock.getTitle(), System.currentTimeMillis());
	     notification.flags = Notification.FLAG_AUTO_CANCEL;                
	     Intent i = new Intent(this, ToolsActivity.class);
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
				if (!"".equals(weeks)||clock.getSignCate()==AppConstant.signs[1]) {
					ah.setNextRingTime(clock,ah);
				}
				cancelNotifaction();
				screenLockManager.releaseUnlock();
				Ringing.this.finish();
			}
		}
	}
}
