package sid.activity_05;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new MyOnClickListener());
    }
    
    class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
//
//			Intent intent = new Intent();
//			intent.setClass(FirstActivity.this, SecondActivity.class);
//			FirstActivity.this.startActivity(intent);
			finish();
		}
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_first, menu);
        return true;
    }
	@Override
	protected void onStart() {
		System.out.println("Second-------------------------->onStart");
		super.onStart();
	}
	@Override
	protected void onResume() {
		System.out.println("Second-------------------------->onResume");
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		System.out.println("Second-------------------------->onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		System.out.println("Second-------------------------->onPause");
		super.onPause();
	}
	@Override
	protected void onRestart() {
		System.out.println("Second-------------------------->onRestart");
		super.onRestart();
	}
}
