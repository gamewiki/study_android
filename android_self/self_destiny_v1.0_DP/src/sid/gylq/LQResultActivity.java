package sid.gylq;

import sid.databasehelper.DatabaseHelper;
import sid.destiny.R;
import sid.modle.LingQian;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.DataUtils;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

public class LQResultActivity extends Activity {
	private int lqNum = 0;
	private LingQian lingqian = null;
	private TextView jixiong;
	private TextView gongwei;
	private TextView shi;
	private TextView jieyue;
	private TextView xianji;
	private TextView diangu;
	private ImageButton shareButton = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "CGResultActivity.onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lq_result);
		getLingQian();
		showLingQian();
		shareButton = (ImageButton) findViewById(R.id.lq_shareButton);
		shareButton.setImageResource(R.drawable.share);
		shareButton.setOnClickListener(new MyShareOnClickListener()); 
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	private void showLingQian() {
		if (null!=lingqian) {
			jixiong = (TextView) findViewById(R.id.lq_jixiong_des);
			jixiong.setText(lingqian.getJixiong());
			gongwei = (TextView) findViewById(R.id.lq_gongwei_des);
			gongwei.setText(lingqian.getGongwei());
			shi = (TextView) findViewById(R.id.lq_shi_des);
			shi.setText(lingqian.getShi().replace(" ", "\n"));
			jieyue = (TextView) findViewById(R.id.lq_jieyue_des);
			jieyue.setText(lingqian.getJieyue());
			xianji = (TextView) findViewById(R.id.lq_xianji_des);
			xianji.setText(lingqian.getJieqian().replace(" ", "\n"));
			diangu = (TextView) findViewById(R.id.lq_diangu_des);
			diangu.setText(lingqian.getDiangu());
		}
	}

	private class MyShareOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareNote1)+AppConstant.SHARE_URL+getString(R.string.shareNote2));   
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, getTitle())); 
		}
	}
	/**
	 * 根据id，获取灵签
	 */
	private void getLingQian() {
		lqNum = getIntent().getIntExtra("lqNum", 0);
		if (lqNum!=0) {
			DatabaseHelper dbh = new DatabaseHelper(LQResultActivity.this,
					"destiny_sid_db", DataUtils.VERSION);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			Cursor cursor;
			cursor = sd.query("lq_description",
					new String[] { "id,jixiong,gongwei,shi,jieyue,jieqian,diangu" }, "id=?", new String[] { lqNum+"" },
					null, null, null);
			while (cursor.moveToNext()) {
				lingqian = new LingQian();
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				String jixiong = cursor.getString(cursor.getColumnIndex("jixiong"));
				String gongwei = cursor.getString(cursor.getColumnIndex("gongwei"));
				String shi = cursor.getString(cursor.getColumnIndex("shi"));
				String jieyue = cursor.getString(cursor.getColumnIndex("jieyue"));
				String jieqian = cursor.getString(cursor.getColumnIndex("jieqian"));
				String diangu = cursor.getString(cursor.getColumnIndex("diangu"));
				lingqian.setId(id);
				lingqian.setJixiong(jixiong);
				lingqian.setGongwei(gongwei);
				lingqian.setShi(shi);
				lingqian.setJieyue(jieyue);
				lingqian.setJieqian(jieqian);
				lingqian.setDiangu(diangu);
			}
		}else{
			Toast.makeText(LQResultActivity.this, getString(R.string.lq_lost), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "CGResultActivity.OnResume()");
		getLingQian();
		super.onResume();
		/** 此处调用基本统计代码 */
		StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "CGResultActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		StatService.onPause(this);
	}
	
	/* 以下是点击菜单按钮执行的方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(LQResultActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
