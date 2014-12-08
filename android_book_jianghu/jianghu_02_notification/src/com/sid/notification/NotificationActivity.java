package com.sid.notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class NotificationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        new MyNotification().notificationShow(NotificationActivity.this,"我的测试","这是个notification的测试啊");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notification, menu);
        return true;
    }

}
