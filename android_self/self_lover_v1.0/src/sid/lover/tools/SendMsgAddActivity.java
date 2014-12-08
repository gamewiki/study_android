package sid.lover.tools;

import java.util.Calendar;

import sid.lover.R;
import sid.modle.Clock;
import sid.service.ClockOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import sid.utils.PhoneTools;
import sid.utils.SelfDateUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SendMsgAddActivity extends Activity {
	private ImageButton saveLover;
	private TextView time;
	private TextView editphone;
	private EditText editphoneText;
	private TextView editdescription;
	private EditText editdescriptionText;
	private TextView editnickname;
	private EditText editnicknameText;
	private int hour, minute;
	private Clock clock = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_send);
		
		saveLover = (ImageButton) findViewById(R.id.saveLover);
		saveLover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveClock();
			}
		});

		editnickname = (TextView)findViewById(R.id.nickname);
		editnickname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.NICK_PICKER_ID);
			}
		});
		time = (TextView) findViewById(R.id.time);
		time.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.TIME_PICKER_ID);
			}
		});
		editdescription = (TextView)findViewById(R.id.editdescription);
		editdescription.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.DES_PICKER_ID);
			}
		});
		
		editphone = (TextView) findViewById(R.id.editphone);
		editphone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.PHNOE_PICKER_ID);
			}
		});


		clock = (Clock) getIntent().getSerializableExtra(AppConstant.CLOCK);
    	if (clock!=null) {
    		editnickname.setText(clock.getBells());
    		time.setText(clock.getHour()+(clock.getMinute()<10?(":0"+clock.getMinute()):(":"+clock.getMinute())));
    		editdescription.setText(clock.getTitle());
    		editphone.setText(clock.getBellsPath());
		}else{
			time.setText(SelfDateUtils.getDateTime("HH:mm"));
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
			intent.setClass(SendMsgAddActivity.this, AboutActivity.class);
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
		switch (id) {
			case AppConstant.TIME_PICKER_ID:
				hour = cal.get(Calendar.HOUR_OF_DAY);// 得到小时
				minute = cal.get(Calendar.MINUTE);// 得到分钟
				return new TimePickerDialog(this, onTimeSetListener, hour, minute,
						true);
			case AppConstant.PHNOE_PICKER_ID:
				editphoneText=new EditText(this);
				editphoneText.setSelectAllOnFocus(true);
				editphoneText.setKeyListener(new DigitsKeyListener(false,true));
				editphoneText.setText(editphone.getText().toString());
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
				editdescriptionText.setText(editdescription.getText().toString());
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
				editnicknameText.setText(editnickname.getText().toString());
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
	 * 编辑电话号码确认按钮
	 * 
	 */
	class editPhoneListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editphoneString = editphoneText.getText().toString();
			if (editphoneString!=null&&!"".equals(editphoneString)) {
				int length = editphoneString.length();
				if(length!=11){
					Toast.makeText(SendMsgAddActivity.this, getString(R.string.editphoneError), Toast.LENGTH_SHORT).show();
				}else{
					editphone.setText(editphoneString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 编辑发送的内容
	 * 
	 */
	class editDescriptionListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editphoneString = editdescriptionText.getText().toString();
			if (editphoneString!=null&&!"".equals(editphoneString)) {
				int length = editphoneString.length();
				if(length>=50){
					Toast.makeText(SendMsgAddActivity.this, getString(R.string.editSendMsgError), Toast.LENGTH_SHORT).show();
				}else{
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
				int length = editnicknameString.length();
				if(length>=10){
					Toast.makeText(SendMsgAddActivity.this, getString(R.string.editnicknameError), Toast.LENGTH_SHORT).show();
				}else{
					editnickname.setText(editnicknameString);
				}
			}
			dialog.dismiss();
		}
	}
	


	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
			hour = hourOfDay;
			minute = minuteOfHour;
			time.setText(hourOfDay + (minuteOfHour<10?(":0"+minuteOfHour):(":"+minuteOfHour)) );
		}
	};
	

	/**
	 * 存储短信
	 */
	private void saveClock() {
		String bellsPath = editphone.getText().toString();
		if (!PhoneTools.isMobileNO(bellsPath)) {
			Toast.makeText(SendMsgAddActivity.this, getString(R.string.editphoneError), Toast.LENGTH_SHORT).show();
			return;
		}
		Calendar c = Calendar.getInstance();
		String time = this.time.getText().toString();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		String createtime = SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss");
		long startime = c.getTimeInMillis();
		String weeks =  "0,1,2,3,4,5,6";
		String interval =  "";
		String bells = editnickname.getText().toString();
		String shockCate = AppConstant.shocks[0];
		boolean exist = true;
		
		if ("".equals(time)||shockCate.equals(R.string.shockClock)) {
			Toast.makeText(SendMsgAddActivity.this, getString(R.string.clockAddErrorText), Toast.LENGTH_SHORT).show();
			return ;
		}
		
		boolean isToday=true;
		int status = 1;
		
		clock = ClockOperation.saveClock(isToday,SendMsgAddActivity.this,clock,0, editnickname.getText().toString(), time, createtime, startime, hour, minute, editdescription.getText().toString(), weeks, interval, bells, bellsPath, shockCate, AppConstant.signs[2],status, exist);
	}
}
