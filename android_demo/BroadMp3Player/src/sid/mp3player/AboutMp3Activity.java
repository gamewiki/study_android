package sid.mp3player;

import sid.utils.AppConstant;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutMp3Activity extends Activity {
	
	private TextView textViewPart1 = null;
	private TextView textViewPart2 = null;
	private TextView textViewPart3 = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_mp3_player);
		textViewPart1 = (TextView)findViewById(R.id.about_player_part1);
		textViewPart1.setText(AppConstant.ABOUT_MP3_PART1);
		textViewPart2 = (TextView)findViewById(R.id.about_player_part2);
		textViewPart2.setText(AppConstant.ABOUT_MP3_PART2);
		textViewPart3 = (TextView)findViewById(R.id.about_player_part3);
		textViewPart3.setText(AppConstant.ABOUT_MP3_PART3);
	}

	/**
	 * 用户点击menu之后；会调用该方法 我们可以再这个方法中加入自己的按钮控件
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 参数分别是：组id，itemid，排序位置，文本内容
		menu.add(0, AppConstant.SETTING, 1, R.string.mp3list_setting);
		menu.add(0, AppConstant.EXIT, 2, R.string.mp3list_exit);
		return super.onCreateOptionsMenu(menu);
	}
	/**
	 * 用户点击menu按钮后调用的方法
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.SETTING) {
		} else if (item.getItemId() == AppConstant.EXIT) {
		}
		return super.onOptionsItemSelected(item);
	}
}
