package sid.lover.tools;

import sid.lover.R;
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
import android.widget.ImageButton;

public class ToolsActivity extends Activity {
	private ImageButton enterClock = null;
	private ImageButton sendMSG = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_tools);

        enterClock = (ImageButton) findViewById(R.id.enterClock);
        enterClock.setOnClickListener(new enterClockListener());

        sendMSG = (ImageButton) findViewById(R.id.sendMSG);
        sendMSG.setOnClickListener(new sendMSGListener());
        
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

    class enterClockListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(ToolsActivity.this, ClockActivity.class);
			startActivity(intent);
		}
    }

    class sendMSGListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(ToolsActivity.this, SendMsgActivity.class);
			startActivity(intent);
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
			intent.setClass(ToolsActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
