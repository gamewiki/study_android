package sid.utils;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;

public class ExitApplication extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();
	private static ExitApplication instance;

	private ExitApplication() {
	}

	/**
	 * 单例模式中获取唯一的ExitApplication 实例
	 * @return
	 */
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	/**
	 * 添加Activity 到容器中
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 遍历所有Activity 并finish
	 */
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}