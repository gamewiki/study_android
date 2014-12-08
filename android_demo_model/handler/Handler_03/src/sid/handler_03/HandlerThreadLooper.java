package sid.handler_03;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;

public class HandlerThreadLooper extends Activity {

	Handler handler = new Handler();
	
	Runnable r = new Runnable() {
		@Override
		public void run() {
			System.out.println("runnable -----getId----------------->"+ Thread.currentThread().getId());
			System.out.println("runnable -----getName------------------>"+ Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
		System.out.println("activity -----getId----------------->"+ Thread.currentThread().getId());
		System.out.println("activity -----getName------------------>"+ Thread.currentThread().getName());
		
		//生成了handlerthread对象，实现了使用looper来处理消息队列的功能
		HandlerThread handlerThread = new HandlerThread("handler_thread");
		handlerThread.start();
		
		//将handler绑定到不同于activity的另一个线程的looper对象上面
		MyHandler my = new MyHandler(handlerThread.getLooper());
		Message msg = my.obtainMessage();

		//类似于java的map
		Bundle bundle = new Bundle();
		bundle.putInt("age", 12);
		bundle.putString("name", "sid");
		msg.setData(bundle);
		//调用Myhandler的handlerMessage方法
		msg.sendToTarget();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_handler_thread, menu);
        return true;
    }
    
    class MyHandler extends Handler{
    	MyHandler(){}
    	MyHandler(Looper looper){
    		super(looper);
    	}
    	@Override
    	public void handleMessage(Message msg) {

    		System.out.println("Myhandler -----getId----------------->"+ Thread.currentThread().getId());
    		System.out.println("Myhandler -----getName------------------>"+ Thread.currentThread().getName());
    		Bundle bundle = msg.getData();
    		int age = bundle.getInt("age");
    		String name = bundle.getString("name");
    		System.out.println("age is "+age);
    		System.out.println("name is "+name);
    	}
    }
    
}
