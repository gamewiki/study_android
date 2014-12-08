package sid.lover.story;

import java.util.ArrayList;
import java.util.List;

import sid.lover.R;
import sid.modle.Note;
import sid.service.NoteOperation;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NotesActivity extends ListActivity implements OnItemLongClickListener{
	private int type = 0;
	private List<Note> listNotes = new ArrayList<Note>();
	private List<String> listNotesDes = new ArrayList<String>();
	private int deletePersonPosition;
	private ImageButton newLover;
	private TextView blueText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notes);
        type = getIntent().getIntExtra("type", AppConstant.NOTE_FAVORITE);
		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);

		blueText = (TextView) findViewById(R.id.blueText);
		newLover=(ImageButton)findViewById(R.id.newLover);
		newLover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", type);
				intent.setClass(NotesActivity.this, NoteActivity.class);
				startActivity(intent);
			}
		});

		
		if (type==AppConstant.NOTE_FAVORITE) {
			blueText.setText(getString(R.string.favorite));
		}else if (type==AppConstant.NOTE_EXPERIENCE) {
			blueText.setText(getString(R.string.experience));
		}else if (type==AppConstant.NOTE_LOVERSTORY) {
			blueText.setText(getString(R.string.storyText));
		}
		
		NoteOperation.getNotes(NotesActivity.this,type,listNotesDes,listNotes);
    	updateAdapter();
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }

	/**
	 * 更新数据
	 */
	private void updateAdapter() {
		if (null != listNotesDes) {
			setListAdapter(new NoteAdapter(this, listNotesDes, listNotesDes,type));
		}
	}
	
	@Override
	protected void onResume() {
    	if (type!=0) {
    		NoteOperation.getNotes(NotesActivity.this,type,listNotesDes,listNotes);
        	updateAdapter();
		}
		super.onResume();
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
			intent.setClass(NotesActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long id) {
		deletePersonPosition = position;
		showDialog(2);
		return true;
	}
    @Override
    protected Dialog onCreateDialog(int id) {
		return dialogCreate(NotesActivity.this,id);
    }
    private Dialog dialogCreate(NotesActivity dialogActivity, int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(dialogActivity);
		builder.setIcon(R.drawable.note_icon);
		builder.setTitle(R.string.note_delete_msg);
		builder.setMessage(R.string.note_delete_msg);
    	switch (id) {
		case 2:
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Note note = listNotes.get(deletePersonPosition);
					if (null != note) {
						NoteOperation.deleteNote(NotesActivity.this,note, deletePersonPosition,listNotesDes,listNotes);
						updateAdapter();
					} else {
						Toast.makeText(NotesActivity.this,getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
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
		Note note = listNotes.get(position);
		if (null != note) {
			Intent intent = new Intent();
			Bundle mBundle = new Bundle();
			mBundle.putSerializable(AppConstant.NOTE, note);
			intent.putExtras(mBundle);
			intent.setClass(NotesActivity.this, NoteActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(NotesActivity.this,
					getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
		}
	}
}
