package sid.activity_04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		System.out.println("First-------------------------->onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button button = (Button)findViewById(R.id.mybutton);
        button.setOnClickListener(new myOnClickListener());
    }

    class myOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(FirstActivity.this, SecondActivity.class);
			FirstActivity.this.startActivity(intent);
			
		}
    	
    }
	@Override
	protected void onStart() {
		System.out.println("First-------------------------->onStart");
		super.onStart();
	}
	@Override
	protected void onResume() {
		System.out.println("First-------------------------->onResume");
		super.onResume();
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_first, menu);
        return true;
    }

	@Override
	protected void onDestroy() {
		System.out.println("First-------------------------->onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		System.out.println("First-------------------------->onPause");
		super.onPause();
	}
	@Override
	protected void onRestart() {
		System.out.println("First-------------------------->onRestart");
		super.onRestart();
	}

}
