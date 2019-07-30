package cn.ces.controller;

import java.util.Map;

import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ces.service.LoginService;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;




@Controller
@SessionAttributes({"userinfo"})
public class LoginController {

	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	// 登陆
	@PostMapping(value = "/login")
    @ResponseBody 
    public Map<String, Object> login(Users users, ModelMap map){
		Map<String, Object> result = loginService.loginService(users.getUid(), users.getPwd());
		if(result.get("userinfo")!=null){
			map.put("userinfo",result.get("userinfo"));
		}
		return result;
	}
	
	// 修改密码
	@PostMapping(value="/updatepwd",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updatepwd(Integer uid,String npwd,String opwd){
		return loginService.AquirieValue(uid, npwd, opwd);
	}

	//退出登录
	@GetMapping(value = "/quit")
	public ModelAndView quit(SessionStatus status){
		status.setComplete();
		return new ModelAndView("Public/login.jsp");
	}


}
