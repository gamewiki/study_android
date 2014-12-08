package sid.note;

import java.util.ArrayList;
import java.util.List;

import sid.modle.Clock;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DatabaseOperation;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ClockActivity extends ListActivity implements OnItemLongClickListener{
	private ImageButton createClock;
	private ImageButton exitClock;
	private List<Clock> listClocks = new ArrayList<Clock>();
	private List<String> listClocksDes = new ArrayList<String>();
	private int deletePersonPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks);
		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);
		createClock=(ImageButton)findViewById(R.id.createClock);
		createClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ClockActivity.this, ClockAddActivity.class);
				startActivity(intent);
			}
		});
		exitClock=(ImageButton)findViewById(R.id.exitClock);
		exitClock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ClockActivity.this, NoteActivity.class);
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
			intent.setClass(ClockActivity.this, AboutActivity.class);
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
		listClocks = DatabaseOperation.getClocks(ClockActivity.this);
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
		return dialogCreate(ClockActivity.this,id);
    }
    private Dialog dialogCreate(ClockActivity dialogActivity, int id) {
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
						DatabaseOperation.deleteClock(ClockActivity.this, clock.getId());
						Toast.makeText(ClockActivity.this,
								getString(R.string.note_delete_success), Toast.LENGTH_SHORT).show();
						listClocks.remove(deletePersonPosition);
						listClocksDes.remove(deletePersonPosition);
						updateAdapter();
					} else {
						Toast.makeText(ClockActivity.this,
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
			intent.setClass(ClockActivity.this, ClockAddActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(ClockActivity.this,
					getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
		}
	}
}
