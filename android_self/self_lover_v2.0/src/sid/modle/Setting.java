package sid.modle;

import java.io.Serializable;

public class Setting implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String password = null;
	private String question = null;
	private String answer = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Setting(int id, String password, String question, String answer) {
		super();
		this.id = id;
		this.password = password;
		this.question = question;
		this.answer = answer;
	}
	public Setting() {
		super();
	}
	public boolean isEmpty(){
		if (password == null||question == null||answer==null) {
			return true;
		}
		return false;
	}
}
