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
	public Map<String,Object> getPageDepart(int pageIndex, int pageSiz){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=departmentDao.selectDepartmentcount();
        List<Department> rows=departmentDao.selectPageList(pageIndex,pageSiz);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    //查询所有的系部
	public List<Department> selectAllDepartment() {
		return departmentDao.selectDepartmentAll();
	}
	
	//按条件分页查询
    public Map<String,Object> selectDepartmentByDidAndDname(int pageIndex, int pageSiz,String dept_name){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=departmentDao.getDnameCount(dept_name);//获取查询结果总数
        List<Department> rows=departmentDao.selectDepartmentByDidAndDname(pageIndex,pageSiz,dept_name);//获取查询结果
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
}
