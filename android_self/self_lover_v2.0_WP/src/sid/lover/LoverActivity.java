
package sid.lover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.waps.AppConnect;

import sid.lover.dto.ActivityMessage;
import sid.lover.ui.adapters.PublicActivityAdapter;
import sid.lover.ui.views.ExtendedListView;
import sid.lover.ui.views.ExtendedListView.OnPositionChangedListener;
import sid.lover.ui.views.MenuRightAnimations;
import sid.modle.Clock;
import sid.service.ClockOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LoverActivity extends BaseActivity implements OnPositionChangedListener {

    private ExtendedListView dataListView;

    private FrameLayout clockLayout;
    
	private List<Clock> listClocks = new ArrayList<Clock>();
    private PublicActivityAdapter chatHistoryAdapter;

	private boolean[] statusCheckedListener = new boolean[] { true, false, false};
    private List<ActivityMessage> messages = new ArrayList<ActivityMessage>();
    // 是否退出程序
    private static Boolean isExit = false;
	// 定时触发器
	private static Timer tExit = null;
	private int deletePosition;//删除位置

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState,R.layout.feed_activity2);

        MenuRightAnimations.initOffset(LoverActivity.this);

        //多普广告
        //方式①：通过AndroidManifest文件读取WAPS_ID和WAPS_PID
        AppConnect.getInstance(this); //必须确保AndroidManifest文件内配置了WAPS_ID
        //方式②：通过代码设置WAPS_ID和WAPS_PID
//        AppConnect.getInstance("WAPS_ID","WAPS_PID",this); 
        //3.插屏广告
        AppConnect.getInstance(this).initPopAd(this);
//        LinearLayout ll = (LinearLayout) findViewById(R.id.AdLinearLayout);
//        AppConnect.getInstance(this).showBannerAd(this, ll);
        
        //
        dataListView = (ExtendedListView) findViewById(R.id.list_view);

        setAdapterForThis();
        
        dataListView.setCacheColorHint(Color.TRANSPARENT);
        dataListView.setOnPositionChangedListener(this);
        clockLayout = (FrameLayout)findViewById(R.id.clock);
        
        SimpleDateFormat formatsss = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        TextView datestr = ((TextView) findViewById(R.id.clock_digital_date));
        String date = formatsss.format(new Date());
        datestr.setText(date);

        View v = findViewById(R.id.composer_buttons_wrapper);
        v.setOnTouchListener(this);

        AppConnect.getInstance(this).showPopAd(this);
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }


    private void initMessages() {
		listClocks = ClockOperation.getClocksBySign(LoverActivity.this,AppConstant.signs[1]);
        // set header
        messages.add(new ActivityMessage());
		for (Clock clock : listClocks) {
			if (clock.getImagePath()!=null&&!clock.getImagePath().equals("")) {
		        // img
		        messages.add(new ActivityMessage(R.drawable.gauss03,clock.getCreater(),clock.getImagePath(), clock.getTitle(), 
		        		clock.getDescription(),clock.getStorytime()));
			}else{
		        // text
		        messages.add(new ActivityMessage(R.drawable.gauss03,clock.getCreater(),clock.getTitle(), 
		        		clock.getDescription(),clock.getStorytime()));
			}
		}
    }


    private void setAdapterForThis() {
        initMessages();
        chatHistoryAdapter = new PublicActivityAdapter(this, messages);
        dataListView.setAdapter(chatHistoryAdapter);
        
        dataListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){ 
            @Override 
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id){ 
				deletePosition = pos;
				showDialog(AppConstant.CHANGE_DELETE);
				return true;
           } 
      });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
		return dialogCreate(LoverActivity.this,id);
    }
    private Dialog dialogCreate(LoverActivity dialogActivity, int id) {
    	switch (id) {
		case AppConstant.CHANGE_DELETE:
			int position = deletePosition-1;//由于创建adapter的时候先加了一个空的message对象，所以这里需要减1
			if (position>=listClocks.size()) {
				Toast.makeText(LoverActivity.this,getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
			}else{
				Clock clock = listClocks.get(position);
				return new AlertDialog.Builder(this).setTitle(getString(R.string.statusChoice))
						.setIcon(R.drawable.clock)
						.setSingleChoiceItems(AppConstant.changes, clock.getStatus(), new statusCheckedListener())
						.setPositiveButton(getString(R.string.confirm), new statusPositiveListener())
						.setNegativeButton(getString(R.string.cancel), new statusNegativeListener()).show();
			}
		case AppConstant.DELETE:
			AlertDialog.Builder builder = new AlertDialog.Builder(dialogActivity);
			builder.setIcon(R.drawable.note_icon);
			builder.setTitle(R.string.statusChoice);
			builder.setMessage(R.string.note_delete_msg);
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int position = deletePosition-1;//由于创建adapter的时候先加了一个空的message对象，所以这里需要减1
					if (position>=listClocks.size()) {
						Toast.makeText(LoverActivity.this,getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
					}else{
						Clock clock = listClocks.get(position);
						if (null != clock) {
							ClockOperation.deleteClock(LoverActivity.this, clock.getId());
							Toast.makeText(LoverActivity.this,
									getString(R.string.note_delete_success), Toast.LENGTH_SHORT).show();
							listClocks.remove(position);
							startActivity(new Intent(LoverActivity.this,LoverActivity.class));
							LoverActivity.this.finish();
						} else {
							Toast.makeText(LoverActivity.this,
									getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
							return;
						}
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
	 * 闹钟标签监听事件
	 * 
	 * @author Administrator
	 * 
	 */
	class statusCheckedListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			for (int i=0;i<statusCheckedListener.length;i++) {
				if (i!=which) {
					statusCheckedListener[i] = false;
				}else{
					statusCheckedListener[i] = true;
				}
			}
		}
	}
	/**
	 * 闹钟标签确认按钮
	 * 
	 */
	class statusPositiveListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			if (statusCheckedListener[2]==true) {
				showDialog(AppConstant.DELETE);
			}else{
				int status = statusCheckedListener[0]==true?1:0;
				int position = deletePosition-1;//由于创建adapter的时候先加了一个空的message对象，所以这里需要减1
				if (position>=listClocks.size()) {
					Toast.makeText(LoverActivity.this,getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
				}else{
					Clock clock = listClocks.get(position);
					if (null != clock) {
						clock.setStatus(status);
						ClockOperation.updateClock(LoverActivity.this, clock);
						Toast.makeText(LoverActivity.this,
								getString(R.string.updateClockSuccess), Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LoverActivity.this,LoverActivity.class));
						LoverActivity.this.finish();
					} else {
						Toast.makeText(LoverActivity.this,
								getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
						return;
					}
				}
			}
		}
	}

	/**
	 * 闹钟标签取消按钮
	 * 
	 */
	class statusNegativeListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			statusCheckedListener = new boolean[] { true, false,false };
			dialog.dismiss();
		}
	}

    @Override
    public void onPositionChanged(ExtendedListView listView, int firstVisiblePosition,
            View scrollBarPanel) {
        SimpleDateFormat formatsss = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        TextView datestr = ((TextView) findViewById(R.id.clock_digital_date));
        ActivityMessage msg = messages.get(firstVisiblePosition);

        String date = formatsss.format(msg.getDate());
        datestr.setText(date);
    }

    @Override
    public void onScollPositionChanged(View scrollBarPanel,int top) {
        MarginLayoutParams layoutParams = (MarginLayoutParams)clockLayout.getLayoutParams();
        layoutParams.setMargins(0, top, 0, 0);
        clockLayout.setLayoutParams(layoutParams);
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.APP, 1, R.string.app).setIcon(R.drawable.app);
		menu.add(0, AppConstant.GAME, 2, R.string.game).setIcon(R.drawable.game);
		menu.add(0, AppConstant.ABOUT, 3, R.string.about).setIcon(R.drawable.about);
		menu.add(0, AppConstant.EXIT, 4, R.string.exit).setIcon(R.drawable.exit);
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
		} else if (item.getItemId() == AppConstant.APP) {
			AppConnect.getInstance(LoverActivity.this).showAppOffers(LoverActivity.this);
		} else if (item.getItemId() == AppConstant.GAME) {
			AppConnect.getInstance(LoverActivity.this).showGameOffers(LoverActivity.this);
		}
		return super.onOptionsItemSelected(item);
	}
	

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isExit == false) {
				isExit = true;
				if (tExit != null) {
					tExit.cancel(); // 将原任务从队列中移除
				}
				// 重新实例一个定时器
				tExit = new Timer();
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						isExit = false;
					}
				};
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				// 延时两秒触发task任务
				tExit.schedule(task, 2000);
			} else {
				finish();
				ExitApplication.getInstance().exit();
			}
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
