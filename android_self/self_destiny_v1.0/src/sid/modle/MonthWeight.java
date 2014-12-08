package sid.modle;

public class MonthWeight {
	private int id;
	private String name;
	private int weight;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public MonthWeight(int id, String name, int weight) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
	} 
}
