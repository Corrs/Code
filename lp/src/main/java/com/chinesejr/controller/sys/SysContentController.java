package com.chinesejr.controller.sys;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.model.sys.SysContentModel;
import com.chinesejr.service.sys.SysContentService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 菜单相关控制器类
 * 
 * @author BobyCo
 * @since 2017-06-09 23:23
 * 
 */
@RestController
@RequestMapping("/sys/content")
public class SysContentController {
	private static final String FOLDER = "modules/sys/content/";
	private static final JSONUtils<SysContentModel> json = new JSONUtils<SysContentModel>();
	private static final String[] pnPrefix = new String[] { "134", "135", "136", "137", "138", "139", "150", "151",
			"152", "158", "159", "157", "182", "187", "188", "147", "130", "131", "132", "155", "156", "185", "186",
			"133", "153", "180", "189" };

	@Autowired
	private SysContentService service;

	@RequestMapping
	public ModelAndView listPage() {
		ModelAndView result = new ModelAndView(FOLDER + "main");
		return result;
	}

	@RequestMapping("/append")
	public ModelAndView appendPage(HttpServletRequest request) {
		ModelAndView result = new ModelAndView(FOLDER + "append");
		return result;
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView view(@PathVariable Integer id, HttpServletRequest request) {
		ModelAndView result = new ModelAndView(FOLDER + "edit");
		try {
			SysContentModel model = service.getById(id);

			request.setAttribute("model", model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}

		return result;
	}

	@RequestMapping("/query")
	public String query(SysContentModel model) {
		return queryData(model);
	}

	@RequestMapping("/api/query")
	public String queryApi(SysContentModel model) {
		model.setProp4(randomPhoneNum());
		return queryData(model);
	}

	public String randomPhoneNum() {
		JSONArray result = new JSONArray();
		List<String> prefix = Arrays.asList(pnPrefix);
		int i = 20;
		while(i > 0) {
			Collections.shuffle(prefix); 
			result.add(prefix.get(0) + "****" + getPnSuffix(4));
			i--;
		}

		return result.toString();
	}
	
	private String getPnSuffix(int i) {
		String r = ""; 
		for(; i>0; i--) {
			r += (int)(Math.random()*10);
		}
		
		return r;
	}

	private String queryData(SysContentModel model) {
		JSONObject result = new JSONObject();

		try {
			List<SysContentModel> modelList = service.query(model);
			result = json.buildJsonPageResult(modelList, model, true, CodeUtils.SUCCESS, "查询成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonPageResult(null, model, false, CodeUtils.ERROR, "查询失败！");
		}

		return result.toString();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysContentModel model) {
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
		JSONObject result;
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

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id, HttpServletRequest request) {
		JSONObject result;
		try {
			int count = service.deleteById(id);
			result = json.buildJsonModelResult(null, true, CodeUtils.SUCCESS, "成功删除" + count + "条数据！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "删除失败，出现未知错误！");
		}

		return result.toString();
	}

}
