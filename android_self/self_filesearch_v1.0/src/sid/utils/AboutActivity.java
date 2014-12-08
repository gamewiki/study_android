package sid.utils;

import sid.filesearch.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.mobstat.StatService;

public class AboutActivity extends Activity  implements  OnGestureListener {

	private TextView textViewPart1 = null;
	private TextView textViewPart2 = null;
	private TextView textViewPart3 = null;
	private ImageButton shareButton = null;
	private String shareNote = null;
    private GestureDetector detector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "AboutActivity.onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		textViewPart1 = (TextView) findViewById(R.id.about_part1);
		textViewPart1.setTextSize(AppConstant.ABOUT_TEXT_SIZE);
		textViewPart1.setText(R.string.about_part1);
		textViewPart2 = (TextView) findViewById(R.id.about_part2);
		textViewPart2.setTextSize(AppConstant.ABOUT_TEXT_SIZE);
		textViewPart2.setText(R.string.about_part2);
		textViewPart3 = (TextView) findViewById(R.id.about_part3);
		textViewPart3.setTextSize(AppConstant.ABOUT_TEXT_SIZE);
		textViewPart3.setText(R.string.about_part3);
		shareNote = this.getString(R.string.shareNote1)+AppConstant.SHARE_URL+this.getString(R.string.shareNote2);
		shareButton = (ImageButton) findViewById(R.id.shareButton);
		shareButton.setImageResource(R.drawable.share);
		shareButton.setOnClickListener(new MyShareOnClickListener()); 
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		//绑定新的分析用户的动作类
        detector = new GestureDetector(this);
	}
	private class MyShareOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
            Intent intent=new Intent(Intent.ACTION_SEND);
//            intent.setType("image/*");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
            intent.putExtra(Intent.EXTRA_TEXT, shareNote);   
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, getTitle())); 
		}
	}
	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "AboutActivity.OnResume()");
		super.onResume();
		/**此处调用基本统计代码*/
		StatService.onResume(this);
	}
	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "AboutActivity.onPause()");
		super.onPause();
		/**此处调用基本统计代码*/
		StatService.onPause(this);
	}

	/**
	 * 用户点击menu之后；会调用该方法 我们可以再这个方法中加入自己的按钮控件
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 参数分别是：组id，itemid，排序位置，文本内容
		menu.add(0, AppConstant.EXIT, 1, R.string.exit);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 用户点击menu按钮后调用的方法
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.EXIT) {
			//执行循环退出
			ExitApplication.getInstance().exit();
			// 如果只是调用以下其中的一个方法，并不会完全退出应用
			// android.os.Process.killProcess(android.os.Process.myPid());
			// System.exit(0);
		}
		return super.onOptionsItemSelected(item);
	}
	

	/* 以下是监听左右滑动事件 ；需要继承OnGestureListener*/
	
    @Override  
    public boolean onTouchEvent(MotionEvent event) {
        return this.detector.onTouchEvent(event);  
    }
    /**
     * 解决ScrollView后不执行左右移动监听事件OnGestureListener
     * 在Activity中添加ScrollView实现滚动activity的效果后，activity的滑动效果却无法生效了
     * 原因是因为activity没有处理滑动效果，重写以下方法即可解决。
     */
    @Override 
    public boolean dispatchTouchEvent(MotionEvent ev) { 
        detector.onTouchEvent(ev); 
        return super.dispatchTouchEvent(ev); 
    } 
    /** 
     * 监听滑动 
     */  
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {
        if (e1.getX() - e2.getX() < -120) {
        	finish();
        }  
        return true;  
    }
    @Override  
    public boolean onDown(MotionEvent e) {  
        return false;  
    }
    @Override  
    public void onLongPress(MotionEvent e) {  
    }  
    @Override  
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY) {  
        return false;  
    }  
    @Override  
    public void onShowPress(MotionEvent e) {  
    }  
    @Override  
    public boolean onSingleTapUp(MotionEvent e) {  
        return false;  
    }  
}
