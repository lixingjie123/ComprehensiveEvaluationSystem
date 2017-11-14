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
	//分页查询
	public Map<String,Object> selectDepartmentall(int pageIndex, int pageSize,String dept_name){
        Map<String,Object> result = new HashMap<String,Object>();
        int total = departmentDao.selectDepartmentcount(dept_name);
        List<Department> rows = departmentDao.selectDepartment(pageSize,pageIndex,dept_name);
        for(int i=0;i<rows.size();i++){
        	Department dept = departmentDao.selectDepartmentByid(rows.get(i).getDept_id());
        	if(dept!=null){
        	rows.get(i).setDept_name(dept.getDept_name());
        	}
        }
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
	/*public List<Department> selectDepartment() {
		return departmentDao.selectDepartmentAll();
	}*/
}
