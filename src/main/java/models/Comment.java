package models;

public class Comment {

	private String name;
	private String comment;
	
	public Comment(String name, String comment) {
		super();
		this.name = name;
		this.comment = comment;
	}
	
	public Comment() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
