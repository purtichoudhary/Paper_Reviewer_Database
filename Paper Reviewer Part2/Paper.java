
public class Paper {
	private final String title;
	private final String abs;
	private final String authorId;
	private final String fileName;
	public Paper(String title, String abs, String fileName, String authorId) {
		super();
		this.title = title;
		this.abs = abs;
		this.authorId = authorId;
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public String getAbs() {
		return abs;
	}
	public String getAuthorId() {
		return authorId;
	}
	public String getFileName() {
		return fileName;
	}
	
}
