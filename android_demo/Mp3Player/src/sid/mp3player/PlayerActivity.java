package sid.mp3player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Queue;

import sid.lrc.LRCProcessor;
import sid.model.MP3Info;
import sid.mp3player.service.PlayerService;
import sid.utils.AppConstant;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlayerActivity extends Activity {
	MP3Info mp3Info = null;
	ImageButton star = null;
	ImageButton pause = null;
	ImageButton stop = null;
	TextView lrcText = null;
	MediaPlayer mediaPlayer = null;

	boolean isPlaying = false;
	boolean isPause = false;
	boolean isReleased = false;

	/** 获取歌词列表，存放的是时间队列和歌词队列*/
	private ArrayList<Queue> queues = null;
	/** handler用于控制歌词更新*/
	private Handler handler = new Handler();
	/** 歌词更新回调函数*/
	private UpdateTimeCallback updateTimeCallback = null;
	/** 显示的歌词信息*/
	private String message = null;
	/** 开始时间设置为0，为做时间偏移量做准备*/
	private long begin = 0;
	/** 下一次更新歌词的时间点*/
	private long nextTimeMill = 0;
	/** 当前的毫秒级时间*/
	private long currentTimeMill = 0;
	/** 暂停的毫秒级时间数*/
	private long pauseTimeMill = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.play_mp3);
		super.onCreate(savedInstanceState);
		// 获取传递过来的mp3信息
		Intent intent = getIntent();
		mp3Info = (MP3Info) intent.getSerializableExtra("mp3Info");
		// 绑定事件
		star = (ImageButton) findViewById(R.id.star);
		star.setOnClickListener(new StarOnClicke());
		pause = (ImageButton) findViewById(R.id.pause);
		pause.setOnClickListener(new PauseOnClicke());
		stop = (ImageButton) findViewById(R.id.stop);
		stop.setOnClickListener(new StopOnClicke());
		lrcText = (TextView) findViewById(R.id.lrcText);
		System.out.println(mp3Info);
	}

	/**
	 * 根据歌词文件的名字读文件的信息
	 * 
	 * @param lrcName
	 */
	private void prepareLrc(String lrcName) {
		try {
			InputStream inputStream = new FileInputStream(Environment
					.getExternalStorageDirectory().getAbsolutePath()
					+File.separator+AppConstant.URL.BASE_URL_LOCAL_DIR
					+File.separator+mp3Info.getLrcName());
			LRCProcessor lrcProcessor = new LRCProcessor();
			queues = lrcProcessor.process(inputStream);
			// 创建一个UpdateTimeCallbakc对象
			updateTimeCallback = new UpdateTimeCallback(queues);
			begin = 0;
			currentTimeMill = 0;
			nextTimeMill = 0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	class UpdateTimeCallback implements Runnable {
		Queue times = null;
		Queue messages = null;
		public UpdateTimeCallback(ArrayList<Queue> queues) {
			times = queues.get(0);
			messages = queues.get(1);
			System.out.println("初始化函数："+messages);
		}
		@Override
		public void run() {
			// 计算偏移量，即从开始播放到现在消耗了多久；以毫秒为单位
			long offset = System.currentTimeMillis() - begin;
			if (currentTimeMill == 0) {
				//下一次更新歌词的时间点
				nextTimeMill = (Long) times.poll();
				message = (String) messages.poll();
			}
			if (offset >= nextTimeMill) {
				//判断时间后显示
				lrcText.setText(message);
				nextTimeMill = (Long) times.poll();
				message = (String) messages.poll();
			}
			currentTimeMill = currentTimeMill + 10;
			//使用handler再次执行；每隔10毫秒
			handler.postDelayed(updateTimeCallback, 10);
		}
	}

	/**
	 * 播放键监听方法
	 * @author Administrator
	 *
	 */
	class StarOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (isPause) {
				pause();
			}else{
				playing();
			}
		}
	}

	/**
	 * 暂停键监听方法
	 * @author Administrator
	 *
	 */
	class PauseOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			pause();
		}
	}

	/**
	 * 停止键监听方法
	 * @author Administrator
	 *
	 */
	class StopOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			stop();
		}
	}
	
	/**
	 * 播放方法
	 */
	private void playing() {
		System.out.println("staronclicke is going ");
		Intent intent = new Intent();
		intent.putExtra("mp3Info", mp3Info);
		intent.putExtra("MSG", AppConstant.PlayMSG.PLAY_MSG);
		intent.setClass(PlayerActivity.this, PlayerService.class);
		//读取Lrc文件
		prepareLrc(mp3Info.getLrcName());
		//启动service
		startService(intent);
		//将begin的值置为当前毫秒数
		begin = System.currentTimeMillis();
		handler.postDelayed(updateTimeCallback, 5);
		isPlaying = true;
		System.out.println("staronclicke is ending ");
	}
	/**
	 * 暂停方法
	 * 包括暂停和恢复播放
	 */
	private void pause() {
		Intent intent = new Intent();
		intent.putExtra("MSG", AppConstant.PlayMSG.PAUSE_MSG);
		intent.setClass(PlayerActivity.this, PlayerService.class);
		startService(intent);
		if (isPlaying) {
			handler.removeCallbacks(updateTimeCallback);
			pauseTimeMill = System.currentTimeMillis();
		}else{
			handler.postDelayed(updateTimeCallback, 5);
			begin = System.currentTimeMillis()-pauseTimeMill +begin;
		}
		isPlaying = isPlaying?false:true;
		isPause = isPause?false:true;
	}
	/**
	 * 停止播放方法
	 */
	private void stop() {
		Intent intent = new Intent();
		intent.putExtra("MSG", AppConstant.PlayMSG.STOP_MSG);
		intent.setClass(PlayerActivity.this, PlayerService.class);
		startService(intent);
		handler.removeCallbacks(updateTimeCallback);
	}
}
