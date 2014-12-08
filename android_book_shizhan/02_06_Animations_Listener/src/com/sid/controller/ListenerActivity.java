package com.sid.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ListenerActivity extends Activity {
	private Button buttonAdd = null;
	private Button buttonDel = null;
	private ImageView imageView = null;
	private ViewGroup viewGroup = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        buttonAdd = (Button) findViewById(R.id.buttonAddId);
        buttonAdd.setOnClickListener(new ButtonAddListener());
        buttonDel = (Button) findViewById(R.id.buttonDeleteId);
        buttonDel.setOnClickListener(new ButtonDeleteListener());
        imageView = (ImageView) findViewById(R.id.imageViewId);
        viewGroup = (ViewGroup) findViewById(R.id.viewGroup);
    }
    
    class ButtonAddListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			AlphaAnimation animation = new AlphaAnimation( 0.0f,1.0f);
			animation.setDuration(1000);
			animation.setStartOffset(500);
			ImageView add = new ImageView(ListenerActivity.this);
			add.setImageResource(R.drawable.mp3_yellow_round_singlecycle);
			viewGroup.addView(add, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			add.startAnimation(animation);
		}
    }
    
    class ButtonDeleteListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
			animation.setDuration(1000);
			animation.setStartOffset(500);
			animation.setAnimationListener(new DeleteAnimationListener());
			imageView.startAnimation(animation);
		}
    }
    
    
    class DeleteAnimationListener implements AnimationListener{
    	//设置动画结束时的操作
		@Override
		public void onAnimationEnd(Animation animation) {
			viewGroup.removeView(imageView);
		}

		//设置动画重复时的操作
		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		//设置动画开始时的操作
		@Override
		public void onAnimationStart(Animation animation) {
		}
    }
}
