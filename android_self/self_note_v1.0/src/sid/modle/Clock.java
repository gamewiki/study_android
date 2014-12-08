package sid.modle;

import java.io.Serializable;

public class Clock implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	/** 闹铃日期*/
	private String clockdate;
	/** 闹铃时间*/
	private String clocktime;
	private String createtime;
	private long startime;
	private int hour;
	private int minute;
	/** 闹钟标题*/
	private String title;
	/** 重复日期*/
	private String weeks;
	/** 响铃间隔*/
	private String interval;
	/** 设置铃声*/
	private String bells;
	/** 设置铃声*/
	private String bellsPath;
	/** 振动类型*/
	private String shockCate;
	/** 闹钟类型*/
	private String signCate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClockdate() {
		return clockdate;
	}
	public void setClockdate(String clockdate) {
		this.clockdate = clockdate;
	}
	public String getClocktime() {
		return clocktime;
	}
	public void setClocktime(String clocktime) {
		this.clocktime = clocktime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public long getStartime() {
		return startime;
	}
	public void setStartime(long startime) {
		this.startime = startime;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getBells() {
		return bells;
	}
	public void setBells(String bells) {
		this.bells = bells;
	}
	public String getBellsPath() {
		return bellsPath;
	}
	public void setBellsPath(String bellsPath) {
		this.bellsPath = bellsPath;
	}
	public String getShockCate() {
		return shockCate;
	}
	public void setShockCate(String shockCate) {
		this.shockCate = shockCate;
	}
	public String getSignCate() {
		return signCate;
	}
	public void setSignCate(String signCate) {
		this.signCate = signCate;
	}
	public Clock(int id, String clockdate, String clocktime, String createtime,
			long startime, int hour, int minute, String title, String weeks,
			String interval, String bells, String bellsPath, String shockCate,
			String signCate) {
		super();
		this.id = id;
		this.clockdate = clockdate;
		this.clocktime = clocktime;
		this.createtime = createtime;
		this.startime = startime;
		this.hour = hour;
		this.minute = minute;
		this.title = title;
		this.weeks = weeks;
		this.interval = interval;
		this.bells = bells;
		this.bellsPath = bellsPath;
		this.shockCate = shockCate;
		this.signCate = signCate;
	}
	public Clock() {
		super();
	}
}
