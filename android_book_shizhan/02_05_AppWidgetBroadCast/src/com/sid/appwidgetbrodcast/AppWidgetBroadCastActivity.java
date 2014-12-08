package com.sid.appwidgetbrodcast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AppWidgetBroadCastActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_widget_broad_cast);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_app_widget_broad_cast, menu);
        return true;
    }
}
