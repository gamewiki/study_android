package sid.modle;

import java.io.Serializable;


public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int constellation;
	private int type;
	private int year;
	private int month;
	private int day;
	private String birthday;
	private String phone;
	private String description;
	/** 例假周期*/
	private int periodDays = 0;
	/** 例假日期：年*/
	private int periodYear = 0;
	/** 例假日期：月*/
	private int periodMonth = 0;
	/** 例假日期：日*/
	private int periodDay = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getConstellation() {
		return constellation;
	}
	public void setConstellation(int constellation) {
		this.constellation = constellation;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPeriodDays() {
		return periodDays;
	}
	public void setPeriodDays(int periodDays) {
		this.periodDays = periodDays;
	}
	public int getPeriodDay() {
		return periodDay;
	}
	public void setPeriodDay(int periodDay) {
		this.periodDay = periodDay;
	}
	public int getPeriodYear() {
		return periodYear;
	}
	public void setPeriodYear(int periodYear) {
		this.periodYear = periodYear;
	}
	public int getPeriodMonth() {
		return periodMonth;
	}
	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}
	public boolean isPartial() {
		if (name == null||name.trim().equals("")||
		birthday == null||birthday.trim().equals("")||
		phone == null||phone.trim().equals("")||
		description == null||description.trim().equals("")){
			return true;
		}
		return false;
	}
	public boolean isSetPeriod() {
		if (periodDays == 0||periodYear == 0||periodMonth == 0||periodDay == 0){
			return false;
		}
		return true;
	}
	public Person(int id, String name, int constellation, int type, int year,
			int month, int day, String birthday, String phone,
			String description, int periodDays, int periodYear,
			int periodMonth, int periodDay) {
		super();
		this.id = id;
		this.name = name;
		this.constellation = constellation;
		this.type = type;
		this.year = year;
		this.month = month;
		this.day = day;
		this.birthday = birthday;
		this.phone = phone;
		this.description = description;
		this.periodDays = periodDays;
		this.periodYear = periodYear;
		this.periodMonth = periodMonth;
		this.periodDay = periodDay;
	}
	public Person() {
		super();
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", constellation="
				+ constellation + ", type=" + type + ", year=" + year
				+ ", month=" + month + ", day=" + day + ", birthday="
				+ birthday + ", phone=" + phone + ", description="
				+ description + ", periodDays=" + periodDays + ", periodYear="
				+ periodYear + ", periodMonth=" + periodMonth + ", periodDay="
				+ periodDay + "]";
	}
}
