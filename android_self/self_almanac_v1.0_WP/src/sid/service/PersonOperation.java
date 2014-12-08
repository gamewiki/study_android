package sid.service;

import sid.utils.AppConstant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonOperation {
	private static final String column = "id,name,type,sex";
	/**
	 * Person
	 * 根据id获取数据
	 * @param context
	 * @param id
	 * @return
	 */
	public static Person getPerson(Context context,int id){
		Person person = new Person();
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.PERSON,new String[] { column },"id=?",
						new String[] {"1"}, null, null,null);
		setPersons(cursor,person,false);
		cursor.close();
		sd.close();
		dbh.close();
		return person;
	}
	/**
	 * 循环设置persons的信息
	 * @param cursor
	 */
	private static void setPersons(Cursor cursor,Person person,boolean addList) {
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int type = cursor.getInt(cursor.getColumnIndex("type"));
			int sex = cursor.getInt(cursor.getColumnIndex("sex"));
			if (addList) {
				person = new Person();
			}
			person.setId(id);
			person.setType(type);
			person.setSex(sex);
			person.setName(name);
		}
	}
	/**
	 * 存储用户
	 * @param person
	 * @param exist
	 * @return
	 */
	public static void savePerson(Context context,Person person) {
		boolean exist = false;
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.PERSON,new String[] { column },"id=?",
				new String[] {"1"}, null, null,null);
		while (cursor.moveToNext()) {
			exist = true;
		}
		cursor.close();

		save(context, person, exist, dbh, sd);
	}
	/**
	 * 保存用户
	 * @param context
	 * @param person
	 * @param exist
	 * @param dbh
	 * @param sd
	 */
	private static void save(Context context, Person person, boolean exist,
			DatabaseHelper dbh, SQLiteDatabase sd) {
		//添加便利贴内容
		ContentValues value = new ContentValues();
		value.put("name", person.getName());
		value.put("type", person.getType());
		value.put("sex", person.getSex());
		if (exist) {
			sd.update(AppConstant.PERSON, value, "id=?", new String[]{person.getId()+""});
			sd.close();
			dbh.close();
		}else{
			sd.insert(AppConstant.PERSON, null, value);
			sd.close();
			dbh.close();
		}
	}

}
