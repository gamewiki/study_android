package sid.utils;

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
	public static final int VERSION = 1;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public DatabaseHelper(Context context, String name, int version){
		this(context, name, null,version);
	}

	public DatabaseHelper(Context context, String name){
		this(context, name, VERSION);
	}

	/**
	 * 该函数在第一次创建数据库的时候得到
	 * 
	 * 实际上是在第一次获得SQLiteDatabase对象时得到
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	private void createTable(SQLiteDatabase db) {
		db.execSQL("create table "+AppConstant.NOTE+" (id long,createtime varchar(20),color int,content text)");
		db.execSQL("create table "+AppConstant.CLOCK+" (id INTEGER PRIMARY KEY AUTOINCREMENT,clockdate varchar(30),"+
				"clocktime varchar(30),createtime varchar(20),startime long,hour int,minute int,title varchar(30),weeks varchar(30),"+
				"interval varchar(30),bells varchar(30),bellsPath text,shockCate varchar(30),signCate varchar(30))");
	}
}
