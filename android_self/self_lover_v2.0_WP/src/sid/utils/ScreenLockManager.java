package sid.utils;


import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

public class ScreenLockManager {

	private Context mContext;
	private KeyguardManager.KeyguardLock mKeyguardLock;
	private boolean isScreenLock;
	private PowerManager.WakeLock mWakelock;

	public ScreenLockManager(Context context) {
		mContext = context;
	}

	/**
	 * 解锁屏幕
	 */
	public void getUnlock() {
		// acquire wake lock
		PowerManager pm = (PowerManager) mContext
				.getSystemService(Context.POWER_SERVICE);
		mWakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.ON_AFTER_RELEASE, "SimpleTimer");
		mWakelock.acquire();

		// unlock screen
		KeyguardManager km = (KeyguardManager) mContext
				.getSystemService(Context.KEYGUARD_SERVICE);
		mKeyguardLock = km.newKeyguardLock("unLock");
		if (km.inKeyguardRestrictedInputMode()) {
			mKeyguardLock.disableKeyguard();
			isScreenLock = true;
		} else {
			isScreenLock = false;
		}
	}

	/**
	 * 重新加锁
	 */
	public void releaseUnlock() {
		// release screen
		if (isScreenLock) {
			mKeyguardLock.reenableKeyguard();
			isScreenLock = false;
		}
		// release wake lock
		if (mWakelock.isHeld()) {
			mWakelock.release();
		}
	}
}
