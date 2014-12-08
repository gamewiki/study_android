package com.sid.json;

import android.app.Activity;
import android.graphics.Paint.Join;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JsonActivity extends Activity {

	private String jsonData = "[{\"name\":\"Michael\",\"age\":20},{\"name\":\"Mike\",\"age\":21}]";
	private String jsonUserData = "{\"name\":\"Michael\",\"age\":20}";
	private Button button = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_json, menu);
        return true;
    }
    
    class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			JsonUtils jsonUtils = new JsonUtils();
			jsonUtils.parseJson(jsonData);
			System.out.println("========================================");
			jsonUtils.parseUserFromJson(jsonUserData);
			System.out.println("========================================");
			jsonUtils.parseUsersFromJson(jsonData);
		}
    	
    }
}
