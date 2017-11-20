package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import cn.ces.entity.GiveCourse;
import cn.ces.entity.Teachers;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ces.service.GiveCourseService;
import cn.ces.service.LoginService;
import cn.ces.service.TeacherService;

//教师
@Controller
public class TeacherController {

	private final TeacherService ts;
	
	@Autowired
	public TeacherController( TeacherService ts) {
		this.ts = ts;
	}
	
	@GetMapping(value = "/selectteacher")
    @ResponseBody 
    public List<Teachers> selectteacher(){
		
		List<Teachers> tlist=ts.selectteacher();
		return  tlist;
	}
	
}
