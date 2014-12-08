package sid.almanac;

import sid.service.Person;
import sid.service.PersonOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class StartActivity extends Activity {
	private static final String[] sp={"程序猿","铁人王进喜","财务狗","射鸡湿","白衣天使","培训助理","点子牛"};
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private Person person = new Person();

	private EditText cgNameE;
	private RadioGroup cgRadioGroup;
	private RadioButton cgMan;
	private RadioButton cgWoman;
	private Button cgSubmit;
	private Integer usersp = null;
	private Integer usersex = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		
		spinner = (Spinner) findViewById(R.id.spinner);
		//将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sp);
		//设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//将adapter 添加到spinner中
		spinner.setAdapter(adapter);
		//添加事件Spinner事件监听  
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		//设置
		spinner.setVisibility(View.VISIBLE);

		/* 初始化对象 */
		cgNameE = (EditText) findViewById(R.id.cgNameE);
		cgRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		cgMan = (RadioButton) findViewById(R.id.man);
		cgWoman = (RadioButton) findViewById(R.id.woman);
		cgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (cgMan.getId() == checkedId) {
							usersex = 0;
						} else if (cgWoman.getId() == checkedId) {
							usersex = 1;
						}
					}
				});
		cgSubmit = (Button) findViewById(R.id.cgSubmit);
		cgSubmit.setText(getString(R.string.cgSubmit));
		cgSubmit.setTextSize(AppConstant.CG_USER_TEXT_SIZE);
		cgSubmit.setOnClickListener(new enterCGListener());

		Person p = PersonOperation.getPerson(StartActivity.this, 1);
		if (p!=null) {
			cgNameE.setText(p.getName());
			spinner.setSelection(p.getType());
			if (p.getSex()==0) {
				cgRadioGroup.check(R.id.man);
			}else{
				cgRadioGroup.check(R.id.woman);
			}
		}else{
			cgRadioGroup.check(R.id.man);
		}

		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}
	//使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener{

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			usersp = arg2;
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	class enterCGListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String name = cgNameE.getText().toString();
			if (usersex!=null&&usersp!=null) {
				person.setId(1);
				person.setName(name);
				person.setSex(usersex);
				person.setType(usersp);
				PersonOperation.savePerson(StartActivity.this, person);
				if (usersp<10) {
					AppConstant.TYPE = "0"+usersp+usersex;
				}else{
					AppConstant.TYPE = ""+usersp+usersex;
				}
				Intent intent = new Intent();
				intent.setClass(StartActivity.this, AlmanacActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(StartActivity.this,"数据丢失，请重新选择", Toast.LENGTH_SHORT).show();
			}
		}
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
			intent.setClass(StartActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
