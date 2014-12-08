package sid.gylq;

import sid.destiny.R;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.baidu.mobstat.StatService;

public class LQSynopsisActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "CGSynopsisActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lq_synopsis);

		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "CGSynopsisActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "CGSynopsisActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}
	

    class createDatabaseListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			
		}
    }
	/* 以下是点击菜单按钮执行的方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about).setIcon(R.drawable.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit).setIcon(R.drawable.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(LQSynopsisActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
