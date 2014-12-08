package sid.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class FirstProviderMetaData {
	public static final String AUTHORIY = "sid.contentprovider.contentprovideractivity";
	/**数据库名称*/
	public static final String DATABASE_NAME = "FirstProvider.db";
	/**数据库版本*/
	public static final int DATABASE_VERSION = 1;
	/**表名*/
	public static final String USERS_TABLE_NAME = "users";
	
	public static final class UserTableMetaData implements BaseColumns{
		/**表名*/
		public static final String TABLE_NAME = "users";
		/**访问该contentProvider的Uri*/
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORIY+"/users");
		//访问多个返回的类型
		/**该contentprovider
		 * 返回的数据类型的定义
		 * dir表示整张表的数据
		 * vnd.firstprovider.user是自己定义的名
		 **/
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.firstprovider.user";
		/**
		 * 访问一个返回的类型
		 * item表示一条数据
		 * vnd.firstprovider.user是自己定义的名
		 **/
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";
		/**列名*/
		public static final String USER_NAME = "name";
		//实现baseCuloms里面已经有一个常量的_id
		/**默认的排序方法*/
		public static final String DEFAULT_SORT_ORDER = "_id desc";
		
	}

}
