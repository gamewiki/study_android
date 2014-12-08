package sid.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sid.modle.Clock;
import sid.note.ClockActivity;
import sid.note.R;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DatabaseOperation {
	private static List<Clock> clocks = new ArrayList<Clock>();
	/**
	 * Clock
	 * 根据id获取数据
	 * @param context
	 * @param id
	 * @return
	 */
	public static Clock getClock(Context context,int id){
		Clock clock = new Clock();
		DatabaseHelper dbh = new DatabaseHelper(context, "note_sid_db",AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { "id,clockdate,clocktime,createtime,startime,hour,minute,title,weeks,interval,bells,bellsPath,shockCate,signCate" },"id=?",
						new String[] {id+""}, null, null,null);
		setClocks(cursor,clock,false);
		cursor.close();
		sd.close();
		dbh.close();
		return clock;
	}
	/**
	 * Clock
	 * 根据id删除数据
	 * @param context
	 * @param id
	 */
	public static void deleteClock(Context context,int id){
		DatabaseHelper dbh = new DatabaseHelper(context, "note_sid_db",AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		sd.delete(AppConstant.CLOCK, "id=?", new String[] {id+"" });
		sd.close();
		dbh.close();
		AlarmHelper ah = new AlarmHelper(context);
		ah.closeAlarm(id);
	}
	/**
	 * Clock
	 * 获取所有数据
	 * @param context
	 * @return
	 */
	public static List<Clock> getClocks(Context context){
		clocks = new ArrayList<Clock>();
		Clock clock = new Clock();
		DatabaseHelper dbh = new DatabaseHelper(context,"note_sid_db", AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { "id,clockdate,clocktime,createtime,startime,hour,minute,title,weeks,interval,bells,bellsPath,shockCate,signCate" },
				null,new String[] {}, null, null,null);
		setClocks(cursor,clock,true);
		cursor.close();
		sd.close();
		dbh.close();
		return clocks;
	}
	/**
	 * 循环设置clock的信息
	 * @param cursor
	 */
	private static void setClocks(Cursor cursor,Clock clock,boolean addList) {
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String date = cursor.getString(cursor.getColumnIndex("clockdate"));
			String time = cursor.getString(cursor.getColumnIndex("clocktime"));
			String createtime = cursor.getString(cursor.getColumnIndex("createtime"));
			long startime = cursor.getLong(cursor.getColumnIndex("startime"));
			int hour = cursor.getInt(cursor.getColumnIndex("hour"));
			int minute = cursor.getInt(cursor.getColumnIndex("minute"));
			String title = cursor.getString(cursor.getColumnIndex("title"));
			String weeks = cursor.getString(cursor.getColumnIndex("weeks"));
			String interval = cursor.getString(cursor.getColumnIndex("interval"));
			String bells = cursor.getString(cursor.getColumnIndex("bells"));
			String bellsPath = cursor.getString(cursor.getColumnIndex("bellsPath"));
			String shockCate = cursor.getString(cursor.getColumnIndex("shockCate"));
			String signCate = cursor.getString(cursor.getColumnIndex("signCate"));
			if (addList) {
				clock = new Clock();
			}
			clock.setId(id);
			clock.setClockdate(date);
			clock.setClocktime(time);
			clock.setCreatetime(createtime);
			clock.setStartime(startime);
			clock.setHour(hour);
			clock.setMinute(minute);
			clock.setTitle(title);
			clock.setWeeks(weeks);
			clock.setInterval(interval);
			clock.setBells(bells);
			clock.setBellsPath(bellsPath);
			clock.setShockCate(shockCate);
			clock.setSignCate(signCate);
			if (addList) {
				clocks.add(clock);
			}
		}
	}

	/**
	 * Clock
	 * 存储闹钟
	 * @param times
	 * @param createTime
	 * @param startTime
	 * @param title
	 * @param weeks
	 * @param interval
	 * @param bells
	 * @param shockCate
	 * @param signCate
	 * @param content
	 * @param exist
	 */
	public static Clock saveClock(boolean isToday,Context context,Clock clock,int times, String date, String time, String createtime, 
			long startime,int hour,int minute, String title, String weeks, String interval, String bells,String bellsPath,
			String shockCate, String signCate, boolean exist) {
		AlarmHelper ah = new AlarmHelper(context);
		Clock clockTemp = new Clock(0, date, time, createtime,
				startime, hour, minute, title, weeks,
				interval, bells, bellsPath, shockCate, signCate);
		if ((signCate.equals(AppConstant.signs[0]))&&(!isToday)){
			startime = ah.getNextStartTime(clockTemp, Calendar.getInstance(), new Date());
		}
		
		DatabaseHelper dbh = new DatabaseHelper(context, "note_sid_db",AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { "id" },"hour=? and minute=? and weeks=? and title=?",
						new String[] {hour+"",minute+"",weeks+"",title}, null, null,null);
		while (cursor.moveToNext()) {
			exist = false;
		}
		cursor.close();
		if (exist) {
			//添加便利贴内容
			ContentValues value = new ContentValues();
			value.put("clockdate", date);
			value.put("clocktime", time);
			value.put("createtime", createtime);
			value.put("startime", startime);
			value.put("hour", hour);
			value.put("minute", minute);
			value.put("title", title);
			value.put("weeks", weeks);
			value.put("interval", interval);
			value.put("bells", bells);
			value.put("bellsPath", bellsPath);
			value.put("shockCate", shockCate);
			value.put("signCate",signCate);
			System.out.println("--------------clock-------------"+clock);
			if (clock!=null) {
				sd.update(AppConstant.CLOCK, value, "id=?", new String[]{clock.getId()+""});
			} else {
				sd.insert(AppConstant.CLOCK, null, value);
			}
			Toast.makeText(context,context.getString(R.string.addClockSuccess),Toast.LENGTH_SHORT).show();
			int id = getNewInsertId(sd, AppConstant.CLOCK);
			clock = new Clock(id, date, time, createtime,
					startime, hour, minute, title, weeks,
					interval, bells, bellsPath, shockCate, signCate);
			sd.close();
			dbh.close();
			Intent intent = new Intent();
			intent.setClass(context, ClockActivity.class);
			context.startActivity(intent);
		}else{
			sd.close();
			dbh.close();
			Toast.makeText(context,
					context.getString(R.string.clockExist),Toast.LENGTH_SHORT).show();
		}


		ah.setNextRingTime(clock, ah);
		return clock;
	}

	/**
	 * Clock
	 * 获取最新插入的id
	 * @param sd
	 * @param table
	 * @return
	 */
	private static int getNewInsertId(SQLiteDatabase sd,String table) {
		Cursor cursorLast = sd.rawQuery("select last_insert_rowid() from "+table,null);
		int strid = 0;
		if(cursorLast.moveToFirst())
		strid = cursorLast.getInt(0);
		return Integer.valueOf(strid);
	}
}
