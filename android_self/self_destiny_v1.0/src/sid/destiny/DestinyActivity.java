package sid.destiny;

import com.baidu.mobstat.StatService;

import sid.cg.CGSynopsisActivity;
import sid.cg.CGUserInfoActivity;
import sid.databasehelper.DatabaseHelper;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class DestinyActivity extends Activity {
	private ImageButton enterCG = null;
	private ImageButton originCG = null;
	/**获取屏幕的宽度*/
	private Integer windowWidth = 420;
	private int width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "DestinyActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny);
		getWH();
        enterCG = (ImageButton) findViewById(R.id.enterCG);
        enterCG.setMaxHeight(width);
        enterCG.setMaxWidth(width);
        enterCG.setOnClickListener(new enterCGListener());
        originCG = (ImageButton) findViewById(R.id.originCG);
        originCG.setMaxHeight(width);
        originCG.setMaxWidth(width);
        originCG.setOnClickListener(new originCGListener());
		DatabaseHelper dbh = new DatabaseHelper(DestinyActivity.this, "destiny_sid_db",AppConstant.VERSION);
//		在执行下面的语句之前是不会创建数据库的
		dbh.getWritableDatabase();
		dbh.close();
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }


	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "DestinyActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "DestinyActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}
	
    class enterCGListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(DestinyActivity.this, CGUserInfoActivity.class);
			startActivity(intent);
		}
    }

    class originCGListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(DestinyActivity.this, CGSynopsisActivity.class);
			startActivity(intent);
		}
    }
	private void getWH() {
		// 获取屏幕宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		windowWidth = dm.widthPixels;
		width = windowWidth / 7;
	}

	/* 以下是点击菜单按钮执行的方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(DestinyActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
