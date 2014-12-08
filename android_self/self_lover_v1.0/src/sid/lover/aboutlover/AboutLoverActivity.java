package sid.lover.aboutlover;

import java.util.Calendar;

import sid.lover.R;
import sid.lover.story.NotesActivity;
import sid.modle.Person;
import sid.service.PersonOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AboutLoverActivity extends Activity {
	private ImageButton saveLover;
	private TextView date;
	private TextView constellationNum;
	private TextView editphone;
	private EditText editphoneText;
	private TextView editdescription;
	private EditText editdescriptionText;
	private TextView editnickname;
	private EditText editnicknameText;
	private ImageButton constellation;
	private ImageButton favorite;
	private ImageButton experience;
	Person person = new Person();
	private boolean isPartial;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_about);
		
		person = PersonOperation.getPersonByType(AboutLoverActivity.this,AppConstant.LOVER);
		
		isPartial = person.isPartial();

		saveLover = (ImageButton) findViewById(R.id.saveLover);
		saveLover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveLover();
			}
		});

		editnickname = (TextView)findViewById(R.id.nickname);
		editnickname.setText(isPartial?getString(R.string.nickname):person.getName());
		editnickname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.NICK_PICKER_ID);
			}
		});
		constellationNum = (TextView) findViewById(R.id.constellationNum);
		date = (TextView) findViewById(R.id.date);
		date.setText(isPartial?"0000-00-00":person.getBirthday());
		date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.DATE_PICKER_ID);
			}
		});
		editdescription = (TextView)findViewById(R.id.editdescription);
		editdescription.setText(isPartial?getString(R.string.editdescriptionError):person.getDescription());
		editdescription.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.DES_PICKER_ID);
			}
		});
		
		editphone = (TextView) findViewById(R.id.editphone);
		editphone.setText(isPartial?getString(R.string.editephone):person.getPhone());
		editphone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.PHNOE_PICKER_ID);
			}
		});

		constellation = (ImageButton) findViewById(R.id.constellation);
		constellation.setOnClickListener(new constellationOnClickListener());
		favorite = (ImageButton) findViewById(R.id.favorite);
		favorite.setOnClickListener(new favoriteOnClickListener());
		experience = (ImageButton) findViewById(R.id.experience);
		experience.setOnClickListener(new experienceOnClickListener());
		
		if (isPartial) {
			person.setName(getString(R.string.nickname));
		}else{
			setConstellation(person.getMonth(),person.getDay());
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
			intent.setClass(AboutLoverActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH);// 得到月，因为从0开始的，所以在使用时要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		if (!isPartial) {
			year = person.getYear();
			month = person.getMonth()-1;
			day = person.getDay();
		}
		switch (id) {
			case AppConstant.DATE_PICKER_ID:
				return new DatePickerDialog(this, onDateSetListener, year, month,
						day);
			case AppConstant.PHNOE_PICKER_ID:
				editphoneText=new EditText(this);
				editphoneText.setSelectAllOnFocus(true);
				editphoneText.setKeyListener(new DigitsKeyListener(false,true));
				editphoneText.setText(person.getPhone());
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.editephone))
						.setIcon(R.drawable.phone_50)
						.setView(editphoneText)
						.setPositiveButton(getString(R.string.confirm), new editPhoneListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			case AppConstant.DES_PICKER_ID:
				editdescriptionText=new EditText(this);
				editdescriptionText.setSelectAllOnFocus(true);
				editdescriptionText.setText(person.getDescription());
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.editdescription))
						.setIcon(R.drawable.smile_50)
						.setView(editdescriptionText)
						.setPositiveButton(getString(R.string.confirm), new editDescriptionListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			case AppConstant.NICK_PICKER_ID:
				editnicknameText=new EditText(this);
				editnicknameText.setSelectAllOnFocus(true);
				editnicknameText.setText(person.getName());
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.nickname))
						.setIcon(R.drawable.smile_50)
						.setView(editnicknameText)
						.setPositiveButton(getString(R.string.confirm), new editNickNameListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			}
		return null;
	}
	
	/**
	 * 创建星座按钮的监听
	 * @author Administrator
	 *
	 */
	class constellationOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("id", constellationNum.getText().toString());
			intent.setClass(AboutLoverActivity.this, ConstellationActivity.class);
			startActivity(intent);
		}
	}
	
	/**
	 * 创建喜好按钮的监听
	 * @author Administrator
	 *
	 */
	class favoriteOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			intentToNotes(AppConstant.NOTE_FAVORITE);
		}
	}
	
	/**
	 * 创建经历按钮的监听
	 * @author Administrator
	 *
	 */
	class experienceOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			intentToNotes(AppConstant.NOTE_EXPERIENCE);
		}
	}

	/**
	 * 挑转到列表页面
	 * @param type
	 */
	private void intentToNotes(int type) {
		Intent intent = new Intent();
		intent.putExtra("type", type);
		intent.setClass(AboutLoverActivity.this, NotesActivity.class);
		startActivity(intent);
	}

	/**
	 * 编辑电话号码确认按钮
	 * 
	 */
	class editPhoneListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editphoneString = editphoneText.getText().toString();
			if (editphoneString!=null&&!"".equals(editphoneString)) {
				int intervel = editphoneString.length();
				if(intervel!=11){
					Toast.makeText(AboutLoverActivity.this, getString(R.string.editphoneError), Toast.LENGTH_SHORT).show();
				}else{
					person.setPhone(editphoneString);
					editphone.setText(editphoneString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 编辑个人描述确认按钮
	 * 
	 */
	class editDescriptionListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editphoneString = editdescriptionText.getText().toString();
			if (editphoneString!=null&&!"".equals(editphoneString)) {
				int intervel = editphoneString.length();
				if(intervel>=100){
					Toast.makeText(AboutLoverActivity.this, getString(R.string.editdescriptionError), Toast.LENGTH_SHORT).show();
				}else{
					person.setDescription(editphoneString);
					editdescription.setText(editphoneString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 编辑个人昵称确认按钮
	 * 
	 */
	class editNickNameListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editnicknameString = editnicknameText.getText().toString();
			if (editnicknameString!=null&&!"".equals(editnicknameString)) {
				int intervel = editnicknameString.length();
				if(intervel>=10){
					Toast.makeText(AboutLoverActivity.this, getString(R.string.editnicknameError), Toast.LENGTH_SHORT).show();
				}else{
					person.setName(editnicknameString);
					editnickname.setText(editnicknameString);
				}
			}
			dialog.dismiss();
		}
	}
	

	// 监听器；用户监听用户点击datepickerDialog的set按钮时所设置的日期信息
	// 月份是从0开始计算的
	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int month,
				int day) {
			person.setYear(year);
			person.setMonth(month+1);
			person.setDay(day);
			person.setBirthday(year + "-" + (month+1) + "-" + day);
			date.setText(year + "-" + (month+1) + "-" + day);
			
			int num = setConstellation(month, day);
			person.setConstellation(num);
		}
	};
	

	/**
	 * 存储用户
	 */
	private void saveLover() {
		if (!person.isPartial()) {
			person.setType(AppConstant.LOVER);
			PersonOperation.savePerson(AboutLoverActivity.this,person);
		}else{
			Toast.makeText(AboutLoverActivity.this, getString(R.string.partialError), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 设置星座
	 * @param month
	 * @param day
	 * @return
	 */
	private int setConstellation(int month, int day) {
		//开始设置星座
		int birthDayNum = Integer.valueOf((month+1) + "" + day);
		int num = 0;
		if (birthDayNum>=1222||birthDayNum<=119) {
			num = 10;
			constellation.setBackgroundResource(R.drawable.capricorn_selector);
		}else if (120<=birthDayNum&&birthDayNum<=218) {
			num = 11;
			constellation.setBackgroundResource(R.drawable.aquarius_selector);
		}else if (219<=birthDayNum&&birthDayNum<=320) {
			num = 12;
			constellation.setBackgroundResource(R.drawable.pisces_selector);
		}else if (321<=birthDayNum&&birthDayNum<=419) {
			num = 1;
			constellation.setBackgroundResource(R.drawable.aries_selector);
		}else if (420<=birthDayNum&&birthDayNum<=520) {
			num = 2;
			constellation.setBackgroundResource(R.drawable.taurus_selector);
		}else if (521<=birthDayNum&&birthDayNum<=621) {
			num = 3;
			constellation.setBackgroundResource(R.drawable.gemini_selector);
		}else if (622<=birthDayNum&&birthDayNum<=722) {
			num = 4;
			constellation.setBackgroundResource(R.drawable.cancer_selector);
		}else if (723<=birthDayNum&&birthDayNum<=822) {
			num = 5;
			constellation.setBackgroundResource(R.drawable.leo_selector);
		}else if (823<=birthDayNum&&birthDayNum<=922) {
			num = 6;
			constellation.setBackgroundResource(R.drawable.virgo_selector);
		}else if (923<=birthDayNum&&birthDayNum<=1022) {
			num = 7;
			constellation.setBackgroundResource(R.drawable.libra_selector);
		}else if (1023<=birthDayNum&&birthDayNum<=1121) {
			num = 8;
			constellation.setBackgroundResource(R.drawable.scorpio_selector);
		}else if (1122<=birthDayNum&&birthDayNum<=1221) {
			num = 9;
			constellation.setBackgroundResource(R.drawable.sagittarius_selector);
		}
		constellationNum.setText(num+"");
		return num;
	}
}
