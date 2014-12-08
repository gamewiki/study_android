package sid.activity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_Intent extends Activity {
	private Button myButton = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = (Button)findViewById(R.id.myButton);
        myButton.setOnClickListener(new myButtonListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    class myButtonListener implements OnClickListener{
		public void onClick(View v) {
//			//1.跳转统一项目中的activity
//			Intent intent = new Intent();
//			//设置传入的参数
//			intent.putExtra("testIntent", "testIntent");
//			//从某个activity跳转到另一个activity
//			intent.setClass(Activity_Intent.this, Activity_other.class);
//			//启动intent
//			Activity_Intent.this.startActivity(intent);
			
			
			//2.跳转不同应用程序中的activity
			Uri uri = Uri.parse("smsto://0800000123");
			Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
			intent.putExtra("sms_body", "this is my Intent");
			Activity_Intent.this.startActivity(intent);
		}
    	
    }
}
