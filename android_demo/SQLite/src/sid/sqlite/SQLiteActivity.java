package sid.sqlite;

import sid.databasehelper.DatabaseHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 在命令行中输入：adb shell进入android的linux命令行
 * @author Administrator
 * 
 * 
 * 生成数据库位置 ;并进入数据库
 * 1.adb shell
 * 2.cd data
 * 3.cd data
 * 4.cd 你的包名
 * 5.cd databases
 * 6.sqlite3 数据库名称
 * 		命令：.schema 查询数据库的ddl语句
 * 7.如果你已经更新了数据库，那么之后所有的操作再进行DatabaseHelper的创建时都要用最新版本的数据库进行操作
 */
public class SQLiteActivity extends Activity {
	private Button createDatabase = null;
	private Button updateDatabase = null;
	private Button insert = null;
	private Button update = null;
	private Button query = null;
	private Button delete = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        System.out.println("onCreate");
        createDatabase = (Button)findViewById(R.id.createDatabase);
        updateDatabase = (Button)findViewById(R.id.updateDatabase);
        insert = (Button)findViewById(R.id.insert);
        update = (Button)findViewById(R.id.update);
        query = (Button)findViewById(R.id.query);
        delete = (Button)findViewById(R.id.delete);
        createDatabase.setOnClickListener(new createDatabaseListener());
        updateDatabase.setOnClickListener(new updateDatabaseListener());
        insert.setOnClickListener(new insertListener());
        update.setOnClickListener(new updateListener());
        query.setOnClickListener(new queryListener());
        delete.setOnClickListener(new deleteListener());
    }

    class createDatabaseListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			System.out.println("createDatebaseLisenter");
//			创建数据库 
//			1.当前上下文对象
//			2.创建数据库的名字
			DatabaseHelper dbh = new DatabaseHelper(SQLiteActivity.this, "test_sid_db");
//			在执行下面的语句之前是不会创建数据库的
			SQLiteDatabase sd = dbh.getReadableDatabase();
		}
    }

    class updateDatabaseListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			DatabaseHelper dbh = new DatabaseHelper(SQLiteActivity.this, "test_sid_db",2);
			SQLiteDatabase sd = dbh.getReadableDatabase();
		}
    }

    class insertListener implements OnClickListener{
		@Override
		public void onClick(View v) {
//			生成contentvalue
			ContentValues value = new ContentValues();
//			第一个key存放列名，第二个参数插入值
			value.put("id", 1);
			value.put("name","sid");
			DatabaseHelper dbh = new DatabaseHelper(SQLiteActivity.this, "test_sid_db",2);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			sd.insert("user", null, value);
		}
    }

    class updateListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			ContentValues value = new ContentValues();
			value.put("name","sid222");
			DatabaseHelper dbh = new DatabaseHelper(SQLiteActivity.this, "test_sid_db",2);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			sd.update("user", value, "id=?", new String[]{"1"});
		}
    }

    class queryListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			DatabaseHelper dbh = new DatabaseHelper(SQLiteActivity.this, "test_sid_db",2);
			SQLiteDatabase sd = dbh.getReadableDatabase();
			Cursor cursor = sd.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"}, null, null, null);
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				System.out.println("=======queryListener======="+name);
			}
		}
    }

    class deleteListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			DatabaseHelper dbh = new DatabaseHelper(SQLiteActivity.this, "test_sid_db",2);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			sd.delete("user", "id=?", new String[]{"1"});
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sqlite, menu);
        return true;
    }
}
