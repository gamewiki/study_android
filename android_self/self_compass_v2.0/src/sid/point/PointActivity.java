package sid.point;

import sid.util.AboutActivity;
import sid.util.AppConstant;
import sid.util.ExitApplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.baidu.mobstat.StatService;

public class PointActivity extends Activity implements SensorEventListener, OnGestureListener {
	/**设置提示性文字*/
	private String textNote = "";
	/**设置提示性文字*/
	private String angleNote = "";
	/**获取屏幕的宽度*/
	private Integer windowWidth = 420;
	/**获取屏幕的高度*/
	private Integer windowHeight = 800;
	/**传感器管理器*/
	private SensorManager sensorManager;
	/** 方向夹角*/
	private Sensor mOrientation;
	/** 旋转实例*/
	private RotateView rotateView= null;
    private GestureDetector detector;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		Log.w(AppConstant.TAG, "PointActivity.onCreate()");
        super.onCreate(savedInstanceState);
    	textNote = this.getString(R.string.textNote);
    	angleNote = this.getString(R.string.angleNote);
		//获取显示屏的宽高
		getWH();
        //获取传感器的管理器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //获取方向传感器
        mOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		//绑定新的分析用户的动作类
        detector = new GestureDetector(this);
		//添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
        
		
        //设置视图
        RelativeLayout layout = new RelativeLayout(this);
        //启动指南针视图勾画指南针
        rotateView = new RotateView(this);
        RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(  
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); 
        layout.addView(rotateView,viewParams);
        
        //设置按钮样式
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
        		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.adword);
        button.getBackground().setAlpha(60);
        layout.addView(button, buttonParams);
        setContentView(layout);
    }
	@Override
	protected void onResume() {
		Log.w(AppConstant.TAG, "PointActivity.OnResume()");
		super.onResume();
		/**此处调用基本统计代码*/
		StatService.onResume(this);
		sensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onPause() {
		Log.w(AppConstant.TAG, "PointActivity.onPause()");
		super.onPause();
		/**此处调用基本统计代码*/
		StatService.onPause(this);
		sensorManager.unregisterListener(this);
	}
	/* 以下是点击菜单按钮执行的方法*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, AppConstant.ABOUT, 1, R.string.about);
    	menu.add(0, AppConstant.EXIT, 2, R.string.exit);
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			//启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(PointActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			//执行循环退出
			ExitApplication.getInstance().exit();
			// 如果只是调用以下其中的一个方法，并不会完全退出应用
			// android.os.Process.killProcess(android.os.Process.myPid());
			// System.exit(0);
		}
    	return super.onOptionsItemSelected(item);
    }
	private void getWH() {
		//获取屏幕宽高
		Display display = this.getWindowManager().getDefaultDisplay();
		windowWidth = display.getWidth();
		windowHeight = display.getHeight();
	}
	

	/* 以下是监听传感器事件 ；需要继承SensorEventListener*/
	@Override
	public void onSensorChanged(SensorEvent event) {
		//y轴与地磁夹角
		float angle_y = event.values[SensorManager.DATA_X];
		//x轴与地磁夹角
		float angle_x = event.values[SensorManager.DATA_Y];
		if (-45<angle_x&&angle_x<65) {
			//y轴与北极的夹角
			int angle_revise = (int)angle_y+AppConstant.ANGLE_REVISE;
			if(350<angle_revise||angle_revise<10){
				if(angle_revise<0){
					angle_revise = 360 + AppConstant.ANGLE_REVISE - angle_revise;
				}
				textNote = this.getString(R.string.north);
			}else if(10<=angle_revise&&angle_revise<80){
				textNote = this.getString(R.string.northeast);
			}else if(80<=angle_revise&&angle_revise<100){
				textNote = this.getString(R.string.east);
			}else if(100<=angle_revise&&angle_revise<170){
				textNote = this.getString(R.string.southeast);
			}else if(170<=angle_revise&&angle_revise<190){
				textNote = this.getString(R.string.south);
			}else if(190<=angle_revise&&angle_revise<260){
				textNote = this.getString(R.string.southwest);
			}else if(260<=angle_revise&&angle_revise<280){
				textNote = this.getString(R.string.west);
			}else if(280<=angle_revise&&angle_revise<350){
				textNote = this.getString(R.string.northwest);
			}
			angleNote = this.getString(R.string.ByNorth)+(360-angle_revise)+"°";
			rotateView.setAngle(angle_revise);
		}else{
			textNote = this.getString(R.string.placed);
			angleNote = this.getString(R.string.upward);
		}
		rotateView.setTextNote(textNote);
		rotateView.setAngleNote(angleNote);
		if (windowWidth!=null&&windowHeight!=null) {
			rotateView.setWindowHeight(windowHeight);
			rotateView.setWindowWidth(windowWidth);
		}else{
			getWH();
			rotateView.setWindowHeight(windowHeight);
			rotateView.setWindowWidth(windowWidth);
		}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
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
        if (e1.getX() - e2.getX() > 120) {
			//启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(PointActivity.this, AboutActivity.class);
			this.startActivity(intent);
//        } else if (e1.getX() - e2.getX() < -120) {  
//            return true;
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
