package sid.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sid.lover.R;
import sid.modle.Clock;
import sid.utils.AppConstant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ClockOperation {
	private static final String column = "id,clockdate,clocktime,createtime,startime,storytime,hour,minute," +
			"title,description,weeks,interval,bells,bellsPath,imagePath,shockCate,signCate,status,creater";
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
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { column },"id=?",
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
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		sd.delete(AppConstant.CLOCK, "id=?", new String[] {id+"" });
		sd.close();
		dbh.close();
		AlarmHelper ah = new AlarmHelper(context);
		ah.closeAlarm(id);
	}

	/**
	 * Clock
	 * 存储闹钟
	 */
	public static Clock saveClock(boolean isToday,Context context,Clock clock,int times, String date, String time, 
			String createtime, long startime,long storytime,int hour,int minute, String title,
			String description,String weeks, String interval, String bells,String bellsPath,String imagePath,
			String shockCate, int signCate,int status, boolean exist) {
		AlarmHelper ah = new AlarmHelper(context);
		Clock clockTemp = new Clock(0, date, time, createtime,
				startime,storytime, hour, minute, title,description, weeks,
				interval, bells, bellsPath,imagePath, shockCate, signCate,status,AppConstant.CREATER);
		if ((signCate==AppConstant.signs[0])&&(!isToday)){
			startime = ah.getNextStartTime(clockTemp, Calendar.getInstance(), new Date());
		}
		
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
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
			imagePath = (imagePath!=null?imagePath.trim():null);
			//添加便利贴内容
			ContentValues value = new ContentValues();
			value.put("clockdate", date);
			value.put("clocktime", time);
			value.put("createtime", createtime);
			value.put("startime", startime);
			value.put("storytime", storytime);
			value.put("hour", hour);
			value.put("minute", minute);
			value.put("title", title);
			value.put("description", description);
			value.put("weeks", weeks);
			value.put("interval", interval);
			value.put("bells", bells);
			value.put("bellsPath", bellsPath);
			value.put("imagePath", imagePath);
			value.put("shockCate", shockCate);
			value.put("signCate",signCate);
			value.put("status",status);
			value.put("creater",AppConstant.CREATER);
			int id = 0;
			if (clock!=null) {
				sd.update(AppConstant.CLOCK, value, "id=?", new String[]{clock.getId()+""});
				id = clock.getId();
			} else {
				sd.insert(AppConstant.CLOCK, null, value);
				id = getNewInsertId(sd, AppConstant.CLOCK);
			}
			Toast.makeText(context,context.getString(R.string.addClockSuccess),Toast.LENGTH_SHORT).show();
			clock = new Clock(id, date, time, createtime,
					startime,storytime, hour, minute, title,description, weeks,
					interval, bells, bellsPath,imagePath, shockCate, signCate,status,AppConstant.CREATER);
			sd.close();
			dbh.close();
//			Intent intent = new Intent();
//			if (signCate==AppConstant.signs[0]){
//				intent.setClass(context, LoverActivity.class);
//			}else if(signCate==AppConstant.signs[1]){
//				intent.setClass(context, LoverActivity.class);
//			}else if(signCate==AppConstant.signs[2]){
//				intent.setClass(context, SendMsgActivity.class);
//			}else{
//				intent.setClass(context, LoverActivity.class);
//			}
//			context.startActivity(intent);
		}else{
			sd.close();
			dbh.close();
			Toast.makeText(context,
					context.getString(R.string.clockExist),Toast.LENGTH_SHORT).show();
		}

		if (status==1) {
			if (clock==null) {
				Toast.makeText(context, "同一时间的提醒已经存在，请更换其他时间", Toast.LENGTH_LONG);
			}else{
				ah.setNextRingTime(clock, ah);
			}
		}
		return clock;
	}

	/**
	 * Clock
	 * 更新存储闹钟
	 */
	public static Clock updateClock(Context context,Clock clock) {
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { "id" },"hour=? and minute=? and weeks=? and title=?",
						new String[] {clock.getHour()+"",clock.getMinute()+"",clock.getWeeks()+"",clock.getTitle()},
						null, null,null);
		boolean exist = false;
		while (cursor.moveToNext()) {
			exist = true;
		}
		cursor.close();
		if (exist) {
			//添加便利贴内容
			ContentValues value = new ContentValues();
			value.put("clockdate", clock.getClockdate());
			value.put("clocktime", clock.getClocktime());
			value.put("createtime", clock.getCreatetime());
			value.put("startime", clock.getStartime());
			value.put("storytime", clock.getStorytime());
			value.put("hour", clock.getHour());
			value.put("minute", clock.getMinute());
			value.put("title", clock.getTitle());
			value.put("description", clock.getDescription());
			value.put("weeks", clock.getWeeks());
			value.put("interval", clock.getInterval());
			value.put("bells", clock.getBells());
			value.put("bellsPath", clock.getBellsPath());
			value.put("imagePath", clock.getImagePath());
			value.put("shockCate", clock.getShockCate());
			value.put("signCate",clock.getSignCate());
			value.put("status",clock.getStatus());
			value.put("creater",clock.getCreater());
			sd.update(AppConstant.CLOCK, value, "id=?", new String[]{clock.getId()+""});
			Toast.makeText(context,context.getString(R.string.updateClockSuccess),Toast.LENGTH_SHORT).show();
			sd.close();
			dbh.close();
		}else{
			sd.close();
			dbh.close();
			Toast.makeText(context,context.getString(R.string.note_miss),Toast.LENGTH_SHORT).show();
		}

		return clock;
	}

	/**
	 * Clock
	 * 根据类型获取闹钟
	 * @param context
	 * @return
	 */
	public static List<Clock> getClocksBySign(Context context,int sign){
		clocks = new ArrayList<Clock>();
		Clock clock = new Clock();
		DatabaseHelper dbh = new DatabaseHelper(context,AppConstant.DATABASE, AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { column },
				"signCate=?",new String[] {sign+""}, null, null," storytime desc ");
		setClocks(cursor,clock,true);
		cursor.close();
		sd.close();
		dbh.close();
		return clocks;
	}
	/**
	 * Clock
	 * 获取所有开启的数据
	 * @param context
	 * @return
	 */
	public static List<Clock> getClocksOpenRing(Context context){
		clocks = new ArrayList<Clock>();
		Clock clock = new Clock();
		DatabaseHelper dbh = new DatabaseHelper(context,AppConstant.DATABASE, AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		Cursor cursor;
		cursor = sd.query(AppConstant.CLOCK,new String[] { column },
				"status=?",new String[] {1+""}, null, null,null);
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
			long storytime = cursor.getLong(cursor.getColumnIndex("storytime"));
			int hour = cursor.getInt(cursor.getColumnIndex("hour"));
			int minute = cursor.getInt(cursor.getColumnIndex("minute"));
			String title = cursor.getString(cursor.getColumnIndex("title"));
			String description = cursor.getString(cursor.getColumnIndex("description"));
			String weeks = cursor.getString(cursor.getColumnIndex("weeks"));
			String interval = cursor.getString(cursor.getColumnIndex("interval"));
			String bells = cursor.getString(cursor.getColumnIndex("bells"));
			String bellsPath = cursor.getString(cursor.getColumnIndex("bellsPath"));
			String imagePath = cursor.getString(cursor.getColumnIndex("imagePath"));
			String shockCate = cursor.getString(cursor.getColumnIndex("shockCate"));
			int signCate = cursor.getInt(cursor.getColumnIndex("signCate"));
			int status = cursor.getInt(cursor.getColumnIndex("status"));
			int creater = cursor.getInt(cursor.getColumnIndex("creater"));
			if (addList) {
				clock = new Clock();
			}
			clock.setId(id);
			clock.setClockdate(date);
			clock.setClocktime(time);
			clock.setCreatetime(createtime);
			clock.setStartime(startime);
			clock.setStorytime(storytime);
			clock.setHour(hour);
			clock.setMinute(minute);
			clock.setTitle(title);
			clock.setDescription(description);
			clock.setWeeks(weeks);
			clock.setInterval(interval);
			clock.setBells(bells);
			clock.setBellsPath(bellsPath);
			clock.setImagePath(imagePath!=null?imagePath.trim():null);
			clock.setShockCate(shockCate);
			clock.setSignCate(signCate);
			clock.setStatus(status);
			clock.setCreater(creater);
			if (addList) {
				clocks.add(clock);
			}
		}
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
