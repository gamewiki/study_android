package sid.contentprovider;

import java.util.HashMap;

import sid.contentprovider.FirstProviderMetaData.UserTableMetaData;
import sid.databse.DatabaseHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class FirstContentProvider extends ContentProvider {
	/**用来加查当前的uri是否符合uri的匹配形式*/
	public static final UriMatcher uriMatcher;
	/**访问一系列*/
	public static final int INCOMING_USER_COLLECTION = 1;
	/**访问某一个对象*/
	public static final int INCOMING_USER_SINGLE = 2;
	private DatabaseHelper dh;
	/**为表里的每一列设置别名*/
	public static HashMap<String, String> userProjiectionMap;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		//1.authoriy
		//2.authoriy的路径
		uriMatcher.addURI(FirstProviderMetaData.AUTHORIY,
				"users", INCOMING_USER_COLLECTION);
		//#号应该是id的占位符
		uriMatcher.addURI(FirstProviderMetaData.AUTHORIY,
				"users/#", INCOMING_USER_SINGLE);
		
		userProjiectionMap = new HashMap<String, String>();
		//起别名这里采用默认的
		userProjiectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjiectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}

	/**
	 * 是一个回调方法
	 * 所以说是在contentprovider创建的时候执行
	 */
	@Override
	public boolean onCreate() {
		dh = new DatabaseHelper(getContext(),FirstProviderMetaData.DATABASE_NAME);
		System.out.println("onCreate");
		return true;
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	/**
	 * 根据传入uri；
	 * 返回uri所表示的数据类型
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case INCOMING_USER_COLLECTION:
			return UserTableMetaData.CONTENT_TYPE;
		case INCOMING_USER_SINGLE:
			return UserTableMetaData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
	}

	/**
	 * 该函数返回值是uri；也就说uri表示刚刚使用的这个函数所插入的数据
	 * content://XXX.XXXXX.CCC/users/1
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		System.out.println("insert");
		SQLiteDatabase db = dh.getWritableDatabase();
		long rowId = db.insert(UserTableMetaData.TABLE_NAME, null, values);
		if(rowId>0){
			//withAppendedId是将自己的id加入到Uri后面返回一个Uri
			Uri insertedUserUri = ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			//通知监听器，数据已经改变
			//ContentResolver可以对contentprovider进行操作
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;
		}
		System.out.println("Failed to insert row into "+uri);
		return null;
	}

	/**
	 * 1.查询数据
	 * 2.查询列
	 * 3.where字句
	 * 4.where子句所对应的参数
	 * 5.orderby
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (uriMatcher.match(uri)) {
		case INCOMING_USER_COLLECTION:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(userProjiectionMap);
			break;
		case INCOMING_USER_SINGLE:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(userProjiectionMap);
			//getPathsqgments:
			//content://XXX.XXXXX.CCC/users/1
			//首先获取/users/1这部分；因为前面是协议；中间是authory的名字；后面的是path
			//获取之后根据首位的/分成两部分，分别是：users和；
			//get(1)是获取最后的1；
			qb.appendWhere(UserTableMetaData._ID+"="+uri.getPathSegments().get(1));
			break;
		}
		String orderBy;
		if(TextUtils.isEmpty(sortOrder)){
			orderBy = UserTableMetaData.DEFAULT_SORT_ORDER;
		}else{
			orderBy = sortOrder;
		}
		SQLiteDatabase db = dh.getWritableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		//通知监听器，数据已经改变
		//ContentResolver可以对contentprovider进行操作
		c.setNotificationUri(getContext().getContentResolver(), uri);
		System.out.println("query");
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
