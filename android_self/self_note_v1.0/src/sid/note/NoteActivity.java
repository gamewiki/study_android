package sid.note;

import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DatabaseHelper;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class NoteActivity extends Activity {
	private DigitalClock clock = null;
	private ImageButton enterNote = null;
	private ImageButton enterClock = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        
        WindowManager manager = getWindowManager();
        int screenWidth = manager.getDefaultDisplay().getWidth();
        int screenHeight = manager.getDefaultDisplay().getHeight();
        clock = (DigitalClock)findViewById(R.id.dClock);
        clock.setTextSize(screenWidth/6);
        
		DatabaseHelper dbh = new DatabaseHelper(NoteActivity.this, "note_sid_db",AppConstant.VERSION);
//		在执行下面的语句之前是不会创建数据库的
		dbh.getWritableDatabase();
		dbh.close();

		RelativeLayout.LayoutParams noteLP = (LayoutParams) findViewById(R.id.enterNote).getLayoutParams();
		noteLP.setMargins(screenWidth/8, screenHeight/6, 0, 0);
		RelativeLayout.LayoutParams clockLP = (LayoutParams) findViewById(R.id.enterClock).getLayoutParams();
		clockLP.setMargins(0, screenHeight/6, screenWidth/8, 0);
		
		enterNote = (ImageButton) findViewById(R.id.enterNote);
        enterNote.setOnClickListener(new enterNoteListener());
        enterNote.setLayoutParams(noteLP);
        enterClock = (ImageButton) findViewById(R.id.enterClock);
        enterClock.setOnClickListener(new enterClockListener());
        enterClock.setLayoutParams(clockLP);
		
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }

    class enterNoteListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(NoteActivity.this, NotesActivity.class);
			startActivity(intent);
		}
    }
    class enterClockListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(NoteActivity.this, ClockActivity.class);
			startActivity(intent);
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
			intent.setClass(NoteActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
