package sid.service_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceActivity extends Activity {
	Button start = null;
	Button stop = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new StartService());
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new StopService());
    }
    
    class StartService implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(ServiceActivity.this, FirstService.class);
			startService(intent);
		}
    	
    }
    
    class StopService implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(ServiceActivity.this, FirstService.class);
			stopService(intent);
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_service, menu);
        return true;
    }
}
