package com.sid.display;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MyTextView extends TextView {
	int current = 0;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 10010)
				invalidate();
		};
	};

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (current++ != 101) {
					try {
						Thread.sleep(100);
						handler.sendEmptyMessage(10010);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// super.onDraw(canvas);
		canvas.save();
		getPaint().setTextSize(getTextSize());
		getPaint().setColor(Color.RED);
		canvas.clipRect(0, 0, current * (getMeasuredWidth() / 100f),
				getMeasuredHeight());
		Log.e("asd", "" + (current * (getMeasuredWidth() / 100)));
		Rect bounds = new Rect();
		getPaint().getTextBounds(getText().toString(), 0, getText().length(),
				bounds);
		int theight = bounds.bottom - bounds.top;
		int ty = getMeasuredHeight()
				- ((getMeasuredHeight() - theight - getCompoundPaddingTop() - getCompoundPaddingBottom()) >> 1);
		canvas.drawText(getText().toString(), 0 + getCompoundPaddingLeft(), ty,
				getPaint());
		canvas.restore();
	}
}