package sid.modle;

import java.io.Serializable;


public class PersonDestiny implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int year;
	private int month;
	private int day;
	private int hour;
	private String username;
	private String usersex;
	private String calendar;
	private String userbirth;
	private String usereight;
	private int weightId;
	private String weight;
	private String name;
	private String destiny;
	private String description;
	public int getWeightId() {
		return weightId;
	}
	public void setWeightId(int weightId) {
		this.weightId = weightId;
	}
	public String getUserbirth() {
		return userbirth;
	}
	public void setUserbirth(String userbirth) {
		this.userbirth = userbirth;
	}
	public String getUsereight() {
		return usereight;
	}
	public void setUsereight(String usereight) {
		this.usereight = usereight;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsersex() {
		return usersex;
	}
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	public String getCalendar() {
		return calendar;
	}
	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDestiny() {
		return destiny;
	}
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PersonDestiny(int id,String weight, String name, String destiny,
			String description) {
		super();
		this.id = id;
		this.year = 1900;
		this.month = 1;
		this.day = 1;
		this.hour = 1;
		this.username = "";
		this.usersex = "";
		this.calendar= "农历/阴历";
		this.userbirth = "";
		this.usereight = "";
		this.weightId = 21;
		this.weight = weight;
		this.name = name;
		this.destiny = destiny;
		this.description = description;
	}
	public PersonDestiny() {
	}
}
