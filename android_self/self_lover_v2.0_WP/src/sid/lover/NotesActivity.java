package sid.lover;

import java.util.ArrayList;
import java.util.List;

import cn.waps.AppConnect;
import sid.lover.ui.views.MenuRightAnimations;
import sid.modle.Note;
import sid.service.NoteOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.annotation.SuppressLint;
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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CutPasteId")
public class NotesActivity extends ListActivity implements OnItemLongClickListener, OnTouchListener {
	private int type = 0;
	private List<Note> listNotes = new ArrayList<Note>();
	private List<String> listNotesDes = new ArrayList<String>();
	private int deletePersonPosition;
	private ImageButton newLover;
	private TextView delteText;

	private boolean areButtonsShowing;
	/** 四个小按钮的组合的布局 */
	private RelativeLayout composerButtonsWrapper;
	/** 大按钮，隐藏的布局 */
	private RelativeLayout composerButtonsShowHideButton;
	/** 大按钮 */
	private ImageView composerButtonsShowHideButtonIcon;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notes);

		init();
        
        type = getIntent().getIntExtra("type", AppConstant.NOTE_FAVORITE);
		//设置相应长按事件
		getListView().setOnItemLongClickListener(this);

		delteText = (TextView) findViewById(R.id.delteText);
		if (type==AppConstant.NOTE_FAVORITE) {
			delteText.setText(getString(R.string.favorite_list_delete));
		}else if (type==AppConstant.NOTE_EXPERIENCE) {
			delteText.setText(getString(R.string.experience_list_delete));
//		}else if (type==AppConstant.NOTE_LOVERSTORY) {
//			delteText.setText(getString(R.string.storyText));
		}

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

		
		NoteOperation.getNotes(NotesActivity.this,type,listNotesDes,listNotes);
    	updateAdapter();
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }


	private void init() {
		MenuRightAnimations.initOffset(NotesActivity.this);
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
									intent.setClass(NotesActivity.this,LoverActivity.class);
									NotesActivity.this.startActivity(intent);
								} else if (id == R.id.composer_period) {
									intent.setClass(NotesActivity.this,CalendarActivity.class);
									NotesActivity.this.startActivity(intent);
								} else if (id == R.id.composer_button_thought) {
									intent.setClass(NotesActivity.this,SendMsgActivity.class);
								} else if (id == R.id.composer_button_photo) {
									intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
									startActivityForResult(intent, 1);
									NotesActivity.this.startActivity(intent);
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
			intent.setClass(NotesActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		} else if (item.getItemId() == AppConstant.APP) {
			AppConnect.getInstance(NotesActivity.this).showAppOffers(NotesActivity.this);
		} else if (item.getItemId() == AppConstant.GAME) {
			AppConnect.getInstance(NotesActivity.this).showGameOffers(NotesActivity.this);
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


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		onClickView(v, true);
		return false;
	}
}
