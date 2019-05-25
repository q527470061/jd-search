package com.jd.search.service;

import com.jd.search.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
