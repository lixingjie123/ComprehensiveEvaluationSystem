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


@Controller
@SessionAttributes({"userinfo"})
public class LoginController {

	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping(value = "/login")
    @ResponseBody 
    public Map<String, Object> login(Users users, ModelMap map){
		Map<String, Object> result = loginService.loginService(users.getUid(), users.getPwd());
		map.put("userinfo",result.get("userinfo"));
		return result;
	}
	
	
	@PostMapping(value="/updatepwd",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updatepwd(Integer uid,String npwd,String opwd){
		return loginService.AquirieValue(uid, npwd, opwd);
		
	}
	
	
}
