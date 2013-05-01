package gui.model;

import domain.BlogPost;
import service.Blog;

public class IndexBean {

    private Blog service;

	/**
     * Default constructor. 
     */
    public IndexBean() {
    	this.service = Blog.getInstance();
    }
    
    public BlogPost getPost(){
    	return service.getLastPost();
    }
}
