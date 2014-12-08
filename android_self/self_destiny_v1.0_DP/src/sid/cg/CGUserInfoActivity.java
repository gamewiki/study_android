package sid.cg;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sid.databasehelper.DatabaseHelper;
import sid.destiny.R;
import sid.modle.CGUserList;
import sid.modle.PersonDestiny;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ChineseCalendar;
import sid.utils.DataUtils;
import sid.utils.ExitApplication;
import sid.utils.Lauar;
import sid.utils.SelfDateUtils;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.waps.AppConnect;

public class CGUserInfoActivity extends Activity {
	private EditText cgNameE;
	private RadioGroup cgRadioGroup;
	private RadioButton cgMan;
	private RadioButton cgWoman;
	private RadioButton cgOther;
	private RadioGroup cgRadioGroup_cgCalendar;
	private RadioButton cgLunar;
	private RadioButton cgSolar;
	private Button cgDate;
	private Button cgTime;
	private Button cgSubmit;
	private Button cgResultList;
	private String calendar;
	private String usersex;
	PersonDestiny person = new PersonDestiny();
	private static final int DATE_PICKER_ID = 1;
	private static final int TIME_PICKER_ID = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "CGUserInfoActivity.onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cg_userinfo);
		
        //3.插屏广告
        AppConnect.getInstance(this).initPopAd(this);
        AppConnect.getInstance(this).showPopAd(this);
        
        
		/* 初始化对象 */
		cgNameE = (EditText) findViewById(R.id.cgNameE);
		cgRadioGroup = (RadioGroup) findViewById(R.id.cgRadioGroup);
		cgMan = (RadioButton) findViewById(R.id.cgMan);
		cgWoman = (RadioButton) findViewById(R.id.cgWoman);
		cgOther = (RadioButton) findViewById(R.id.cgOther);
		usersex = getString(R.string.cgMan);
		person.setUsersex(usersex);
		cgRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (cgMan.getId() == checkedId) {
							usersex = getString(R.string.cgMan);
						} else if (cgWoman.getId() == checkedId) {
							usersex = getString(R.string.cgWoman);
						} else if (cgOther.getId() == checkedId) {
							usersex = getString(R.string.cgOther);
						}
						person.setUsersex(usersex);
					}
				});
		cgRadioGroup_cgCalendar = (RadioGroup) findViewById(R.id.cgRadioGroup_cgCalendar);
		cgLunar = (RadioButton) findViewById(R.id.cgLunar);
		cgSolar = (RadioButton) findViewById(R.id.cgSolar);
		calendar = getString(R.string.cgLunar);
		person.setCalendar(calendar);
		cgRadioGroup_cgCalendar
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (cgLunar.getId() == checkedId) {
							calendar = getString(R.string.cgLunar);
						} else if (cgSolar.getId() == checkedId) {
							calendar = getString(R.string.cgSolar);
						}
						person.setCalendar(calendar);
					}
				});
		cgDate = (Button) findViewById(R.id.cgDate);
		cgDate.setText(SelfDateUtils.getDateTime("yyyy-MM-dd"));
		cgDate.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_PICKER_ID);
			}
		});
		cgTime = (Button) findViewById(R.id.cgTime);
		cgTime.setText(SelfDateUtils.getDateTime("HH:mm"));
		cgTime.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_PICKER_ID);
			}
		});
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		int hour = cal.get(Calendar.HOUR);// 得到小时
		person.setYear(year);
		person.setMonth(month);
		person.setDay(day);
		person.setHour(hour);
		person.setUserbirth(year + "-" + month + "-" + day);
		cgSubmit = (Button) findViewById(R.id.cgSubmit);
		cgSubmit.setText(getString(R.string.cgSubmit));
		cgSubmit.setTextSize(DataUtils.CG_USER_TEXT_SIZE);
		cgSubmit.setOnClickListener(new enterCGListener());
		cgResultList = (Button) findViewById(R.id.cgResultList);
		cgResultList.setText(getString(R.string.cgResultList));
		cgResultList.setTextSize(DataUtils.CG_USER_TEXT_SIZE);
		cgResultList.setOnClickListener(new cgResultListListener());
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		// 设置默认软键盘不弹出
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "CGUserInfoActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "CGUserInfoActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}
	

	class enterCGListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String name = cgNameE.getText().toString();
			person.setUsername(name);
			if (null != name && !"".equals(name) && null != person
					&& !"".equals(person) && null != person.getUsersex()
					&& !"".equals(person.getUsersex()) && 0 != person.getYear()
					&& 0 != person.getMonth() && 0 != person.getDay()
					&& 0 != person.getHour()) {
				if (name.length() > 4) {
					Toast.makeText(CGUserInfoActivity.this,
							getString(R.string.longName), Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (person.getYear() > 2049 || person.getYear() < 1900) {
					Toast.makeText(CGUserInfoActivity.this,
							getString(R.string.longYear), Toast.LENGTH_SHORT).show();
					return;
				}
				if (getString(R.string.cgLunar).equals(person.getCalendar())) {
					person = ChineseCalendar.getPersonByCalendarLundarToSolar(person.getYear(),
							person.getMonth(), person.getDay(),person);
				}
				person = Lauar.getPersonBySolar(person.getYear(),
						person.getMonth(), person.getDay(), person);
				person = getDestiny(person);
				Intent intent = new Intent();
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("person", person);
				intent.putExtras(mBundle);
				intent.setClass(CGUserInfoActivity.this, CGResultActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(CGUserInfoActivity.this,
						getString(R.string.empty), Toast.LENGTH_SHORT).show();
			}
		}
	}

	class cgResultListListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			DatabaseHelper dbh = new DatabaseHelper(CGUserInfoActivity.this,
					"destiny_sid_db", DataUtils.VERSION);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			Cursor cursor;
			cursor = sd.query("cg_user",new String[] { "username,year,month,day,weightId" },
					null,new String[] {}, null, null,null);
			List<String> listPersonDes = new ArrayList<String>();
			List<PersonDestiny> listPerson = new ArrayList<PersonDestiny>();
			while (cursor.moveToNext()) {
				PersonDestiny person = new PersonDestiny();
				String username = cursor.getString(cursor.getColumnIndex("username"));
				int year = cursor.getInt(cursor.getColumnIndex("year"));
				int month = cursor.getInt(cursor.getColumnIndex("month"));
				int day = cursor.getInt(cursor.getColumnIndex("day"));
				int weightId = cursor.getInt(cursor.getColumnIndex("weightId"));
				person.setUsername(username);
				person.setYear(year);
				person.setMonth(month);
				person.setDay(day);
				person.setWeightId(weightId);
				listPerson.add(person);
				listPersonDes.add(username+getString(R.string.r_cg_weight)+weightId+username+getString(R.string.r_cg_weight_unit)
						+getString(R.string.cgBirthdayLunar)+year+month+day);
			}
			cursor.close();
			sd.close();
			CGUserList userlist = new CGUserList();
			userlist.setListPerson(listPerson);
			userlist.setListPersonDes(listPersonDes);
			Intent intent = new Intent();
			Bundle mBundle = new Bundle();
			mBundle.putSerializable("userlist", userlist);
			intent.putExtras(mBundle);
			intent.putExtras(mBundle);
			intent.setClass(CGUserInfoActivity.this, CGUserListActivity.class);
			startActivity(intent);
		}
	}

	private PersonDestiny getDestiny(PersonDestiny person) {
		String eight = person.getUsereight();
		String yearCy = eight.substring(0, 2);
		DatabaseHelper dbh = new DatabaseHelper(CGUserInfoActivity.this,
				"destiny_sid_db", DataUtils.VERSION);
		SQLiteDatabase sd = dbh.getReadableDatabase();
		person = getPersonWeight(person, yearCy, sd);
		person = getPersonDestiny(person, sd);
		sd.close();
		return person;
	}

	private PersonDestiny getPersonWeight(PersonDestiny person, String yearCy,
			SQLiteDatabase sd) {
		int weightY = 0;
		int weightM = 0;
		int weightD = 0;
		int weightH = 0;
		String nameH = "";
		Cursor cursorWeightYear;
		cursorWeightYear = sd.query("cg_year_weight",
				new String[] { "weight" }, "name=?", new String[] { yearCy },
				null, null, null);
		while (cursorWeightYear.moveToNext()) {
			weightY = cursorWeightYear.getInt(cursorWeightYear
					.getColumnIndex("weight"));
		}
		cursorWeightYear.close();
		Cursor cursorWeightM;
		cursorWeightM = sd.query("cg_month_weight", new String[] { "weight" },
				"id=?", new String[] { person.getMonth() + "" }, null, null,
				null);
		while (cursorWeightM.moveToNext()) {
			weightM = cursorWeightM.getInt(cursorWeightM
					.getColumnIndex("weight"));
		}
		cursorWeightM.close();
		Cursor cursorWeightD;
		cursorWeightD = sd
				.query("cg_day_weight", new String[] { "weight" }, "id=?",
						new String[] { person.getDay() + "" }, null, null, null);
		while (cursorWeightD.moveToNext()) {
			weightD = cursorWeightD.getInt(cursorWeightD
					.getColumnIndex("weight"));
		}
		cursorWeightD.close();
		Cursor cursorWeightH;
		cursorWeightH = sd.query("cg_hour_weight", new String[] { "weight",
				"name" }, "id=?", new String[] { person.getHour() + "" }, null,
				null, null);
		while (cursorWeightH.moveToNext()) {
			weightH = cursorWeightH.getInt(cursorWeightH
					.getColumnIndex("weight"));
			nameH = cursorWeightH.getString(cursorWeightH
					.getColumnIndex("name"));
		}
		cursorWeightH.close();
		int weight = weightY + weightM + weightD + weightH;
		person.setWeightId(weight);
		person.setUsereight(person.getUsereight() + nameH);
		return person;
	}

	private PersonDestiny getPersonDestiny(PersonDestiny person,
			SQLiteDatabase sd) {
		Cursor cursorPerson;
		if (getString(R.string.cgWoman).equals(person.getUsersex())) {
			cursorPerson = sd.query("cg_women", new String[] { "id", "weight",
					"name", "destiny", "description" }, "id=?",
					new String[] { person.getWeightId() + "" }, null, null,
					null);
		} else {
			cursorPerson = sd.query("cg_man", new String[] { "id", "weight",
					"name", "destiny", "description" }, "id=?",
					new String[] { person.getWeightId() + "" }, null, null,
					null);
		}
		while (cursorPerson.moveToNext()) {
			String weight = cursorPerson.getString(cursorPerson
					.getColumnIndex("weight"));
			String name = cursorPerson.getString(cursorPerson
					.getColumnIndex("name"));
			String destiny = cursorPerson.getString(cursorPerson
					.getColumnIndex("destiny"));
			String description = cursorPerson.getString(cursorPerson
					.getColumnIndex("description"));
			person.setWeight(weight);
			person.setName(name);
			person.setDestiny(destiny);
			person.setDescription(description);
		}
		cursorPerson.close();
		return person;
	}

	// 监听器；用户监听用户点击datepickerDialog的set按钮时所设置的日期信息
	// 月份是从0开始计算的
	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			person.setYear(year);
			person.setMonth(monthOfYear+1);
			person.setDay(dayOfMonth);
			person.setUserbirth(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
			cgDate.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
		}
	};

	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			person.setHour(hourOfDay);
			cgTime.setText(hourOfDay + ":" + minute);
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH);// 得到月，因为从0开始的，所以在使用时要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		int hour = cal.get(Calendar.HOUR);// 得到小时
		int minute = cal.get(Calendar.MINUTE);// 得到分钟
		switch (id) {
		case DATE_PICKER_ID:
			return new DatePickerDialog(this, onDateSetListener, year, month,
					day);
		case TIME_PICKER_ID:
			return new TimePickerDialog(this, onTimeSetListener, hour, minute,
					true);
		}
		return null;
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
			intent.setClass(CGUserInfoActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
