package sid.modle;

public class LingQian {
	private int id;
	private String jixiong;
	private String gongwei;
	private String shi; 
	private String jieyue;
	private String jieqian;
	private String diangu;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJixiong() {
		return jixiong;
	}
	public void setJixiong(String jixiong) {
		this.jixiong = jixiong;
	}
	public String getGongwei() {
		return gongwei;
	}
	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getJieyue() {
		return jieyue;
	}
	public void setJieyue(String jieyue) {
		this.jieyue = jieyue;
	}
	public String getJieqian() {
		return jieqian;
	}
	public void setJieqian(String jieqian) {
		this.jieqian = jieqian;
	}
	public String getDiangu() {
		return diangu;
	}
	public void setDiangu(String diangu) {
		this.diangu = diangu;
	}
	public LingQian(int id, String jixiong, String gongwei, String shi,
			String jieyue, String jieqian, String diangu) {
		super();
		this.id = id;
		this.jixiong = jixiong;
		this.gongwei = gongwei;
		this.shi = shi;
		this.jieyue = jieyue;
		this.jieqian = jieqian;
		this.diangu = diangu;
	}
	public LingQian() {
	}
}
