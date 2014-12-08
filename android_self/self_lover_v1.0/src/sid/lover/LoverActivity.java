package sid.lover;

import sid.lover.aboutlover.AboutLoverActivity;
import sid.lover.days.DaysActivity;
import sid.lover.period.PeriodActivity;
import sid.lover.setting.SettingActivity;
import sid.lover.story.NotesActivity;
import sid.lover.tools.ToolsActivity;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class LoverActivity extends Activity {
	private ImageButton enterLover;
	private ImageButton enterPeriod;
	private ImageButton aboutlover;
	private ImageButton days;
	private ImageButton tools;
	private ImageButton setting;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover);
		
		enterLover = (ImageButton)findViewById(R.id.enterLover);
		enterLover.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", AppConstant.NOTE_LOVERSTORY);
				intent.setClass(LoverActivity.this, NotesActivity.class);
				startActivity(intent);
			}
		});
		enterPeriod = (ImageButton)findViewById(R.id.enterPeriod);
		enterPeriod.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoverActivity.this, PeriodActivity.class);
				startActivity(intent);
			}
		});
		aboutlover = (ImageButton)findViewById(R.id.aboutlover);
		aboutlover.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoverActivity.this, AboutLoverActivity.class);
				startActivity(intent);
			}
		});
		days = (ImageButton)findViewById(R.id.days);
		days.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoverActivity.this, DaysActivity.class);
				startActivity(intent);
			}
		});
		tools = (ImageButton)findViewById(R.id.tools);
		tools.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoverActivity.this, ToolsActivity.class);
				startActivity(intent);
			}
		});
		setting = (ImageButton)findViewById(R.id.setting);
		setting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoverActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
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
			intent.setClass(LoverActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
