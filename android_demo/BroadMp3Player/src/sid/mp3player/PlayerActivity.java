package sid.mp3player;

import java.util.ArrayList;
import java.util.List;

import sid.model.MP3Info;
import sid.mp3player.service.PlayerService;
import sid.utils.AppConstant;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlayerActivity extends Activity {
	/** MP3 */
	MP3Info mp3Info = null;
	/** 播放暂停按钮*/
	ImageButton pause = null;
	/** 下一曲按钮*/
	ImageButton next = null;
	/** 上一曲按钮*/
	ImageButton previous = null;
	/** 循环更改按钮*/
	ImageButton cycle = null;
	/** 随即顺序更改按钮*/
	ImageButton sequence = null;
	/** 歌词显示TextView*/
	TextView previousMessage = null;
	/** 歌词显示TextView*/
	TextView message = null;
	/** 歌词显示TextView*/
	TextView nextMessage = null;
	/** 当前位置*/
	int position = 0;
	/** 歌曲列表*/
	private ArrayList<List<MP3Info>> localMp3List = null;

	private IntentFilter intentFilter = null;
	private BroadcastReceiver receiver = null;
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		receiver = new LrcMessageBroadcastReceiver();
		registerReceiver(receiver,getIntentFilter());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.play_mp3);
		super.onCreate(savedInstanceState);
		// 获取传递过来的mp3信息
		Intent intent = getIntent();
		mp3Info = (MP3Info) intent.getSerializableExtra("mp3Info");
		position = intent.getIntExtra("position", 0);
		localMp3List = (ArrayList) intent.getSerializableExtra("localMp3List");
		// 绑定事件
		// 绑定事件
		cycle = (ImageButton) findViewById(R.id.cycle);
		cycle.setOnClickListener(new CycleOnClicke());
		sequence = (ImageButton) findViewById(R.id.sequence);
		sequence.setOnClickListener(new SequenceOnClicke());
		previous = (ImageButton) findViewById(R.id.previous);
		previous.setOnClickListener(new PreviousOnClicke());
		pause = (ImageButton) findViewById(R.id.pause);
		pause.setImageResource(R.drawable.mp3_yellow_round_pause);
		pause.setOnClickListener(new PauseOnClicke());
		next = (ImageButton) findViewById(R.id.next);
		next.setOnClickListener(new NextOnClicke());
		previousMessage = (TextView) findViewById(R.id.previousMessage);
		message = (TextView) findViewById(R.id.message);
		nextMessage = (TextView) findViewById(R.id.nextMessage);
		System.out.println(mp3Info);
		playMP3(AppConstant.PlayMSG.IS_NEW, AppConstant.PlayMSG.PLAY_MSG,AppConstant.PlayMSG.NOCANGE_MSG);
		if (PlayerService.IMAGE_BUTTON_STYLE_CYCLE==AppConstant.PlayMSG.IMAGE_BUTTON_REPEATALL) {
			cycle.setImageResource(R.drawable.mp3_yellow_round_singlecycle);
		}else if (PlayerService.IMAGE_BUTTON_STYLE_CYCLE==AppConstant.PlayMSG.IMAGE_BUTTON_REPEATONE) {
			cycle.setImageResource(R.drawable.mp3_yellow_round_repeatone);
		}else{
			cycle.setImageResource(R.drawable.mp3_yellow_round_closerepeat);
		}
		if (PlayerService.IMAGE_BUTTON_STYLE_SEQUENCE!=AppConstant.PlayMSG.IMAGE_BUTTON_RANDOM_SEQUENCE) {
			sequence.setImageResource(R.drawable.mp3_yellow_round_random);
		}else{
			sequence.setImageResource(R.drawable.mp3_yellow_round_random_sequence);
		}
	}

	/**
	 * 上一曲监听方法
	 * @author Administrator
	 *
	 */
	class PreviousOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			change(AppConstant.PlayMSG.PREVIOUS_MSG);
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
	 * 下一曲键监听方法
	 * @author Administrator
	 *
	 */
	class NextOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			change(AppConstant.PlayMSG.NEXT_MSG);
		}
	}

	/**
	 * 循环键监听方法
	 * @author Administrator
	 *
	 */
	class CycleOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			changeCycle();
		}
	}

	/**
	 * 下一曲键监听方法
	 * @author Administrator
	 *
	 */
	class SequenceOnClicke implements OnClickListener {
		@Override
		public void onClick(View v) {
			changeSequence();
		}
	}
	
	/**
	 * 改变按钮样式
	 */
	private void changeCycle() {
		if (PlayerService.IMAGE_BUTTON_STYLE_CYCLE==AppConstant.PlayMSG.IMAGE_BUTTON_REPEATALL) {
			cycle.setImageResource(R.drawable.mp3_yellow_round_repeatone);
			PlayerService.IMAGE_BUTTON_STYLE_CYCLE=AppConstant.PlayMSG.IMAGE_BUTTON_REPEATONE;
		}else if (PlayerService.IMAGE_BUTTON_STYLE_CYCLE==AppConstant.PlayMSG.IMAGE_BUTTON_REPEATONE) {
			cycle.setImageResource(R.drawable.mp3_yellow_round_closerepeat);
			PlayerService.IMAGE_BUTTON_STYLE_CYCLE=AppConstant.PlayMSG.IMAGE_BUTTON_CLOSE_REPEAT;
		}else{
			cycle.setImageResource(R.drawable.mp3_yellow_round_singlecycle);
			PlayerService.IMAGE_BUTTON_STYLE_CYCLE=AppConstant.PlayMSG.IMAGE_BUTTON_REPEATALL;
		}
	}
	
	/**
	 * 改变顺序按钮的样式
	 */
	private void changeSequence() {
		if (PlayerService.IMAGE_BUTTON_STYLE_SEQUENCE!=AppConstant.PlayMSG.IMAGE_BUTTON_RANDOM_SEQUENCE) {
			sequence.setImageResource(R.drawable.mp3_yellow_round_random_sequence);
			PlayerService.IMAGE_BUTTON_STYLE_SEQUENCE=AppConstant.PlayMSG.IMAGE_BUTTON_RANDOM_SEQUENCE;
		}else{
			sequence.setImageResource(R.drawable.mp3_yellow_round_random);
			PlayerService.IMAGE_BUTTON_STYLE_SEQUENCE=AppConstant.PlayMSG.IMAGE_BUTTON_RANDOM;
		}
	}
	
	/**
	 * 上一曲下一曲播放方法
	 * sign = AppConstant.PlayMSG.NEXT_MSG表示下一曲
	 * sign = AppConstant.PlayMSG.PREVIOUS_MSG表示上一曲
	 * @param sign
	 */
	private void change(int sign) {
		mp3Info = null;
		pause.setImageResource(R.drawable.mp3_yellow_round_pause);
		playMP3(null,AppConstant.PlayMSG.NO_MSG,sign);
	}
	/**
	 * 暂停方法
	 * 包括暂停和恢复播放
	 */
	private void pause() {
		if (PlayerService.IMAGE_BUTTON_STYLE_PLAYE == AppConstant.PlayMSG.IMAGE_BUTTON_PLAY) {
			pause.setImageResource(R.drawable.mp3_yellow_round_pause);
		}else{
			pause.setImageResource(R.drawable.mp3_yellow_round_star);
		}
		playMP3(null, AppConstant.PlayMSG.PLAY_MSG,AppConstant.PlayMSG.NOCANGE_MSG);
	}

	/**
	 * 播放音乐 
	 */
	private void playMP3(String isNew,int MSG,int chageSign) {
		Intent intent = new Intent();
		intent.putExtra("mp3Info", mp3Info);
		intent.putExtra("isNew", isNew);
		intent.putExtra("MSG",MSG);
		intent.putExtra("chageSign",chageSign);
		intent.putExtra("position", position);
		intent.putExtra("localMp3List", localMp3List);
		intent.setClass(PlayerActivity.this, PlayerService.class);
		//启动service
		startService(intent);
	}

	/**
	 * 广播接收器，主要作用是接收service所发送的广播
	 * 并且更新UI，放置歌词信息
	 * @author Administrator
	 *
	 */
	class LrcMessageBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String previousMsg = intent.getStringExtra("previousMessage");
			String Msg = intent.getStringExtra("message");
			String nextMsg = intent.getStringExtra("nextMessage");
			previousMessage.setText(previousMsg);
			message.setText(Msg);
			nextMessage.setText(nextMsg);
		}
	}

	public IntentFilter getIntentFilter() {
		if (intentFilter==null) {
			intentFilter = new IntentFilter();
			intentFilter.addAction(AppConstant.LRC_MESSAGE_ACTION);
		}
		return intentFilter;
	}
	public void setIntentFilter(IntentFilter intentFilter) {
		this.intentFilter = intentFilter;
	}
}
