package sid.lover.period;

import java.util.Calendar;

import sid.lover.R;
import sid.modle.Person;
import sid.service.PersonOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phone.vip.main.GEInstance;

/**
 * Android日期控件修正版
 * 
 * @Description: 可以选择上，下月，可以显示选择的日期。
 * @Version V1.0
 */
public class CalendarActivity extends Activity implements
		CalendarView.OnCellTouchListener {
	private GEInstance geInstance;
	private LinearLayout interLinearLayout;
	public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.exina.android.calendar.date";
	private CalendarView mView = null;
	private Handler mHandler = new Handler();
	private TextView yearMonth;
	private TextView periodDays;
	private EditText editperiodDays;
	private TextView periodDay;
	private TextView periodDayText;
	Person person = new Person();
	private boolean isSetPeriod = false;
	private int pYear,pMonth,pDay,pDays;
	private ImageButton savePerson;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_period_calendar);
		initGEDate();
		geInstance.loadInterAd(15, GEInstance.INTERUP, interLinearLayout);
		person = PersonOperation.getPersonByType(CalendarActivity.this,AppConstant.LOVER);
		isSetPeriod = person.isSetPeriod();
		pYear = person.getPeriodYear();
		pMonth = person.getPeriodMonth();
		pDay = person.getPeriodDay();
		pDays = person.getPeriodDays();
		
//		System.out.println(person);
		
		mView = (CalendarView) findViewById(R.id.calendar);
		mView.setOnCellTouchListener(this);

		savePerson = (ImageButton) findViewById(R.id.save);
		savePerson.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				savePerson();
			}
		});

		periodDays = (TextView) findViewById(R.id.periodDays);
		periodDays.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.PERIODS_PICKER_ID);
			}
		});
		periodDayText = (TextView) findViewById(R.id.periodDayText);
		periodDay = (TextView) findViewById(R.id.periodDay);
		
		if (isSetPeriod) {
			periodDays.setText(pDays+"");
			periodDayText.setText(getString(R.string.periodDayfirst));
			periodDay.setVisibility(View.VISIBLE);
			periodDay.setText(pYear+"-"+pMonth+"-"+pDay);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			setCalendarColor(calendar);
		}else{
			pDays = Integer.valueOf(periodDays.getText().toString());
		}
		
		yearMonth = (TextView) findViewById(R.id.month);
		yearMonth.setText(mView.getYear() + "-" + (mView.getMonth() + 1));
		
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about).setIcon(
				R.drawable.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit)
				.setIcon(R.drawable.exit);
		return true;
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
		}
		return super.onOptionsItemSelected(item);
	}

	public void onTouch(Cell cell) {
		int day = cell.getDayOfMonth();
		if (cell.mPaint.getColor() == Color.GRAY) {
			return ;
			// 这是上月的
//			mView.previousMonth();
//			yearMonth.setText(mView.getYear() + "-" + (mView.getMonth() + 1));
		} else if (cell.mPaint.getColor() == Color.LTGRAY) {
			// 下月的
			mView.nextMonth();
			yearMonth.setText(mView.getYear() + "-" + (mView.getMonth() + 1));

			Calendar cal = Calendar.getInstance();//当前显示月份
			cal.set(Calendar.YEAR, mView.getYear());
			cal.set(Calendar.MONTH, mView.getMonth());
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
//			System.out.println("---------mView.getYear()-------"+mView.getYear());
//			System.out.println("---------mView.getMonth()-------"+mView.getMonth());
			
			setCalendarColor(cal);

		} else {
			periodDayText.setText(getString(R.string.periodDayfirst));
			periodDay.setVisibility(View.VISIBLE);
			pYear = mView.getYear();
			pMonth = mView.getMonth() + 1;
			pDay = day;
			periodDay.setText(pYear + "-" +pMonth+ "-" + pDay);
			mView.periodDays(day,Integer.valueOf(periodDays.getText().toString()));
		}
		
		
		mHandler.post(new Runnable() {
			public void run() {
				yearMonth.setText(mView.getYear() + "-" + (mView.getMonth() + 1));
			}
		});
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
//			System.out.println("---------pYear-------"+pYear);
//			System.out.println("---------pMonth-1-------"+(pMonth-1));
//			System.out.println("---------pDay-------"+pDay);
			long times = cal.getTimeInMillis() - pcal.getTimeInMillis();
			long dayTimes = 1000*60*60*24;
			long days = times/dayTimes;
			long multiple = days/pDays;
//			long differDays = days+pDays;
			long date = (multiple+1)*pDays-days+1;
//			System.out.println("------cal----days-------"+days);
//			System.out.println("------cal----multiple-------"+multiple);
//			System.out.println("------cal----date-------"+date);
			
			mView.periodDays(Integer.valueOf(date+""),Integer.valueOf(periodDays.getText().toString()));
		}
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
	 * 存储用户
	 */
	private void savePerson() {
		String days= periodDays.getText().toString();
		String day= periodDay.getText().toString();
		System.out.println("-days-"+days+"-day-"+day);
		System.out.println(("".equals(days)||"".equals(day)));
		if ("".equals(days)||"".equals(day)) {
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
	protected void onDestroy() {
		super.onDestroy();
		geInstance.unbindService(this);
		geInstance.unRegisterReceiver(this);
	}
	protected void initGEDate()
    {
        geInstance = GEInstance.getInstance();
        //初始函数
        geInstance.initialize(this,AppConstant.GE_UID,AppConstant.GE_PID);//每次程序启动只要初始化一次(设置开发者应用UID和PID)
        geInstance.setTestMode(false);//开启测试模式(默认是关闭的,测试的时候可以开启,方便调试并查看广告后台错误信息)
//        geInstance.setOnGEListener(this);//继承GEListener接口(1.监听自定义广告数据  2.监听是否获取金币成功)
        geInstance.setNotificationIcon(R.drawable.icon);//设置状态栏图标
        geInstance.setOpenIntegralWall(true);//是否打开积分墙积分 true打开 false不打开
        geInstance.setScoreRemind(true);//是否每次下载提示“当前下载有积分” true打开，false不打开
        geInstance.openListAdOn(false);//是否开启点击任意(自定义广告除外)广告都打开积分墙(默认不开启)
        geInstance.loadGEPop();//预加载插屏广告
        //互动广告
        interLinearLayout=(LinearLayout)findViewById(R.id.interGELinearLayout);//初始化互动广告必须的布局
    }
}