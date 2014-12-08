package org.marsdroid.oauth02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	final String TAG = getClass().getName();
	private SharedPreferences prefs;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Button launchOauth = (Button) findViewById(R.id.btn_launch_oauth);
        
        launchOauth.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	startActivity(new Intent().setClass(v.getContext(), PrepareRequestTokenActivity.class));
            }
        });
	}
	
	
	
}