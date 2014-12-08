package sid.modle;

import java.io.Serializable;

public class SendMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int hour;
	private int minute;
	private String moble;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMoble() {
		return moble;
	}
	public void setMoble(String moble) {
		this.moble = moble;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SendMsg(int id, int hour, int minute, String moble, String content) {
		super();
		this.id = id;
		this.hour = hour;
		this.minute = minute;
		this.moble = moble;
		this.content = content;
	}
	public SendMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
}
