package com.sid.map;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class GoogleMapActivity extends MapActivity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_map);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
