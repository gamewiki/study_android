package sid.modle;

public class Sky {
	private int id;
	private String name;
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
	public Sky(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
