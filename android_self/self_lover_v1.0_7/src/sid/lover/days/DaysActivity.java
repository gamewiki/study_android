package sid.lover.days;

import java.util.ArrayList;
import java.util.List;

import com.mobile.app.main.GEInstance;

import sid.lover.R;
import sid.modle.Clock;
import sid.service.ClockOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class DaysActivity extends ListActivity implements OnItemLongClickListener{
	private GEInstance geInstance;
	private LinearLayout interLinearLayout;
	private List<Clock> listClocks = new ArrayList<Clock>();
	private List<String> listClocksDes = new ArrayList<String>();
	private int deletePersonPosition;
	private ImageButton newDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lover_days);
		initGEDate();
		geInstance.loadInterAd(15, GEInstance.INTERUP, interLinearLayout);
		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);

		newDay=(ImageButton)findViewById(R.id.newDay);
		newDay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DaysActivity.this, DayAddActivity.class);
				startActivity(intent);
			}
		});

        
    	getClocks();
        
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about).setIcon(R.drawable.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit).setIcon(R.drawable.exit);
        return true;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(DaysActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 获取数据
	 */
	private void getClocks(){
		listClocks = ClockOperation.getClocksBySign(DaysActivity.this,AppConstant.signs[1]);
		for (Clock clock : listClocks) {
			System.out.println("----------listClocks--------"+clock.getTitle());
			String text = (clock.getTitle().length())>10?(clock.getTitle().substring(0,10)+"..."):(clock.getTitle());
			if (!listClocksDes.contains(clock.getClockdate()+" "+clock.getClocktime()+"  "+text)) {
				listClocksDes.add(clock.getClockdate()+" "+clock.getClocktime()+"  "+text);
			}
		}
    	updateAdapter();
	}
	/**
	 * 更新数据
	 */
	private void updateAdapter() {
		if (null != listClocksDes) {
			setListAdapter(new DaysAdapter(this, listClocksDes, listClocksDes));
		}
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long id) {
		deletePersonPosition = position;
		showDialog(2);
		return true;
	}
    @Override
    protected Dialog onCreateDialog(int id) {
		return dialogCreate(DaysActivity.this,id);
    }
    private Dialog dialogCreate(DaysActivity dialogActivity, int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(dialogActivity);
		builder.setIcon(R.drawable.note_icon);
		builder.setTitle(R.string.note_delete_msg);
		builder.setMessage(R.string.note_delete_msg);
    	switch (id) {
		case 2:
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Clock clock = listClocks.get(deletePersonPosition);
					if (null != clock) {
						ClockOperation.deleteClock(DaysActivity.this, clock.getId());
						Toast.makeText(DaysActivity.this,
								getString(R.string.note_delete_success), Toast.LENGTH_SHORT).show();
						listClocks.remove(deletePersonPosition);
						listClocksDes.remove(deletePersonPosition);
						updateAdapter();
					} else {
						Toast.makeText(DaysActivity.this,
								getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
						return;
					}
				}
			});
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			return builder.create();
		default:
			break;
		}
		return null;
	}
	/**
	 * 设置ListItem被点击时要做的动作
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Clock clock = listClocks.get(position);
		if (null != clock) {
			Intent intent = new Intent();
			Bundle mBundle = new Bundle();
			mBundle.putSerializable(AppConstant.CLOCK, clock);
			intent.putExtras(mBundle);
			intent.setClass(DaysActivity.this, DayAddActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(DaysActivity.this,
					getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
		}
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
