package sid.activity_04;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		System.out.println("Second-------------------------->onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_first, menu);
        return true;
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
