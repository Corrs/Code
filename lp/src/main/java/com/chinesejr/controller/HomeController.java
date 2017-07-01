package com.chinesejr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.model.sys.MenuModel;
import com.chinesejr.model.sys.UserModel;
import com.chinesejr.service.sys.MenuService;
import com.chinesejr.service.sys.UserService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;
import com.chinesejr.util.StringUtil;
import com.chinesejr.vo.util.TreeNodeVO;

import net.sf.json.JSONObject;

/**
 * @since 2017.06.02 19:51
 */
@RestController
@RequestMapping("/home")
public class HomeController {
	/*
	 * @RequestMapping public ModelAndView home() { ModelAndView result = new
	 * ModelAndView("index"); return result; }
	 */
	private static final JSONUtils<TreeNodeVO> treeJson = new JSONUtils<TreeNodeVO>();
	private static final JSONUtils<UserModel> userJson = new JSONUtils<UserModel>();
	private static final String DEFAULT_IMG = "static/img/defaultImgs/1.jpg";
	@Autowired
	private MenuService service;
	@Autowired
	private UserService userService;


	@RequestMapping
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("index");
		try {
			request.setAttribute("menus", queryMenus());
			request.setAttribute("userinfor", getCurrentUser(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}
		return result;
	}

	private String queryMenus() {
		JSONObject result = new JSONObject();
		MenuModel model = new MenuModel();
		model.setSort("code");
		model.setProp1("1");
		try {
			result = treeJson.buildJsonModelResult(service.queryTree(model), true, CodeUtils.SUCCESS, "查询成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = treeJson.buildJsonModelResult(null, false, CodeUtils.ERROR, "查询失败！");
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
	@RequestMapping("/queryCurrentUser")
	public String queryCurrentUser(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			result = userJson.buildJsonModelResult(getCurrentUser(request), true, CodeUtils.SUCCESS, "查询成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = userJson.buildJsonModelResult(null, false, CodeUtils.ERROR, "查询失败！");
		}
		return result.toString();
	}
	
	private UserModel getCurrentUser(HttpServletRequest request) throws Exception {
		UserModel model = new UserModel();
		UserModel userinfor = (UserModel) request.getSession().getAttribute("userinfor");
		userinfor = userService.getById(userinfor.getId());
		if(StringUtil.isEmpty(userinfor.getTruename())) {
			userinfor.setTruename(userinfor.getUsername());
		}
		if(StringUtil.isEmpty(userinfor.getImage())) {
			userinfor.setImage(DEFAULT_IMG);
		}
		model.setId(userinfor.getId());
		model.setUsername(userinfor.getUsername());
		model.setImage(userinfor.getImage());
		model.setTruename(userinfor.getTruename());
		
		return model;
	}

}
