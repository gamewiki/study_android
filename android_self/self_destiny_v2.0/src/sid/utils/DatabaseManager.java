package sid.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import sid.destiny.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DatabaseManager {
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "destiny_sid_db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "sid.destiny";// 包名
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME; // 在手机里存放数据库的位置
	private SQLiteDatabase database;
	private Context context;

	public DatabaseManager(Context context) {
		this.context = context;
	}

	public SQLiteDatabase openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
		return database;
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		try {
			if (!(new File(dbfile).exists())) {// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = this.context.getResources().openRawResource(
						R.raw.destiny_sid_db); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}
}