package sid.note;

import java.util.ArrayList;
import java.util.List;

import sid.modle.Note;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DatabaseHelper;
import sid.utils.ExitApplication;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class NotesActivity extends ListActivity implements OnItemLongClickListener{
	private ImageButton createNote;
	private ImageButton exitNote;
	private List<Note> listNotes = new ArrayList<Note>();
	private List<String> listNotesDes = new ArrayList<String>();
	private int deletePersonPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);
    	createNote=(ImageButton)findViewById(R.id.createNote);
    	createNote.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(NotesActivity.this, NoteAddActivity.class);
				startActivity(intent);
			}
		});
    	exitNote=(ImageButton)findViewById(R.id.exitNote);
    	exitNote.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(NotesActivity.this, NoteActivity.class);
				startActivity(intent);
			}
		});
        
    	getNotes();
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }


	/**
	 * 获取数据
	 */
	private void getNotes(){
		DatabaseHelper dbh = new DatabaseHelper(NotesActivity.this,
				"note_sid_db", AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		Cursor cursor;
		cursor = sd.query(AppConstant.NOTE,new String[] { "id,createtime,color,content" },
				null,new String[] {}, null, null,null);
		while (cursor.moveToNext()) {
			Note note = new Note();
			long id = cursor.getLong(cursor.getColumnIndex("id"));
			String createtime = cursor.getString(cursor.getColumnIndex("createtime"));
			int color = cursor.getInt(cursor.getColumnIndex("color"));
			String content = cursor.getString(cursor.getColumnIndex("content"));
			note.setId(id);
			note.setCreatetime(createtime);
			note.setColor(color);
			note.setContent(content);
			String text = content.length()>10?(content.substring(0,10)+"..."):content;
			if (!listNotesDes.contains(createtime+"   "+text)) {
				listNotesDes.add(createtime+"   "+text);
				listNotes.add(note);
			}
		}
		cursor.close();
		sd.close();
		dbh.close();
    	updateAdapter();
	}
	/**
	 * 更新数据
	 */
	private void updateAdapter() {
		if (null != listNotesDes) {
			setListAdapter(new NoteAdapter(this, listNotesDes, listNotesDes));
		}
	}
	
	@Override
	protected void onResume() {
    	getNotes();
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
						DatabaseHelper dbh = new DatabaseHelper(NotesActivity.this, "note_sid_db",AppConstant.VERSION);
						SQLiteDatabase sd = dbh.getWritableDatabase();
						sd.delete(AppConstant.NOTE, "id=?", new String[] { note.getId()+"" });
						sd.close();
						dbh.close();
						Toast.makeText(NotesActivity.this,
								getString(R.string.note_delete_success), Toast.LENGTH_SHORT).show();
						listNotes.remove(deletePersonPosition);
						listNotesDes.remove(deletePersonPosition);
						updateAdapter();
					} else {
						Toast.makeText(NotesActivity.this,
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
		Note note = listNotes.get(position);
		if (null != note) {
			Intent intent = new Intent();
			Bundle mBundle = new Bundle();
			mBundle.putSerializable(AppConstant.NOTE, note);
			intent.putExtras(mBundle);
			intent.setClass(NotesActivity.this, NoteAddActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(NotesActivity.this,
					getString(R.string.note_miss), Toast.LENGTH_SHORT).show();
		}
	}
}
