package com.cn.Abimation.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

public class CanClickAnimationListener implements AnimationListener {
	private View view1;
    private View view2;
    
    
    private View viewbg;
	public CanClickAnimationListener(View view1) {
		this.view1 = view1;
	}
	
	public CanClickAnimationListener(View view1,View view2) {
		this.view1 = view1;
		this.view2 = view2;
	}
	public CanClickAnimationListener setBackgroup_transparent(View viewbg)
	{
		this.viewbg=viewbg;
		return this;
	}
	

	@Override
	public void onAnimationEnd(Animation arg0) {
		try {
			view1.setEnabled(true);
			view2.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			viewbg.setBackgroundResource(android.R.color.transparent);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		try {
			view1.setEnabled(false);
			view2.setEnabled(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
