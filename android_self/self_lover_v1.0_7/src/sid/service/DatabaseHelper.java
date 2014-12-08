package sid.service;

import sid.modle.Constellation;
import sid.utils.AppConstant;
import sid.utils.SelfDateUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHelper作为一个助手类；提供两方面的功能
 * 1.getReadableDatabase和getWriteableDatabase可以获得SqlitDatabase对象
 * 2.提供了两个回调函数onCreate和onUpgrade；允许我们对数据库进行创建和升级
 * 
 * @author Administrator
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public DatabaseHelper(Context context, String name, int version){
		this(context, name, null,version);
	}

	public DatabaseHelper(Context context, String name){
		this(context, name, AppConstant.VERSION);
	}

	/**
	 * 该函数在第一次创建数据库的时候得到
	 * 
	 * 实际上是在第一次获得SQLiteDatabase对象时得到
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
		init(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	private void createTable(SQLiteDatabase db) {
		db.execSQL("create table "+AppConstant.PERSON+" (id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20),type int,year int,month int,day int,birthday varchar(10),constellation int,phone varchar(11),description varchar(200),periodDays int,periodYear int,periodMonth int,periodDay int)");
		db.execSQL("create table "+AppConstant.CONSTELLATION+" (id int,name varchar(25),start int,end int,description text)");
		
		db.execSQL("create table "+AppConstant.NOTE+" (id long,createtime varchar(20),color int,type int,content text)");
		db.execSQL("create table "+AppConstant.SETTING+" (id long,password varchar(40),question varchar(40),answer varchar(40))");
		db.execSQL("create table "+AppConstant.CLOCK+" (id INTEGER PRIMARY KEY AUTOINCREMENT,clockdate varchar(30),"+
				"clocktime varchar(30),createtime varchar(20),startime long,hour int,minute int,title varchar(100),weeks varchar(30),"+
				"interval varchar(30),bells varchar(30),bellsPath text,shockCate varchar(30),signCate int,status int)");
	}
	
	private void init(SQLiteDatabase db){
		db.beginTransaction();
		//初始化星座数据
		for (Constellation constellation : AppConstant.constellations) {
			ContentValues value = new ContentValues();
//			第一个key存放列名，第二个参数插入值
			value.put("id", constellation.getId());
			value.put("name",constellation.getName());
			value.put("start",constellation.getStart());
			value.put("end",constellation.getEnd());
			value.put("description",constellation.getDescription());
			db.insert(AppConstant.CONSTELLATION, null, value);
		}
		//初始化月经数据

		ContentValues value = new ContentValues();
//		第一个key存放列名，第二个参数插入值
		value.put("id", 1);
		value.put("createtime", SelfDateUtils.getDateTime("yyyy-MM-dd"));
		value.put("color", 0);
		value.put("type", AppConstant.NOTE_PERIOD);
		value.put("content", AppConstant.NOTE_PERIOD_TEXT);
		db.insert(AppConstant.NOTE, null, value);
		db.setTransactionSuccessful();
		db.endTransaction();
	}
}
