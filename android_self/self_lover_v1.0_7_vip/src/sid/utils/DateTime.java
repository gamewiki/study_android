package sid.utils;


import java.util.Calendar;
import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DateTime extends ScrollView {

	TableLayout table;
	TextView showDateTxt;
	TextView btnpre;
	TextView btnnext;
	TextView nowDate;
	int year;
	int month; // 0,1,..,,11

	int srcday; // 初始日
	int srcyear; // 初始年
	int srcmonth; // 初始月

	String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	View preSelectedView = null; // 前一个选中的日期

	Context ct;
	DateCallBack callBack;
	int weekTitleColor = 0xFFFF0000;
	int dayColor = 0xFFFF0000;
	int titleColor = Color.BLACK;
	int selectedColor = Color.TRANSPARENT;
	boolean init = false; // 初始化标志
	int colWidth = 30; // 单元格宽度
	int rowHeight = 0; // 单元格高度
	int textSize = 12;
	LinearLayout dayLayOut;

	public DateTime(Context context, final int year1, final int month1, int day1) {
		super(context);
		ct = context;
		this.srcmonth = month1;
		this.srcyear = year1;
		setLayoutParams(new LayoutParams(ScrollView.LayoutParams.FILL_PARENT,
				ScrollView.LayoutParams.FILL_PARENT));

		LinearLayout mainlayout = new LinearLayout(ct);
		addView(mainlayout);

		mainlayout.setOrientation(LinearLayout.VERTICAL);
		mainlayout.setLayoutParams(new LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));

		LinearLayout titleLayOut = new LinearLayout(ct);
		titleLayOut.setOrientation(LinearLayout.HORIZONTAL);
		titleLayOut.setGravity(Gravity.CENTER_HORIZONTAL);
		mainlayout.addView(titleLayOut);

		table = new TableLayout(ct);
		mainlayout.addView(table);

		showDateTxt = new TextView(ct);
		LinearLayout.LayoutParams la = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		la.rightMargin = 20;
		la.leftMargin = 20;
		showDateTxt.setLayoutParams(la);
		showDateTxt.setTextSize(textSize);

		this.year = year1;
		if (month1 < 1 || month1 > 12)
			this.month = 0;
		else
			this.month = month1 - 1;
		if (day1 < 1 || day1 > 31)
			this.srcday = 1;
		else
			this.srcday = day1;

		showDateTxt.setText(String.valueOf(year1) + "年"
				+ String.valueOf(month1));
		showDateTxt.setTextColor(titleColor);

		btnpre = new TextView(ct);
		btnpre.setTextColor(titleColor);
		btnpre.setText("上月");
		btnpre.setTextSize(textSize);

		btnnext = new TextView(ct);
		btnnext.setTextColor(titleColor);
		btnnext.setText("下月");
		btnnext.setTextSize(textSize);

		nowDate = new TextView(ct);
		nowDate.setTextColor(titleColor);
		nowDate.setText("今天");
		nowDate.setTextSize(textSize);
		nowDate.setGravity(Gravity.CENTER_HORIZONTAL);

		dayLayOut = new LinearLayout(ct);
		dayLayOut.setOrientation(LinearLayout.VERTICAL);
		dayLayOut.setGravity(Gravity.CENTER_VERTICAL);
		dayLayOut.addView(showDateTxt);
		dayLayOut.addView(nowDate);

		titleLayOut.addView(btnpre);
		titleLayOut.addView(dayLayOut);
		titleLayOut.addView(btnnext);

		nowDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				year = srcyear;
				month = srcmonth - 1;
				showDateTxt.setText(String.valueOf(year) + "年"
						+ String.valueOf(month + 1) + "月");
				loadDate(1, 1 + 5);
			}
		});

		btnnext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (preSelectedView != null)
					preSelectedView.setBackgroundColor(color.transparent);
				nextMonth();
				showDateTxt.setText(String.valueOf(year) + "年"
						+ String.valueOf(month + 1) + "月");
				loadDate(1, 1 + 5);
			}
		});
		btnpre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				preMonth();
				showDateTxt.setText(String.valueOf(year) + "年"
						+ String.valueOf(month + 1) + "月");
				loadDate(1, 1 + 5);
			}
		});

	}

	public void setPre_Next_BackGround(int preRes, int nextPre) {

		btnpre.setBackgroundResource(preRes);
		btnnext.setBackgroundResource(nextPre);
		btnpre.setText("");
		btnnext.setText("");

	}

	public void setNowDateBackGround(int res) {

		dayLayOut.setBackgroundResource(res);

	}

	/**
	 * 初始化日期
	 * 
	 * @param titleCoclor
	 *            标题颜色
	 * @param weekTitleColor
	 *            星期颜色
	 * @param dayColor
	 *            日期颜色
	 */
	public void initDate(int titleCoclor, int weekTitleColor, int dayColor,
			int selectedColor) {
		if (!init) {
			this.weekTitleColor = weekTitleColor;
			this.dayColor = dayColor;
			this.titleColor = titleCoclor;
			showDateTxt.setTextColor(titleCoclor);
			btnpre.setTextColor(titleCoclor);
			btnnext.setTextColor(titleCoclor);
			this.selectedColor = selectedColor;
			generateDate();
		}

	}

	/**
	 * 初始化日期 颜色默认
	 */
	public void initDate() {
		if (!init) {
			generateDate();
		}

	}

	/**
	 * 以"周日","周一","周二","周三","周四","周五","周六"为顺序
	 * 
	 * @param weekdays
	 */
	public void setWeekTitle(String[] weekdays) {
		if (weekdays != null && weekdays.length > 0 && weekdays.length == 7)
			this.weeks = weekdays;
	}

	public int maxDay() {
		Calendar time = Calendar.getInstance();
		Log.v("", time.toString());
		time.clear();
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month);
		int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);// 本月份的天数
		return day;

	}

	public int nowWeekDay() {
		Calendar time = Calendar.getInstance();
		Log.v("", time.toString());
		time.clear();
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month);
		time.set(Calendar.DATE, 1);
		int weekday = time.get(Calendar.DAY_OF_WEEK);
		if (weekday == 7)
			return 0;
		else
			return weekday - 1;

	}

	public void generateDate() {
		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT);

		TableRow row2 = new TableRow(ct);
		row2.setPadding(0, 2, 0, 0);
		row2.setGravity(Gravity.CENTER_HORIZONTAL);

		for (int i = 0; i < 7; i++) {
			TextView col1 = new TextView(ct);
			col1.setMinWidth(colWidth);
			col1.setMaxWidth(colWidth);
			if (rowHeight > 0)
				col1.setMinHeight(rowHeight);
			col1.setTextColor(weekTitleColor);
			col1.setText(weeks[i]);
			col1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
			col1.setTextSize(textSize);
			row2.addView(col1); // 添加列
		}
		table.addView(row2);

		int weekday = nowWeekDay();
		int maxday = maxDay();
		Log.v("date----", weekday + "-----------" + maxday);
		int count = 0;
		for (int i = 0; i < 5; i++) { // 添加6行
			TableRow row = new TableRow(ct);
			row.setPadding(0, 2, 0, 2);
			row.setGravity(Gravity.CENTER_HORIZONTAL);
			row.setLayoutParams(params);
			// row.setBackgroundColor(0xFF00FF00);
			for (int j = 0; j < 7; j++) { // 添加1列
				TextView col = new TextView(ct);
				col.setTextColor(dayColor);
				col.setBackgroundColor(color.transparent);
				col.setTextSize(textSize);
				if (rowHeight > 0)
					col.setMinHeight(rowHeight);
				if (i == 0) {
					if (weekday <= j) {
						count++;
						col.setText(String.valueOf(count));
						if (srcday == count) {
							col.setBackgroundColor(selectedColor);
							this.preSelectedView = col;
						}
					}
				} else {
					if (count < maxday) {
						count++;
						col.setText(String.valueOf(count));
						if (srcday == count) {
							col.setBackgroundColor(selectedColor);
							this.preSelectedView = col;
						}
					} else {
						count++;
						col.setText("");
					}
				}
				col.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (((TextView) v).getText().toString().length() > 0) {
							if (preSelectedView != null) {
								preSelectedView
										.setBackgroundColor(color.transparent);
							}
							preSelectedView = v;
							v.setBackgroundColor(selectedColor);

							callBack.execute(v, year + "", (month + 1) + "",
									((TextView) v).getText().toString());

						}
					}
				});

				col.setGravity(Gravity.CENTER_HORIZONTAL
						| Gravity.CENTER_VERTICAL);
				row.addView(col); // 添加列
			}
			table.addView(row);// 添加行
		}
	}

	public void nextMonth() {
		if (month == 11) {
			year++;
			month = 0;
		} else {
			month++;
		}
	}

	public void preMonth() {
		if (month == 0) {
			year--;
			month = 11;
		} else {
			month--;
		}
	}

	public void loadDate(int startIndex, int endIndex) {
		if (this.preSelectedView != null)
			this.preSelectedView.setBackgroundColor(color.transparent);
		int weekday = nowWeekDay();
		int maxday = maxDay();
		Log.v("date----", weekday + "-----------" + maxday);
		int count = 0;
		for (int i = startIndex; i < endIndex; i++) {
			TableRow row = (TableRow) table.getChildAt(i);

			for (int j = 0; j < 7; j++) {

				TextView col = (TextView) row.getChildAt(j);
				if (i == startIndex) {

					if (weekday <= j) {
						count++;
						col.setMinWidth(colWidth);
						col.setMaxWidth(colWidth);
						col.setText(String.valueOf(count));
						if (srcday == count) {
							col.setBackgroundColor(selectedColor);
							this.preSelectedView = col;
						}

					} else {
						col.setText("");
					}
				} else {
					if (count < maxday) {
						count++;
						col.setText(String.valueOf(count));
						if (srcday == count) {
							col.setBackgroundColor(selectedColor);
							this.preSelectedView = col;
						}
					} else {
						col.setText("");
					}
				}

			}
		}
	}

	/**
	 * 回调函数
	 * 
	 * @author Acer
	 * 
	 */
	public interface DateCallBack {
		public void execute(View v, String year, String month, String day);

	}

	public void setCallBack(DateCallBack callBack) {
		this.callBack = callBack;
	}

	/**
	 * 设置单元格的宽度，高度
	 * 
	 * @param colWidth
	 *            单元格宽度
	 * @param rowHeight
	 *            单元格高度
	 * @param textSize1
	 *            文字大小
	 */
	public void setWidthHeightTextSize(int colWidth, int rowHeight,
			int textSize1) {
		if (colWidth > 0)
			this.colWidth = colWidth;
		this.rowHeight = rowHeight;
		this.textSize = textSize1;
		btnpre.setTextSize(textSize1);
		btnnext.setTextSize(textSize1);
		showDateTxt.setTextSize(textSize1);
	}

}
