package sid.lover.tools;

import com.mobile.app.main.GEInstance;

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
import android.widget.LinearLayout;

public class ToolsActivity extends Activity {
	private GEInstance geInstance;
	private LinearLayout interLinearLayout;
	private ImageButton enterClock = null;
	private ImageButton sendMSG = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_tools);
		
		initGEDate();
		geInstance.loadInterAd(15, GEInstance.INTERUP, interLinearLayout);
		geInstance.showPopGe(this);

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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		geInstance.unbindService(this);
		geInstance.unRegisterReceiver(this);
	}
	protected void initGEDate()
    {
        geInstance = GEInstance.getInstance();
        //初始函数
        geInstance.initialize(this,AppConstant.GE_UID,AppConstant.GE_PID);//每次程序启动只要初始化一次(设置开发者应用UID和PID)
        geInstance.setTestMode(false);//开启测试模式(默认是关闭的,测试的时候可以开启,方便调试并查看广告后台错误信息)
//        geInstance.setOnGEListener(this);//继承GEListener接口(1.监听自定义广告数据  2.监听是否获取金币成功)
        geInstance.setNotificationIcon(R.drawable.icon);//设置状态栏图标
        geInstance.setOpenIntegralWall(true);//是否打开积分墙积分 true打开 false不打开
        geInstance.setScoreRemind(true);//是否每次下载提示“当前下载有积分” true打开，false不打开
        geInstance.openListAdOn(false);//是否开启点击任意(自定义广告除外)广告都打开积分墙(默认不开启)
        geInstance.loadGEPop();//预加载插屏广告
        //互动广告
        interLinearLayout=(LinearLayout)findViewById(R.id.interGELinearLayout);//初始化互动广告必须的布局
    }
}
