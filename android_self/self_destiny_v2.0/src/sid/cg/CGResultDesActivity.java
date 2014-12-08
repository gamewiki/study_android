package sid.cg;

import sid.destiny.R;
import sid.modle.PersonDestiny;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mobstat.StatService;

public class CGResultDesActivity extends Activity {    
    private ImageView r_cg_imageDestiny; 
    private TextView r_cg_name;
    private TextView r_cg_description;
	/**获取屏幕的宽度*/
	private Integer windowWidth = 420;
	/**获取屏幕的高度*/
	private Integer windowHeight = 800;
	private int width;
	private int height;
    @Override
    public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "CGResultDesActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cg_result_des);   

        
		//获取显示屏的宽高
		getWH();
        PersonDestiny person = ( PersonDestiny)getIntent().getSerializableExtra("person");
        String sex = person.getUsersex().equals(getString(R.string.cgOther))?"":person.getUsersex();
        String name = person.getUsername()+sex+getString(R.string.cgEightText)+person.getUsereight();
        String description = person.getDescription();
        int weight = person.getWeightId();
        r_cg_imageDestiny = (ImageView) findViewById(R.id.r_cg_imageDestiny);
        if (weight<26) {
        	r_cg_imageDestiny.setImageResource(R.drawable.zhiming);
		}else if (weight>60) {
        	r_cg_imageDestiny.setImageResource(R.drawable.yuming);
		}else{
        	r_cg_imageDestiny.setImageResource(R.drawable.piming);
		}
        r_cg_name = (TextView) findViewById(R.id.r_cg_name);
        r_cg_name.setText(name);
        r_cg_name.setPadding(width, height, width, height);
        r_cg_description = (TextView) findViewById(R.id.r_cg_description);
        r_cg_description.setText(description);
        r_cg_description.setPadding(width, 0, width, 0);
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
    }

	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "CGResultDesActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "CGResultDesActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		 StatService.onPause(this);
	}
	
	private void getWH() {
		// 获取屏幕宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		windowWidth = dm.widthPixels;
		windowHeight = dm.widthPixels;
		height = windowHeight / 10;
		width = windowWidth / 7;
	}
	/* 以下是点击菜单按钮执行的方法 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about).setIcon(R.drawable.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit).setIcon(R.drawable.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(CGResultDesActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
