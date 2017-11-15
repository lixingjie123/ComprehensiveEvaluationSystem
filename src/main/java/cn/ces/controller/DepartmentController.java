package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.enhance.store.manager.common.BaseMysqlCRUDManager;

import cn.ces.entity.Department;
import cn.ces.entity.Power;
import cn.ces.service.DepartmentService;

@Controller
public class DepartmentController {

	private final BaseMysqlCRUDManager baseMysqlCRUDManager;
	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(BaseMysqlCRUDManager baseMysqlCRUDManager, DepartmentService departmentService) {
		super();
		this.baseMysqlCRUDManager = baseMysqlCRUDManager;
		this.departmentService = departmentService;
	}

	@GetMapping(value = "/selectDepartment")
	@ResponseBody
	public Map<String, Object> selectDepartment(int offset, int limit, String dept_name) {
		try {
			dept_name = URLDecoder.decode(dept_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dept_name);
		String d = "%%";
		if (!dept_name.equals("null")) {
			d = "%" + dept_name + "%";
		}
		System.out.println(d);
		return departmentService.selectDepartmentall(offset, limit, d);
	}

}
