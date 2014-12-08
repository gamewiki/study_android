package sid.utils;

public interface AppConstant {
	/** 设置广播的action名称*/
	public static final String LRC_MESSAGE_ACTION = "sid.mp3.lrc.action";
	/** 关于阿良播放器*/
	public static final String ABOUT_MP3_PLAYER = "关于阿良播放器";
	/** 本地歌曲*/
	public static final String MP3_LOCAL_LIST = "本地歌曲";
	/** mp3歌词播放开始*/
	public static final int MP3_START = 201;
	/** mp3歌词播放进行*/
	public static final int MP3_PLAY = 202;
	/** menu菜单内容 设置*/
	public static final int SETTING = 101;
	/** menu菜单内容 退出*/
	public static final int EXIT = 102;
	/** 关于播放器的第一部分*/
	public static final String ABOUT_MP3_PART1 = "    阿良播放器v1.0，本程序默认歌曲存储位置为SD卡根目录下的"
												+"MUSIC文件夹还有很多需要改进的地方。包括应用的布局，"
												+"功能的改进，但是由于是自己的第一个小程序，所以还是很兴奋，"
												+"就提前发布了，希望朋友们有时间发给我你们的已经。";
	/** 关于播放器的第二部分*/
	public static final String ABOUT_MP3_PART2 = "    阿良播放器是由一个名字叫阳小良的程序员单独完成编写的，小良"
												+"本身是个爱好多样的人，喜欢画画，喜欢运动，当然也喜欢自己编一些"
												+"小程序，这个播放器，是阿良自学android后编写的第一个应用，还有许"
												+"多需要改进的地方，希望用到的朋友，可以把修改意见发送给小良，"
												+"小良的联系方式如下：";
	/** 关于播放器的第三部分*/
	public static final String ABOUT_MP3_PART3 = "    阿良的邮箱：devilzy2656@163.com \n "
												+"    人人公共主页：阳小良 \n "
												+"    新浪微博：@阳小良 \n ";
	/** 关于播放器的第一部分*/
	public static final String ERROR_1 = " 播放器默认的播放路径在sd卡的MUSIC目录下，目前不支持其他路径读取文件。为您带来的不便敬请谅解";

	public class PlayMSG{
		/** 播放状态*/
		public static final int PLAY_MSG = 1;
		/** 暂停状态*/
		public static final int PAUSE_MSG = 2;
		/** 停止状态*/
		public static final int STOP_MSG = 3;
		/** 无变状态*/
		public static final int NO_MSG = 4;
		/** 上一曲状态*/
		public static final int PREVIOUS_MSG = -1;
		/** 下一曲状态*/
		public static final int NEXT_MSG = +1;
		/** 无改变状态*/
		public static final int NOCANGE_MSG = 0;
		/** 改变歌曲状态*/
		public static final int CHANGE_MSG = 6;
		/** 判断当前是不是新歌播放*/
		public static final String IS_NEW = "true";
		/** 判断是不是列表的边界*/
		public static final String IS_BORDER = "border";
		/** 按钮的播放图片*/
		public static final int IMAGE_BUTTON_PLAY = 7;
		/** 按钮的暂停图片*/
		public static final int IMAGE_BUTTON_PAUSE = 8;
		/** 按钮的全部循环图片*/
		public static final int IMAGE_BUTTON_REPEATALL = 9;
		/** 按钮的单曲循环图片*/
		public static final int IMAGE_BUTTON_REPEATONE = 10;
		/** 按钮的列表播放图片*/
		public static final int IMAGE_BUTTON_SEQUENCE = 11;
		/** 按钮的关闭循环播放图片*/
		public static final int IMAGE_BUTTON_CLOSE_REPEAT = 12;
		/** 按钮的随机播放图片*/
		public static final int IMAGE_BUTTON_RANDOM = 13;
		/** 按钮的顺序播放图片*/
		public static final int IMAGE_BUTTON_RANDOM_SEQUENCE = 14;
	}
	
	public class URL{
		/** MP3下载地址*/
		public static final String BASE_URL_MP3 = "http://192.168.1.183:8080/mp3/MP3/";
		/** 歌曲列表的下载地址*/
		public static final String BASE_URL_RESOURCE = "http://192.168.1.183:8080/mp3/resource.xml";
		/** 保存在本地sdcard的地址路径*/
		public static final String BASE_URL_LOCAL_DIR = "MUSIC";
	}

}
