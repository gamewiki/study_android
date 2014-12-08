package sid.lover;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import cn.waps.AppConnect;

import sid.modle.Clock;
import sid.service.ClockOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import sid.utils.FileUtils;
import sid.utils.ResultLover;
import sid.utils.SelfDateUtils;
import sid.utils.Tools;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddStoryActivity extends BaseActivity {
	private ImageButton saveClock;
	private TextView storyDate;
	private TextView description;
	private TextView datePicker;
	private TextView timePicker;
	private TextView bellsClock;
	private TextView shockClock;
	private TextView statusClock;
	private TextView albumText;
	private EditText titleText;
	private EditText descText;
	private ImageView storyImage;
	private String titleString;
	private String descString;
	private int year, month, day, hour, minute,width;
	private Uri bellsPath=null;
	private String bells=null;
	private String imagePath = null;
	private boolean sdCardExit;
	private File myRecAudioDir;
	/* 铃声文件夹 */
	private String sdPath = "";
	private boolean[] shocksChecked = new boolean[] { true, false, false };
	private boolean[] shocksCheckedListener = new boolean[] { true, false, false};
	private boolean[] statusChecked = new boolean[] { true, false };
	private boolean[] statusCheckedListener = new boolean[] { true, false };
	private Calendar c = Calendar.getInstance();
	private Clock clock = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.lover_addstory_activity);
		width = Tools.getWidth(AddStoryActivity.this);
//		height = PhoneTools.getHeight(AddStoryActivity.this);
//		size = width>height?height:width;

		/* 判断SD Card是否插入 */
		sdCardExit = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		/* 取得SD Card路径作为录音的文件位置 */
		if (sdCardExit) myRecAudioDir = Environment.getExternalStorageDirectory();
		sdPath = myRecAudioDir.getAbsolutePath();
		c.setTimeInMillis(System.currentTimeMillis());
		
		saveClock = (ImageButton) findViewById(R.id.saveClock);
		saveClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addClock();
			}
		});

		storyDate = (TextView) findViewById(R.id.date);
		storyDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.TITLE);
			}
		});
		description = (TextView) findViewById(R.id.description);
		description.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.DESCRITION);
			}
		});
		datePicker = (TextView) findViewById(R.id.datePicker);
		datePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.DATE_PICKER_ID);
			}
		});
		timePicker = (TextView) findViewById(R.id.timePicker);
		timePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.TIME_PICKER_ID);
			}
		});
		bellsClock = (TextView) findViewById(R.id.bellsClock);
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
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, getString(R.string.bellsChoice));
					// 当设置完成之后返回到当前的Activity
					startActivityForResult(intent, AppConstant.BUTTONALARM);
				}else{
					Toast.makeText(AddStoryActivity.this,
							getString(R.string.bellsChoice_failed),Toast.LENGTH_SHORT).show();
				};
			}
		});
		shockClock = (TextView) findViewById(R.id.shockClock);
		shockClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.SHOCK);
			}
		});
		statusClock = (TextView) findViewById(R.id.statusClock);
		statusClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.STATUS);
			}
		});
		albumText = (TextView) findViewById(R.id.album);
		albumText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
			    intent.addCategory(Intent.CATEGORY_OPENABLE);
			    intent.setType("image/*");
			    intent.putExtra("crop", "true");
//			    intent.putExtra("aspectX", 1);
//			    intent.putExtra("aspectY", 1);
//			    intent.putExtra("outputX", size*3/4);
//			    intent.putExtra("outputY", size*3/4);
			    intent.putExtra("return-data", true);

			    startActivityForResult(intent, AppConstant.CAMERABITMAP);
			}
		});
		storyImage = (ImageView) findViewById(R.id.storyImage);
		storyImage.setMaxWidth(width*3/4);

		clock = (Clock) getIntent().getSerializableExtra(AppConstant.CLOCK);
    	Calendar calendar = Calendar.getInstance();
    	if (clock!=null) {
        	initCheckedArray();
        	datePicker.setText(clock.getClockdate());
        	timePicker.setText(clock.getClocktime());
        	storyDate.setText(clock.getTitle());
        	bellsClock.setText(("".equals(clock.getBells()))?getString(R.string.bellsChoice):clock.getBells());
        	shockClock.setText(clock.getShockCate());
        	statusClock.setText(clock.getStatus()==1?getString(R.string.statusOpen):getString(R.string.statusClose));

        	calendar.setTimeInMillis(clock.getStartime());
		}else{
        	datePicker.setText(SelfDateUtils.getDateTime("yyyy-MM-dd"));
        	timePicker.setText(SelfDateUtils.getDateTime("HH:mm"));
        	shockClock.setText(AppConstant.shocks[0]);
		}
    	year = calendar.get(Calendar.YEAR);
    	month = calendar.get(Calendar.MONTH)+1;
    	day = calendar.get(Calendar.DAY_OF_MONTH);
    	hour = calendar.get(Calendar.HOUR_OF_DAY);
    	minute = calendar.get(Calendar.MINUTE);
		
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.APP, 1, R.string.app).setIcon(R.drawable.app);
		menu.add(0, AppConstant.GAME, 2, R.string.game).setIcon(R.drawable.game);
		menu.add(0, AppConstant.ABOUT, 3, R.string.about).setIcon(R.drawable.about);
		menu.add(0, AppConstant.EXIT, 4, R.string.exit).setIcon(R.drawable.exit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(AddStoryActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		} else if (item.getItemId() == AppConstant.APP) {
			AppConnect.getInstance(AddStoryActivity.this).showAppOffers(AddStoryActivity.this);
		} else if (item.getItemId() == AppConstant.GAME) {
			AppConnect.getInstance(AddStoryActivity.this).showGameOffers(AddStoryActivity.this);
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
			case AppConstant.BUTTONALARM:
				try {
					// 得到我们选择的铃声
					bellsPath = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
					bells = RingtoneManager.getRingtone(AddStoryActivity.this, bellsPath).getTitle(AddStoryActivity.this);
					bellsClock.setText(bells);
				} catch (Exception e) {
			}
			case AppConstant.CAMERABITMAP:
				Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
				ResultLover rl = FileUtils.getInstance().compressAndSaveBitmapToSDCard(cameraBitmap, 
						"loverstory",String.valueOf(new Date().getTime()), 100);
				imagePath = (String) rl.getObj();
//				System.out.println("imagePath=========="+imagePath);
				storyImage.setImageBitmap(cameraBitmap);// 将图片显示在ImageView里
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
			datePicker.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
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
			titleText.setText(getString(R.string.titleDays));
			titleString = getString(R.string.titleDays);
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.titleTextClock))
					.setIcon(R.drawable.clock)
					.setView(titleText)
					.setPositiveButton(getString(R.string.confirm), new titlePositiveListener())
					.setNegativeButton(getString(R.string.cancel), null)
					.show();
		case AppConstant.DESCRITION:
			descText=new EditText(this);
			descText.setText(getString(R.string.titleDays));
			descString = getString(R.string.titleDays);
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.titleTextClock))
					.setIcon(R.drawable.clock)
					.setView(descText)
					.setPositiveButton(getString(R.string.confirm), new descriptionPositiveListener())
					.setNegativeButton(getString(R.string.cancel), null)
					.show();
		case AppConstant.DATE_PICKER_ID:
			year = cal.get(Calendar.YEAR);// 得到年
			month = cal.get(Calendar.MONTH);// 得到月，因为从0开始的，所以在使用时要加1
			day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
			return new DatePickerDialog(this, onDateSetListener, year, month,
					day);
		case AppConstant.TIME_PICKER_ID:
			hour = cal.get(Calendar.HOUR_OF_DAY);// 得到小时
			minute = cal.get(Calendar.MINUTE);// 得到分钟
			return new TimePickerDialog(this, onTimeSetListener, hour, minute,
					true);
		case AppConstant.SHOCK:
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.shockChoice))
					.setIcon(R.drawable.clock)
					.setSingleChoiceItems(AppConstant.shocks, 0,
							new shockCheckedListener())
					.setPositiveButton(getString(R.string.confirm), new shockPositiveListener())
					.setNegativeButton(getString(R.string.cancel), new shockNegativeListener())
					.show();
		case AppConstant.STATUS:
			return new AlertDialog.Builder(this).setTitle(getString(R.string.statusChoice))
					.setIcon(R.drawable.clock)
					.setSingleChoiceItems(AppConstant.status, 0, 
							new statusCheckedListener())
					.setPositiveButton(getString(R.string.confirm), new statusPositiveListener())
					.setNegativeButton(getString(R.string.cancel), new statusNegativeListener()).show();
		default:
			break;
		}
		return null;
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
					Toast.makeText(AddStoryActivity.this, getString(R.string.titleError), Toast.LENGTH_SHORT).show();
				}else{
					storyDate.setText(titleString);
				}
			}
			dialog.dismiss();
		}
	}
	
	/**
	 * 提醒文字确认按钮
	 * 
	 */
	class descriptionPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			descString = descText.getText().toString();
			if (descString!=null&&!"".equals(descString)) {
				int title = descString.length();
				if(title<1||title>1000){
					Toast.makeText(AddStoryActivity.this, getString(R.string.note_length_1000), Toast.LENGTH_SHORT).show();
				}else{
					description.setText(descString);
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
	class statusCheckedListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			for (int i=0;i<statusCheckedListener.length;i++) {
				if (i!=which) {
					statusCheckedListener[i] = false;
				}else{
					statusCheckedListener[i] = true;
				}
			}
		}
	}

	/**
	 * 闹钟标签确认按钮
	 * 
	 */
	class statusPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			statusChecked = statusCheckedListener;
			statusClock.setText(getArrayText(statusChecked,AppConstant.status,""));
			dialog.dismiss();
		}
	}


	/**
	 * 闹钟标签取消按钮
	 * 
	 */
	class statusNegativeListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			statusCheckedListener = new boolean[] { true, false };
			dialog.dismiss();
		}
	}

	private void addClock() {
		String shockCate = getArrayText(shocksChecked,AppConstant.shocks,"");
		String date = ((getString(R.string.datePicker)).equals(datePicker.getText().toString()))?"":(datePicker.getText().toString());
		String time = ((getString(R.string.timePicker)).equals(timePicker.getText().toString()))?"":(timePicker.getText().toString());
		c.setTimeInMillis(System.currentTimeMillis());
		
		if ("".equals(date)||"".equals(time)||shockCate.equals(R.string.shockChoice)) {
			Toast.makeText(AddStoryActivity.this, getString(R.string.clockAddErrorText), Toast.LENGTH_SHORT).show();
			return ;
		}
		
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		String createtime = SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss");
		long startime = c.getTimeInMillis();
		String title = storyDate.getText().toString();
		String desc = description.getText().toString();
		String bells = this.bells==null?"":this.bells;
		String bellsPath = this.bellsPath==null?"":this.bellsPath.toString();
		int status = (statusClock.getText().toString().equals(getString(R.string.statusClose)))?0:1;
		boolean exist = true;
		
		boolean isToday=false;
		c.setTimeInMillis(System.currentTimeMillis());
		
		clock = ClockOperation.saveClock(isToday,AddStoryActivity.this,clock, 0, date, time, createtime, startime,
				startime, hour, minute, title, desc, "","0", bells, bellsPath,imagePath, shockCate,
				AppConstant.signs[1],status, exist);
		initArray();

		startActivity(new Intent(AddStoryActivity.this,LoverActivity.class));
		AddStoryActivity.this.finish();
	}

	/**
	 * 初始化数据
	 */
	private void initArray() {
		year=0; month=0; day=0; hour=0; minute=0;
		bellsPath=null;
		bells=null;
		sdPath = "";
		shocksChecked = new boolean[] { true, false, false };
		statusChecked = new boolean[] { true, false };
		c = Calendar.getInstance();
		clock = null;
		initCheckedArray();
	}
	/**
	 * 初始化选择框数据
	 */
	private void initCheckedArray(){
		statusCheckedListener = new boolean[] { true, false };
		shocksCheckedListener = new boolean[] { true, false, false};
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
