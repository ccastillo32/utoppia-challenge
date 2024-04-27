package co.com.tecso.utoppia.challenge.domain;

import java.util.List;

public class PagedList<T> {

	private List<T> results;
	long totalElements;
	private int totalPages;
	long offset;
	private int limit;
	
	public PagedList(List<T> results, long totalElements, int totalPages, long offset, int limit) {
		this.results = results;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.offset = offset;
		this.limit = limit;
	}

	public List<T> getResults() {
		return results;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public long getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}
	
}
