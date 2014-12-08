package sid.mp3player;

import sid.utils.AppConstant;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mp3);
		
		//得到TabHost对象，针对tabhostActivity的操作都由这个对象完成
		TabHost tabHost = getTabHost();
		//用来获取系统内置资源
		Resources resources = getResources();
//		Drawable drawable = resources.getDrawable(android.R.drawable.stat_sys_download);
		TabHost.TabSpec remoteSpec = createTabSpec(tabHost,AppConstant.ABOUT_MP3_PLAYER,null,AboutMp3Activity.class);
		TabHost.TabSpec localSpec = createTabSpec(tabHost,AppConstant.MP3_LOCAL_LIST,null,LocalMP3Activity.class);
		//将设置好的spec设置收到tabHost当中
		tabHost.addTab(remoteSpec);
		tabHost.addTab(localSpec);
		//设置选项卡的高度和宽度
		final TabWidget tabWidget = tabHost.getTabWidget();
		for (int i =0; i < tabWidget.getChildCount(); i++) {
			tabWidget.getChildAt(i).getLayoutParams().height = 45;
//			tabWidget.getChildAt(i).getLayoutParams().width = 65;
		}
	}

	/**
	 * 生成一个tab页签
	 * @param tabHost
	 * @param tabName
	 * @param drawable
	 * @return
	 */
	private TabHost.TabSpec createTabSpec(TabHost tabHost,String tabName,Drawable drawable,Class cla) {
		//生成一个Intent对象，指向一个Activity对象
		Intent remoteIntent = new Intent();
		remoteIntent.setClass(this, cla);
		//生成一个tabSpec对象，这个对象代表了一个页签
		TabHost.TabSpec  remoteSpec =  tabHost.newTabSpec(tabName);
		//设置该页的indicator，包括页签文字和页签图标
		remoteSpec.setIndicator(tabName, drawable);
		//设置该页的内容
		remoteSpec.setContent(remoteIntent);
		return remoteSpec;
	}
}
