package sid.handler_03;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;

public class HandlerThread extends Activity {

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
        //放在setContentView之前，并设置睡10秒；是为了验证是在一个线程中；否则不会等十秒后才输出
//        handler.post(r);
        
        //直接打印了，说明不是一个线程了，而是另开启了一个新的线程
        Thread t = new Thread(r);
        t.start();
        setContentView(R.layout.activity_handler_thread);
		System.out.println("activity -----getId----------------->"+ Thread.currentThread().getId());
		System.out.println("activity -----getName------------------>"+ Thread.currentThread().getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_handler_thread, menu);
        return true;
    }
    
    
}
