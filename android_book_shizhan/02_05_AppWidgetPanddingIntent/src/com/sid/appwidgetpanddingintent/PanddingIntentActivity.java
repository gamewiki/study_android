package com.sid.appwidgetpanddingintent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PanddingIntentActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandding_intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pandding_intent, menu);
        return true;
    }
}
