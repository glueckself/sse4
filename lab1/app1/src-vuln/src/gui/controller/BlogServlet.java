package gui.controller;

import gui.helper.IllegalStringException;
import gui.helper.ScriptTagValidator;
import gui.helper.ValidatorInterface;
import gui.model.PostBean;
import gui.model.SearchResultBean;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.BlogPost;

import service.Blog;

/**
 * Servlet implementation class BlogServlet
 */
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Blog service;
	private ValidatorInterface validator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogServlet() {
		super();
		service = Blog.getInstance();
		validator = new ScriptTagValidator();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		String redirect = "index.jsp";
		HttpSession session = request.getSession();
		try {
			if (path != null && path.startsWith("/post/")) {
				String valid = validator.validate(path.substring(6));

				int id = Integer.parseInt(valid);
				PostBean bean = new PostBean();
				bean.setId(id);
				bean.setAuthor(service.getPost(id).getAuthor());
				bean.setContent(service.getPost(id).getContent());
				session.setAttribute("post", bean);
				redirect = "../../post.jsp";

			} else if (path != null && path.equalsIgnoreCase("/search")) {
				SearchResultBean result = new SearchResultBean();
				LinkedList<PostBean> list = new LinkedList<PostBean>();
				String query = validator.validate(request.getParameter("q"));

				for (BlogPost post : service.searchByAuthor(query)) {
					PostBean postBean = new PostBean();
					postBean.setAuthor(post.getAuthor());
					postBean.setContent(post.getContent());
					postBean.setId(post.getId());
				}
				result.setSearchTerm(query);
				result.setResults(list);
				session.setAttribute("bean", result);
				redirect = "../searchResults.jsp";
			} else if (path != null && path.equalsIgnoreCase("/archive")) {
				SearchResultBean result = new SearchResultBean();
				LinkedList<PostBean> list = new LinkedList<PostBean>();

				for (BlogPost post : service.getPosts()) {
					PostBean postBean = new PostBean();
					postBean.setAuthor(post.getAuthor());
					postBean.setContent(post.getContent());
					postBean.setId(post.getId());
					list.add(postBean);
				}
				result.setResults(list);
				session.setAttribute("bean", result);
				redirect = "../archive.jsp";
			}
			response.sendRedirect(redirect);
		} catch (IllegalStringException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			if (action.equals("/post")) {
				service.addPost(
						validator.validate(request.getParameter("author")),
						validator.validate(request.getParameter("content")));
			}
			response.sendRedirect("../index.jsp");
		} catch (IllegalStringException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
