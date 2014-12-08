package sid.modle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CGUserList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<PersonDestiny> listPerson = new ArrayList<PersonDestiny>();
	private List<String> listPersonDes = new ArrayList<String>();
	public List<PersonDestiny> getListPerson() {
		return listPerson;
	}
	public void setListPerson(List<PersonDestiny> listPerson) {
		this.listPerson = listPerson;
	}
	public List<String> getListPersonDes() {
		return listPersonDes;
	}
	public void setListPersonDes(List<String> listPersonDes) {
		this.listPersonDes = listPersonDes;
	}
}
