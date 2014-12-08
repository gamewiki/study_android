package sid.utils;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

public class AppConstant {
	/** 设置文字颜色*/
	public static final int TEXT_COLOR = Color.rgb(62, 184, 248);
	/** 设置文字颜色*/
	public static final int TEXT_BG_DEFAULT_COLOR = Color.rgb(255, 255, 255);
	/** 设置文字颜色*/
	public static final int TEXT_BG_ONCLICK_COLOR = Color.rgb(27, 68, 254);
	/** 设置文字的字体大小*/
	public static final float CG_USER_TEXT_SIZE = 15f;
	/** 设置数据库版本*/
	public static final int VERSION = 4;
	public static List<String> items = new ArrayList<String>();
	static{
		items.add("重复");
		items.add("铃声");
		items.add("振动");
		items.add("标签");
	}
	public static List<String> paths = new ArrayList<String>();
	static{
		paths.add("重复");
		paths.add("铃声");
		paths.add("振动");
		paths.add("标签");
//		paths.add("一律不");
//		paths.add("默认铃声");
//		paths.add("");
//		paths.add("");
	}
	public static final int WEEKS = 0;
	public static final int INTERVAL = 1;
	public static final int BELLS = 2;
	public static final int SHOCK = 3;
	public static final int SIGNS = 4;
	public static final int TIMEPICKER = 5;
	public static final int DATEPICKER = 6;
	public static final int TITLE = 7;
	public static final int SHOCKONE = 20;
	public static final int SHOCKREP = 21;
	public static final int ButtonAlarm = 888;
	public static final String[] weeks = new String[] {"周日","周一","周二","周三","周四","周五","周六"};
	public static final String[] shocks = new String[] {"振动伴随响铃","响铃","振动"};
	public static final String[] signs = new String[] {"起床闹钟","便利贴提醒"};
	public static final String strAlarmFolder = "/music/alarm";
    //这边的Long型数组中，第一个参数是开始振动前等待的时间，第二个参数是第一次振动的时间，第三个参数是第二次振动的时间，以此类推，随便定义多长的数组。但是采用这种方法，没有办法做到重复振动。
	public static final long[] vibrate = {0,100,200,300,400,500,600,700,800,900,1000,
   		 1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,
   		 2100,2200,2300,2400,2500,2600,2700,2800,2900,3000,
   		 3100,3200,3300,3400,3500,3600,3700,3800,3900,4000,
   		 4100,4200,4300,4400,4500,4600,4700,4800,4900,5000,
   		 5100,5200,5300,5400,5500,5600,5700,5800,5900,6000,
   		 6100,6200,6300,6400,6500,6600,6700,6800,6900,7000,};
	
	/** 设置menu的关于按钮值*/
	public static final int ABOUT = 0;
	/** 设置menu的退出按钮值*/
	public static final int EXIT = 1;
	/** 设置关于文字的字体大小*/
	public static final float ABOUT_TEXT_SIZE = 20f;
	/** 便利贴表名*/
	public static final String NOTE = "note";
	/** 闹钟表名*/
	public static final String CLOCK = "clock";
	

	/** 百度日志应用参数*/
	public static final String TAG = "Baidu Mobstat";
}
