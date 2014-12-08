package sid.lover;

import java.util.ArrayList;
import java.util.List;

import cn.waps.AppConnect;

import sid.lover.R;
import sid.lover.tools.ClockAdapter;
import sid.lover.ui.views.MenuRightAnimations;
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
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SendMsgActivity extends ListActivity implements OnItemLongClickListener, OnTouchListener {
	private ImageButton createClock;
	private List<Clock> listClocks = new ArrayList<Clock>();
	private List<String> listClocksDes = new ArrayList<String>();
	private int deletePersonPosition;

	private boolean areButtonsShowing;
	/** 四个小按钮的组合的布局 */
	private RelativeLayout composerButtonsWrapper;
	/** 大按钮，隐藏的布局 */
	private RelativeLayout composerButtonsShowHideButton;
	/** 大按钮 */
	private ImageView composerButtonsShowHideButtonIcon;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_messages);

		init();

		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);
		createClock=(ImageButton)findViewById(R.id.createClock);
		createClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SendMsgActivity.this, SendMsgAddActivity.class);
				startActivity(intent);
			}
		});
        
    	getClocks();

        LinearLayout ll = (LinearLayout) findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, ll);
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	protected void onCreate(Bundle savedInstanceState, int layoutId) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layoutId);

		init();
	}

	private void init() {
		MenuRightAnimations.initOffset(SendMsgActivity.this);
		composerButtonsWrapper = (RelativeLayout) findViewById(R.id.composer_buttons_wrapper);
		composerButtonsShowHideButton = (RelativeLayout) findViewById(R.id.composer_buttons_show_hide_button);
		composerButtonsShowHideButtonIcon = (ImageView) findViewById(R.id.composer_buttons_show_hide_button_icon);

		composerButtonsShowHideButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickView(v, false);
			}
		});
		for (int i = 0; i < composerButtonsWrapper.getChildCount(); i++) {
			composerButtonsWrapper.getChildAt(i).setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							if (areButtonsShowing) {
//								Toast.makeText(getApplicationContext(),"argo=" + arg0.getId() + " click", Toast.LENGTH_SHORT).show();
								int id = arg0.getId();
								Intent intent = new Intent();
								if (id == R.id.composer_button_people) {
									intent.setClass(SendMsgActivity.this,LoverActivity.class);
									SendMsgActivity.this.startActivity(intent);
								} else if (id == R.id.composer_period) {
									intent.setClass(SendMsgActivity.this,CalendarActivity.class);
									SendMsgActivity.this.startActivity(intent);
								} else if (id == R.id.composer_button_photo) {
									intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
									startActivityForResult(intent, 1);
									SendMsgActivity.this.startActivity(intent);
								}
							}
						}
					});
		}

		composerButtonsShowHideButton.startAnimation(MenuRightAnimations.getRotateAnimation(0, 360, 200));

		View v = findViewById(R.id.composer_buttons_wrapper);
		v.setOnTouchListener(this);
	}

	public void onClickView(View v, boolean isOnlyClose) {
		if (isOnlyClose) {
			if (areButtonsShowing) {
				MenuRightAnimations.startAnimationsOut(composerButtonsWrapper,300);
				composerButtonsShowHideButtonIcon.startAnimation(MenuRightAnimations.getRotateAnimation(-315, 0, 300));
				areButtonsShowing = !areButtonsShowing;
			}
		} else {
			if (!areButtonsShowing) {
				MenuRightAnimations.startAnimationsIn(composerButtonsWrapper,300);
				composerButtonsShowHideButtonIcon.startAnimation(MenuRightAnimations.getRotateAnimation(0, -315, 300));
			} else {
				MenuRightAnimations.startAnimationsOut(composerButtonsWrapper,300);
				composerButtonsShowHideButtonIcon.startAnimation(MenuRightAnimations.getRotateAnimation(-315, 0, 300));
			}
			areButtonsShowing = !areButtonsShowing;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		onClickView(v, true);
		return false;
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
			intent.setClass(SendMsgActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		} else if (item.getItemId() == AppConstant.APP) {
			AppConnect.getInstance(SendMsgActivity.this).showAppOffers(SendMsgActivity.this);
		} else if (item.getItemId() == AppConstant.GAME) {
			AppConnect.getInstance(SendMsgActivity.this).showGameOffers(SendMsgActivity.this);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 获取数据
	 */
	private void getClocks(){
		listClocks = ClockOperation.getClocksBySign(SendMsgActivity.this,AppConstant.signs[2]);
		for (Clock clock : listClocks) {
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
			setListAdapter(new ClockAdapter(this, listClocksDes, listClocksDes));
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
		return dialogCreate(SendMsgActivity.this,id);
    }
    private Dialog dialogCreate(SendMsgActivity dialogActivity, int id) {
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
						ClockOperation.deleteClock(SendMsgActivity.this, clock.getId());
						Toast.makeText(SendMsgActivity.this,
								getString(R.string.note_delete_success), Toast.LENGTH_SHORT).show();
						listClocks.remove(deletePersonPosition);
						listClocksDes.remove(deletePersonPosition);
						updateAdapter();
					} else {
						Toast.makeText(SendMsgActivity.this,
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
			intent.setClass(SendMsgActivity.this, SendMsgAddActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(SendMsgActivity.this,
					getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
		}
	}
}
