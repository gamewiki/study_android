package sid.cg;

import java.util.Date;

import com.baidu.mobstat.StatService;

import sid.databasehelper.DatabaseHelper;
import sid.destiny.R;
import sid.modle.PersonDestiny;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import sid.utils.SelfDateUtils;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CGResultActivity extends Activity {
	private ImageView r_cg_imageDestiny;
	private TextView r_cg_name;
	private TextView r_cg_destiny;
	private TextView r_cg_weight;
	/** 获取屏幕的宽度 */
	private Integer windowWidth = 420;
	/** 获取屏幕的高度 */
	private Integer windowHeight = 800;
	private int width;
	private int height;
	private ImageButton cgSave;
	private ImageButton cgDes;
	private ImageButton cgShare;
	PersonDestiny person;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "CGResultActivity.onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cg_result);
		// 获取显示屏的宽高
		getWH();
		person = (PersonDestiny) getIntent().getSerializableExtra("person");
		String sex = person.getUsersex().equals(getString(R.string.cgOther)) ? ""
				: person.getUsersex();
		String name = person.getUsername() + sex
				+ getString(R.string.cgEightText) + person.getUsereight();
		String weight = person.getWeight();
		String destiny = person.getDestiny();
		String calendar = person.getCalendar();
		if (calendar.indexOf("/")>0) {
			String str = calendar.substring(0, calendar.indexOf("/"));
			person.setCalendar(str);
		}
		int weightId = person.getWeightId();
		r_cg_imageDestiny = (ImageView) findViewById(R.id.r_cg_imageDestiny);
		if (weightId < 26) {
			r_cg_imageDestiny.setImageResource(R.drawable.zhiming);
		} else if (weightId > 60) {
			r_cg_imageDestiny.setImageResource(R.drawable.yuming);
		} else {
			r_cg_imageDestiny.setImageResource(R.drawable.piming);
		}
		r_cg_name = (TextView) findViewById(R.id.r_cg_name);
		r_cg_name.setText(name);
		r_cg_name.setPadding(width, height, width, height-10);
		r_cg_destiny = (TextView) findViewById(R.id.r_cg_destiny);
		r_cg_destiny.setText(destiny.replace(" ", "\n"));
		r_cg_weight = (TextView) findViewById(R.id.r_cg_weight);
		r_cg_weight.setText(getString(R.string.r_cg_weight) + weight);
		r_cg_weight.setPadding(width, height-10, width, height-10);
		cgSave = (ImageButton) findViewById(R.id.cgSave);
//		cgSave.setPadding(width, 0, width, 0);
//		cgSave.setMaxHeight(width);
//		cgSave.setMaxWidth(width);
		cgSave.setOnClickListener(new cgSaveListener());
		cgDes = (ImageButton) findViewById(R.id.cgDes);
		if (null==person.getDescription()||"".equals(person.getDescription())) {
			cgDes.setVisibility(Button.INVISIBLE);
		}else{
			cgDes.setVisibility(Button.VISIBLE);
//			cgDes.setPadding(width, height, width, height);
//			cgDes.setMaxHeight(width);
//			cgDes.setMaxWidth(width);
			cgDes.setOnClickListener(new cgDesListener());
		}
		cgShare = (ImageButton) findViewById(R.id.cgShare);
//		cgShare.setPadding(width, 0, width, 0);
//		cgShare.setMaxHeight(width);
//		cgShare.setMaxWidth(width);
		cgShare.setOnClickListener(new cgShareListener());
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "CGResultActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "CGResultActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}
	

	class cgSaveListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (null != person) {
				boolean exist = true;
				String existDate = "";
				DatabaseHelper dbh = new DatabaseHelper(CGResultActivity.this,"destiny_sid_db", AppConstant.VERSION);
				SQLiteDatabase sd = dbh.getWritableDatabase();
				Cursor cursor;
				cursor = sd.query("cg_user",new String[] { "id" },
								"year=?" + " and month=?" + " and day=?"+ " and weightId=?" + " and username=?",
								new String[] { person.getYear() + "",
										person.getMonth() + "",
										person.getDay() + "",
										person.getWeightId() + "",
										person.getUsername() }, null, null,
								null);
				while (cursor.moveToNext()) {
					String id = cursor.getString(cursor.getColumnIndex("id"));
					existDate = SelfDateUtils.getDateFromLong("yyyy-MM-dd HH:mm", id);
					exist = false;
				}
				cursor.close();
				if (exist) {
					ContentValues value = new ContentValues();
					value.put("id", new Date().getTime() + "");
					value.put("year", person.getYear());
					value.put("month", person.getMonth());
					value.put("day", person.getDay());
					value.put("username", person.getUsername());
					value.put("usersex", person.getUsersex());
					value.put("calendar", person.getCalendar());
					value.put("userbirth", person.getUserbirth());
					value.put("usereight", person.getUsereight());
					value.put("weightId", person.getWeightId());
					value.put("weight", person.getWeight());
					value.put("name", person.getName());
					value.put("destiny", person.getDestiny());
					value.put("description", person.getDescription());
					sd.insert("cg_user", null, value);
					Toast.makeText(CGResultActivity.this,
							getString(R.string.r_cg_success),Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(CGResultActivity.this,
							getString(R.string.r_cg_exist1) + existDate+ getString(R.string.r_cg_exist2),
							Toast.LENGTH_SHORT).show();
				}
				sd.close();
			} else {
				Toast.makeText(CGResultActivity.this,
						getString(R.string.r_cg_miss), Toast.LENGTH_SHORT).show();
				return;
			}
		}
	}

	class cgDesListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (null != person) {
				Intent intent = new Intent();
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("person", person);
				intent.putExtras(mBundle);
				intent.setClass(CGResultActivity.this,CGResultDesActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(CGResultActivity.this,
						getString(R.string.r_cg_miss), Toast.LENGTH_SHORT)
						.show();
				return;
			}
		}
	}

	class cgShareListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String shareNote = getString(R.string.shareNote1)+ AppConstant.SHARE_URL + getString(R.string.shareNote2);
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
			intent.putExtra(Intent.EXTRA_TEXT, shareNote);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, getTitle()));
		}
	}

	private void getWH() {
		// 获取屏幕宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		windowWidth = dm.widthPixels;
		windowHeight = dm.widthPixels;
		height = windowHeight / 10;
		width = windowWidth / 7;
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
			intent.setClass(CGResultActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
