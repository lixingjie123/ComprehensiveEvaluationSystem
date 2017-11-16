package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.ces.entity.Department;
import cn.ces.service.DepartmentService;

//用于系部管理
@Controller
@SessionAttributes({ "Department" })
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
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
		String p = "%%";
		if (!dept_name.equals("null")) {
			p = "%" + dept_name + "%";
		}
		return departmentService.selectDepartmentall(offset, limit, p);
	}

	// 更具系部id查询
	@GetMapping(value = "/selectDepartmentByDeptid")
	@ResponseBody
	public Department selectDepartmentbyid(Integer dept_id) {
		return departmentService.selectDepartmentbyid(dept_id);
	}

	// 增加系部
	@PostMapping(value = "/addDepartment", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String addDepartment(Department aDepartment) {
		return departmentService.AddDepartment(aDepartment);
	}

	// 修改状态
	@GetMapping(value = "/updateDepartmentStatus", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateDepartmentStatus(Integer dept_id) {
		return departmentService.updateDepartmentOfFettle(dept_id);
	}

	//修改系部
	@PostMapping(value = "/updateDepartment", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateDepartment(Department aDepartment) {
		return departmentService.UpdateDepartment(aDepartment);
	}

}
