package sid.service_01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FirstService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("Service onBind");
		return null;
	}
	
	@Override
	public void onCreate() {
		System.out.println("Service onCreate");
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("intent is :"+intent);
		System.out.println("starId is :"+startId);
		System.out.println("Service onStart");
		super.onStart(intent, startId);
	}
	
	@Override
	public void onDestroy() {
		System.out.println("Service onDestroy");
		super.onDestroy();
	}

}
