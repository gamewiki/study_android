package sid.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sid.modle.SendMsg;
import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.send.msg.SendMsgActivity;

public class DatabaseOperation {
	private static List<SendMsg> sendmsgs = new ArrayList<SendMsg>();
	/**
	 * SendMsg
	 * 根据id获取数据
	 * @param context
	 * @param id
	 * @return
	 */
	public static SendMsg getSendMsg(Context context,int id){
		SendMsg clock = new SendMsg();
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.SENDMSG,new String[] { "id,hour,minute,content" },"id=?",
						new String[] {id+""}, null, null,null);
		setSendMsgs(cursor,clock,false);
		cursor.close();
		sd.close();
		dbh.close();
		return clock;
	}
	/**
	 * SendMsg
	 * 根据id删除数据
	 * @param context
	 * @param id
	 */
	public static void deleteSendMsg(Context context,int id){
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		sd.delete(AppConstant.SENDMSG, "id=?", new String[] {id+"" });
		sd.close();
		dbh.close();
		AlarmHelper ah = new AlarmHelper(context);
		ah.closeAlarm(id);
	}
	/**
	 * SendMsg
	 * 获取所有数据
	 * @param context
	 * @return
	 */
	public static List<SendMsg> getSendMsgs(Context context){
		sendmsgs = new ArrayList<SendMsg>();
		SendMsg sendmsg = new SendMsg();
		DatabaseHelper dbh = new DatabaseHelper(context,"note_sid_db", AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		Cursor cursor;
		cursor = sd.query(AppConstant.SENDMSG,new String[] { "id,hour,minute,content" },
				null,new String[] {}, null, null,null);
		setSendMsgs(cursor,sendmsg,true);
		cursor.close();
		sd.close();
		dbh.close();
		return sendmsgs;
	}
	/**
	 * 循环设置SendMsg的信息
	 * @param cursor
	 */
	private static void setSendMsgs(Cursor cursor,SendMsg sendmsg,boolean addList) {
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int hour = cursor.getInt(cursor.getColumnIndex("hour"));
			int minute = cursor.getInt(cursor.getColumnIndex("minute"));
			String content = cursor.getString(cursor.getColumnIndex("content"));
			if (addList) {
				sendmsg = new SendMsg();
			}
			sendmsg.setId(id);
			sendmsg.setHour(hour);
			sendmsg.setMinute(minute);
			sendmsg.setContent(content);
			if (addList) {
				sendmsgs.add(sendmsg);
			}
		}
	}

	/**
	 * SendMsg
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
	public static SendMsg saveSendMsg(boolean isToday,Context context,SendMsg clock,int times, String date, String time, String createtime, 
			long startime,int hour,int minute, String title, String weeks, String interval, String bells,String bellsPath,
			String shockCate, String signCate, boolean exist) {
		AlarmHelper ah = new AlarmHelper(context);
		SendMsg clockTemp = new SendMsg(0,hour, minute, title,title);
		if ((signCate.equals(AppConstant.signs[0]))&&(!isToday)){
			startime = ah.getNextStartTime(clockTemp, Calendar.getInstance(), new Date());
		}
		
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.DATABASE,new String[] { "id" },"hour=? and minute=? and moble=? and content=?",
						new String[] {hour+"",minute+"",weeks+"",title}, null, null,null);
		while (cursor.moveToNext()) {
			exist = false;
		}
		cursor.close();
		if (exist) {
			//添加便利贴内容
			ContentValues value = new ContentValues();
			value.put("hour", hour);
			value.put("minute", minute);
			value.put("moble", moble);
			value.put("content", content);
			System.out.println("--------------clock-------------"+clock);
			if (clock!=null) {
				sd.update(AppConstant.DATABASE, value, "id=?", new String[]{clock.getId()+""});
			} else {
				sd.insert(AppConstant.DATABASE, null, value);
			}
			int id = getNewInsertId(sd, AppConstant.CLOCK);
			clock = new SendMsg(id,hour, minute, title);
			sd.close();
			dbh.close();
			Intent intent = new Intent();
			intent.setClass(context, SendMsgActivity.class);
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
	 * SendMsg
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
