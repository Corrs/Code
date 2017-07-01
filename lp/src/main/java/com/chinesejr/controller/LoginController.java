package com.chinesejr.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chinesejr.mail.EmailUtils;
import com.chinesejr.mail.ParseHtml;
import com.chinesejr.mail.vo.EmailValidVO;
import com.chinesejr.model.sys.UserModel;
import com.chinesejr.service.sys.UserService;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.JSONUtils;
import com.chinesejr.util.L;
import com.chinesejr.util.RandomUtils;
import com.chinesejr.webservices.client.MobileCodeWSClient;
import com.chinesejr.webservices.client.ValidateCodeWebServiceClient;
import com.chinesejr.webservices.client.MobileCodeWS.GetMobileCodeInfoResponse;
import com.chinesejr.webservices.client.ValidateCodeWebService.EnValidateByteResponse;

import net.sf.json.JSONObject;

/**
 * @since 2017.06.02 19:51
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static final JSONUtils<UserModel> json = new JSONUtils<UserModel>();
	
	@Autowired
	private MobileCodeWSClient mobileCodeWSClient;

	@Autowired
	private ValidateCodeWebServiceClient validateCodeWebServiceClient;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping
    public ModelAndView loginPage(HttpServletRequest request) {
        ModelAndView result = new ModelAndView("login");
        return result;
    }
	
	/**
	 * 访问接口 验证手机号是否存在
	 * @param mobile
	 * @return boolean 
	 */
	@RequestMapping("/validMobileCode")
	public String validMobileCode(String mobile) {
		JSONObject result = new JSONObject();
		try {
			GetMobileCodeInfoResponse mobileCode = mobileCodeWSClient.getMobileCode(mobile);
			result.element("valid", mobileCode.getGetMobileCodeInfoResult().contains(mobile));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			L.e(e.getMessage());
			result.element("valid", false);
		}
		
		return result.toString();
	}

	/**
	 * 获取随机验证图片 base64
	 * @param request
	 * @return string
	 */
	@RequestMapping("/validCode")
	public String validCode(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String randomCode = RandomUtils.getRandomCode();
		result.element("code", randomCode);
		try {
			result.element("validCode", getValidCode(randomCode));
			result.element("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.element("success", false);
		}
		return result.toString();
	}
	
	
	@RequestMapping("/register")
	public String register(UserModel model, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			UserModel registerUser = userService.registerUser(model);
			result = json.buildJsonModelResult(registerUser, true, CodeUtils.SUCCESS, "用户注册成功！");
			request.getSession().setAttribute("userinfor", registerUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "用户注册失败，请重新注册！");
		}
		
		return result.toString();
	}
	
	@RequestMapping("/checkUser")
	public String login(UserModel model, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		
		try {
			result.element("valid", !userService.checkUser(model));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result.element("valid", false);
		}
		
		return result.toString();
	}
	
	@RequestMapping("/loginUser")
	public String loginUser(UserModel model, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		
		try {
			UserModel loginUser = userService.selectLoginUser(model);
			if (loginUser != null) {
				request.getSession().setAttribute("userinfor", loginUser);
				result = json.buildJsonModelResult(loginUser, true, CodeUtils.SUCCESS, "登录成功！");
			} else {
				result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "用户名或密码错误！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result = json.buildJsonModelResult(null, false, CodeUtils.ERROR, "用户名或密码错误！");
		}
		
		return result.toString();
	}
	
	@RequestMapping("/logoutUser")
	public ModelAndView logoutUser(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("redirect:/login");
        return result;
	}
	
	@RequestMapping("/checkMobileExsit")
	public boolean checkMobileExsit(UserModel model) {
		boolean flag = true;
		try {
			flag = userService.checkUser(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
		}
		
		return flag;
	}
	
	@RequestMapping("/validEmail")
	public String validEmail(UserModel model) {
		JSONObject result = new JSONObject();
		try {
			result.element("isExsit", userService.checkUser(model));
			result.element("sendMail", true);
			if (!userService.checkUser(model)) {
				String code = RandomUtils.getRandomCode();
				EmailValidVO validVO = new EmailValidVO(model.getEmail(), code);
				ParseHtml p = new ParseHtml();
				boolean flag = EmailUtils.sendHtmlMail("匠人网络用户注册验证", p.parse(validVO), model.getEmail());
				if (flag) {
					Calendar nowTime = Calendar.getInstance();
					nowTime.add(Calendar.MINUTE, 5);
					result.element("emailCode", code);
					result.element("endTime", nowTime.getTime().getTime());
				}
				result.element("sendMail", flag);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			L.e(e.getMessage());
			result.element("sendMail", false);
		}
		
		return result.toString();
	}
	
	
	/**
	 * 调用接口 生成随机数图片
	 * @param randomCode 4位随机数
	 * @return
	 * @throws Exception
	 */
	private String getValidCode(String randomCode) throws Exception {
		EnValidateByteResponse response = validateCodeWebServiceClient.getImageByteResponse(randomCode);
		return Base64Utils.encodeToString(response.getEnValidateByteResult());
	}

}
