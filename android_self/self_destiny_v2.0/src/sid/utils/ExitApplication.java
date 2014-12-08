package sid.utils;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

import com.waps.AppConnect;

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
		//多普广告
		//以下方法将用于释放SDK占用的系统资源
		AppConnect.getInstance(this).finalize();
		
		for (Activity activity : activityList) {
			activity.finish();
		}
		
		
		System.exit(0);
	}
}