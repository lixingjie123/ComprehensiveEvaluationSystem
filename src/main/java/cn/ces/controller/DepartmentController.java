package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.ces.service.DepartmentService;
//用于系部管理
@Controller
@SessionAttributes({"Department"})
public class DepartmentController {
	

    private final DepartmentService departmentService;
    
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
    	this.departmentService=departmentService;
    }
    @GetMapping(value = "/selectDepartment")
    @ResponseBody
    public Map<String,Object> selectDepartment(int offset, int limit,String dept_name){
    		try {
    			dept_name=URLDecoder.decode(dept_name,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	System.out.println(dept_name);
    	String p="%%";
    	if(!dept_name.equals("null")){
    	 p="%"+dept_name+"%";
    	 } 
    	System.out.println(p);
        return  departmentService.selectDepartmentall(offset, limit, p);
    }
    
}
