package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mybatis.enhance.store.manager.common.BaseMysqlCRUDManager;

import cn.ces.entity.Department;
import cn.ces.entity.Power;
import cn.ces.service.DepartmentService;
import cn.ces.service.PowerService;
import net.sf.json.JSONArray;

@Controller
@SessionAttributes({"Department"})
public class DepartmentController {
	
	private final BaseMysqlCRUDManager baseMysqlCRUDManager;
    private final DepartmentService departmentService;
    
    @Autowired
    public DepartmentController(BaseMysqlCRUDManager baseMysqlCRUDManager,DepartmentService departmentService) {
    	this.baseMysqlCRUDManager=baseMysqlCRUDManager;
    	this.departmentService=departmentService;
    }
    
    @GetMapping(value = "/selectPageDepart")
    @ResponseBody
    public Map<String,Object> selectPageDepart(int offset, int limit){
    	System.out.println(offset);
    	System.out.println(limit);
        return  departmentService.getPageDepart(offset,limit);
    }
    
}
