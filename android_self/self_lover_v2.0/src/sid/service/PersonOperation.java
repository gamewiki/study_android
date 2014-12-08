package sid.service;

import java.util.ArrayList;
import java.util.List;

import sid.lover.R;
import sid.modle.Constellation;
import sid.modle.Person;
import sid.utils.AppConstant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class PersonOperation {
	private static final String column = "id,name,type,year,month,day,birthday,constellation,phone,description,periodDays,periodYear,periodMonth,periodDay";
	private static List<Person> persons = new ArrayList<Person>();
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
						new String[] {id+""}, null, null,null);
		setPersons(cursor,person,false);
		cursor.close();
		sd.close();
		dbh.close();
		return person;
	}
	/**
	 * Person
	 * 根据id获取数据
	 * @param context
	 * @param id
	 * @return
	 */
	public static Constellation getConstellation(Context context,String cid){
		Constellation constellation = new Constellation();
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.CONSTELLATION,new String[] { "id,name,start,end,description" },"id=?",
						new String[] {cid+""}, null, null,null);
		
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int start = cursor.getInt(cursor.getColumnIndex("start"));
			int end = cursor.getInt(cursor.getColumnIndex("end"));
			String description = cursor.getString(cursor.getColumnIndex("description"));
			constellation.setId(id);
			constellation.setName(name);
			constellation.setStart(start);
			constellation.setEnd(end);
			constellation.setDescription(description);
		}
		
		cursor.close();
		sd.close();
		dbh.close();
		return constellation;
	}
	/**
	 * Person
	 * 根据type获取数据
	 * @param context
	 * @param type
	 * @return
	 */
	public static Person getPersonByType(Context context,int type){
		Person person = new Person();
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.PERSON,new String[] { column },"type=?",
						new String[] {type+""}, null, null,null);
		setPersons(cursor,person,false);
		cursor.close();
		sd.close();
		dbh.close();
		return person;
	}
	/**
	 * Person
	 * 根据id删除数据
	 * @param context
	 * @param id
	 */
	public static void deletePerson(Context context,int id){
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		sd.delete(AppConstant.PERSON, "id=?", new String[] {id+"" });
		sd.close();
		dbh.close();
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
			int year = cursor.getInt(cursor.getColumnIndex("year"));
			int month = cursor.getInt(cursor.getColumnIndex("month"));
			int day = cursor.getInt(cursor.getColumnIndex("day"));
			String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
			int constellation = cursor.getInt(cursor.getColumnIndex("constellation"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String description = cursor.getString(cursor.getColumnIndex("description"));
			int periodDays = cursor.getInt(cursor.getColumnIndex("periodDays"));
			int periodYear = cursor.getInt(cursor.getColumnIndex("periodYear"));
			int periodMonth = cursor.getInt(cursor.getColumnIndex("periodMonth"));
			int periodDay = cursor.getInt(cursor.getColumnIndex("periodDay"));
			if (addList) {
				person = new Person();
			}
			person.setId(id);
			person.setType(type);
			person.setName(name);
			person.setYear(year);
			person.setMonth(month);
			person.setDay(day);
			person.setBirthday(birthday);
			person.setConstellation(constellation);
			person.setPhone(phone);
			person.setDescription(description);
			person.setPeriodDays(periodDays);
			person.setPeriodYear(periodYear);
			person.setPeriodMonth(periodMonth);
			person.setPeriodDay(periodDay);
			if (addList) {
				persons.add(person);
			}
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
		cursor = sd.query(AppConstant.PERSON,new String[] { column },"name=? and birthday=? and phone=?",
						new String[] {person.getName(),person.getBirthday(),person.getPhone()}, null, null,null);
		while (cursor.moveToNext()) {
			exist = true;
		}
		cursor.close();

		save(context, person, exist, dbh, sd);
	}
	/**
	 * 存储用户
	 * @param person
	 * @param exist
	 * @return
	 */
	public static void savePersonPeriod(Context context,Person person) {
		boolean exist = false;
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		//判断是否已经存在
		Cursor cursor;
		cursor = sd.query(AppConstant.PERSON,new String[] { column },"type=?",
				new String[] {AppConstant.LOVER+""}, null, null,null);
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
		value.put("year", person.getYear());
		value.put("month", person.getMonth());
		value.put("day", person.getDay());
		value.put("birthday", person.getBirthday());
		value.put("constellation", person.getConstellation());
		value.put("phone", person.getPhone());
		value.put("description", person.getDescription());
		value.put("periodDays", person.getPeriodDays()); 
		value.put("periodYear", person.getPeriodYear());
		value.put("periodMonth", person.getPeriodMonth());
		value.put("periodDay", person.getPeriodDay());
//		System.out.println("---------exist-----------"+exist);
		if (exist) {
			sd.update(AppConstant.PERSON, value, "id=?", new String[]{person.getId()+""});
			sd.close();
			dbh.close();
			Toast.makeText(context,context.getString(R.string.addSuccess),Toast.LENGTH_SHORT).show();
		}else{
			sd.insert(AppConstant.PERSON, null, value);
			sd.close();
			dbh.close();
			Toast.makeText(context,context.getString(R.string.addSuccess),Toast.LENGTH_SHORT).show();
		}
	}

}
