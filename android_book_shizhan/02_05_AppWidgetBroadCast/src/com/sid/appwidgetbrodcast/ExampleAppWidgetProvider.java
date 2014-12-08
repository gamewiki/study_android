package com.sid.appwidgetbrodcast;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
	/** 自定义action**/
	private static final String UPDATE_ACTION = "sid.appwidget.UPDATE_APPWIDGET_ACTION";

	
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive");
		String action = intent.getAction();
		//截获自己发送的广播事件
		if (action.endsWith(UPDATE_ACTION)) {
			System.out.println("在OnRecevie中获取broadCast--->"+UPDATE_ACTION);
			//修改remoteViews的内容，可以修改图片，文本，等等
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
										R.layout.example_appwidget);
			remoteViews.setTextViewText(R.id.widgetTextId, "mmmmm");
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			//获取整个widget
			ComponentName componentName = new ComponentName(context, ExampleAppWidgetProvider.class);
			appWidgetManager.updateAppWidget(componentName, remoteViews);
		}else{
			super.onReceive(context, intent);
		}
	}
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		System.out.println("onUpdate");
		for (int i : appWidgetIds) {
			System.out.println("appwidgetID对象："+i);
			//创建一个Intent对象并设置action
			Intent intent = new Intent();
			intent.setAction(UPDATE_ACTION);
			//创建一个pendingIntent对象:三种：getActivity、getBrodcast、getService
			//当该对象执行是会发送一个广播，发送的action为intent中的action
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
			//为按钮绑定事件处理器
			//第一个参数用来指定被绑定处理器的控件ID
			//第二个参数用来指定当事件发生时，哪个PendingIntent将会执行
			remoteViews.setOnClickPendingIntent(R.id.widgetButton, pendingIntent);
			//更新appwidget
			//第一个参数用于指定被更新AppwidgetId
			//第二个参数要更新的remoteViews的对象
			appWidgetManager.updateAppWidget(i, remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		System.out.println("onDeleted");
		super.onDeleted(context, appWidgetIds);
	}
	
	@Override
	public void onDisabled(Context context) {
		System.out.println("onDisabled");
		super.onDisabled(context);
	}
	
	@Override
	public void onEnabled(Context context) {
		System.out.println("onEnabled");
		super.onEnabled(context);
	}

}
