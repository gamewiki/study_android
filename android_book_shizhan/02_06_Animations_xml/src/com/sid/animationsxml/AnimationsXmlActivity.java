package com.sid.animationsxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationsXmlActivity extends Activity {
	private Button scale;
	private Button rotate;
	private Button alpha;
	private Button translate;
	private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_xml);
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
			Animation animation = AnimationUtils.loadAnimation(AnimationsXmlActivity.this, R.anim.scale);
			imageView.startAnimation(animation);
		}
    }
    class RotateOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(AnimationsXmlActivity.this, R.anim.rotate);
			imageView.startAnimation(animation);
		}
    }
    class AlphaOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(AnimationsXmlActivity.this, R.anim.alpha);
			imageView.startAnimation(animation);
		}
    }
    class TranslateOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(AnimationsXmlActivity.this, R.anim.translate);
			imageView.startAnimation(animation);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_animations_xml, menu);
        return true;
    }
}
