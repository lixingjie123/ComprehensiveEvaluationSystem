package cn.ces.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.DepartmentDao;
import cn.ces.entity.Department;
import cn.ces.entity.Power;
import cn.ces.entity.Users;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	public Map<String,Object> getPageDepart(int pageIndex, int pageSiz){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=departmentDao.selectDeptcount();
        List<Department> rows=departmentDao.selectPageList(pageIndex,pageSiz);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }

	public List<Department> selectAllDepartment() {
		return departmentDao.selectDeptAll();
	}
	
	public Map<String,Object> selectDepartmentBydeptname(int pageIndex, int pageSize,String dept_name){
	    Map<String,Object> result = new HashMap<String,Object>();
	    int total=departmentDao.getdeptnameCount(dept_name);//获取查询结果总数
	    List<Department> rows=departmentDao.selectDepartmentBydeptname(pageIndex, pageSize, dept_name);//获取查询结果
	    result.put("total",total);
	    result.put("rows",rows);
	    return result;
	}
	
	public Department selectDeptid(Integer dept_id){
        return departmentDao.selectDeptid(dept_id);
    }
}
