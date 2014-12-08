package com.sid.display;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DisplayActivity extends Activity {
	GradientTextView text = null;
	Button button = null;
	boolean isGradientFlag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);

		text = (GradientTextView) findViewById(R.id.text);
		text.setShowText("1234567890");
		text.setShowText("abcdefg");
		text.setShowText("$%^S&(#");
		text.startGradient();

		button = (Button) findViewById(R.id.button);
		button.setText("控制按钮");
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (text.getGradientFlag()) {
					text.stopGradient();
				} else {
					text.startGradient();
				}
			}
		});
	}
}
