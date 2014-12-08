package sid.gylq;

import java.util.Random;

import sid.destiny.R;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import sid.utils.ShakeListener;
import sid.utils.ShakeListener.OnShakeListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.waps.AppConnect;

public class LQStartActivity extends Activity {
	private TextView lq_shake;
	private ImageView image_lq_lq;
	private ImageView image_lq_box;
	private ImageView image_lq_zhibei;
	private ImageView image_lq_zhibei_jieguo;
	private ImageButton lq_shake_button;
	private ImageButton lq_zhibei_button;
	private ImageButton lq_chongchou_button;
	private RelativeLayout layout;
	private int lqNum;
	private int zhibeiNum;
	private int zhibeiSeq = 0;
	private ShakeListener shakeListener = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "LQStartActivity.onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lq_start);
		
        //3.插屏广告
        AppConnect.getInstance(this).initPopAd(this);
        AppConnect.getInstance(this).showPopAd(this);
        

		layout = (RelativeLayout) findViewById(R.id.lq_start);
        lq_shake = (TextView) findViewById(R.id.lq_shake);
        image_lq_lq = (ImageView) findViewById(R.id.image_lq_lq);
        image_lq_box = (ImageView) findViewById(R.id.image_lq_box);
        image_lq_zhibei = (ImageView) findViewById(R.id.image_lq_zhibei);
        image_lq_zhibei_jieguo = (ImageView) findViewById(R.id.image_lq_zhibei_jieguo);
        lq_shake_button = (ImageButton) findViewById(R.id.lq_shake_button);
        lq_shake_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				shackLQ();
			}
		});
        lq_zhibei_button = (ImageButton) findViewById(R.id.lq_zhibei_button);
        lq_zhibei_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				zhiBei();
			}
		});
        lq_chongchou_button = (ImageButton) findViewById(R.id.lq_chongchou_button);
        lq_chongchou_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				layout.setBackgroundResource(R.drawable.lq_backgroud);
				image_lq_box.setVisibility(View.VISIBLE);
				lq_chongchou_button.setVisibility(View.INVISIBLE);
				image_lq_zhibei_jieguo.setVisibility(View.INVISIBLE);
				lq_shake.setText(R.string.lq_shake);
				getShakeListener();
			}
		});
        getShakeListener();
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
		// 设置默认软键盘不弹出
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}
	/**
	 * 获取摇晃监听事件
	 */
	private void getShakeListener() {
		shakeListener = new ShakeListener(this);//创建一个对象 
    	if (shakeListener.isHasSensor()) {
            shakeListener.setOnShakeListener(new OnShakeListener(){//调用setOnShakeListener方法进行监听        
    	        public void onShake() {
    	        	shackLQ();
    	        }
            });
		}else{
			lq_shake.setText(R.string.lq_dianji);
			lq_shake_button.setVisibility(View.VISIBLE);
		}
	}
	
	class ShakeAnimationListener implements AnimationListener{
		@Override
		public void onAnimationEnd(Animation animation) {
			shackPhone();
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
		}
		@Override
		public void onAnimationStart(Animation animation) {
		}
	}
	
	class ZhibeiAnimationListener implements AnimationListener{
		@Override
		public void onAnimationEnd(Animation animation) {
			image_lq_zhibei.setVisibility(View.INVISIBLE);
			switch (zhibeiNum) {
				case 0://笑杯
					zhibeiSeq = 0;
					layout.setBackgroundResource(R.drawable.lq_xinbucheng);
					image_lq_lq.setVisibility(View.INVISIBLE);
					image_lq_box.setVisibility(View.INVISIBLE);
					lq_zhibei_button.setVisibility(View.INVISIBLE);
					lq_chongchou_button.setVisibility(View.VISIBLE);
					lq_shake.setText(R.string.lq_zhibei);
					image_lq_zhibei_jieguo.setBackgroundResource(R.drawable.lq_xiaobei);
					image_lq_zhibei_jieguo.setVisibility(View.VISIBLE);
					Toast.makeText(LQStartActivity.this, getString(R.string.lq_xiaobei), Toast.LENGTH_LONG).show();
					break;
				case 2://阴杯
					zhibeiSeq = 0;
					layout.setBackgroundResource(R.drawable.lq_shenlingshengqile);
					image_lq_lq.setVisibility(View.INVISIBLE);
					image_lq_box.setVisibility(View.INVISIBLE);
					lq_zhibei_button.setVisibility(View.INVISIBLE);
					lq_chongchou_button.setVisibility(View.VISIBLE);
					lq_shake.setText(R.string.lq_zhibei);
					image_lq_zhibei_jieguo.setBackgroundResource(R.drawable.lq_yinbei);
					image_lq_zhibei_jieguo.setVisibility(View.VISIBLE);
					Toast.makeText(LQStartActivity.this, getString(R.string.lq_yinbei), Toast.LENGTH_LONG).show();
			    	break;
				default://圣杯
					zhibeiSeq++;
					image_lq_zhibei_jieguo.setBackgroundResource(R.drawable.lq_shengbei);
					image_lq_zhibei_jieguo.setVisibility(View.VISIBLE);
					if (1==zhibeiSeq) {
						Toast.makeText(LQStartActivity.this, getString(R.string.lq_shengbei1), Toast.LENGTH_SHORT).show();
						lq_zhibei_button.setBackgroundResource(R.drawable.zhibei2);
						lq_shake.setText(R.string.lq_zhibei2);
					}else if (2==zhibeiSeq) {
						Toast.makeText(LQStartActivity.this, getString(R.string.lq_shengbei2), Toast.LENGTH_SHORT).show();
						lq_zhibei_button.setBackgroundResource(R.drawable.zhibei3);
						lq_shake.setText(R.string.lq_zhibei2);
					}else{
						layout.setBackgroundResource(R.drawable.lq_qiqiuchenggong);
						image_lq_lq.setVisibility(View.INVISIBLE);
						image_lq_box.setVisibility(View.INVISIBLE);
						Toast.makeText(LQStartActivity.this, getString(R.string.lq_shengbei3), Toast.LENGTH_SHORT).show();
						lq_zhibei_button.setBackgroundResource(R.drawable.chakan);
						lq_shake.setText(R.string.lq_zhibei3);
					}
					break;
			}
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
		}
		@Override
		public void onAnimationStart(Animation animation) {
		}
	}

	/**
	 * 置杯
	 */
	private void zhiBei() {
        image_lq_lq.setVisibility(View.INVISIBLE);
        image_lq_box.setVisibility(View.INVISIBLE);
		if (zhibeiSeq<3) {
			Random random = new Random();
			//正常是4，为了方便用户获得签文，设置为6
			zhibeiNum = random.nextInt(6);
			zhiBeiAnimation();
		}else{
			Intent intent = new Intent();
			intent.putExtra("lqNum", lqNum);
			intent.setClass(LQStartActivity.this, LQResultActivity.class);
			this.startActivity(intent);
		}
	}
	/**
	 * 摇晃手机获取灵签
	 */
	private void shackPhone() {
		image_lq_lq.setVisibility(View.VISIBLE);
		Random random = new Random();
		lqNum = random.nextInt(100)+1;
		if (lqNum>100)lqNum = 100;
		lq_shake_button.setVisibility(View.INVISIBLE);
		lq_zhibei_button.setVisibility(View.VISIBLE);
		lq_zhibei_button.setBackgroundResource(R.drawable.zhibei1);
    	Toast.makeText(LQStartActivity.this, getString(R.string.lq_getLQ)+lqNum+getString(R.string.lq_zhibei1), Toast.LENGTH_LONG).show();
    	lq_shake.setText(getString(R.string.lq_zhibei1));
    	if (null!=shakeListener) {
    		shakeListener.stop();
		}
	}
	/**
	 * 开始摇晃灵签的动作
	 */
	private void shackLQ() {
		TranslateAnimation alphaAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.ABSOLUTE, 10,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.ABSOLUTE, 10);  
    	alphaAnimation.setDuration(1000);  
    	alphaAnimation.setRepeatCount(2);  
    	alphaAnimation.setRepeatMode(Animation.REVERSE);  
    	alphaAnimation.setAnimationListener(new ShakeAnimationListener());
    	image_lq_box.startAnimation(alphaAnimation);
	}
	/**
	 * 掷杯的动画
	 */
	private void zhiBeiAnimation() {
		image_lq_zhibei_jieguo.setVisibility(View.INVISIBLE);
		image_lq_zhibei.setVisibility(View.VISIBLE);
		TranslateAnimation alphaAnimation = new TranslateAnimation(0,0,0,150);
    	alphaAnimation.setDuration(1500);
    	alphaAnimation.setAnimationListener(new ZhibeiAnimationListener());
    	image_lq_zhibei.startAnimation(alphaAnimation);
	}
	
	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "LQStartActivity.OnResume()");
		super.onResume();
		/** 此处调用基本统计代码 */
		 StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "LQStartActivity.onPause()");
		super.onPause();
		/** 此处调用基本统计代码 */
		StatService.onPause(this);
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
			intent.setClass(LQStartActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}
}
