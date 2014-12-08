package com.sid.appwidgetpanddingintent;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {



	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		System.out.println("onUpdate");
		for (int i : appWidgetIds) {
			System.out.println("appwidgetID对象："+i);
			//创建一个Intent对象
			Intent intent = new Intent(context, TargetActivity.class);
			//创建一个pendingIntent对象:三种：getActivity、getBrodcast、getService
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
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
	
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive");
		super.onReceive(context, intent);
	}

}
