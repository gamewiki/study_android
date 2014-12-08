package com.sid.appwidgetsimple;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SimpleAppWidgetActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_app_widget);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_simple_app_widget, menu);
        return true;
    }
}
