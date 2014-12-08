package sid.mp3player.service;

import java.io.File;

import sid.model.MP3Info;
import sid.utils.AppConstant;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

public class PlayerService extends Service {
	MP3Info mp3Info = null;
	MediaPlayer mediaPlayer = null;
	boolean isPlaying = false;
	boolean isPause = false;
	boolean isReleased = false;

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
		mp3Info = (MP3Info) intent.getSerializableExtra("mp3Info");
		int MSG = intent.getIntExtra("MSG", 0);
		if (mp3Info!=null) {
			if (MSG == AppConstant.PlayMSG.PLAY_MSG) {
				play();
			}
		}else{
			if (MSG == AppConstant.PlayMSG.PAUSE_MSG) {
				pause();
			}else if (MSG == AppConstant.PlayMSG.STOP_MSG) {
				stop();
			}
		}
	}

	private void play() {
		if (!isPlaying) {
			String path = getMp3Path(mp3Info);
			mediaPlayer = MediaPlayer.create(this, Uri.parse("file://"+path));
			//设置不循环播放
			mediaPlayer.setLooping(false);
			mediaPlayer.start();
			isPlaying = true;
			isReleased = false;
		}
	}
	private void pause() {
		if (mediaPlayer!=null) {
			if (!isReleased) {
				if (!isPause) {
					mediaPlayer.pause();
					isPlaying = false;
					isPause = true;
				}else{
					mediaPlayer.start();
					isPlaying = true;
					isPause = false;
				}
			}
		}
	}
	private void stop() {
		if (mediaPlayer!=null) {
			if (isPlaying) {
				if (!isReleased) {
					mediaPlayer.stop();
					isReleased = true;
				}
				isPlaying = false;
				isPause = false;
			}
		}
	}
	private String getMp3Path(MP3Info mp3Info){
		String sdCard = Environment.getExternalStorageDirectory().getAbsolutePath();
		String path = sdCard + File.separator + AppConstant.URL.BASE_URL_LOCAL_DIR + File.separator + mp3Info.getMp3Name();
		return path;
	};
}
