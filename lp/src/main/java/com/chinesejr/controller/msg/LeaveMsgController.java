package com.chinesejr.controller.msg;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.model.msg.LeaveMsgModel;
import com.chinesejr.service.msg.LeaveMsgService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/msg")
public class LeaveMsgController {
	private static final String FOLDER = "modules/leaveMsg/";
	private static final JSONUtils<LeaveMsgModel> json = new JSONUtils<LeaveMsgModel>();
	
	@Autowired
	private LeaveMsgService service;
	
	@RequestMapping
	public ModelAndView listPage() {
		ModelAndView result = new ModelAndView(FOLDER + "main");
        return result;
    }
	
	
	@RequestMapping("/query")
	public String query(LeaveMsgModel model) {
		return queryData(model);
	}
	
	private String queryData(LeaveMsgModel model) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		
		try {
			List<LeaveMsgModel> modelList = service.query(model);
			result = json.buildJsonPageResult(modelList, model, true, CodeUtils.SUCCESS, "查询成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonPageResult(null, model, false, CodeUtils.ERROR, "查询失败！");
		}
		
		return result.toString();
	}

	@RequestMapping("/queryMap")
	public String queryMap(@RequestParam String sort, @RequestParam String order, @RequestParam Integer offset, @RequestParam Integer limit) {
		LeaveMsgModel model = new LeaveMsgModel();
		model.setSort(sort);
		model.setOffset(offset);
		model.setOrder(order);
		model.setLimit(limit);
		
		return queryMapData(model);
	}
	
	private String queryMapData(LeaveMsgModel model) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		JSONUtils<Map<String, String>> j = new JSONUtils<Map<String, String>>();
		try {
			List<Map<String, String>> modelList = service.queryMap(model);
			result = j.buildJsonPageResult(modelList, null, true, CodeUtils.SUCCESS, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = j.buildJsonPageResult(null, null, false, CodeUtils.ERROR, "");
		}
		
		return result.toString();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(LeaveMsgModel model) {
        return saveData(model);
    }
	
	@RequestMapping(value = "/api/save", method = RequestMethod.POST)
    public String saveApi(LeaveMsgModel model) {
        return saveData(model);
    }
	
	@RequestMapping(value = "/api/saveMap", method = RequestMethod.POST)
	public void saveMapApi(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, String> model = new HashMap<String, String>();
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			model.put(name, request.getParameter(name));
		}
		System.out.println(model);
		try {
			service.saveMap(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}
//		return saveData(model);
	}


	private String saveData(LeaveMsgModel model) {
		JSONObject result = new JSONObject();
        String msg = model.getId() == null ? "保存成功！" : "更新成功！";
        try {
			int i = service.save(model);
			result = json.buildJsonModelResult(model, true, CodeUtils.SUCCESS, msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.i(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "保存失败，请与系统管理员联系！");
		}
        return result.toString();
	}
	
	@RequestMapping("/delete")
	public String batchDeleteByIds(String ids) {
		JSONObject result = new JSONObject();
		try {
			Integer count = service.batchDeleteByIds(ids);
			result = json.buildJsonModelResult(null, true, CodeUtils.SUCCESS, "删除成功，共删除" + count + "条数据！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "删除失败，出现未知错误！");
		}
		return result.toString();
	}
	
	@RequestMapping("/deleteMap")
	public String batchDeleteMapByIds(@RequestParam String ids) {
		JSONObject result = new JSONObject();
		try {
			Integer count = service.batchDeleteMapByIds(ids);
			result = json.buildJsonModelResult(null, true, CodeUtils.SUCCESS, "删除成功，共删除" + count + "条数据！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "删除失败，出现未知错误！");
		}
		return result.toString();
	}
	
	
	@RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			int count = service.deleteById(id);
			result = json.buildJsonModelResult(null, true, CodeUtils.SUCCESS, "成功删除"+count+"条数据！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "删除失败，出现未知错误！");
		}
		
        return result.toString();
    }
}
