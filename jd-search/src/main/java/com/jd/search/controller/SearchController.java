package com.jd.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jd.pojo.JDResult;
import com.jd.search.pojo.SearchResult;
import com.jd.search.service.SearchService;
import com.jd.util.ExceptionUtil;

/**
 * 商品查询Controller
 */
@RequestMapping("/search/item")
@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/query")
	public JDResult search(@RequestParam("q")String queryString, 
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows) {
		//查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return JDResult.build(400, "查询条件不能为空");
		}
		SearchResult searchResult = null;
		try {
			/*queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");*/
			searchResult = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return JDResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return JDResult.ok(searchResult);
		
	}
	
}
