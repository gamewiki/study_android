package com.sid.frame;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class FrameActivity extends Activity {

	private Button translate;
	private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        translate = (Button)findViewById(R.id.translateButtonId);
        translate.setOnClickListener(new TranslateOnClickListener());
        
        imageView = (ImageView)findViewById(R.id.imageViewId);
    }
    class TranslateOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			imageView.setBackgroundResource(R.drawable.anim_frame);
			AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
			drawable.start();
		}
    }
}
