package com.chinesejr.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.model.sys.UserModel;
import com.chinesejr.service.sys.UserService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;

import net.sf.json.JSONObject;

/**
 * 用户相关控制器类
 * 
 * @author BobyCo
 * @since 2017-06-19 12:57
 * 
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
	private static final String FOLDER = "modules/sys/user/";
	private static final JSONUtils<UserModel> json = new JSONUtils<UserModel>();

	@Autowired
	private UserService service;

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

	@RequestMapping("/query")
	public String query(UserModel model) {
		JSONObject result = new JSONObject();

		try {
			List<UserModel> modelList = service.query(model);
			result = json.buildJsonPageResult(modelList, model, true, CodeUtils.SUCCESS, "查询成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonPageResult(null, model, false, CodeUtils.ERROR, "查询失败！");
		}

		return result.toString();
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView view(@PathVariable Integer id, HttpServletRequest request) {
		ModelAndView result = new ModelAndView(FOLDER + "edit");
		try {
			UserModel model = service.getById(id);
			List<String> defaultImgs = service.getDefaultImg();
			List<String> customImgs = service.getCustomImg(model.getUsername());
			request.setAttribute("model", model);
			request.setAttribute("defaultImgs", defaultImgs);
			request.setAttribute("customImgs", customImgs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(UserModel model) {
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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request, UserModel model) {
		
		JSONObject result = new JSONObject();
		try {
			UserModel userModel = service.uploadFile(request, model);
			result = json.buildJsonModelResult(userModel, true, String.valueOf(HttpStatus.OK.value()), "头像上传成功！");
		} catch (Exception e) {
			result = json.buildJsonModelResult(null, false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "头像上传失败！");
		} 
		
		return result.toString();
	}
}
