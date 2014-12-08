package sid.lover.period;

import java.util.Calendar;

import sid.lover.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 日期控件绘制类
 * 
 * @Description: 日期控件绘制类
 * @Version V1.0
 */
public class CalendarView extends ImageView {
	private static int CELL_WIDTH = 58;
	private static int CELL_HEIGH = 53;
	private static int CELL_MARGIN_TOP = 0;
	private static int CELL_MARGIN_LEFT = 39;
	private static float CELL_TEXT_SIZE;
	private static int periodDay = 0;
	private static int periodDays = 0;
	public static final int CURRENT_MOUNT = 0;
	public static final int NEXT_MOUNT = 1;
	public static final int PREVIOUS_MOUNT = -1;
	private Calendar mRightNow = null;
	private Cell mToday = null;
	private Cell[][] mCells = new Cell[6][7];
	private OnCellTouchListener mOnCellTouchListener = null;
	MonthDisplayHelper mHelper;
	Drawable mDecoration = null;
	Drawable mDecoraClick = null;
	/** 获取屏幕的宽度 */
	private Integer windowWidth = 420;
	/** 获取屏幕的高度 */
	private Integer windowHeight = 800;
	private Context context;

	public interface OnCellTouchListener {
		public void onTouch(Cell cell);
	}

	public CalendarView(Context context) {
		this(context, null);
	}

	public CalendarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		mDecoration = context.getResources().getDrawable(
				R.drawable.typeb_calendar_today);
		mDecoraClick = context.getResources().getDrawable(
				R.drawable.typeb_calendar_today);
		initCalendarView();
	}

	/**
	 * 获取屏幕宽高
	 */
	private void getWH() {
		// 获取屏幕宽高
		windowHeight = this.getContext().getResources().getDisplayMetrics().heightPixels;
		windowWidth = this.getContext().getResources().getDisplayMetrics().widthPixels;
	}

	private void initCalendarView() {
		mRightNow = Calendar.getInstance();
//		Resources res = getResources();

		getWH();
		System.out.println("--windowWidth---"+windowWidth+"---windowHeight--"+windowHeight);
		CELL_WIDTH = (int) (windowWidth/7);
//		CELL_HEIGH = (int) (windowHeight/13.5);
		CELL_HEIGH = (int) (windowWidth/8);
		CELL_MARGIN_TOP = (int) ((windowWidth*3)/8);
		CELL_MARGIN_LEFT = 0;


		CELL_TEXT_SIZE = 26f;
		setImageResource(R.drawable.background);

		mHelper = new MonthDisplayHelper(mRightNow.get(Calendar.YEAR),
				mRightNow.get(Calendar.MONTH), mRightNow.getFirstDayOfWeek());
	}

	private void initCells() {
		class _calendar {
			public int day;
			public int whichMonth; // -1 为上月 1为下月 0为此月

			public _calendar(int d, int b) {
				day = d;
				whichMonth = b;
			}

			public _calendar(int d) { // 上个月 默认为
				this(d, PREVIOUS_MOUNT);
			}
		}
		;
		_calendar tmp[][] = new _calendar[6][7];

		for (int i = 0; i < tmp.length; i++) {
			int n[] = mHelper.getDigitsForRow(i);
			for (int d = 0; d < n.length; d++) {
				if (mHelper.isWithinCurrentMonth(i, d)) {
					tmp[i][d] = new _calendar(n[d], CURRENT_MOUNT);
				} else if (i == 0) {
					tmp[i][d] = new _calendar(n[d]);
				} else {
					tmp[i][d] = new _calendar(n[d], NEXT_MOUNT);
				}
			}
		}

		Calendar today = Calendar.getInstance();
		int thisDay = 0;
		mToday = null;
		if (mHelper.getYear() == today.get(Calendar.YEAR)
				&& mHelper.getMonth() == today.get(Calendar.MONTH)) {
			thisDay = today.get(Calendar.DAY_OF_MONTH);
		}
		// build cells
		Rect Bound = new Rect(CELL_MARGIN_LEFT, CELL_MARGIN_TOP, CELL_WIDTH
				+ CELL_MARGIN_LEFT, CELL_HEIGH + CELL_MARGIN_TOP);
		for (int week = 0; week < mCells.length; week++) {
			for (int day = 0; day < mCells[week].length; day++) {
				if (tmp[week][day].whichMonth == CURRENT_MOUNT) {
					// 此月 开始设置cell
					if (periodDay == 0) {
						mCells[week][day] = new Cell(tmp[week][day].day,
								new Rect(Bound), CELL_TEXT_SIZE);
					} else {
						int perviousD = periodDay - 19;
						int perviousS = periodDay - 9;
						int perviousC = periodDay - 3;
						int nextD = perviousD + periodDays;
						int nextS = perviousS + periodDays;
						int nextC = perviousC + periodDays;
						int nextP = periodDay + periodDays;
						if (tmp[week][day].day >= (nextP + 4)) {
							mCells[week][day] = new GreenCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if ((nextP + 4) >= tmp[week][day].day
								&& tmp[week][day].day >= nextP) {
							mCells[week][day] = new YellowCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= nextC) {
							mCells[week][day] = new BlueCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= nextS) {
							mCells[week][day] = new GreenCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= nextD) {
							mCells[week][day] = new RedCell(tmp[week][day].day,
									new Rect(Bound), CELL_TEXT_SIZE);
						} else if ((periodDay + 4) >= tmp[week][day].day
								&& tmp[week][day].day >= periodDay) {
							mCells[week][day] = new YellowCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= (periodDay + 4)) {
							mCells[week][day] = new GreenCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= perviousC) {
							mCells[week][day] = new BlueCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= perviousS) {
							mCells[week][day] = new GreenCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						} else if (tmp[week][day].day >= perviousD) {
							mCells[week][day] = new RedCell(tmp[week][day].day,
									new Rect(Bound), CELL_TEXT_SIZE);
						} else {
							mCells[week][day] = new GreenCell(
									tmp[week][day].day, new Rect(Bound),
									CELL_TEXT_SIZE);
						}
					}
				} else if (tmp[week][day].whichMonth == PREVIOUS_MOUNT) {
					// 上月为gray
					mCells[week][day] = new PreviousMonthCell(
							tmp[week][day].day, new Rect(Bound), CELL_TEXT_SIZE);
				} else {
					// 下月为LTGray
					mCells[week][day] = new NextMonthCell(tmp[week][day].day,
							new Rect(Bound), CELL_TEXT_SIZE);
				}

				Bound.offset(CELL_WIDTH, 0); // move to next column

				// get today
				if (tmp[week][day].day == thisDay
						&& tmp[week][day].whichMonth == 0) {
					mToday = mCells[week][day];
					mDecoration.setBounds(mToday.getBound());
				}
			}
			Bound.offset(0, CELL_HEIGH); // move to next row and first column
			Bound.left = CELL_MARGIN_LEFT;
			Bound.right = CELL_MARGIN_LEFT + CELL_WIDTH;
		}
	}

	@Override
	public void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
//		Rect re = getDrawable().getBounds();
		getDrawable().getBounds();
		initCells();
		super.onLayout(changed, left, top, right, 0);
	}

	public void setTimeInMillis(long milliseconds) {
		mRightNow.setTimeInMillis(milliseconds);
		initCells();
		this.invalidate();
	}

	public int getYear() {
		return mHelper.getYear();
	}

	public int getMonth() {
		return mHelper.getMonth();
	}

	public void nextMonth() {
		mHelper.nextMonth();
		initCells();
		invalidate();
	}

	public void previousMonth() {
		mHelper.previousMonth();
		initCells();
		invalidate();
	}

	public void periodDays(int day, int days) {
		periodDay = day;
		periodDays = days;
		initCells();
		invalidate();
	}

	public void goToday() {
		Calendar cal = Calendar.getInstance();
		mHelper = new MonthDisplayHelper(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH));
		initCells();
		invalidate();
	}

	public Calendar getDate() {
		return mRightNow;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mOnCellTouchListener != null) {
			for (Cell[] week : mCells) {
				for (Cell day : week) {
					if (day.hitTest((int) event.getX(), (int) event.getY())) {
						mOnCellTouchListener.onTouch(day);
					}
				}
			}
		}
		return super.onTouchEvent(event);
	}

	public void setOnCellTouchListener(OnCellTouchListener p) {
		mOnCellTouchListener = p;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// draw background
		super.onDraw(canvas);

		// draw cells
		for (Cell[] week : mCells) {
			for (Cell day : week) {
				day.draw(canvas);
			}
		}

		// draw today
		if (mDecoration != null && mToday != null) {
			mDecoration.draw(canvas);
		}
		if (mDecoraClick.getBounds() != null) {
			mDecoraClick.draw(canvas);
			// 设置这里过后 要想办法
			mDecoraClick = context.getResources().getDrawable(
					R.drawable.typeb_calendar_today);
		}
	}

	private class PreviousMonthCell extends Cell {
		public PreviousMonthCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.GRAY);
		}
	}

	private class NextMonthCell extends Cell {
		public NextMonthCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.LTGRAY);
		}
	}

	private class YellowCell extends Cell {
		public YellowCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.YELLOW);
		}
	}

	private class RedCell extends Cell {
		public RedCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.RED);
		}
	}

	private class GreenCell extends Cell {
		public GreenCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.GREEN);
		}
	}

	private class BlueCell extends Cell {
		public BlueCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.rgb(117, 210, 240));
		}
	}
}