package sid.modle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartToSendding extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, Sendding.class);
		i.putExtra("id", (Integer)intent.getExtras().get("id"));
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
