package com.chinesejr.controller.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.model.form.FormModel;
import com.chinesejr.service.form.FormService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/form")
public class FormController {
	private static final String FOLDER = "modules/form/";
	private static final JSONUtils<FormModel> json = new JSONUtils<FormModel>();
	
	
	@Autowired
	private FormService service;
	
	@RequestMapping
	public ModelAndView listPage() {
		ModelAndView result = new ModelAndView(FOLDER + "main");
        return result;
    }
	
	@RequestMapping("/append")
	public ModelAndView appendPage() {
		ModelAndView result = new ModelAndView(FOLDER + "append");
        return result;
    }
	
	
	@RequestMapping(value = "/edit/{id}")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request) {
        ModelAndView result = new ModelAndView(FOLDER + "edit");
		try {
			FormModel model = service.getById(id);
			request.setAttribute("model", model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}
		
        return result;
    }
	
	
	@RequestMapping("/query")
	public String query(FormModel model) {
		return queryData(model);
	}
	
	@RequestMapping("/api/query")
	public String queryApi(FormModel model) {
		return queryData(model);
	}
	
	private String queryData(FormModel model) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		
		try {
			List<FormModel> modelList = service.query(model);
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
    public String save(FormModel model) {
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
			result = json.buildJsonModelResult(null, true, CodeUtils.SUCCESS, "成功删除"+count+"条数据！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "删除失败，出现未知错误！");
		}
		
        return result.toString();
    }
	
	
	@RequestMapping("/validFieldname/{id}")
	public String validFieldname(@PathVariable Integer id,String fieldname) {
		JSONObject result = new JSONObject();
		try {
			result.element("valid", service.validFieldname(fieldname, id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			L.e(e.getMessage());
			result.element("valid", false);
		}
		
		return result.toString();
	}
	
}
