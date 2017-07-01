package com.chinesejr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chinesejr.model.sys.UserModel;



public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 项目路径名
		String contextPath = request.getContextPath();
		// 全路径
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + contextPath;
		// TODO Auto-generated method stub
		boolean flag = true;
		HttpSession session = request.getSession();
//		session.setAttribute("userinfor", new UserModel());
		UserModel userInfo = (UserModel) session.getAttribute("userinfor");
		// session是否过期 无用户信息即过期
		session.setAttribute("sessiontimeout", String.valueOf(userInfo==null));
		// 要访问的路径
		session.setAttribute("uri", request.getRequestURI());
		if(request.getRequestURI().indexOf("/api/") == -1 && userInfo == null) {
			//response.sendError(HttpServletResponse.SC_FORBIDDEN, "登录超时或无效！");
			response.sendRedirect(basePath + "/login");
			flag = false;
		} 
		
		return flag;
	}
}
