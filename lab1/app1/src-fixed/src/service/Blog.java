package service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import domain.BlogPost;
import domain.Comment;

public class Blog {
	private static Blog instance = null;
	private List<BlogPost> posts = new ArrayList<BlogPost>();
	
	private Blog(){
		this.addPost("Tomcat", "Hello World");
	};
	
	public static Blog getInstance(){
		if(instance==null)
			instance=new Blog();
		return instance;
	}
	
	public BlogPost addPost(String author, String content){
		BlogPost post = new BlogPost(posts.size(),author,content);
		posts.add(post);
		return post;
	}
	
	public List<BlogPost> getPosts(){
		return posts;
	}
	
	public BlogPost getPost(int id) {
		return posts.get(id);
	}
	
	public BlogPost getLastPost() {
		return this.getPost(posts.size()-1);
	}
	
	public Comment createComment(String author,String text) {
		Comment c = new Comment();
		c.setAuthor(author);
		c.setText(text);
		return c;
	}
	
	public void addCommentToPost(Comment comment,int id) {
		this.addCommentToPost(comment, posts.get(id));
	}
	
	public void addCommentToPost(Comment comment, BlogPost post) {
		post.addComment(comment);
	}
	
	public List<BlogPost> searchByAuthor(String author) {
		List<BlogPost> erg = new LinkedList<BlogPost>();
		for (BlogPost post : posts) {
			if(post.getAuthor().equalsIgnoreCase(author)){
				erg.add(post);
			}
		}
		return erg;
	}
}
