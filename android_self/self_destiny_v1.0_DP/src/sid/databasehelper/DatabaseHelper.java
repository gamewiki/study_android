package sid.databasehelper;

import sid.modle.DayWeight;
import sid.modle.Earth;
import sid.modle.HourWeight;
import sid.modle.LingQian;
import sid.modle.MonthWeight;
import sid.modle.PersonDestiny;
import sid.modle.Sky;
import sid.modle.YearWeight;
import sid.utils.DataUtils;
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
		init(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	private void createTable(SQLiteDatabase db) {
		db.execSQL("create table cg_sky (id int,name varchar(3))");
		db.execSQL("create table cg_earth (id int,name varchar(3),zodiac varchar(3))");
		db.execSQL("create table cg_year_weight (id int,name varchar(6),weight int)");
		db.execSQL("create table cg_month_weight (id int,name varchar(6),weight int)");
		db.execSQL("create table cg_day_weight (id int,name varchar(6),weight int)");
		db.execSQL("create table cg_hour_weight (id int,name varchar(6),weight int)");
		db.execSQL("create table cg_man (id int,weight varchar(12),name varchar(50),destiny text,description text)");
		db.execSQL("create table cg_women (id int,weight varchar(12),name varchar(50),destiny text,description text)");
		db.execSQL("create table cg_user (id text,year int,month int,day int,username varchar(12),usersex varchar(3),usereight varchar(24),calendar varchar(6),userbirth varchar(24),weightId int,weight varchar(12),name varchar(50),destiny text,description text)");
		db.execSQL("create table cg_config (id int,synopsis int)");
		db.execSQL("create table lq_description (id int,jixiong varchar(6),gongwei varchar(6),shi text,jieyue text,jieqian text,diangu text)");
	}
	private void init(SQLiteDatabase db){
		db.beginTransaction();
		//初始化天干数据
		for (Sky sky : DataUtils.skys) {
//			生成contentvalue
			ContentValues value = new ContentValues();
//			第一个key存放列名，第二个参数插入值
			value.put("id", sky.getId());value.put("name",sky.getName());
			db.insert("cg_sky", null, value);
		}
		//初始化地支数据
		for (Earth earth : DataUtils.earths) {
			ContentValues value = new ContentValues();
			value.put("id", earth.getId());
			value.put("name",earth.getName());
			value.put("zodiac",earth.getZodiac());
			db.insert("cg_earth", null, value);
		}
		//初始化年重数据
		for (YearWeight year : DataUtils.years) {
			ContentValues value = new ContentValues();
			value.put("id", year.getId());
			value.put("name",year.getName());
			value.put("weight",year.getWeight());
			db.insert("cg_year_weight", null, value);
		}
		//初始化月重数据
		for (MonthWeight month : DataUtils.months) {
			ContentValues value = new ContentValues();
			value.put("id", month.getId());
			value.put("name",month.getName());
			value.put("weight",month.getWeight());
			db.insert("cg_month_weight", null, value);
		}
		//初始化日重数据
		for (DayWeight day : DataUtils.days) {
			ContentValues value = new ContentValues();
			value.put("id", day.getId());
			value.put("name",day.getName());
			value.put("weight",day.getWeight());
			db.insert("cg_day_weight", null, value);
		}
		//初始化时重数据
		for (HourWeight hour : DataUtils.hours) {
			ContentValues value = new ContentValues();
			value.put("id", hour.getId());
			value.put("name",hour.getName());
			value.put("weight",hour.getWeight());
			db.insert("cg_hour_weight", null, value);
		}
		//初始化男命数据
		for (PersonDestiny man : DataUtils.men) {
			ContentValues value = new ContentValues();
			value.put("id", man.getId());
			value.put("name",man.getName());
			value.put("weight",man.getWeight());
			value.put("destiny",man.getDestiny());
			value.put("description",man.getDescription());
			db.insert("cg_man", null, value);
		}
		//初始化女命数据
		for (PersonDestiny woman : DataUtils.women) {
			ContentValues value = new ContentValues();
			value.put("id", woman.getId());
			value.put("name",woman.getName());
			value.put("weight",woman.getWeight());
			value.put("destiny",woman.getDestiny());
			value.put("description",woman.getDescription());
			db.insert("cg_women", null, value);
		}
		//初始化灵签数据
		for (LingQian lingqian : DataUtils.lingqians) {
			ContentValues value = new ContentValues();
			value.put("id", lingqian.getId());
			value.put("jixiong",lingqian.getJixiong());
			value.put("gongwei",lingqian.getGongwei());
			value.put("shi",lingqian.getShi());
			value.put("jieyue",lingqian.getJieyue());
			value.put("jieqian",lingqian.getJieqian());
			value.put("diangu",lingqian.getDiangu());
			db.insert("lq_description", null, value);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

}
