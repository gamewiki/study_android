package sid.handler_01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * 异步的消息处理
 * 放入Runable中进行处理
 * @author Administrator
 *
 * 1.创建handler
 * 2.将所要执行的线程添加到队列中
 * 3.将要执行的操作写在线程对象的run方法当中
 */
@SuppressLint("HandlerLeak")
public class Handler extends Activity {
	Button start = null;
	ProgressBar bar = null;
	//1
	android.os.Handler handler = new android.os.Handler(){
		@Override
		public void handleMessage(Message msg) {
			bar.setProgress(msg.arg1);
			bar.setSecondaryProgress(msg.arg1+10);
			//将线程压入到线程队列中去
			handler.post(updateThread);
		}
	};
	//3
	Runnable updateThread = new Runnable() {
		int i = 0 ;
		@Override
		public void run() {
			System.out.println(i+10+"==========updateThread==========");
			i=i+10;

			//获取message队列
			Message msg = handler.obtainMessage();
			msg.arg1 = i;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(i>=100){
				handler.removeCallbacks(updateThread);
			}else{
				//将消息压入到消息队列中去
				handler.sendMessage(msg);
			}
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        start = (Button)findViewById(R.id.startButton);
        start.setOnClickListener(new MyStartOnClickListener());
        bar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_handler, menu);
        return true;
    }
    
    class MyStartOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			bar.setVisibility(View.VISIBLE);
			//2
			handler.post(updateThread);
		}
    }
}
