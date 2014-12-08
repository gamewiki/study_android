package sid.note;

import java.util.Date;

import sid.modle.Note;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DatabaseHelper;
import sid.utils.ExitApplication;
import sid.utils.SelfDateUtils;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NoteAddActivity extends Activity {
	private ImageButton createNote;
	private ImageButton exitNote;
	private EditText editNote;
	private EditText editNoteID;
	private EditText editColor;
	private String noteContent;
	private int color=0;
	private String noteId = null;
	private Note note;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        editNote = (EditText)findViewById(R.id.editNote);
        editNoteID = (EditText)findViewById(R.id.editNoteID);
        editColor = (EditText)findViewById(R.id.editColor);
    	createNote=(ImageButton)findViewById(R.id.createNote);
    	createNote.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNote();
			}
		});
    	exitNote=(ImageButton)findViewById(R.id.exitNote);
    	exitNote.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(NoteAddActivity.this, NotesActivity.class);
				startActivity(intent);
			}
		});
    	note = (Note) getIntent().getSerializableExtra(AppConstant.NOTE);
    	if (note!=null) {
            editNote.setText(note.getContent());
            editNoteID.setText(note.getId()+"");
            editColor.setText(note.getColor()+"");
		}
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		// 设置默认软键盘不弹出
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

	private void addNote() {
		noteContent = editNote.getText().toString();
		if (noteContent!=null&&!noteContent.equals("")) {
			boolean exist = true;
			noteId = editNoteID.getText().toString();
			color = editColor.getText().toString()!=null&&!"".equals(editColor.getText().toString())?Integer.parseInt(editColor.getText().toString()):0;
			DatabaseHelper dbh = new DatabaseHelper(NoteAddActivity.this, "note_sid_db",AppConstant.VERSION);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			//判断是否已经存在
			Cursor cursor;
			cursor = sd.query(AppConstant.NOTE,new String[] { "id" },"content=?",
							new String[] {noteContent}, null, null,null);
			while (cursor.moveToNext()) {
				exist = false;
			}
			cursor.close();
			if (exist) {
				//添加便利贴内容
				ContentValues value = new ContentValues();
				value.put("createtime", SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss"));
				value.put("color", color);
				value.put("content", noteContent);
				if (noteId!=null&&!noteId.equals("")) {
					sd.update(AppConstant.NOTE, value, "id=?", new String[]{noteId});
				} else {
					value.put("id", new Date().getTime());
					sd.insert(AppConstant.NOTE, null, value);
				}
				Toast.makeText(NoteAddActivity.this,
						getString(R.string.addNoteSuccess),Toast.LENGTH_SHORT).show();
				sd.close();
				dbh.close();
				noteId = null;
				Intent intent = new Intent();
				intent.setClass(NoteAddActivity.this, NotesActivity.class);
				startActivity(intent);
			}else{
				sd.close();
				dbh.close();
				Toast.makeText(NoteAddActivity.this,
						getString(R.string.NoteExist),Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(NoteAddActivity.this,
					getString(R.string.addNoteNull),Toast.LENGTH_SHORT).show();
		}
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
			intent.setClass(NoteAddActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
