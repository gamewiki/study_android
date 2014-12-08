package sid.lover.period;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sid.lover.R;
import sid.modle.Note;
import sid.service.NoteOperation;
import sid.utils.AboutActivity;
import sid.utils.AppConstant;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PeriodActivity extends Activity{
	private ImageButton enterCalendar;
	private ImageButton periodThings;
	private TextView periodDaysDes;
	private TextView coldDaysDes;
	private TextView dangerDaysDes;
	private TextView safeDaysDes;
	private List<Note> listNotes = new ArrayList<Note>();
	private List<String> listNotesDes = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lover_period);

		enterCalendar = (ImageButton)findViewById(R.id.enterCalendar);
		enterCalendar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(null, CalendarActivity.MIME_TYPE));
			}
		});
		periodThings = (ImageButton)findViewById(R.id.periodThings);
		periodThings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				NoteOperation.getNotes(PeriodActivity.this, AppConstant.NOTE_PERIOD, listNotesDes, listNotes);
				if (listNotes.size()>0) {
					Bundle mBundle = new Bundle();
					mBundle.putSerializable(AppConstant.NOTE, listNotes.get(0));
					intent.putExtras(mBundle);
				}
				intent.setClass(PeriodActivity.this, PeriodThingsActivity.class);
				startActivity(intent);
//				startActivityForResult(new Intent(Intent.ACTION_PICK).setDataAndType(null, CalendarActivity.MIME_TYPE), 100);
			}
		});

		periodDaysDes = (TextView) findViewById(R.id.periodDaysDes);
		periodDaysDes.setTextColor(Color.YELLOW);
		coldDaysDes = (TextView) findViewById(R.id.coldDaysDes);
		coldDaysDes.setTextColor(Color.rgb(117, 210, 240));
		dangerDaysDes = (TextView) findViewById(R.id.dangerDaysDes);
		dangerDaysDes.setTextColor(Color.RED);
		safeDaysDes = (TextView) findViewById(R.id.safeDaysDes);
		safeDaysDes.setTextColor(Color.GREEN);
		
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_OK) {
			int year = data.getIntExtra("year", 0);
			int month = data.getIntExtra("month", 0);
			int day = data.getIntExtra("day", 0);
			final Calendar dat = Calendar.getInstance();
			dat.set(Calendar.YEAR, year);
			dat.set(Calendar.MONTH, month);
			dat.set(Calendar.DAY_OF_MONTH, day);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd");
			Toast.makeText(PeriodActivity.this, format.format(dat.getTime()), Toast.LENGTH_LONG).show();
					
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, AppConstant.ABOUT, 1, R.string.about).setIcon(
				R.drawable.about);
		menu.add(0, AppConstant.EXIT, 2, R.string.exit)
				.setIcon(R.drawable.exit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.ABOUT) {
			// 启动新的activity用于显示关于信息
			Intent intent = new Intent();
			intent.setClass(PeriodActivity.this, AboutActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == AppConstant.EXIT) {
			// 执行循环退出
			ExitApplication.getInstance().exit();
		}
		return super.onOptionsItemSelected(item);
	}

//	/**
//	 * 自定义日历一
//	 */
//	private void initDate() {
//		Date date = new Date();
//		DateTime dateTime = new DateTime(this, date.getYear() + 1900,
//				date.getMonth() + 1, date.getDate());
//		dateTime.setWidthHeightTextSize(40, 40, 12); // 设置单元格宽度、高度和字体大小
//		dateTime.setWeekTitle(new String[] { "日", "一", "二", "三", "四", "五", "六" }); // 设置星期的样式
////		dateTime.setBackgroundResource(R.drawable.day);
////		dateTime.setPre_Next_BackGround(R.drawable.pre_month,R.drawable.next_month); // 设置上月、下月的背景图
////		dateTime.setNowDateBackGround(R.drawable.center);
//		dateTime.initDate(Color.BLACK, Color.DKGRAY, Color.BLACK, Color.GRAY); // 设置标题、星期、日期、选中日期的颜色
//		dateTime.setCallBack(new DateCallBack() {
//			@Override
//			public void execute(View v, String year, String month, String day) {
//				// callBack.execute(year,month,day);
//			}
//		});
//		setContentView(dateTime);
//	}
}
