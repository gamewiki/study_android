package com.sid.volume;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class VolumeActivity extends Activity {
	/* 变量声明 */
	private ImageView myImage;
	private ImageButton downButton;
	private ImageButton upButton;
	private ImageButton normalButton;
	private ImageButton muteButton;
	private ImageButton vibrateButton;
	private ProgressBar myProgress;
	private AudioManager audioMa;
	private int volume = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volume);

		/* 对象初始化 */
		audioMa = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		myImage = (ImageView) findViewById(R.id.myImage);
		myProgress = (ProgressBar) findViewById(R.id.myProgress);
		downButton = (ImageButton) findViewById(R.id.downButton);
		upButton = (ImageButton) findViewById(R.id.upButton);
		normalButton = (ImageButton) findViewById(R.id.normalButton);
		muteButton = (ImageButton) findViewById(R.id.muteButton);
		vibrateButton = (ImageButton) findViewById(R.id.vibrateButton);

		/* 设置初始的手机音量 */
		volume = audioMa.getStreamVolume(AudioManager.STREAM_RING);
		myProgress.setProgress(volume);
		/* 设置初始的声音模式 */
		int mode = audioMa.getRingerMode();
		if (mode == AudioManager.RINGER_MODE_NORMAL) {
			myImage.setImageDrawable(getResources().getDrawable(
					R.drawable.normal));
		} else if (mode == AudioManager.RINGER_MODE_SILENT) {
			myImage.setImageDrawable(getResources()
					.getDrawable(R.drawable.mute));
		} else if (mode == AudioManager.RINGER_MODE_VIBRATE) {
			myImage.setImageDrawable(getResources().getDrawable(
					R.drawable.vibrate));
		}

		/* 音量调小声的Button */
		downButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 设置音量调小声一格 */
				audioMa.adjustVolume(AudioManager.ADJUST_LOWER, 0);
				volume = audioMa.getStreamVolume(AudioManager.STREAM_RING);
				myProgress.setProgress(volume);
				/* 设置调整后声音模式 */
				int mode = audioMa.getRingerMode();
				if (mode == AudioManager.RINGER_MODE_NORMAL) {
					myImage.setImageDrawable(getResources().getDrawable(
							R.drawable.normal));
				} else if (mode == AudioManager.RINGER_MODE_SILENT) {
					myImage.setImageDrawable(getResources().getDrawable(
							R.drawable.mute));
				} else if (mode == AudioManager.RINGER_MODE_VIBRATE) {
					myImage.setImageDrawable(getResources().getDrawable(
							R.drawable.vibrate));
				}
			}
		});

		/* 音量调大声的Button */
		upButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 设置音量调大声一格 */
				audioMa.adjustVolume(AudioManager.ADJUST_RAISE, 0);
				volume = audioMa.getStreamVolume(AudioManager.STREAM_RING);
				myProgress.setProgress(volume);
				/* 设置调整后的声音模式 */
				int mode = audioMa.getRingerMode();
				if (mode == AudioManager.RINGER_MODE_NORMAL) {
					myImage.setImageDrawable(getResources().getDrawable(
							R.drawable.normal));
				} else if (mode == AudioManager.RINGER_MODE_SILENT) {
					myImage.setImageDrawable(getResources().getDrawable(
							R.drawable.mute));
				} else if (mode == AudioManager.RINGER_MODE_VIBRATE) {
					myImage.setImageDrawable(getResources().getDrawable(
							R.drawable.vibrate));
				}
			}
		});

		/* 调整铃声模式为正常模式的Button */
		normalButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 设置铃声模式为NORMAL */
				audioMa.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				/* 设置音量与声音模式 */
				volume = audioMa.getStreamVolume(AudioManager.STREAM_RING);
				myProgress.setProgress(volume);
				myImage.setImageDrawable(getResources().getDrawable(
						R.drawable.normal));
			}
		});

		/* 调整铃声模式为静音模式的Button */
		muteButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 设置铃声模式为SILENT */
				audioMa.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				/* 设置音量与声音状态 */
				volume = audioMa.getStreamVolume(AudioManager.STREAM_RING);
				myProgress.setProgress(volume);
				myImage.setImageDrawable(getResources().getDrawable(
						R.drawable.mute));
			}
		});

		/* 调整铃声模式为震动模式的Button */
		vibrateButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 设置铃声模式为VIBRATE */
				audioMa.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
				/* 设置音量与声音状态 */
				volume = audioMa.getStreamVolume(AudioManager.STREAM_RING);
				myProgress.setProgress(volume);
				myImage.setImageDrawable(getResources().getDrawable(
						R.drawable.vibrate));
			}
		});
	}
}
