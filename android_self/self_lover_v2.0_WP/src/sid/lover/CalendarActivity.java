package sid.lover;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.waps.AppConnect;
import sid.lover.ui.views.CalendarView;
import sid.lover.ui.views.CalendarView.OnItemClickListener;
import sid.modle.Note;
import sid.modle.Person;
import sid.service.NoteOperation;
import sid.service.PersonOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class CalendarActivity extends BaseActivity {
	Person person = new Person();
	private CalendarView mView = null;
	private Calendar calendar = null;
	private TextView yearMonth;
	private TextView periodDays;
	private EditText editperiodDays;
	private boolean isSetPeriod = false;
	private int pYear,pMonth,pDay,pDays;
	private ImageButton savePerson;
	private ImageButton periodThings;
	private ImageButton calendarLeft;
	private ImageButton calendarRight;
	private List<Note> listNotes = new ArrayList<Note>();
	private List<String> listNotesDes = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.activity_calendar);
		person = PersonOperation.getPersonByType(CalendarActivity.this,AppConstant.LOVER);
		isSetPeriod = person.isSetPeriod();
		pYear = person.getPeriodYear();
		pMonth = person.getPeriodMonth();
		pDay = person.getPeriodDay();
		pDays = person.getPeriodDays();
		calendar = Calendar.getInstance();


		savePerson = (ImageButton) findViewById(R.id.savePerson);
		savePerson.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				savePerson();
			}
		});
		periodThings = (ImageButton) findViewById(R.id.periodThings);
		periodThings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				NoteOperation.getNotes(CalendarActivity.this, AppConstant.NOTE_PERIOD, listNotesDes, listNotes);
				if (listNotes.size()>0) {
					Bundle mBundle = new Bundle();
					mBundle.putSerializable(AppConstant.NOTE, listNotes.get(0));
					intent.putExtras(mBundle);
				}
				intent.setClass(CalendarActivity.this, PeriodThingsActivity.class);
				startActivity(intent);
			}
		});

		periodDays = (TextView) findViewById(R.id.periodDays);
		periodDays.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.PERIODS_PICKER_ID);
			}
		});

		yearMonth = (TextView) findViewById(R.id.calendarCenter);
		
		//获取日历控件对象
		mView = (CalendarView)findViewById(R.id.calendar);
		//获取日历中年月 ya[0]为年，ya[1]为月（格式大家可以自行在日历控件中改）
//		String[] ya = mView.getYearAndmonth().split("-"); 
		//点击上一月 同样返回年月 
//		String leftYearAndmonth = mView.clickLeftMonth(); 
//		String[] lya = leftYearAndmonth.split("-");
		//点击下一月
//		String rightYearAndmonth = mView.clickRightMonth(); 
//		String[] rya = rightYearAndmonth.split("-");
		class CalendarItemClickListener implements OnItemClickListener{
			@Override
			public void OnItemClick(Date date) {
				int day = date.getDate();
				pYear = date.getYear()+1900;
				pMonth = date.getMonth()+1;
				pDay = day;
				yearMonth.setText(pYear+"-"+pMonth+"-"+pDay);
				mView.periodDays(day,Integer.valueOf(periodDays.getText().toString()));
			}
		}
		//设置控件监听，可以监听到点击的每一天（大家也可以在控件中自行设定）
		mView.setOnItemClickListener(new CalendarItemClickListener());

		calendarLeft = (ImageButton) findViewById(R.id.calendarLeft);
		calendarLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String YearAndmonth = mView.clickLeftMonth();
				Toast.makeText(CalendarActivity.this, "当前显示月份："+YearAndmonth, Toast.LENGTH_SHORT);
			}
		});
		calendarRight = (ImageButton) findViewById(R.id.calendarRight);
		calendarRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String YearAndmonth = mView.clickRightMonth(); 
				Toast.makeText(CalendarActivity.this, "当前显示月份："+YearAndmonth, Toast.LENGTH_SHORT);
			}
		});
		
		
		if (isSetPeriod) {
			periodDays.setText(pDays+"");
			
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			yearMonth.setText(pYear+"-"+pMonth+"-"+pDay);

			setCalendarColor(calendar);
		}else{
			pDays = Integer.valueOf(periodDays.getText().toString());
		}

		if (isSetPeriod) {
			periodDays.setText(pDays+"");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			setCalendarColor(calendar);
		}else{
			pDays = Integer.valueOf(periodDays.getText().toString());
		}
        AppConnect.getInstance(this).initPopAd(this);
        LinearLayout ll = (LinearLayout) findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, ll);
        AppConnect.getInstance(this).showPopAd(this);
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case AppConstant.PERIODS_PICKER_ID:
			editperiodDays = new EditText(this);
			editperiodDays.setSelectAllOnFocus(true);
			editperiodDays.setKeyListener(new DigitsKeyListener(false, true));
			editperiodDays.setText(periodDays.getText().toString());
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.periodDaysText))
					.setIcon(R.drawable.smile_50)
					.setView(editperiodDays)
					.setPositiveButton(getString(R.string.confirm),
							new editperiodDaysListener())
					.setNegativeButton(getString(R.string.cancel), null).show();
		}
		return null;
	}


	/**
	 * 编辑周期确认按钮
	 * 
	 */
	class editperiodDaysListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			int days;
			String editperiodDaysString = editperiodDays.getText().toString();
			if (editperiodDaysString != null
					&& !"".equals(editperiodDaysString)) {
				days = Integer.valueOf(editperiodDaysString);
				if (days < 25 || days > 45) {
					Toast.makeText(CalendarActivity.this,
							getString(R.string.periodDaysError),
							Toast.LENGTH_SHORT).show();
				} else {
					pDays = days;
					person.setPeriodDays(days);
					periodDays.setText(days + "");
				}
			}
			dialog.dismiss();
		}
	}

	/**
	 * 更新当前日期颜色
	 * @param cal
	 * @param day
	 */
	private void setCalendarColor(Calendar cal) {
		if (pYear==cal.get(Calendar.YEAR)&&(pMonth-1)==cal.get(Calendar.MONTH)) {
			mView.periodDays(pDay,Integer.valueOf(periodDays.getText().toString()));
		}else{
			Calendar pcal = Calendar.getInstance();//例假日期
			pcal.set(Calendar.YEAR, pYear);
			pcal.set(Calendar.MONTH, pMonth-1);
			pcal.set(Calendar.DAY_OF_MONTH, pDay);
			pcal.set(Calendar.SECOND, 0);
			pcal.set(Calendar.MILLISECOND, 0);
			long times = cal.getTimeInMillis() - pcal.getTimeInMillis();
			long dayTimes = 1000*60*60*24;
			long days = times/dayTimes;
			long multiple = days/pDays;
			long date = (multiple+1)*pDays-days+1;
			
			mView.periodDays(Integer.valueOf(date+""),Integer.valueOf(periodDays.getText().toString()));
		}
	}

	/**
	 * 存储用户
	 */
	private void savePerson() {
		String days= periodDays.getText().toString();
//		System.out.println("-days-"+days+"-day-"+pDay);
		if ("".equals(days)||"".equals(pDay)) {
			Toast.makeText(CalendarActivity.this,
					getString(R.string.periodDayError),
					Toast.LENGTH_SHORT).show();
		}else{
			person.setPeriodYear(pYear);
			person.setPeriodMonth(pMonth);
			person.setPeriodDay(pDay);
			person.setPeriodDays(Integer.valueOf(days));
			person.setType(AppConstant.LOVER);
			PersonOperation.savePersonPeriod(CalendarActivity.this,person);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(CalendarActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		} else if (item.getItemId() == AppConstant.APP) {
			AppConnect.getInstance(CalendarActivity.this).showAppOffers(CalendarActivity.this);
		} else if (item.getItemId() == AppConstant.GAME) {
			AppConnect.getInstance(CalendarActivity.this).showGameOffers(CalendarActivity.this);
		}
		return super.onOptionsItemSelected(item);
	}
}
