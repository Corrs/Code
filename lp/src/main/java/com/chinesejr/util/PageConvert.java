package com.chinesejr.util;

import java.util.ArrayList;
import java.util.List;

import com.chinesejr.model.BaseEntity;

public class PageConvert {
	public static Integer getRows(BaseEntity entity) {
		if(entity.getLimit() != null) {
			entity.setRows(entity.getLimit());
		}
		return entity.getRows();
	}
	
	
	public static Integer getPage(BaseEntity entity) {
		Integer limit = entity.getLimit();
		Integer offset = entity.getOffset();
		if(limit != null && offset != null) {
			entity.setPage(offset/limit+1);
		}
		return entity.getPage();
	}
	
	
	public static String getOrderBy(BaseEntity entity) {
		String sort = entity.getSort();
		String order = entity.getOrder();
		List<String> orderBy = new ArrayList<String>();
		if (!StringUtil.isEmpty(sort)) {
			String[] sorts = sort.split(",");
			
			if(StringUtil.isEmpty(order)) {
				order = "";
			}
			
			String[] orders = order.split(",");
				
			for (int i=0; i < sorts.length; i++) {
				String o = "asc";
				if (i < orders.length) {
					o = orders[i];
				}
				orderBy.add(sorts[i] + " " + o);
			}
			
			entity.setOrderBy(StringUtil.join(orderBy, ","));
		} 
		
		return entity.getOrderBy();
	}
}
