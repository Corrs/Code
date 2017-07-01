package com.chinesejr.controller.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.model.content.ContentModel;
import com.chinesejr.model.sys.CatalogModel;
import com.chinesejr.service.content.ContentService;
import com.chinesejr.service.sys.CatalogService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/content")
public class ContentController {
	private static final String FOLDER = "modules/content/";
	private static final JSONUtils<ContentModel> json = new JSONUtils<ContentModel>();
	
	@Autowired
	private ContentService service;
	
	@Autowired
	private CatalogService catalogService;
	
	@RequestMapping
	public ModelAndView listPage() {
		ModelAndView result = new ModelAndView(FOLDER + "main");
        return result;
    }
	
	@RequestMapping("/append")
	public ModelAndView appendPage(HttpServletRequest request) {
		ModelAndView result = new ModelAndView(FOLDER + "append");
		try {
			List<CatalogModel> list = catalogService.query(new CatalogModel());
			request.setAttribute("catalogList", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}
        return result;
    }
	
	@RequestMapping(value = "/edit/{id}")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request) {
        ModelAndView result = new ModelAndView(FOLDER + "edit");
		try {
			ContentModel model = service.getById(id);
			List<CatalogModel> list = catalogService.query(new CatalogModel());
			request.setAttribute("catalogList", list);
			request.setAttribute("model", model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}
		
        return result;
    }
	
	@RequestMapping("/query")
	public String query(ContentModel model) {
		return queryData(model);
	}
	
	@RequestMapping("/api/query")
	public String queryApi(ContentModel model) {
		return queryData(model);
	}
	
	private String queryData(ContentModel model) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		
		try {
			List<ContentModel> modelList = service.query(model);
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
    public String save(ContentModel model) {
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
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request, ContentModel model) {
		
		JSONObject result = new JSONObject();
		try {
			ContentModel contentModel = service.uploadFile(request, model);
			result = json.buildJsonModelResult(contentModel, true, String.valueOf(HttpStatus.OK.value()), "头像上传成功！");
		} catch (Exception e) {
			result = json.buildJsonModelResult(null, false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "头像上传失败！");
			L.e(e.getMessage());
		} 
		
		return result.toString();
	}
}
