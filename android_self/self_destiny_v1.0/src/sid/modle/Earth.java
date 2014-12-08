package sid.modle;

public class Earth {
	private int id;
	private String name;
	private String zodiac;
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
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public Earth(int id, String name, String zodiac) {
		super();
		this.id = id;
		this.name = name;
		this.zodiac = zodiac;
	}
}
