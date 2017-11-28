package cn.ces.controller;

import cn.ces.entity.Teachers;
import cn.ces.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
