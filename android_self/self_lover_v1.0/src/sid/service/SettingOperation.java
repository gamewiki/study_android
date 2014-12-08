package sid.service;

import sid.lover.R;
import sid.lover.setting.SettingActivity;
import sid.modle.Setting;
import sid.utils.AppConstant;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class SettingOperation {
	private static final String column = "id,password,question,answer";
	/**
	 * Setting
	 * 根据id获取数据
	 * @param context
	 * @param id
	 * @return
	 */
	public static Setting getSetting(Context context){
		Setting setting = new Setting();
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.SETTING,new String[] { column },null,null, null, null,null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			String question = cursor.getString(cursor.getColumnIndex("question"));
			String answer = cursor.getString(cursor.getColumnIndex("answer"));
			setting.setId(id);
			setting.setPassword(password);
			setting.setQuestion(question);
			setting.setAnswer(answer);
		}
		cursor.close();
		sd.close();
		dbh.close();
		return setting;
	}
	/**
	 * Setting
	 * 根据id删除数据
	 * @param context
	 * @param id
	 */
	public static void deleteSetting(Context context,int id){
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		sd.delete(AppConstant.SETTING, "id=?", new String[] {id+"" });
		sd.close();
		dbh.close();
	}

	/**
	 * Setting
	 * 存储setting
	 * @param times
	 * @param createTime
	 * @param startTime
	 */
	public static Setting saveSetting(Context context,Setting setting,String password, String question, String answer,boolean exist) {
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.SETTING,new String[] { "id" },"password=? and question=? and answer=? ",
						new String[] {password+"",question+"",answer}, null, null,null);
		while (cursor.moveToNext()) {
			exist = false;
		}
		cursor.close();
		if (exist) {
			//添加便利贴内容
			ContentValues value = new ContentValues();
			value.put("password", password);
			value.put("question", question);
			value.put("answer", answer);
			int id = 0;
			if (setting!=null) {
				sd.update(AppConstant.SETTING, value, "id=?", new String[]{setting.getId()+""});
				id = setting.getId();
			} else {
				sd.insert(AppConstant.SETTING, null, value);
				id = getNewInsertId(sd, AppConstant.SETTING);
			}
			Toast.makeText(context,context.getString(R.string.addSuccess),Toast.LENGTH_SHORT).show();
			setting = new Setting(id, password, question, answer);
			sd.close();
			dbh.close();
			Intent intent = new Intent();
			intent.setClass(context, SettingActivity.class);
			context.startActivity(intent);
		}else{
			sd.close();
			dbh.close();
			Toast.makeText(context,
					context.getString(R.string.exist),Toast.LENGTH_SHORT).show();
		}
		return setting;
	}

	/**
	 * Setting
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
