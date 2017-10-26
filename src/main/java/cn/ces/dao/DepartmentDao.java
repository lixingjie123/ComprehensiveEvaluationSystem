package cn.ces.dao;

import cn.ces.entity.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 15:18
 */
public interface DepartmentDao {

    @Select("select * from department")
    List<Department> selectDeptAll();
}
