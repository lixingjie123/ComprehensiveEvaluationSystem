package cn.ces.dao;

import cn.ces.entity.Department;
import cn.ces.entity.Power;
import cn.ces.entity.Users;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 15:18
 */
public interface DepartmentDao {

    @Select("select * from department where dept_id = #{deptId}")
    Department selectDeptid(@Param("deptId")int deptId);

    @Select("select * from department")
    List<Department> selectDeptAll();
    
    @Select("select count(*) from department")
    int selectDeptcount();
    
    @Select("select * from department limit #{pageIndex},#{pageSize}")
    List<Department> selectPageList(@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
}

