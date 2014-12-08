package sid.contentprovider;

import sid.contentprovider.FirstProviderMetaData.UserTableMetaData;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContentProviderActivity extends Activity {
	Button query = null;
	Button insert = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        query = (Button)findViewById(R.id.queryButton);
        query.setOnClickListener(new QueryListener());
        insert = (Button)findViewById(R.id.insertButton);
        insert.setOnClickListener(new InsertListener());
    }
    
    class QueryListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Cursor c = getContentResolver().query(
					FirstProviderMetaData.UserTableMetaData.CONTENT_URI, 
					null, null, null, null);
			while(c.moveToNext()){
				System.out.println(c.getString(c.getColumnIndex(UserTableMetaData.USER_NAME)));
			}
		}
    }
    
    class InsertListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			ContentValues values = new ContentValues();
			values.put(FirstProviderMetaData.UserTableMetaData.USER_NAME, "zhangsan");
			Uri uri = getContentResolver().insert(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, values);
			System.out.println("uri----->"+uri.toString());
		}
    }
}
