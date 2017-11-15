package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gitee.sunchenbin.mybatis.actable.manager.common.BaseMysqlCRUDManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


import cn.ces.entity.Department;
import cn.ces.entity.Power;
import cn.ces.service.DepartmentService;
import cn.ces.service.PowerService;
import cn.ces.tool.TreeNode;
import cn.ces.tool.TreeNodeTool;
import net.sf.json.JSONArray;
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
