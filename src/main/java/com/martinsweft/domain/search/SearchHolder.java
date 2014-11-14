package com.martinsweft.domain.search;

import java.util.List;
import java.util.Map;


public class SearchHolder {

	
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	private Map<String, Object> searchTerms;

	public Map<String, Object> getSearchTerms() {
		return searchTerms;
	}

	public void setSearchTerms(Map<String, Object> searchTerms) {
		this.searchTerms = searchTerms;
	}

	private Map<String, List<Searchable>> results;
	

	public Map<String, List<Searchable>> getResults() {
		return results;
	}

	public void setResults(Map<String, List<Searchable>> results) {
		this.results = results;
	}
	
	public List<Searchable> getResultByType(String key)
	{
		return results.get(key);
	}

	/**
	 * Used for paging. Indicates the current page
	 */
	private Float currentPage = Float.valueOf(0);
	
	/**
	 * The maximum number of results to display on a page
	 */
	private Float resultsPerPage;
	

	/**
	 * The total number of results in the propertyAddressIds expressed as a 
	 * int so the jsp can call this method for display
	 */
	private Float totalResults = Float.valueOf(0);
	
	/**
	 * Total number of pages 
	 */
	private Integer totalPages;
	

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("maxNumberOfResults:"+resultsPerPage);
		sb.append("currentPage:"+currentPage);
		sb.append("total results:"+totalResults);
		if (null !=results) sb.append("results:"+results.size());
		return sb.toString();
	}

	public Float getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Float currentPage) {
		this.currentPage = currentPage;
	}

	public Float getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Float totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getTotalPages() {
		return (int)Math.ceil(getTotalResults() / getResultsPerPage());
		
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Float getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(Float resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	
}
