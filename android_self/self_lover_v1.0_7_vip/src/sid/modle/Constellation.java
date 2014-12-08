package sid.modle;

public class Constellation {
	private long id;
	private String name;
	private int start;
	private int end;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Constellation(long id, String name, int start, int end,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.description = description;
	}
	public Constellation() {
		super();
	}
	@Override
	public String toString() {
		return "Constellation [id=" + id + ", name=" + name + ", start="
				+ start + ", end=" + end + ", description=" + description + "]";
	}
}
