package sid.utils;

import java.util.LinkedList;
import java.util.List;

import cn.waps.AppConnect;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class ExitApplication extends Application {
	private static List<Activity> activityList = new LinkedList<Activity>();
	private static ExitApplication instance;

	private ExitApplication() {
	}

	/**
	 * 单例模式中获取唯一的ExitApplication 实例
	 * @return
	 */
	public static ExitApplication getInstance() {
		if (instance==null) {
			synchronized (ExitApplication.class) {
				if (instance==null) {
					instance = new ExitApplication();
				}
			}
			instance = new ExitApplication();
		}
		return instance;
	}

	/**
	 * 添加Activity 到容器中
	 * @param activity
	 */
	@SuppressWarnings("static-access")
	public void addActivity(Activity activity) {
		ExitApplication.getInstance().activityList.add(activity);
	}

	/**
	 * 遍历所有Activity 并finish
	 */
	@SuppressWarnings("static-access")
	public void exit() {
		//多普广告
		//以下方法将用于释放SDK占用的系统资源
		AppConnect.getInstance(this).close();
		
		Log.d("exit", "activityList size is :"+ExitApplication.getInstance().activityList.size());
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

}