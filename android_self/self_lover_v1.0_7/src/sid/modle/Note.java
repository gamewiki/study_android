package sid.modle;

import java.io.Serializable;

public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String createtime;
	private int color;
	private int type;
	private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Note(long id, String createtime, int color, int type, String content) {
		super();
		this.id = id;
		this.createtime = createtime;
		this.color = color;
		this.type = type;
		this.content = content;
	}
	public Note() {
		super();
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", createtime=" + createtime + ", color="
				+ color + ", type=" + type + ", content=" + content + "]";
	}
}
