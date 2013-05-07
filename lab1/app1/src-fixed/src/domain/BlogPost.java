package domain;

import java.util.ArrayList;
import java.util.List;

public class BlogPost {
	private final int id;
	private final String author;
	private String content;
	private List<Comment> comments = new ArrayList<Comment>();
	
	public BlogPost(int id, String author, String content) {
		this.author=author;
		this.content=content;
		this.id=id;
	}
	
	public BlogPost(int id,String author){
		this(id,author,"");
	}

	public String getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	public int getId(){
		return this.id;
	}
}
