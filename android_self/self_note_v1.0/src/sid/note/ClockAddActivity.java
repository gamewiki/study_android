package sid.note;

import java.io.File;
import java.util.Calendar;

import sid.modle.Clock;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DatabaseOperation;
import sid.utils.ExitApplication;
import sid.utils.SelfDateUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClockAddActivity extends Activity {
	private ImageButton createClock;
	private ImageButton exitClock;
	private Button titleClock;
	private Button datePicker;
	private Button timePicker;
	private Button weeksClock;
	private Button intervalClock;
	private Button bellsClock;
	private Button shockClock;
	private Button signsClock;
	private EditText titleText;
	private String titleString;
	private EditText intervelText;
	private String intervelString;
	private int intervelInteger=0;
	private int year, month, day, hour, minute;
	private Uri bellsPath=null;
	private String bells=null;
	private boolean sdCardExit;
	private File myRecAudioDir;
	/* 铃声文件夹 */
	private String sdPath = "";
	private boolean[] weeksChecked = new boolean[] { false, false, false,
			false, false, false, false };
	private boolean[] weeksCheckedListener = new boolean[] { false, false,
			false, false, false, false, false };
	private boolean[] shocksChecked = new boolean[] { true, false, false };
	private boolean[] shocksCheckedListener = new boolean[] { true, false, false};
	private boolean[] signsChecked = new boolean[] { true, false };
	private boolean[] signsCheckedListener = new boolean[] { true, false };
	private Calendar c = Calendar.getInstance();
	private Clock clock = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock_add);
		/* 判断SD Card是否插入 */
		sdCardExit = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		/* 取得SD Card路径作为录音的文件位置 */
		if (sdCardExit) myRecAudioDir = Environment.getExternalStorageDirectory();
		sdPath = myRecAudioDir.getAbsolutePath();
		c.setTimeInMillis(System.currentTimeMillis());
		
		createClock = (ImageButton) findViewById(R.id.createClock);
		createClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addClock();
			}
		});
		exitClock = (ImageButton) findViewById(R.id.exitClock);
		exitClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initArray();
				finish();
			}
		});
		
		titleClock = (Button) findViewById(R.id.titleClock);
		titleClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.TITLE);
			}
		});
		datePicker = (Button) findViewById(R.id.datePicker);
		datePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.DATEPICKER);
			}
		});
		timePicker = (Button) findViewById(R.id.timePicker);
		timePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.TIMEPICKER);
			}
		});
		weeksClock = (Button) findViewById(R.id.weeksClock);
		weeksClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.WEEKS);
			}
		});
		intervalClock = (Button) findViewById(R.id.intervalClock);
		intervalClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.INTERVAL);
			}
		});
		bellsClock = (Button) findViewById(R.id.bellsClock);
		bellsClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				showDialog(AppConstant.BELLS);
				if (bFolder(AppConstant.strAlarmFolder)) {
					// 打开系统铃声设置
					Intent intent = new Intent(
							RingtoneManager.ACTION_RINGTONE_PICKER);
					// 设置铃声类型和title
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
							RingtoneManager.TYPE_ALARM);
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, getString(R.string.bellsClock));
					// 当设置完成之后返回到当前的Activity
					startActivityForResult(intent, AppConstant.ButtonAlarm);
				}else{
					Toast.makeText(ClockAddActivity.this,
							getString(R.string.bellsClock_failed),Toast.LENGTH_SHORT).show();
				};
			}
		});
		shockClock = (Button) findViewById(R.id.shockClock);
		shockClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.SHOCK);
			}
		});
		signsClock = (Button) findViewById(R.id.signsClock);
		signsClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.SIGNS);
			}
		});

		clock = (Clock) getIntent().getSerializableExtra(AppConstant.CLOCK);
    	if (clock!=null) {
        	datePicker.setText(clock.getClockdate());
        	timePicker.setText(clock.getClocktime());
        	titleClock.setText(clock.getTitle());
        	initCheckedArray();
        	String weeks = clock.getWeeks();
        	if (!weeks.equals("")) {
            	String[] weeksCheckedDate = weeks.split(",");
            	for (int i = 0; i < weeksCheckedDate.length; i++) {
    				weeksChecked[Integer.valueOf(weeksCheckedDate[i])]=true;
    			}
			}
        	getWeeksText(weeksChecked);
        	intervalClock.setText(clock.getInterval());
        	bellsClock.setText(("".equals(clock.getBells()))?getString(R.string.bellsClock):clock.getBells());
        	shockClock.setText(clock.getShockCate());
        	signsClock.setText(clock.getSignCate());
		}
		
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
			intent.setClass(ClockAddActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

	/** 当设置铃声之后的回调函数 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case AppConstant.ButtonAlarm:
			try {
				// 得到我们选择的铃声
				bellsPath = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				bells = RingtoneManager.getRingtone(ClockAddActivity.this, bellsPath).getTitle(ClockAddActivity.this);
				bellsClock.setText(bells);
			} catch (Exception e) {
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	// 监听器；用户监听用户点击datepickerDialog的set按钮时所设置的日期信息
	// 月份是从0开始计算的
	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int yearOfDate, int monthOfYear,
				int dayOfMonth) {
			year = yearOfDate;
			month = monthOfYear + 1;
			day = dayOfMonth;
			datePicker.setText(year + "-" + (monthOfYear + 1) + "-"
					+ dayOfMonth);
		}
	};
	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
			hour = hourOfDay;
			minute = minuteOfHour;
			timePicker.setText(hourOfDay + (minuteOfHour<10?(":0"+minuteOfHour):(":"+minuteOfHour)) );
		}
	};

	/**
	 * 创建闹钟事件对话框 1：表示闹钟是否重复 2：显示闹钟铃声 3：设置闹钟是否振动 4：设置闹钟标签
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar cal = Calendar.getInstance();// 使用日历类
		switch (id) {
		case AppConstant.TITLE:
			titleText=new EditText(this);
			titleText.setText(getString(R.string.title));
			titleString = getString(R.string.title);
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.titleTextClock))
					.setIcon(R.drawable.clock)
					.setView(titleText)
					.setPositiveButton(getString(R.string.confirm), new titlePositiveListener())
					.setNegativeButton(getString(R.string.cancel), null)
					.show();
		case AppConstant.DATEPICKER:
			year = cal.get(Calendar.YEAR);// 得到年
			month = cal.get(Calendar.MONTH);// 得到月，因为从0开始的，所以在使用时要加1
			day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
			return new DatePickerDialog(this, onDateSetListener, year, month,
					day);
		case AppConstant.TIMEPICKER:
			hour = cal.get(Calendar.HOUR_OF_DAY);// 得到小时
			minute = cal.get(Calendar.MINUTE);// 得到分钟
			return new TimePickerDialog(this, onTimeSetListener, hour, minute,
					true);
		case AppConstant.WEEKS:
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.weeksClock))
					.setIcon(R.drawable.clock)
					.setMultiChoiceItems(AppConstant.weeks,
							weeksCheckedListener, new weeksCheckedListener())
					.setPositiveButton(getString(R.string.confirm), new weeksPositiveListener())
					.setNegativeButton(getString(R.string.cancel), new weeksNegativeListener())
					.show();
		case AppConstant.INTERVAL:
			intervelText=new EditText(this);
			intervelText.setText(getString(R.string.interval));
			intervelText.setSelectAllOnFocus(true);
			intervelString = getString(R.string.interval);
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.intervalClock))
					.setIcon(R.drawable.clock)
					.setView(intervelText)
					.setPositiveButton(getString(R.string.confirm), new intervalPositiveListener())
					.setNegativeButton(getString(R.string.cancel), null)
					.show();
		case AppConstant.SHOCK:
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.shockClock))
					.setIcon(R.drawable.clock)
					.setSingleChoiceItems(AppConstant.shocks, 0,
							new shockCheckedListener())
					.setPositiveButton(getString(R.string.confirm), new shockPositiveListener())
					.setNegativeButton(getString(R.string.cancel), new shockNegativeListener())
					.show();
		case AppConstant.SIGNS:
			return new AlertDialog.Builder(this).setTitle(getString(R.string.signsClock))
					.setIcon(R.drawable.clock)
					.setSingleChoiceItems(AppConstant.signs, 0, 
							new signsCheckedListener())
					.setPositiveButton(getString(R.string.confirm), new signsPositiveListener())
					.setNegativeButton(getString(R.string.cancel), new signsNegativeListener()).show();
		default:
			break;
		}
		return null;
	}

	/**
	 * 重复多选监听事件
	 * 
	 * @author Administrator
	 * 
	 */
	class weeksCheckedListener implements OnMultiChoiceClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			weeksCheckedListener[which] = isChecked;
		}
	}

	/**
	 * 重复确认按钮
	 * 
	 */
	class weeksPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			weeksChecked = weeksCheckedListener;
			getWeeksText(weeksChecked);
		}
	}
	private void getWeeksText(boolean[] weeksChecked) {
		if (weeksChecked[0]==true&&weeksChecked[1]==true&&weeksChecked[2]==true&&weeksChecked[3]==true&&weeksChecked[4]==true&&weeksChecked[5]==false&&weeksChecked[6]==false) {
			weeksClock.setText(getString(R.string.weeksClock_workday));
		}else if (weeksChecked[0]==true&&weeksChecked[1]==true&&weeksChecked[2]==true&&weeksChecked[3]==true&&weeksChecked[4]==true&&weeksChecked[5]==true&&weeksChecked[6]==true) {
			weeksClock.setText(getString(R.string.weeksClock_everyday));
		}else if (weeksChecked[0]==false&&weeksChecked[1]==false&&weeksChecked[2]==false&&weeksChecked[3]==false&&weeksChecked[4]==false&&weeksChecked[5]==false&&weeksChecked[6]==false) {
			weeksClock.setText(getString(R.string.weeksClock_norepeate));
		}else{
			weeksClock.setText(getString(R.string.weeksClock_before)+getArrayText(weeksChecked,AppConstant.weeks," ")+getString(R.string.weeksClock_after));
		}
	}

	/**
	 * 重复取消按钮
	 * 
	 */
	class weeksNegativeListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			weeksCheckedListener = new boolean[] { false, false, false, false,
					false, false, false };
		}
	}
	
	/**
	 * 提醒文字确认按钮
	 * 
	 */
	class titlePositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			titleString = titleText.getText().toString();
			if (titleString!=null&&!"".equals(titleString)) {
				int title = titleString.length();
				if(title<1||title>60){
					Toast.makeText(ClockAddActivity.this, getString(R.string.titleError), Toast.LENGTH_SHORT).show();
				}else{
					titleClock.setText(titleString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 间隔确认按钮
	 * 
	 */
	class intervalPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			intervelString = intervelText.getText().toString();
			if (intervelString!=null&&!"".equals(intervelString)) {
				int intervel = Integer.parseInt(intervelString);
				if(intervel<1||intervel>60){
					Toast.makeText(ClockAddActivity.this, getString(R.string.intervalError), Toast.LENGTH_SHORT).show();
				}else{
					intervelInteger = Integer.parseInt(intervelString);
					intervalClock.setText(intervelString);
				}
			}
			dialog.dismiss();
		}
	}
	
	/**
	 * 振动模式监听事件
	 * 
	 * @author Administrator
	 * 
	 */
	class shockCheckedListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			for (int i=0;i<shocksCheckedListener.length;i++) {
				if (i!=which) {
					shocksCheckedListener[i] = false;
				}else{
					shocksCheckedListener[i] = true;
				}
			}
		}
	}

	/**
	 * 振动方式确认按钮
	 * 
	 */
	class shockPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			shocksChecked = shocksCheckedListener;
			shockClock.setText(getArrayText(shocksChecked,AppConstant.shocks,""));
			dialog.dismiss();
		}
	}

	/**
	 * 振动方式取消按钮
	 * 
	 */
	class shockNegativeListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			shocksCheckedListener = new boolean[] { true, false, false };
			dialog.dismiss();
		}
	}

	/**
	 * 闹钟标签监听事件
	 * 
	 * @author Administrator
	 * 
	 */
	class signsCheckedListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			for (int i=0;i<signsCheckedListener.length;i++) {
				if (i!=which) {
					signsCheckedListener[i] = false;
				}else{
					signsCheckedListener[i] = true;
				}
			}
		}
	}

	/**
	 * 闹钟标签确认按钮
	 * 
	 */
	class signsPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			signsChecked = signsCheckedListener;
			if (signsChecked[0]==false) {
				datePicker.setVisibility(View.VISIBLE);
				weeksClock.setVisibility(View.GONE);
				weeksCheckedListener = new boolean[] { false, false, false, false,
						false, false, false };
				intervalClock.setVisibility(View.GONE);
			}else{
				datePicker.setVisibility(View.GONE);
				weeksClock.setVisibility(View.VISIBLE);
				intervalClock.setVisibility(View.VISIBLE);
			}
			signsClock.setText(getArrayText(signsChecked,AppConstant.signs,""));
			dialog.dismiss();
		}
	}

	/**
	 * 闹钟标签取消按钮
	 * 
	 */
	class signsNegativeListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			signsCheckedListener = new boolean[] { true, false };
			dialog.dismiss();
		}
	}

	private void addClock() {
		int times = intervelInteger * 1000 * 60;
		String date = ((getString(R.string.datePicker)).equals(datePicker.getText().toString()))?"":(datePicker.getText().toString());
		String time = ((getString(R.string.timePicker)).equals(timePicker.getText().toString()))?"":(timePicker.getText().toString());
		c.setTimeInMillis(System.currentTimeMillis());
		if (!"".equals(date)) {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month-1);
			c.set(Calendar.DAY_OF_MONTH, day);
		}
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		String createtime = SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss");
		long startime = c.getTimeInMillis();
		String title = titleClock.getText().toString();
		String weeks =  getArrayNum(weeksChecked,AppConstant.weeks,",");
		String interval =  intervalClock.getText().toString();
		String bells = this.bells==null?"":this.bells;
		String bellsPath = this.bellsPath==null?"":this.bellsPath.toString();
		String shockCate = getArrayText(shocksChecked,AppConstant.shocks,"");
		String signCate = getArrayText(signsChecked,AppConstant.signs,"");
		boolean exist = true;
		
		if ("".equals(time)||signCate.equals(R.string.cateClock)||shockCate.equals(R.string.shockClock)) {
			Toast.makeText(ClockAddActivity.this, getString(R.string.clockAddErrorText), Toast.LENGTH_SHORT).show();
			return ;
		}
		
		boolean isToday=false;
		String[] weekdays = weeks.split(",");
		c.setTimeInMillis(System.currentTimeMillis());
		String weekday = (c.get(Calendar.DAY_OF_WEEK)-1)+"";
		for (String week : weekdays) {
			if (week.equals(weekday)) {
				isToday = true;
			}
		}
		
		clock = DatabaseOperation.saveClock(isToday,ClockAddActivity.this,clock,times, date, time, createtime, startime, hour, minute, title, weeks, interval, bells, bellsPath, shockCate, signCate, exist);
		initArray();
	}

	/**
	 * 初始化数据
	 */
	private void initArray() {
		intervelInteger=0;
		year=0; month=0; day=0; hour=0; minute=0;
		bellsPath=null;
		bells=null;
		sdPath = "";
		weeksChecked = new boolean[] { false, false, false,
				false, false, false, false };
		shocksChecked = new boolean[] { true, false, false };
		signsChecked = new boolean[] { true, false };
		c = Calendar.getInstance();
		clock = null;
		initCheckedArray();
	}
	/**
	 * 初始化选择框数据
	 */
	private void initCheckedArray(){
		signsCheckedListener = new boolean[] { true, false };
		shocksCheckedListener = new boolean[] { true, false, false};
		weeksCheckedListener = new boolean[] { false, false,
				false, false, false, false, false };
	}

	/**
	 * 获取数组的文字内容
	 * @param arrayChecked
	 * @param array
	 * @return
	 */
	private String getArrayText(boolean[] arrayChecked,String[] array,String split) {
		String buttonText="";
		int num=0;
		for (boolean isChecked : arrayChecked) {
			if (isChecked) {
				buttonText = buttonText + split + array[num];
			}
			num++;
		}
		return buttonText;
	}

	/**
	 * 获取数组的列标
	 * @param arrayChecked
	 * @param array
	 * @return
	 */
	private String getArrayNum(boolean[] arrayChecked,String[] array,String split) {
		String buttonText="";
		int num=0;
		for (boolean isChecked : arrayChecked) {
			if (isChecked) {
				buttonText = buttonText + split + num;
			}
			num++;
		}
		return buttonText.length()>0?buttonText.substring(1):buttonText;
	}
	// 检测是否存在指定的文件夹
	// 如果不存在则创建
	private boolean bFolder(String strFolder) {
		boolean btmp = false;
		File f = new File(sdPath+strFolder);
		if (!f.exists()) {
			if (f.mkdirs()) {
				btmp = true;
			} else {
				btmp = false;
			}
		} else {
			btmp = true;
		}
		return btmp;
	}

}