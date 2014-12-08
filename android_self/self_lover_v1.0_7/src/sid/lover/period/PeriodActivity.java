package sid.lover.period;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mobile.app.main.GEInstance;

import sid.lover.R;
import sid.modle.Note;
import sid.service.NoteOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PeriodActivity extends Activity {
	private GEInstance geInstance;
	private LinearLayout interLinearLayout;
	private ImageButton enterCalendar;
	private ImageButton periodThings;
	private TextView periodDaysDes;
	private TextView coldDaysDes;
	private TextView dangerDaysDes;
	private TextView safeDaysDes;
	private List<Note> listNotes = new ArrayList<Note>();
	private List<String> listNotesDes = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_period);
		initGEDate();
		geInstance.loadInterAd(15, GEInstance.INTERUP, interLinearLayout);
		geInstance.showPopGe(this);

		enterCalendar = (ImageButton) findViewById(R.id.enterCalendar);
		enterCalendar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(
						null, CalendarActivity.MIME_TYPE));
			}
		});
		periodThings = (ImageButton) findViewById(R.id.periodThings);
		periodThings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				NoteOperation.getNotes(PeriodActivity.this,
						AppConstant.NOTE_PERIOD, listNotesDes, listNotes);
				if (listNotes.size() > 0) {
					Bundle mBundle = new Bundle();
					mBundle.putSerializable(AppConstant.NOTE, listNotes.get(0));
					intent.putExtras(mBundle);
				}
				intent.setClass(PeriodActivity.this, PeriodThingsActivity.class);
				startActivity(intent);
				// startActivityForResult(new
				// Intent(Intent.ACTION_PICK).setDataAndType(null,
				// CalendarActivity.MIME_TYPE), 100);
			}
		});

		periodDaysDes = (TextView) findViewById(R.id.periodDaysDes);
		periodDaysDes.setTextColor(Color.YELLOW);
		coldDaysDes = (TextView) findViewById(R.id.coldDaysDes);
		coldDaysDes.setTextColor(Color.rgb(117, 210, 240));
		dangerDaysDes = (TextView) findViewById(R.id.dangerDaysDes);
		dangerDaysDes.setTextColor(Color.RED);
		safeDaysDes = (TextView) findViewById(R.id.safeDaysDes);
		safeDaysDes.setTextColor(Color.GREEN);

		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			int year = data.getIntExtra("year", 0);
			int month = data.getIntExtra("month", 0);
			int day = data.getIntExtra("day", 0);
			final Calendar dat = Calendar.getInstance();
			dat.set(Calendar.YEAR, year);
			dat.set(Calendar.MONTH, month);
			dat.set(Calendar.DAY_OF_MONTH, day);

			SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd");
			Toast.makeText(PeriodActivity.this, format.format(dat.getTime()),
					Toast.LENGTH_LONG).show();

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
			intent.setClass(PeriodActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		geInstance.unbindService(this);
		geInstance.unRegisterReceiver(this);
	}

	protected void initGEDate() {
		geInstance = GEInstance.getInstance();
		// 初始函数
		geInstance.initialize(this, AppConstant.GE_UID, AppConstant.GE_PID);// 每次程序启动只要初始化一次(设置开发者应用UID和PID)
		geInstance.setTestMode(false);// 开启测试模式(默认是关闭的,测试的时候可以开启,方便调试并查看广告后台错误信息)
		// geInstance.setOnGEListener(this);//继承GEListener接口(1.监听自定义广告数据
		// 2.监听是否获取金币成功)
		geInstance.setNotificationIcon(R.drawable.icon);// 设置状态栏图标
		geInstance.setOpenIntegralWall(true);// 是否打开积分墙积分 true打开 false不打开
		geInstance.setScoreRemind(true);// 是否每次下载提示“当前下载有积分” true打开，false不打开
		geInstance.openListAdOn(false);// 是否开启点击任意(自定义广告除外)广告都打开积分墙(默认不开启)
		geInstance.loadGEPop();// 预加载插屏广告
		// 互动广告
		interLinearLayout = (LinearLayout) findViewById(R.id.interGELinearLayout);// 初始化互动广告必须的布局
	}
}
