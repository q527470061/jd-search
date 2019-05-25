package com.jd.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jd.pojo.JDResult;
import com.jd.search.service.ItemService;

@RequestMapping("/search/item")
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	public JDResult importAllItems() {
		return itemService.importAllItems();
	}

}
