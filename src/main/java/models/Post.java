package models;

public class Post {
	
	private String title;
	private String content;
	
	public Post(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public Post() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + "]";
	}
	
	
	
}
