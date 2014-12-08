package sid.lover.period;

import sid.lover.R;
import sid.modle.Note;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class PeriodThingsActivity extends Activity{
	private TextView periodThings;
	
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_period_things);

		Note note = (Note) getIntent().getSerializableExtra(AppConstant.NOTE);
		if (note==null) {
			Toast.makeText(PeriodThingsActivity.this, getString(R.string.periodThingsError), Toast.LENGTH_SHORT).show();
			finish();
		}else{
			periodThings = (TextView)findViewById(R.id.periodThings);
			periodThings.setText(note.getContent());
		}
		
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
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
			intent.setClass(PeriodThingsActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

}
