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
	private long storytime;
	private int hour;
	private int minute;
	/** 闹钟标题*/
	private String title;
	private String description;
	/** 重复日期*/
	private String weeks;
	/** 响铃间隔*/
	private String interval;
	/** 闹铃：设置铃声；短信：收信人姓名*/
	private String bells;
	/** 闹钟：设置铃声；短信：电话号码*/
	private String bellsPath;
	/** 图片地址**/
	private String imagePath;
	/** 振动类型*/
	private String shockCate;
	/** 闹钟类型*/
	private int signCate;
	/** 提醒状态*/
	private int status;
	/**创建者*/
	private int creater;
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
	public long getStorytime() {
		return storytime;
	}
	public void setStorytime(long storytime) {
		this.storytime = storytime;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getSignCate() {
		return signCate;
	}
	public void setSignCate(int signCate) {
		this.signCate = signCate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Clock() {
		super();
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public Clock(int id, String clockdate, String clocktime, String createtime,
			long startime, long storytime, int hour, int minute, String title,
			String description, String weeks, String interval, String bells,
			String bellsPath, String imagePath, String shockCate, int signCate,
			int status, int creater) {
		super();
		this.id = id;
		this.clockdate = clockdate;
		this.clocktime = clocktime;
		this.createtime = createtime;
		this.startime = startime;
		this.storytime = storytime;
		this.hour = hour;
		this.minute = minute;
		this.title = title;
		this.description = description;
		this.weeks = weeks;
		this.interval = interval;
		this.bells = bells;
		this.bellsPath = bellsPath;
		this.imagePath = imagePath;
		this.shockCate = shockCate;
		this.signCate = signCate;
		this.status = status;
		this.creater = creater;
	}
	@Override
	public String toString() {
		return "Clock [id=" + id + ", clockdate=" + clockdate + ", clocktime="
				+ clocktime + ", createtime=" + createtime + ", startime="
				+ startime + ", storytime=" + storytime + ", hour=" + hour
				+ ", minute=" + minute + ", title=" + title + ", description="
				+ description + ", weeks=" + weeks + ", interval=" + interval
				+ ", bells=" + bells + ", bellsPath=" + bellsPath
				+ ", imagePath=" + imagePath + ", shockCate=" + shockCate
				+ ", signCate=" + signCate + ", status=" + status
				+ ", creater=" + creater + "]";
	}
}
