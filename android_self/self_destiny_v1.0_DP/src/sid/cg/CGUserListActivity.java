package sid.cg;

import java.util.ArrayList;
import java.util.List;

import sid.databasehelper.DatabaseHelper;
import sid.destiny.DestinyActivity;
import sid.destiny.R;
import sid.modle.CGUserList;
import sid.modle.PersonDestiny;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DataUtils;
import sid.utils.ExitApplication;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

public class CGUserListActivity extends ListActivity implements OnItemLongClickListener{
	private Button cg_delete_Button;
	private List<PersonDestiny> listPerson = new ArrayList<PersonDestiny>();
	private List<String> listPersonDes = new ArrayList<String>();
	private int deletePersonPosition;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "CGUserListActivity.onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cg_userlist);
		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);
		Log.w(AppConstant.TAG, "CGUserListActivity.onCreate()");
		CGUserList userlist = (CGUserList) getIntent().getSerializableExtra("userlist");
		cg_delete_Button = (Button) findViewById(R.id.cg_delete_Button);
		cg_delete_Button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				showDialog(1);
			}
		});

		listPerson = userlist.getListPerson();
		listPersonDes = userlist.getListPersonDes();
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		updateAdapter();
	}

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "CGUserListActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "CGUserListActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}
	

	/**
	 * 更新数据
	 */
	private void updateAdapter() {
		if (null != listPersonDes) {
			setListAdapter(new MyAdapter(this, listPersonDes, listPersonDes));
		} else {
			Toast.makeText(CGUserListActivity.this,
					getString(R.string.notExistUserList), Toast.LENGTH_SHORT)
					.show();
		}
	}


	/* 以下是点击菜单按钮执行的方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(CGUserListActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
    @Override
    protected Dialog onCreateDialog(int id) {
		return dialogCreate(CGUserListActivity.this,id);
    }

    private Dialog dialogCreate(CGUserListActivity dialogActivity, int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(dialogActivity);
		builder.setIcon(R.drawable.ic_action_search);
		builder.setTitle(R.string.cg_delete_button);
		builder.setMessage(R.string.cg_delete_msg);
    	switch (id) {
		case 1:
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					DatabaseHelper dbh = new DatabaseHelper(CGUserListActivity.this, "destiny_sid_db",DataUtils.VERSION);
					SQLiteDatabase sd = dbh.getWritableDatabase();
					sd.delete("cg_user", null, new String[]{});
					sd.close();
					Toast.makeText(CGUserListActivity.this,
							getString(R.string.cg_delete_success), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					intent.setClass(CGUserListActivity.this, DestinyActivity.class);
					startActivity(intent);
				}
			});
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			return builder.create();
		case 2:
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					PersonDestiny person = listPerson.get(deletePersonPosition);
					if (null != person) {
						DatabaseHelper dbh = new DatabaseHelper(CGUserListActivity.this, "destiny_sid_db",DataUtils.VERSION);
						SQLiteDatabase sd = dbh.getWritableDatabase();
						sd.delete("cg_user", "year=?" + " and month=?" + " and day=?"+ " and weightId=?" + " and username=?", 
								new String[] { person.getYear() + "",
								person.getMonth() + "",
								person.getDay() + "",
								person.getWeightId() + "",
								person.getUsername() });
						sd.close();
						Toast.makeText(CGUserListActivity.this,
								getString(R.string.cg_delete_success), Toast.LENGTH_SHORT).show();
						listPerson.remove(deletePersonPosition);
						listPersonDes.remove(deletePersonPosition);
						updateAdapter();
					} else {
						Toast.makeText(CGUserListActivity.this,
								getString(R.string.r_cg_miss), Toast.LENGTH_SHORT).show();
						return;
					}
				}
			});
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			return builder.create();
		default:
			break;
		}
		return null;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long id) {
		deletePersonPosition = position;
		showDialog(2);
		return true;
	}
	/**
	 * 设置ListItem被点击时要做的动作
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		PersonDestiny person = listPerson.get(position);
		if (null != person) {
			DatabaseHelper dbh = new DatabaseHelper(CGUserListActivity.this,"destiny_sid_db", DataUtils.VERSION);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			Cursor cursor;
			cursor = sd.query("cg_user",new String[] { "id,year,month,day,username,usersex,usereight,calendar,userbirth,weightId,weight,name,destiny,description" },
							"year=?" + " and month=?" + " and day=?"+ " and weightId=?" + " and username=?",
							new String[] { person.getYear() + "",
									person.getMonth() + "",
									person.getDay() + "",
									person.getWeightId() + "",
									person.getUsername() }, null, null,
							null);
			while (cursor.moveToNext()) {
				person.setId(cursor.getInt(cursor.getColumnIndex("id")));
				person.setYear(cursor.getInt(cursor.getColumnIndex("year")));
				person.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
				person.setDay(cursor.getInt(cursor.getColumnIndex("day")));
				person.setUsername(cursor.getString(cursor.getColumnIndex("username")));
				person.setUsersex(cursor.getString(cursor.getColumnIndex("usersex")));
				person.setUsereight(cursor.getString(cursor.getColumnIndex("usereight")));
				person.setCalendar(cursor.getString(cursor.getColumnIndex("calendar")));
				person.setUserbirth(cursor.getString(cursor.getColumnIndex("userbirth")));
				person.setWeightId(cursor.getInt(cursor.getColumnIndex("weightId")));
				person.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setDestiny(cursor.getString(cursor.getColumnIndex("destiny")));
				person.setDescription(cursor.getString(cursor.getColumnIndex("description")));
			}
			cursor.close();
			sd.close();
			Intent intent = new Intent();
			Bundle mBundle = new Bundle();
			mBundle.putSerializable("person", person);
			intent.putExtras(mBundle);
			intent.setClass(CGUserListActivity.this, CGResultActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(CGUserListActivity.this,
					getString(R.string.r_cg_miss), Toast.LENGTH_SHORT).show();
		}
	}

}
