package sid.lover.setting;

import sid.lover.R;
import sid.modle.Setting;
import sid.service.SettingOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity {
	private ImageButton saveLover;
	private TextView editPassword;
	private EditText editPasswordText;
	private TextView editQuestion;
	private EditText editQuestionText;
	private TextView editAnswer;
	private EditText editAnswerText;
	private Button resetting;
	private EditText editResettingText;
	private Setting setting = null;

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

		editPassword = (TextView)findViewById(R.id.password);
		editPassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.PASSWORD_PICKER_ID);
			}
		});
		editQuestion = (TextView)findViewById(R.id.question);
		editQuestion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.QUESTION_PICKER_ID);
			}
		});
		
		editAnswer = (TextView) findViewById(R.id.answer);
		editAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.ANSWER_PICKER_ID);
			}
		});
		
		resetting = (Button)findViewById(R.id.resetting);
		editAnswer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(AppConstant.RESET_PICKER_ID);
			}
		});


		setting = SettingOperation.getSetting(SettingActivity.this);
    	if (setting.isEmpty()) {
    		resetting.setVisibility(View.GONE);
		}else{
    		resetting.setVisibility(View.VISIBLE);
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
			intent.setClass(SettingActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case AppConstant.ANSWER_PICKER_ID:
				editAnswerText=new EditText(this);
				editAnswerText.setSelectAllOnFocus(true);
				editAnswerText.setKeyListener(new DigitsKeyListener(false,true));
				editAnswerText.setText(editAnswer.getText().toString());
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.setAnswer))
						.setIcon(R.drawable.phone_50)
						.setView(editAnswerText)
						.setPositiveButton(getString(R.string.confirm), new editAnswerListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			case AppConstant.QUESTION_PICKER_ID:
				editQuestionText=new EditText(this);
				editQuestionText.setSelectAllOnFocus(true);
				editQuestionText.setText(editQuestion.getText().toString());
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.setQuestion))
						.setIcon(R.drawable.smile_50)
						.setView(editQuestionText)
						.setPositiveButton(getString(R.string.confirm), new editQuestionListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			case AppConstant.PASSWORD_PICKER_ID:
				editPasswordText=new EditText(this);
				editPasswordText.setSelectAllOnFocus(true);
				editPasswordText.setText(editPassword.getText().toString());
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.setPassword))
						.setIcon(R.drawable.smile_50)
						.setView(editPasswordText)
						.setPositiveButton(getString(R.string.confirm), new editPasswordListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			case AppConstant.RESET_PICKER_ID:
				editResettingText=new EditText(this);
				editResettingText.setSelectAllOnFocus(true);
				return new AlertDialog.Builder(this)
						.setTitle(getString(R.string.setAnswer))
						.setIcon(R.drawable.smile_50)
						.setView(editResettingText)
						.setPositiveButton(getString(R.string.confirm), new editResettingListener())
						.setNegativeButton(getString(R.string.cancel), null)
						.show();
			}
		return null;
	}
	
	/**
	 * 编辑答案确认按钮
	 * 
	 */
	class editAnswerListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editAnswerString = editAnswerText.getText().toString();
			if (editAnswerString!=null&&!"".equals(editAnswerString)) {
				int length = editAnswerString.length();
				if(length>=20){
					Toast.makeText(SettingActivity.this, getString(R.string.settingError), Toast.LENGTH_SHORT).show();
				}else{
					editAnswer.setText(editAnswerString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 编辑发送的内容
	 * 
	 */
	class editQuestionListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editAnswerString = editQuestionText.getText().toString();
			if (editAnswerString!=null&&!"".equals(editAnswerString)) {
				int length = editAnswerString.length();
				if(length>=20){
					Toast.makeText(SettingActivity.this, getString(R.string.settingError), Toast.LENGTH_SHORT).show();
				}else{
					editQuestion.setText(editAnswerString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 编辑个人昵称确认按钮
	 * 
	 */
	class editPasswordListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editPasswordString = editPasswordText.getText().toString();
			if (editPasswordString!=null&&!"".equals(editPasswordString)) {
				int length = editPasswordString.length();
				if(length>=20){
					Toast.makeText(SettingActivity.this, getString(R.string.settingError), Toast.LENGTH_SHORT).show();
				}else{
					editPassword.setText(editPasswordString);
				}
			}
			dialog.dismiss();
		}
	}
	/**
	 * 编辑个人昵称确认按钮
	 * 
	 */
	class editResettingListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String editPasswordString = editPasswordText.getText().toString();
			if (editPasswordString!=null&&!"".equals(editPasswordString)) {
				int length = editPasswordString.length();
				if(length>=20){
					Toast.makeText(SettingActivity.this, getString(R.string.settingError), Toast.LENGTH_SHORT).show();
				}else{
					editPassword.setText(editPasswordString);
				}
			}
			dialog.dismiss();
		}
	}
	

	/**
	 * 存储短信
	 */
	private void saveClock() {
		setting = SettingOperation.saveSetting(SettingActivity.this, setting, 
				editPassword.getText().toString(), 
				editQuestion.getText().toString(), 
				editAnswer.getText().toString(), true);
	}
}
