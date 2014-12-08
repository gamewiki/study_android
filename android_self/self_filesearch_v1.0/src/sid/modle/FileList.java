package sid.modle;

public class FileList {
	private String filename;
	private String filepath;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public FileList(String filename, String filepath) {
		super();
		this.filename = filename;
		this.filepath = filepath;
	}
}
