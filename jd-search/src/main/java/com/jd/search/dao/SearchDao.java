package com.jd.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.jd.search.pojo.SearchResult;


public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
