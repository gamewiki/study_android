package com.sid.bar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class BarActivity extends Activity {
	SeekBar seekBar = null;
	RatingBar ratingBar = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        seekBar =(SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBarListener());
        ratingBar =(RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBarListener());
        
    }
    
    class RatingBarListener implements RatingBar.OnRatingBarChangeListener{

		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			System.out.println(rating);
		}
    }
    
    class SeekBarListener implements SeekBar.OnSeekBarChangeListener{
    	/**
    	 * 进度条发生变化时
    	 * 不论是手动还是自动
    	 */
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			System.out.println(progress);
		}

		/**
		 * 用户开始滑动滑块时调用
		 */
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			System.out.println("star---->"+seekBar.getProgress());
		}

		/**
		 * 当用户结束滑块的滑动时
		 */
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			System.out.println("begin---->"+seekBar.getProgress());
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bar, menu);
        return true;
    }
}
