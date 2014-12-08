package sid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_other extends Activity {
	TextView myText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		//获取传递过来啊Intent
		Intent intent = getIntent();
		String value = intent.getStringExtra("testIntent");
		myText = (TextView)findViewById(R.id.myOtherText);
		myText.setText(value);
	}

}
