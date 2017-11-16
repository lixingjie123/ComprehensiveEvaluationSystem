package cn.ces.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.DepartmentDao;
import cn.ces.entity.Class;
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
	
	//根据系部id查询
	public Department selectDepartmentbyid(Integer dept_id) {
		return departmentDao.selectDepartmentbyid(dept_id);
	}
	
	//新增系部
    public String AddDepartment(Department aDepartment){
        String msg = "添加失败";
        try{ 
        	int p = departmentDao.insertDepartment(aDepartment);
            if (p!=0){
                msg = "添加成功！";
            }
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }
    
    //修改班级状态
    public String updateDepartmentOfFettle(Integer dept_id){
        String msg = "启用成功";
        Integer fettle = 1;
        try{
        	Department  aDepartment = departmentDao.selectDepartmentbyid(dept_id);
            fettle = aDepartment.getFettle();
            if (fettle!=0){
                fettle = 0;
                msg = "禁用成功";
            }else {
                fettle = 1;
                msg = "启用成功";
            }
            departmentDao.updateDepartmentOfFettle(fettle,dept_id);
        }catch (Exception e){
            msg=e.getMessage();
        }
        return msg;
    }
	
    //修改系部
    public String UpdateDepartment(Department aDepartment){
        String msg =  "修改失败!";
        try{ 
        	int p = departmentDao.updateDepartment(aDepartment);
            if (p!=0){
                msg = "修改成功!";
            }
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }
	
}
