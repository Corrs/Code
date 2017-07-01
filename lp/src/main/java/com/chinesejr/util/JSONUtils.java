package com.chinesejr.util;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtils<T> {
	public JSONObject buildJsonPageResult(List<T> list, T model, boolean success, String code, String msg) {
		JSONObject obj = new JSONObject();
		
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		
		if(list == null) {
			obj.element("rows", new JSONArray());
		} else {
			obj.element("rows", pageInfo.getList());
		}
		
		obj.element("queryParam", model);
		obj.element("total", pageInfo.getTotal());
		obj.element("success", success);
		obj.element("code", code);
		obj.element("msg", msg);
		
		return obj;
	}
	
	
	public JSONObject buildJsonModelResult(T model, boolean success, String code, String msg) {
		JSONObject obj = new JSONObject();
		
		obj.element("model", JSONObject.fromObject(model));
		obj.element("msg", msg);
		obj.element("success", success);
		obj.element("code", code);
		
		return obj;
	}
}
