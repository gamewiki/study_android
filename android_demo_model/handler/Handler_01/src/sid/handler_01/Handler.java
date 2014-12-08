package sid.handler_01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 异步的消息处理
 * 放入Runable中进行处理
 * @author Administrator
 *
 * 1.创建handler
 * 2.将所要执行的线程添加到队列中
 * 3.将要执行的操作写在线程对象的run方法当中
 */
public class Handler extends Activity {
	Button start = null;
	Button end = null;
	//1
	android.os.Handler handler = new android.os.Handler();
	//3
	Runnable updateThread = new Runnable() {
		@Override
		public void run() {
			System.out.println("==========updateThread==========");
			handler.postDelayed(updateThread, 3000);
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        start = (Button)findViewById(R.id.startButton);
        start.setOnClickListener(new MyStartOnClickListener());
        end = (Button)findViewById(R.id.endButton);
        end.setOnClickListener(new MyEndOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_handler, menu);
        return true;
    }
    
    class MyStartOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//2
			handler.post(updateThread);
		}
    }
    
    class MyEndOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			handler.removeCallbacks(updateThread);
		}
    }
}
