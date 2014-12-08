package sid.mp3player.service;

import sid.download.HttpDownloader;
import sid.model.MP3Info;
import sid.mp3player.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service {
	private String resultText = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/**
	 * 每当用户点击列表中的一条数据时
	 * 就会启动一个下载service后，启动这个方法
	 * 生成一个下载线程
	 * 并将MP3Info对象作为参数传递到线程当中
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		MP3Info mp3Info = (MP3Info)intent.getSerializableExtra("mp3Info");
		String mp3URL = intent.getStringExtra("mp3URL");
		String dirPath = intent.getStringExtra("dirPath");
		DownloadThread downloadThread = new DownloadThread(mp3Info,mp3URL,dirPath);
		Thread thread = new Thread(downloadThread);
		thread.start();
		//Toast方式提示文件已存在
		//Toast.makeText(DownloadService.this, resultText, Toast.LENGTH_LONG).show();
		NotificationShow();
		super.onStart(intent, startId);
	}
	
	class DownloadThread implements Runnable{
		private MP3Info mp3Info = null;
		private String mp3URL = null;
		private String dirPath = null;
		public DownloadThread(MP3Info mp3info,String mp3URL,String dirPath){
			this.mp3Info = mp3info;
			this.mp3URL = mp3URL;
			this.dirPath = dirPath;
		}
		@Override
		public void run() {
			HttpDownloader httpDownloader = new HttpDownloader();
			int result = httpDownloader.downFile(mp3URL+mp3Info.getMp3Name(), dirPath, mp3Info.getMp3Name());
			switch (result) {
			case -1:
				resultText = "歌曲下载失败";
				break;
			case 0:
				resultText = "歌曲下载成功";
				break;
			case 1:
				resultText = "歌曲文件已存在";
				break;
			default:
				break;
			}
			int lrcResult = httpDownloader.downFile(mp3URL+mp3Info.getLrcName(), dirPath, mp3Info.getLrcName());
			switch (lrcResult) {
			case -1:
				resultText = "歌词下载失败";
				break;
			case 0:
				resultText = "歌词下载成功";
				break;
			case 1:
				resultText = "歌词文件已存在";
				break;
			default:
				break;
			}
		}
	}

	/**
	 * notification方式
	 */
	private void NotificationShow() {
		//创建 NotificationManager，其中创建的 nm 对象负责“发出”与“取消”  Notification。
		NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		//创建 Notification 
		//参数依次为：icon的资源id，在状态栏上展示的滚动信息，时间。
		//其中创建的 n 对象用来描述出现在系统通知栏的信息，之后我们将会看到会在 n 对象上设置点击此条通知发出的Intent。
		Notification n = new Notification(R.drawable.ic_action_search, resultText, System.currentTimeMillis());  
		//设置 n.flags 为 Notification.FLAG_AUTO_CANCEL ，该标志表示当用户点击 Clear 之后，能够清除该通知          
		n.flags = Notification.FLAG_AUTO_CANCEL;
		//创建一个Intent，该Intent使得当用户点击该通知后发出这个Intent
		Intent i = new Intent(DownloadService.this, NotificationShow.class);
		//请注意，如果要以该Intent启动一个Activity，一定要设置 Intent.FLAG_ACTIVITY_NEW_TASK 标记。
		//Intent.FLAG_ACTIVITY_CLEAR_TOP ：如果在当前Task中，有要启动的Activity，那么把该Acitivity之前的所有Activity都关掉，并把此Activity置前以避免创建Activity的实例
		//Intent.FLAG_ACTIVITY_NEW_TASK ：系统会检查当前所有已创建的Task中是否有该要启动的Activity的Task，若有，则在该Task上创建Activity，若没有则新建具有该Activity属性的Task，并在该新建的Task上创建Activity
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);  
		//PendingIntent 为Intent的包装，这里是启动Intent的描述，PendingIntent.getActivity 返回的PendingIntent表示，此PendingIntent实例中的Intent是用于启动 Activity 的Intent。PendingIntent.getActivity的参数依次为：Context，发送者的请求码（可以填0），用于系统发送的Intent，标志位。
		//其中 PendingIntent.FLAG_UPDATE_CURRENT  表示如果该描述的PendingIntent已存在，则改变已存在的PendingIntent的Extra数据为新的PendingIntent的Extra数据。
		//这里再简要说一下 Intent 与 PendingIntent 的区别：
		PendingIntent contentIntent = PendingIntent.getActivity(
				DownloadService.this,
		        R.string.app_name,
		        i,
		        PendingIntent.FLAG_UPDATE_CURRENT);
		//Intent ：意图，即告诉系统我要干什么，然后系统根据这个Intent做对应的事。如startActivity相当于发送消息，而Intent是消息的内容。
		//PendingIntent ：包装Intent，Intent 是我们直接使用 startActivity ， startService 或 sendBroadcast 启动某项工作的意图。
		//而某些时候，我们并不能直接调用startActivity ， startServide 或 sendBroadcast ，而是当程序或系统达到某一条件才发送Intent。
		//如这里的Notification，当用户点击Notification之后，由系统发出一条Activity 的 Intent 。
		//因此如果我们不用某种方法来告诉系统的话，系统是不知道是使用 startActivity ，startService 还是 sendBroadcast 来启动Intent 的（当然还有其他的“描述”），因此这里便需要PendingIntent。
		n.setLatestEventInfo(
				DownloadService.this,
				resultText,
				resultText,
		        contentIntent);
		//启动Notification，参数依次为：在你的程序中标识Notification的id值（用来区分同一程序中的不同Notifycation，如果程序中只有一个Notification那么这里随便你填什么都可以，不过类型必须要为int），要通知的Notification。
		nm.notify(R.string.app_name, n);
		//如何使自己的Notification像Android QQ一样能出现在 “正在运行的”栏目下面
		//其实很简单，只需设置Notification.flags = Notification.FLAG_ONGOING_EVENT;便可以了。
		//如何改变 Notification 在“正在运行的”栏目下面的布局
		//创建 RemoteViews 并赋给 Notification.contentView ，再把 PendingIntent 赋给 Notification.contentIntent 便可以了，如：
	}
}
