package com.sid.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
public class MainActivity extends Activity implements OnGestureListener {  
      
    private GestureDetector detector;  
    private ViewFlipper flipper;  
      
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);  
        flipper.addView(addButtonByText("按钮"),new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)); 
        detector = new GestureDetector(this);  
    }  
      
    public View addButtonByText(String text){  
        Button btn = new Button(this);    
        btn.setText(text);    
        return btn;    
    }    
    public View addTextByText(String text){  
        TextView tv = new TextView(this);    
        tv.setText(text);    
        tv.setGravity(1);    
        return tv;    
    }    
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        Log.i("Fling", "Activity onTouchEvent!");  
        return this.detector.onTouchEvent(event);  
    }  
    /** 
     * 监听滑动 
     */  
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {  
        Log.i("Fling", "Fling Happened!");  
        if (e1.getX() - e2.getX() > 120) {  
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in)); 
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out)); 
            this.flipper.addView(addTextByText("文本框"),new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)); 
            this.flipper.showNext();  
            return true;  
        } else if (e1.getX() - e2.getX() < -120) {  
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in)); 
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out)); 
            this.flipper.showPrevious();  
            return true;  
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


