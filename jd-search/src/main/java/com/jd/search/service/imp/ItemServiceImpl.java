package com.jd.search.service.imp;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.pojo.JDResult;
import com.jd.search.mapper.ItemMapper;
import com.jd.search.pojo.Item;
import com.jd.search.service.ItemService;
import com.jd.util.ExceptionUtil;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private SolrClient solrClient;

	@Override
	public JDResult importAllItems() {
		try {

			// 查询商品列表
			List<Item> list = itemMapper.getItemList();
			// 把商品信息写入索引库
			for (Item item : list) {
				// 创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSellPoint());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategoryName());
				document.setField("item_desc", item.getItem_des());
				// 写入索引库
				solrClient.add(document);
			}
			// 提交修改
			solrClient.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return JDResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return JDResult.ok();
	}

}
