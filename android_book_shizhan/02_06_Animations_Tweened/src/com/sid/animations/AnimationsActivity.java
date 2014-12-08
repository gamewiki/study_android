package com.sid.animations;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 
 * @author Administrator
 *
 */
public class AnimationsActivity extends Activity {
	private Button scale;
	private Button rotate;
	private Button alpha;
	private Button translate;
	private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        scale = (Button)findViewById(R.id.scaleButtonId);
        scale.setOnClickListener(new ScaleOnClickListener());
        
        rotate = (Button)findViewById(R.id.rotateButtonId);
        rotate.setOnClickListener(new RotateOnClickListener());
        
        alpha = (Button)findViewById(R.id.alphaButtonId);
        alpha.setOnClickListener(new AlphaOnClickListener());
        
        translate = (Button)findViewById(R.id.translateButtonId);
        translate.setOnClickListener(new TranslateOnClickListener());
        
        imageView = (ImageView)findViewById(R.id.imageViewId);
    }
    class ScaleOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//设置的属性全部应用的里面包含的animation对象
			//true表示将animationSet的interpolator的属性进行共享
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new DecelerateInterpolator());
			//1、2：X轴从多大到多大
			//3、4：Y轴从多大到多大
			//5、6：X轴的缩小中心点
			//7、8：Y轴的缩小中心点
			ScaleAnimation animation = new ScaleAnimation(1, 0.1f, 1, 0.1f, 
					Animation.RELATIVE_TO_SELF, 0.5f, 
					Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(1000);
			//是否设置停留最后的状态
			animation.setFillAfter(true);
			//是否回到之前的状态
			animation.setFillBefore(true);
			animationSet.addAnimation(animation);
			//使用imageView的startAnimation执行动画
			imageView.startAnimation(animationSet);

		}
    }
    class RotateOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//设置的属性全部应用的里面包含的animation对象
			//true表示将animationSet的interpolator的属性进行共享
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new DecelerateInterpolator());
			//1.从哪个角度开始转
			//2、3.围绕的旋转轴在哪里：x轴
			//4、5.围绕的旋转轴在哪里：y轴
			//(自身轴：RELATIVE_TO_SELF、父控件：RELATIVE_TO_PARENT、绝对位置：ABSOLUTE)
			RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 
					Animation.RELATIVE_TO_PARENT, 1f, 
					Animation.RELATIVE_TO_PARENT, 0f);
			rotateAnimation.setDuration(1000);
			animationSet.addAnimation(rotateAnimation);
			imageView.startAnimation(animationSet);
		}
    }
    class AlphaOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//设置的属性全部应用的里面包含的animation对象
			//true表示将animationSet的interpolator的属性进行共享
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new DecelerateInterpolator());
			//2.创建一个AlphaAnimation对象
			//参数表示：从什么样子的透明度到什么样子的透明度
			AlphaAnimation alphaAnimation = new AlphaAnimation(1,0); 
			alphaAnimation.setDuration(1000);
			animationSet.addAnimation(alphaAnimation);
			//使用imageView的startAnimation执行动画
			imageView.startAnimation(animationSet);
		}
    }
    class TranslateOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//设置的属性全部应用的里面包含的animation对象
			//true表示将animationSet的interpolator的属性进行共享
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new DecelerateInterpolator());
			//1、2：设置X轴的开始位置
			//3、4：设置X轴的结束位置
			//5、6：设置Y轴的开始位置
			//7、8：设置Y轴的结束位置
			TranslateAnimation animation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f, 
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0f, 
					Animation.RELATIVE_TO_SELF, 1.0f);
			animation.setDuration(1000);
			animationSet.addAnimation(animation);
			//使用imageView的startAnimation执行动画
			imageView.startAnimation(animationSet);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_animations, menu);
        return true;
    }
}
