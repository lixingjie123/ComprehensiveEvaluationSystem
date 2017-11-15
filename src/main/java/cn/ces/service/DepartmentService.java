package cn.ces.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.DepartmentDao;
import cn.ces.entity.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	//分页查询
	public Map<String,Object> selectDepartmentall(int pageIndex, int pageSize,String dept_name){
        Map<String,Object> result = new HashMap<String,Object>();
        int total = departmentDao.selectDepartmentcount(dept_name);
        List<Department> rows = departmentDao.selectDepartment(pageIndex, pageSize,dept_name);
        result.put("total",total);
        result.put("rows",rows);
        return result;
	
	}
	
	/*public List<Department> selectDepartment() {
         return departmentDao.selectDepartmentall();
	}*/
	
}
