package cn.ces.service;

import cn.ces.dao.DepartmentDao;
import cn.ces.dao.TestDao;
import cn.ces.entity.Department;
import cn.ces.entity.Test;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestService {
    private final TestDao testDao;
    private final DepartmentDao departmentDao;


    @Autowired
    public TestService(TestDao testDao, DepartmentDao departmentDao) {
        this.testDao = testDao;
        this.departmentDao = departmentDao;
    }

    public Test selectAll(){
        return testDao.selectAll();
    }

    public Department selectDeptAll(Integer dept_id){
        return departmentDao.selectDepartmentByid(dept_id);
    }
}
