package sid.mp3player.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import sid.model.MP3Info;
import sid.utils.AppConstant;
import sid.utils.LRCConvertCode;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;

public class PlayerService extends Service {
	MP3Info mp3Info = null;
	MediaPlayer mediaPlayer = null;
	/** 当前位置 */
	int position = 0;
	/** 歌曲列表 */
	private static List<MP3Info> mp3InfoList = null;
	/** 表示当前是否在播放MP3 初始值为false */
	boolean isPlaying = false;
	/** 表示当前歌曲是否为暂停状态 初始值为false */
	boolean isPause = false;
	/** 获取歌词列表，存放的是时间队列和歌词队列 */
	private ArrayList<Queue> queues = null;
	/** handler用于控制歌词更新 */
	private Handler handler = new Handler();
	/** 歌词更新回调函数 */
	private UpdateTimeCallback updateTimeCallback = null;
	/** 显示的歌词信息 */
	private String previousMessage = null;
	/** 显示的歌词信息 */
	private String message = null;
	/** 显示的歌词信息 */
	private String nextMessage = null;
	/** 显示的歌词信息是否是第一次进入 */
	public static int signMessage = AppConstant.MP3_START;
	/** 开始时间设置为0，为做时间偏移量做准备 */
	private long begin = 0;
	/** 下一次更新歌词的时间点 */
	private long nextTimeMill = 0;
	/** 当前的毫秒级时间 */
	private long currentTimeMill = 0;
	/** 暂停的毫秒级时间数 */
	private long pauseTimeMill = 0;
	/** 记录播放按钮的图片样式 */
	public static int IMAGE_BUTTON_STYLE_PLAYE = AppConstant.PlayMSG.IMAGE_BUTTON_PLAY;
	/** 记录循环按钮的图片样式 */
	public static int IMAGE_BUTTON_STYLE_CYCLE = AppConstant.PlayMSG.IMAGE_BUTTON_REPEATALL;
	/** 记录随即按钮的图片样式 */
	public static int IMAGE_BUTTON_STYLE_SEQUENCE = AppConstant.PlayMSG.IMAGE_BUTTON_RANDOM_SEQUENCE;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/**
	 * 每当用户点击列表中的一条数据时 就会启动一个下载service后，启动这个方法 生成一个下载线程 并将MP3Info对象作为参数传递到线程当中
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		MP3Info mp3InfoTemp = (MP3Info) intent.getSerializableExtra("mp3Info");
		int MSG = intent.getIntExtra("MSG", 0);
		String isNew = intent.getStringExtra("isNew");
		int changeSign = intent.getIntExtra("chageSign",
				AppConstant.PlayMSG.NOCANGE_MSG);
		int positionTemp = intent.getIntExtra("position", -2);
		if (positionTemp == -2) {
			position = positionTemp;
		}
		ArrayList<List<MP3Info>> localMp3List = null;
		localMp3List = (ArrayList) intent.getSerializableExtra("localMp3List");
		if (localMp3List != null) {
			mp3InfoList = localMp3List.get(0);
		}
		if (mp3Info != null) {
			if (changeSign != AppConstant.PlayMSG.NOCANGE_MSG
					&& MSG == AppConstant.PlayMSG.NO_MSG) {
				operatePlayer(MSG, changeSign);
				// 如果两首歌的id相等，表示播放的仍然是当前歌曲；否则是新的歌曲
			} else if (mp3Info.getId() == mp3InfoTemp.getId()) {
				if (!(isNew != null && isNew.equals(AppConstant.PlayMSG.IS_NEW))) {
					operatePlayer(MSG, changeSign);
				}
				return;
			} else {
				isPlaying = false;
				isPause = false;
				stop();
				mp3Info = mp3InfoTemp;
			}
		} else {
			mp3Info = mp3InfoTemp;
		}
		if (mp3Info != null) {
			operatePlayer(MSG, changeSign);
		}
	}

	/**
	 * 根据用户操作执行不同 的方法
	 * 
	 * @param MSG
	 */
	private void operatePlayer(int MSG, int changeSign) {
		if (MSG == AppConstant.PlayMSG.PLAY_MSG) {
			if (isPlaying) {
				pause();
			} else {
				play();
			}
			if (PlayerService.IMAGE_BUTTON_STYLE_PLAYE == AppConstant.PlayMSG.IMAGE_BUTTON_PLAY) {
				IMAGE_BUTTON_STYLE_PLAYE = AppConstant.PlayMSG.IMAGE_BUTTON_PAUSE;
			} else {
				IMAGE_BUTTON_STYLE_PLAYE = AppConstant.PlayMSG.IMAGE_BUTTON_PLAY;
			}
		} else if (MSG == AppConstant.PlayMSG.NO_MSG) {
			change(changeSign);
		}
	}

	/**
	 * 播放按钮
	 */
	private void play() {
		if (!isPlaying && mp3Info != null) {
			String path = getMp3Path(mp3Info);
			mediaPlayer = MediaPlayer.create(this, Uri.parse("file://" + path));
			mediaPlayer.setOnCompletionListener(new MyCompletionListener());
			// 设置不循环播放
			mediaPlayer.setLooping(false);
			mediaPlayer.start();
			// 读取Lrc文件
			prepareLrc(mp3Info.getLrcName());
			// 将begin的值置为当前毫秒数
			begin = System.currentTimeMillis();
			handler.postDelayed(updateTimeCallback, 5);
			isPlaying = isPlaying ? false : true;
			IMAGE_BUTTON_STYLE_PLAYE = AppConstant.PlayMSG.IMAGE_BUTTON_PLAY;
		}
	}

	/**
	 * 监听歌曲是否播放完成
	 * 
	 * @author Administrator
	 * 
	 */
	class MyCompletionListener implements OnCompletionListener {
		@Override
		public void onCompletion(MediaPlayer mp) {
			System.out.println("歌曲播放完成，继续下一首歌");
			change(AppConstant.PlayMSG.NEXT_MSG);
		}
	}

	/**
	 * 暂停按钮
	 */
	private void pause() {
		if (mediaPlayer != null) {
			if (!isPause) {
				mediaPlayer.pause();
				handler.removeCallbacks(updateTimeCallback);
				pauseTimeMill = System.currentTimeMillis();
			} else {
				mediaPlayer.start();
				handler.postDelayed(updateTimeCallback, 5);
				begin = System.currentTimeMillis() - pauseTimeMill + begin;
			}
			isPause = isPause ? false : true;
		}
	}

	/**
	 * 停止按钮
	 */
	private void stop() {
		if (mediaPlayer != null) {
			handler.removeCallbacks(updateTimeCallback);
			mediaPlayer.stop();
			mediaPlayer.release();
			isPlaying = false;
			isPause = false;
			signMessage = AppConstant.MP3_START;
		}
	}

	/**
	 * 上一曲下一曲播放方法 sign = AppConstant.PlayMSG.NEXT_MSG表示下一曲 sign =
	 * AppConstant.PlayMSG.PREVIOUS_MSG表示上一曲 sign =
	 * AppConstant.PlayMSG.NOCANGE_MSG表示不进行循环播放
	 * 
	 * @param sign
	 */
	private void change(int sign) {
		getNextMp3(sign);
		stop();
		play();
	}

	/**
	 * 获取MP3文件路径
	 * 
	 * @param mp3Info
	 * @return
	 */
	private String getMp3Path(MP3Info mp3Info) {
		String sdCard = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String path = sdCard + File.separator
				+ AppConstant.URL.BASE_URL_LOCAL_DIR + File.separator
				+ mp3Info.getMp3Name();
		return path;
	};

	/**
	 * 根据歌词文件的名字读文件的信息
	 * 
	 * @param lrcName
	 */
	private void prepareLrc(String lrcName) {
		String pathFile = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator
				+ AppConstant.URL.BASE_URL_LOCAL_DIR
				+ File.separator
				+ mp3Info.getLrcName();
		LRCConvertCode LRCConvertCode = new LRCConvertCode();
		queues = LRCConvertCode.process(pathFile);
		// 创建一个UpdateTimeCallbakc对象
		updateTimeCallback = new UpdateTimeCallback(queues);
		begin = 0;
		currentTimeMill = 0;
		nextTimeMill = 0;
	}

	class UpdateTimeCallback implements Runnable {
		Queue times = null;
		Queue messages = null;

		public UpdateTimeCallback(ArrayList<Queue> queues) {
			times = queues.get(0);
			messages = queues.get(1);
			nextMessage = (String) messages.poll();
		}

		@Override
		public void run() {
			// 计算偏移量，即从开始播放到现在消耗了多久；以毫秒为单位
			long offset = System.currentTimeMillis() - begin;
			if (currentTimeMill == 0) {
				// 下一次更新歌词的时间点
				nextTimeMill = (Long) times.poll();
				nextMessage = (String) messages.poll();
			}
			if (offset >= nextTimeMill) {
				previousMessage = message;
				if (signMessage != AppConstant.MP3_START) {
					message = nextMessage;
				} else {
					signMessage = AppConstant.MP3_PLAY;
				}
				// 判断时间后显示
				if (times != null && times.size() != 0) {
					nextTimeMill = (Long) times.poll();
				}
				if (messages != null && messages.size() != 0) {
					nextMessage = (String) messages.poll();
				}
				Intent intent = new Intent();
				intent.setAction(AppConstant.LRC_MESSAGE_ACTION);
				intent.putExtra("previousMessage", previousMessage);
				intent.putExtra("message", message);
				intent.putExtra("nextMessage", nextMessage);
				sendBroadcast(intent);
			}
			currentTimeMill = currentTimeMill + 10;
			// 使用handler再次执行；每隔10毫秒
			handler.postDelayed(updateTimeCallback, 10);
		}
	}

	/**
	 * 获取下一个播放的MP3文件对象
	 * 
	 * @return
	 */
	public MP3Info getNextMp3(int sign) {
		if (IMAGE_BUTTON_STYLE_SEQUENCE == AppConstant.PlayMSG.IMAGE_BUTTON_RANDOM) {
			randomMp3();
		} else {
			if (PlayerService.IMAGE_BUTTON_STYLE_CYCLE == AppConstant.PlayMSG.IMAGE_BUTTON_REPEATALL) {
				sequenceMP3(sign);
			} else if (PlayerService.IMAGE_BUTTON_STYLE_CYCLE == AppConstant.PlayMSG.IMAGE_BUTTON_CLOSE_REPEAT) {
				sequenceMP3(AppConstant.PlayMSG.NOCANGE_MSG);
			}
		}
		return null;
	}

	/**
	 * 随机产生一个MP3
	 * 
	 */
	private MP3Info randomMp3() {
		Random random = new Random(System.currentTimeMillis());
		int size = mp3InfoList.size();
		int index = random.nextInt(size);
		System.out.println("当前的index随即数为：" + index);
		position = index > 0 ? index : -index;
		mp3Info = mp3InfoList.get(position);
		System.out.println("当前的position随即数为：" + position);
		return mp3Info;
	}

	/**
	 * 按照顺序产生MP3 sign = AppConstant.PlayMSG.NEXT_MSG表示下一曲 sign =
	 * AppConstant.PlayMSG.PREVIOUS_MSG表示上一曲 sign =
	 * AppConstant.PlayMSG.NOCANGE_MSG表示不进行循环播放
	 * 
	 * @param sign
	 */
	private MP3Info sequenceMP3(int sign) {
		if (sign == AppConstant.PlayMSG.NEXT_MSG) {
			if (position + 1 < mp3InfoList.size()) {
				mp3Info = mp3InfoList.get(++position);
			} else {
				position = 0;
				mp3Info = mp3InfoList.get(position);
			}
		} else if (sign == AppConstant.PlayMSG.PREVIOUS_MSG) {
			if (position - 1 >= 0) {
				mp3Info = mp3InfoList.get(--position);
			} else {
				position = mp3InfoList.size() - 1;
				mp3Info = mp3InfoList.get(position);
			}
		} else if (sign == AppConstant.PlayMSG.NOCANGE_MSG) {
			if (position + 1 < mp3InfoList.size()) {
				mp3Info = mp3InfoList.get(++position);
			} else {
				mp3Info = null;
			}
		}
		System.out.println("这是下一首歌：" + mp3Info);
		return mp3Info;
	}
}
