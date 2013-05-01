package gui.model;

import java.util.List;

public class SearchResultBean {
	private List<PostBean> results;
	private String searchTerm;

	public List<PostBean> getResults() {
		return results;
	}

	public void setResults(List<PostBean> results) {
		this.results = results;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
}
