package com.sid.tencent.progress.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button = null;
	private ProgressBar progressBar = null;
	private TextView textView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        
//        button = (Button)findViewById(R.id.buttonId);
//        progressBar = (ProgressBar)findViewById(R.id.progerssBarId);
//        textView = (TextView)findViewById(R.id.textViewId);
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressBarAsyncTask asyncTask = new ProgressBarAsyncTask(textView,progressBar);
				asyncTask.execute(1000);
			}
		});
    }
}