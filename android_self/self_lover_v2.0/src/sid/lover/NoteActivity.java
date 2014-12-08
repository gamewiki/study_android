package sid.lover;

import sid.lover.R;
import sid.modle.Note;
import sid.service.NoteOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends Activity {
	private ImageButton saveLover;
	private TextView blueText;
	private TextView editNoteID;
	private TextView editColor;
	private TextView noteContent;
	private EditText noteContentText;
	private Note note;
	private int type = 0;
	public static final int NOTE_PICKER_ID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_note_add);
		blueText = (TextView) findViewById(R.id.blueText);
		editNoteID = (TextView) findViewById(R.id.editNoteID);
		editColor = (TextView) findViewById(R.id.editColor);
		noteContent = (TextView) findViewById(R.id.noteContent);
		noteContent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(NOTE_PICKER_ID);
			}
		});

		note = (Note) getIntent().getSerializableExtra(AppConstant.NOTE);
		if (note != null) {
			editNoteID.setText(note.getId() + "");
			editColor.setText(note.getColor() + "");
			noteContent.setText(note.getContent());
			type = note.getType();
		} else {
			note = new Note();
			type = getIntent().getIntExtra("type", AppConstant.NOTE_FAVORITE);
			// Toast.makeText(NoteActivity.this,getString(R.string.note_miss),
			// Toast.LENGTH_SHORT).show();
			// this.finish();
		}
		
		if (type==AppConstant.NOTE_FAVORITE) {
			blueText.setText(getString(R.string.favorite));
		}else if (type==AppConstant.NOTE_EXPERIENCE) {
			blueText.setText(getString(R.string.experience));
//		}else if (type==AppConstant.NOTE_LOVERSTORY) {
//			blueText.setText(getString(R.string.storyText));
		}

		saveLover = (ImageButton) findViewById(R.id.saveLover);
		saveLover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NoteOperation.addNote(
						NoteActivity.this,
						noteContent.getText().toString(),
						editNoteID.getText().toString(),
						editColor.getText().toString() != null
								&& !"".equals(editColor.getText().toString()) ? Integer
								.parseInt(editColor.getText().toString()) : 0,
						type);
				finish();
			}
		});

		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		// 设置默认软键盘不弹出
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
			intent.setClass(NoteActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.heightPixels / 2;

		switch (id) {
		case NOTE_PICKER_ID:
			noteContentText = new EditText(this);
			noteContentText.setSelectAllOnFocus(true);
			noteContentText.setHeight(width);
			noteContentText.setText(noteContent.getText().toString());
			return new AlertDialog.Builder(this)
					.setTitle(getString(R.string.editNote))
					.setIcon(R.drawable.smile_50)
					.setView(noteContentText)
					.setPositiveButton(getString(R.string.confirm),
							new editNickNameListener())
					.setNegativeButton(getString(R.string.cancel), null).show();
		}
		return null;
	}

	/**
	 * 编辑确认按钮
	 * 
	 */
	class editNickNameListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String noteContentString = noteContentText.getText().toString();
			if (noteContentString != null && !"".equals(noteContentString)) {
				int intervel = noteContentString.length();
				if (intervel >= 1000) {
					Toast.makeText(NoteActivity.this,
							getString(R.string.note_length_1000),
							Toast.LENGTH_SHORT).show();
				} else {
					note.setContent(noteContentString);
					noteContent.setText(noteContentString);
				}
			}
			dialog.dismiss();
		}
	}

}
